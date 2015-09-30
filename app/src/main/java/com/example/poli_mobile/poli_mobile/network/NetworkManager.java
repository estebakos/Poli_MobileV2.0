package com.example.poli_mobile.poli_mobile.network;

import com.example.poli_mobile.poli_mobile.listeners.IPoliWebService;
import com.example.poli_mobile.poli_mobile.ui.NetworkUiListener;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile.utilidades.PoliPreferences;
import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;
import com.example.poli_mobile.poli_mobile_ws.PoliWebService;

import java.util.ArrayList;
import java.util.List;

public class NetworkManager implements IPoliWebService {

    private PoliPreferences _axPrefs;
    private static NetworkManager NManagerInstance;
    private PoliWebService webService;
    private List<NetworkUiListener> nUiListener;

    public NetworkManager() {
        _axPrefs = new PoliPreferences(AppContext.getContext());
        webService = new PoliWebService(AppContext.getContext(), this);
        nUiListener = new ArrayList<>();
    }

    public static synchronized NetworkManager getNManagerInstance() {
        if (null == NManagerInstance) {
            synchronized (NetworkManager.class) {
                if (null == NManagerInstance) {
                    NManagerInstance = new NetworkManager();
                }
            }
        }
        return NManagerInstance;
    }

    public void addNetworkUiListener(NetworkUiListener nUiListener)
    {
        this.nUiListener.add(nUiListener);
    }


    public void Authenticate(String domain, String user, String pass) {
       // webService.Authenticate(domain, user, pass);
    }

    public void ObtenerCitas() {
        webService.ObtenerCitaMedica(_axPrefs.getToken());
    }


    @Override
    public void onInternetFail() {
         //  showTooltip("No se detecta conexión a ninguna red wi-fi o ethernet, mientras encontramos alguna, estarás en modo offline.");
    }

    @Override
    public void onHttpError(Object resp, String method) {

    }


    @Override
    public void onUnexpectedError() {

    }

   /* @Override
    public void onAuthenticate(AuthenticateResponse resp) {
        if(resp != null && resp.getToken() != null) {
            _axPrefs.storageToken(resp.getToken());
            for(NetworkUiListener networkUiListener : nUiListener) {
                networkUiListener.onAuthenticate();
            }
        }
    }*/

    @Override
    public void onObtenerCitas(List<CitaMedica> resp) {
        if(resp!=null)
        {
            for(NetworkUiListener networkUiListener : nUiListener) {
                networkUiListener.onObtenerCitas(resp);
            }
        }
    }


    @Override
    public void onGetDevicesResponseFail(int Error) {
        for(NetworkUiListener networkUiListener : nUiListener) {
            networkUiListener.onGetDeviceListFail();
        }
    }

    @Override
    public void onGetChannelsResponseFail(int Error) {

    }


    @Override
    public void onUnauthorized() {

    }

    @Override
    public void onAuthenticationFail() {
        for(NetworkUiListener networkUiListener : nUiListener) {
            networkUiListener.onAuthenticationFail();
        }
    }

}
