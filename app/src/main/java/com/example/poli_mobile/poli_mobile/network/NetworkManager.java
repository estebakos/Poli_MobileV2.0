package com.example.poli_mobile.poli_mobile.network;

import com.example.poli_mobile.poli_mobile.listeners.CalendarioListener;
import com.example.poli_mobile.poli_mobile.listeners.CitaListener;
import com.example.poli_mobile.poli_mobile.listeners.FacultadesListener;
import com.example.poli_mobile.poli_mobile.listeners.HorarioListener;
import com.example.poli_mobile.poli_mobile.listeners.IPoliWebService;
import com.example.poli_mobile.poli_mobile.listeners.ListadorMateriaListener;
import com.example.poli_mobile.poli_mobile.listeners.NotasListener;
import com.example.poli_mobile.poli_mobile.listeners.ParcialListener;
import com.example.poli_mobile.poli_mobile.ui.NetworkUiListener;
import com.example.poli_mobile.poli_mobile.utilidades.AppContext;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile.utilidades.PoliPreferences;
import com.example.poli_mobile.poli_mobile_entidades.CalendarioAcademico;
import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;
import com.example.poli_mobile.poli_mobile_entidades.HorarioSemestreActual;
import com.example.poli_mobile.poli_mobile_entidades.ListaMateria;
import com.example.poli_mobile.poli_mobile_entidades.NotaMateria;
import com.example.poli_mobile.poli_mobile_entidades.ProgramacionParcial;
import com.example.poli_mobile.poli_mobile_ws.PoliWebService;

import java.util.ArrayList;
import java.util.List;

public class NetworkManager implements IPoliWebService {

    private PoliPreferences _axPrefs;
    private static NetworkManager NManagerInstance;
    private PoliWebService webService;
    private List<NetworkUiListener> nUiListener;

    private CitaListener citaListener;
    private FacultadesListener facultadesListener;
    private CalendarioListener calendarioListener;
    private HorarioListener horarioListener;
    private NotasListener notasListener;
    private ListadorMateriaListener listadorMateriaListener;
    private ParcialListener parcialListener;

    public ParcialListener getParcialListener() {
        return parcialListener;
    }

    public void setParcialListener(ParcialListener parcialListener) {
        this.parcialListener = parcialListener;
    }

    public ListadorMateriaListener getListadorMateriaListener() {
        return listadorMateriaListener;
    }

    public void setListadorMateriaListener(ListadorMateriaListener listadorMateriaListener) {
        this.listadorMateriaListener = listadorMateriaListener;
    }

    public NotasListener getNotasListener() {
        return notasListener;
    }

    public void setNotasListener(NotasListener notasListener) {
        this.notasListener = notasListener;
    }

    public HorarioListener getHorarioListener() {
        return horarioListener;
    }

    public void setHorarioListener(HorarioListener horarioListener) {
        this.horarioListener = horarioListener;
    }

    public CalendarioListener getCalendarioListener() {
        return calendarioListener;
    }

    public void setCalendarioListener(CalendarioListener calendarioListener) {
        this.calendarioListener = calendarioListener;
    }

    public FacultadesListener getFacultadesListener() {
        return facultadesListener;
    }

    public void setFacultadesListener(FacultadesListener facultadesListener) {
        this.facultadesListener = facultadesListener;
    }

    public CitaListener getCitaListener() {
        return citaListener;
    }

    public void setCitaListener(CitaListener citaListener) {
        this.citaListener = citaListener;
    }

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


    public void Authenticate(String user, String pass) {
       webService.Login(user, pass);
    }

    public void ObtenerCitas() {
        webService.ObtenerCitaMedica(ApplicationSession.getToken());
    }

    public void obtenerParciales() {
        webService.obtenerParciales(ApplicationSession.getToken());
    }

    public void getFacultades() {
        webService.getFacultades(ApplicationSession.getToken());
    }

    public void getCalendario() {
        webService.getCalendario(ApplicationSession.getToken());
    }

    public void getHorario(String dia) {
        webService.getHorario(ApplicationSession.getToken(), dia);
    }

    public void getNotas() {
        webService.getNotas(ApplicationSession.getToken());
    }

    public void getListadoClase() {
        webService.getListadoClase(ApplicationSession.getToken());
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
            if(citaListener != null)
            {
                citaListener.CitaLista(resp);
            }
        }
    }

    @Override
    public void onAuthenticate(String token) {
        ApplicationSession.setToken(token);
        for(NetworkUiListener networkUiListener : nUiListener) {
            networkUiListener.onAuthenticate();
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

    @Override
    public void onFacultades(List<Facultad> lFacultades) {
        if(facultadesListener != null)
        {
            facultadesListener.onFacultades(lFacultades);
        }
    }

    @Override
    public void onCalendario(List<CalendarioAcademico> lCalendario) {
        if(calendarioListener != null)
        {
            calendarioListener.onCalendario(lCalendario);
        }
    }

    @Override
    public void HorarioListo(List<HorarioSemestreActual> webServiceEntity) {
        if(horarioListener != null)
        {
            horarioListener.HorarioListo(webServiceEntity);
        }
    }

    @Override
    public void NotasLitas(List<NotaMateria> webServiceEntity) {
        if(notasListener != null)
        {
            notasListener.LNotas(webServiceEntity);
        }
    }

    @Override
    public void ListaMateria(List<ListaMateria> webServiceEntity) {
        if(listadorMateriaListener != null)
        {
            listadorMateriaListener.LMateriaLista(webServiceEntity);
        }
    }

    @Override
    public void onParciales(List<ProgramacionParcial> lParcial) {
        if(parcialListener != null)
        {
            parcialListener.ParcialListo(lParcial);
        }
    }

}
