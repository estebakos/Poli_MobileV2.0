package com.example.poli_mobile.poli_mobile.ui;

import java.util.List;

import br.liveo.utils.Constant;

import com.example.poli_mobile.R;
import com.example.poli_mobile.R.id;
import com.example.poli_mobile.R.layout;
import com.example.poli_mobile.poli_mobile.listeners.HorarioListener;
import com.example.poli_mobile.poli_mobile.listeners.ListadorMateriaListener;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile_adaptadores.HorarioAdapter;
import com.example.poli_mobile.poli_mobile_adaptadores.ListaMateriaAdapter;
import com.example.poli_mobile.poli_mobile_entidades.HorarioSemestreActual;
import com.example.poli_mobile.poli_mobile_entidades.ListaMateria;
import com.example.poli_mobile.poli_mobile_ws.WsHorario;
import com.example.poli_mobile.poli_mobile_ws.WsListadoClases;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListadoClasesFragment extends Fragment implements
		ListadorMateriaListener, OnRefreshListener {

	private ListView lvLunes;
	private SwipeRefreshLayout srLunes;
	private ImageView ivScheduleBackground;
	public TextView tvScheduleBackground;

	public ListadoClasesFragment newInstance(String text) {
		ListadoClasesFragment mFragment = new ListadoClasesFragment();
		Bundle mBundle = new Bundle();
		mBundle.putString(Constant.TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = new View(getActivity());
		v = inflater.inflate(layout.list_materias, container, false);
		if (ApplicationSession.rlayout != null) {
			ApplicationSession.rlayout.setBackgroundColor(Color
					.parseColor("#f44336"));
		}
		srLunes = (SwipeRefreshLayout) v.findViewById(id.swipe_container);
		lvLunes = (ListView) v.findViewById(id.lvMaterias);
		srLunes.setOnRefreshListener(this);
		tvScheduleBackground = (TextView) v
				.findViewById(id.tvScheduleBackground);
		ivScheduleBackground = (ImageView) v
				.findViewById(id.ivScheduleBackground);
		callService();

		return v;
	}

	public void callService() {
		WsListadoClases wshora = new WsListadoClases(ApplicationSession.SessionTeacher);
		wshora.setLoginListener(this);

		wshora.execute();
	}

	@Override
	public void onRefresh() {
		callService();
	}

	@Override
	public void LMateriaLista(List<ListaMateria> lMateria) {

		if (lMateria.size() == 0) {
			ivScheduleBackground.setVisibility(View.VISIBLE);
			tvScheduleBackground.setVisibility(View.VISIBLE);
		} else {
			lvLunes.setAdapter(new ListaMateriaAdapter(getActivity(),
					layout.materia_item, lMateria));
		}

	}

	@Override
	public void LMateriaFallida() {
		ivScheduleBackground.setVisibility(View.VISIBLE);
		tvScheduleBackground.setVisibility(View.VISIBLE);
	}

}
