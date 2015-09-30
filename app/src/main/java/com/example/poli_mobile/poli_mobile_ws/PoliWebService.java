package com.example.poli_mobile.poli_mobile_ws;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.poli_mobile.poli_mobile.listeners.IPoliWebService;
import com.example.poli_mobile.poli_mobile.network.SOAPHandler;
import com.example.poli_mobile.poli_mobile.network.SOAPListener;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile.utilidades.PoliPreferences;
import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;

import org.apache.http.HttpStatus;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PoliWebService {

    private final Context _context;
    private IPoliWebService _IWebService;
    private final PoliPreferences _axPrefs;


    public PoliWebService(Context context, IPoliWebService listener) {
        _IWebService = listener;
        _context = context;
        _axPrefs = new PoliPreferences(_context);
    }

    private boolean isNetworkAvailable() {
        if (_context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) _context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } else {
            return false;
        }
    }

    private String getRestUrl() {
        return ApplicationSession.getBaseURL();
    }

    private String getNameSpace() {
        return ApplicationSession.getNAMESPACE();
    }


    public void ObtenerCitaMedica(String token) {
        try {
            if (token == null) {
                //TODO En este caso deberia de solicitar nuevamente el Registro del aplicativo
                _IWebService.onUnauthorized();
                return;
            }

            if (!isNetworkAvailable()) {
                _IWebService.onInternetFail();
            } else {
                if (getRestUrl() != "") {
                    SOAPHandler httpGet = new SOAPHandler(getRestUrl(),
                            "WS_Estudiante?wsdl",getNameSpace(),"Traer_Cita_Medica", new SOAPListener() {

                        @Override
                        public void onRequestFinish(
                                Object SOAPResponse) {
                            Vector<SoapObject> result = null;
                            if (SOAPResponse instanceof SoapObject) {
                                result = new Vector();
                                result.add((SoapObject) SOAPResponse);
                            } else if (SOAPResponse instanceof Vector) {
                                result = (Vector<SoapObject>) SOAPResponse;
                            }


                            List<CitaMedica> lCitas = new ArrayList<CitaMedica>();
                            for (SoapObject soap : result) {

                                CitaMedica wse = new CitaMedica();
                                wse.setApe_medico(soap.getPropertyAsString(0).toString());
                                wse.setCod_cita_medica(soap.getPropertyAsString(1)
                                        .toString());
                                wse.setDescripcion(soap.getPropertyAsString(2).toString());
                                wse.setDia(soap.getPropertyAsString(3).toString());
                                wse.setEstado(soap.getPropertyAsString(4).toString());
                                wse.setHora(soap.getPropertyAsString(5).toString());
                                wse.setNom_medico(soap.getPropertyAsString(6).toString());
                                wse.setUbi_consultorio(soap.getPropertyAsString(7)
                                        .toString());

                                lCitas.add(wse);

                            }
                            _IWebService.onObtenerCitas(lCitas);
                        }

                    });

                    httpGet.addSOAPParam("Session_Id", token);
                    httpGet.ExecuteSOAP();
                } else {
                    _IWebService.onUnexpectedError();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public String intToIp(int i) {

        return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "."
                + ((i >> 8) & 0xFF) + "." + (i & 0xFF);
    }
}
