package com.example.poli_mobile.poli_mobile.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile.listeners.AcercaDeListener;
import com.example.poli_mobile.poli_mobile.listeners.ComprobantelListener;
import com.example.poli_mobile.poli_mobile.network.NetworkManager;
import com.example.poli_mobile.poli_mobile_entidades.AcercaDe;
import com.example.poli_mobile.poli_mobile_entidades.ComprobantePago;

import java.util.List;

/**
 * Created by TEBAN on 8/04/2016.
 */
public class ComprobanteFragment extends Fragment implements ComprobantelListener {

    private TextView tvCedula, tvCargo, tvNumeroCuenta, tvBasico, tvValorDevengado, tvValorDeduccion, tvValorPagado, tvBanco, tvAnio, tvQuincena, tvFechaPago,tvConcepto, tvHorasDias;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvAnio = (TextView) view.findViewById(R.id.tvAnio);
        tvBanco = (TextView) view.findViewById(R.id.tvBanco);
        tvBasico = (TextView) view.findViewById(R.id.tvSalarioBasico);
        tvCargo = (TextView) view.findViewById(R.id.tvCargo);
        tvCedula = (TextView) view.findViewById(R.id.tvCedulaDocente);
        tvConcepto = (TextView) view.findViewById(R.id.tvConcepto);
        tvFechaPago = (TextView) view.findViewById(R.id.tvFecha);
        tvHorasDias = (TextView) view.findViewById(R.id.tvHorasDias);
        tvNumeroCuenta = (TextView) view.findViewById(R.id.tvNumeroCuenta);
        tvQuincena = (TextView) view.findViewById(R.id.tvQuincena);
        tvValorDeduccion = (TextView) view.findViewById(R.id.tvValorDeduccion);
        tvValorDevengado = (TextView) view.findViewById(R.id.tvValorDevengado);
        tvValorPagado = (TextView) view.findViewById(R.id.tvValorPagado);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recibo, container, false);    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkManager.getNManagerInstance().setComprobantelListener(this);
        NetworkManager.getNManagerInstance().obtenerComprobante();
    }

    @Override
    public void Comprobante(List<ComprobantePago> comprobantePagos) {
        if(comprobantePagos != null && comprobantePagos.size() >0)
        {
            ComprobantePago pago = comprobantePagos.get(0);
            tvValorDeduccion.setText(pago.getValorDeduccion());
            tvValorPagado.setText(pago.getValorPagado());
            tvQuincena.setText(pago.getQuincena());
            tvValorDevengado.setText(pago.getValorDevengado());
            tvBanco.setText(pago.getBanco());
            tvAnio.setText(pago.getAnio());
            tvBasico.setText(pago.getBasicoDia());
            tvCargo.setText(pago.getCargoDocente());
            tvCedula.setText(pago.getCedulaDocente());
            tvConcepto.setText(pago.getConcepto());
            tvFechaPago.setText(pago.getFechaPago());
            tvNumeroCuenta.setText(pago.getNumeroCuenta());
            tvHorasDias.setText(pago.getHoras_dias());
        }
    }

    @Override
    public void ComprobanteFail() {

    }
}
