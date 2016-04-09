package com.example.poli_mobile.poli_mobile_ws;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.poli_mobile.poli_mobile.listeners.IPoliWebService;
import com.example.poli_mobile.poli_mobile.network.SOAPHandler;
import com.example.poli_mobile.poli_mobile.network.SOAPListener;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile.utilidades.PoliPreferences;
import com.example.poli_mobile.poli_mobile_entidades.CalendarioAcademico;
import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;
import com.example.poli_mobile.poli_mobile_entidades.Facultad;
import com.example.poli_mobile.poli_mobile_entidades.HorarioSemestreActual;
import com.example.poli_mobile.poli_mobile_entidades.ListaMateria;
import com.example.poli_mobile.poli_mobile_entidades.NotaMateria;
import com.example.poli_mobile.poli_mobile_entidades.Programa;
import com.example.poli_mobile.poli_mobile_entidades.ProgramacionParcial;

import org.apache.http.HttpStatus;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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


    public void getFacultades(String token) {
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
                            "WS_General?wsdl", getNameSpace(), "Traer_facultades", new SOAPListener() {

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


                            List<Facultad> lFacultades = new ArrayList<Facultad>();
                            if (result != null) {
                                for (SoapObject soap : result) {
                                    try {
                                        Facultad wse = new Facultad();
                                        wse.setCod_facultad(soap.getPropertyAsString("cod_facultad"));
                                        wse.setFundamentos(soap.getPropertyAsString("fundamentos"));
                                        wse.setGeneralidades(soap.getPropertyAsString("generalidades"));
                                        wse.setNom_facultad(soap.getPropertyAsString("nom_facultad"));
                                        wse.setMision(soap.getPropertyAsString("mision"));
                                        wse.setPrincipios(soap.getPropertyAsString("principios"));
                                        Vector<SoapObject> programas = null;
                                        List<Programa> programaList = new ArrayList<Programa>();
                                        for (int i = 0; i < soap.getPropertyCount(); i++) {
                                            Object property = soap.getProperty(i);
                                            if (property instanceof SoapObject) {
                                                programas = new Vector();
                                                programas.add((SoapObject) soap.getProperty(i));
                                                if (programas != null) {
                                                    for (SoapObject programa : programas) {
                                                        try {
                                                            Programa prog = new Programa();
                                                            prog.setMision(programa.getPropertyAsString("mision"));
                                                            prog.setCod_programa(programa.getPropertyAsString("cod_programa"));
                                                            prog.setContacto(programa.getPropertyAsString("contacto"));
                                                            prog.setNom_programa(programa.getPropertyAsString("nom_programa"));
                                                            prog.setPerfil_Profesional(programa.getPropertyAsString("perfil_Profesional"));
                                                            prog.setCreditos(programa.getPropertyAsString("creditos"));
                                                            prog.setEmail(programa.getPropertyAsString("email"));
                                                            prog.setModalidad(programa.getPropertyAsString("modalidad"));
                                                            prog.setPlan_estudios(programa.getPropertyAsString("plan_estudios"));
                                                            prog.setPresentacion(programa.getPropertyAsString("presentacion"));
                                                            prog.setVision(programa.getPropertyAsString("vision"));
                                                            programaList.add(prog);
                                                        } catch (Exception ex) {
                                                            ex.printStackTrace();
                                                        }
                                                        wse.setProgramas(programaList);
                                                    }
                                                }
                                            }
                                        }


                                        wse.setValores(soap.getPropertyAsString("valores"));
                                        wse.setVision(soap.getPropertyAsString("vision"));
                                        lFacultades.add(wse);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                }
                                _IWebService.onFacultades(lFacultades);
                            }
                        }

                    });
                    httpGet.ExecuteSOAP();
                } else

                {
                    _IWebService.onUnexpectedError();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

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
                            "WS_Estudiante?wsdl", getNameSpace(), "Traer_Cita_Medica", new SOAPListener() {

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
                            if (result != null) {
                                for (SoapObject soap : result) {
                                    try {
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
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                }
                                _IWebService.onObtenerCitas(lCitas);
                            }
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

    public void obtenerParciales(String token) {
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
                            "WS_Estudiante?wsdl", getNameSpace(), "Traer_ProgramacionParcial", new SOAPListener() {

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


                            List<ProgramacionParcial> lParcial = new ArrayList<ProgramacionParcial>();
                            if (result != null) {
                                for (SoapObject soap : result) {
                                    try {
                                        ProgramacionParcial wse = new ProgramacionParcial();
                                        wse.setAulaParcial(soap.getPropertyAsString("aulaParcial"));
                                        wse.setCodigoMateria(soap.getPropertyAsString("codigoMateria"));
                                        wse.setDiaParcial(soap.getPropertyAsString("diaParcial"));
                                        wse.setGrupoMateria(soap.getPropertyAsString("grupoMateria"));
                                        wse.setHoraParcial(soap.getPropertyAsString("horaParcial"));
                                        wse.setSedeParcial(soap.getPropertyAsString("sedeParcial"));
                                        lParcial.add(wse);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                }
                                _IWebService.onParciales(lParcial);
                            }
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

    public void getCalendario(String token) {
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
                            "WS_General?wsdl", getNameSpace(), "Traer_calendarioAcademico", new SOAPListener() {

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

                            List<CalendarioAcademico> lCalendario = new ArrayList<CalendarioAcademico>();
                            if (result != null) {
                                for (SoapObject soap : result) {
                                    try {
                                        CalendarioAcademico wse = new CalendarioAcademico();
                                        wse.setAnio(soap.getPropertyAsString("anio"));
                                        wse.setSemestre(soap.getPropertyAsString("semestre"));
                                        wse.setActividad(soap.getPropertyAsString("actividad"));
                                        wse.setFecha(soap.getPropertyAsString("fecha"));
                                        lCalendario.add(wse);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                }
                                _IWebService.onCalendario(lCalendario);
                            }
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

    public void getHorario(String token, final String dia) {
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
                            "WS_Estudiante?wsdl", getNameSpace(), "Traer_Horario_Semestre_Actual", new SOAPListener() {

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

                            if (result != null) {
                                List<HorarioSemestreActual> webServiceEntity = new ArrayList<HorarioSemestreActual>();
                                for (SoapObject soap : result) {
                                    if (soap.getPropertyAsString(1).toString().equals(dia)) {
                                        HorarioSemestreActual wse = new HorarioSemestreActual();
                                        wse.setNomSede(soap.getPropertyAsString(6).toString());
                                        wse.setAula(soap.getPropertyAsString(0).toString());
                                        wse.setHorario(soap.getPropertyAsString(3).toString());
                                        wse.setDia(soap.getPropertyAsString(1).toString());
                                        wse.setNomProfesor(soap.getPropertyAsString(5).toString());
                                        wse.setPrograma(soap.getPropertyAsString(8).toString());
                                        wse.setFacultad(soap.getPropertyAsString(2).toString());
                                        wse.setSemestre(soap.getPropertyAsString(9).toString());
                                        wse.setNomMateria(soap.getPropertyAsString(4).toString());
                                        wse.setNumCreditos(soap.getPropertyAsString(7).toString());
                                        webServiceEntity.add(wse);
                                    }
                                }
                                _IWebService.HorarioListo(webServiceEntity);
                            }
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

    public void getNotas(String token) {
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
                            "WS_Estudiante?wsdl", getNameSpace(), "Traer_Notas_Materia_Semestre_Actual", new SOAPListener() {

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

                            if (result != null) {
                                List<NotaMateria> webServiceEntity = new ArrayList<NotaMateria>();
                                for (SoapObject soap : result) {

                                    NotaMateria wse = new NotaMateria();
                                    wse.setNomMateria(soap.getPropertyAsString(1).toString());
                                    wse.setNotafinal(soap.getPropertyAsString(2).toString());
                                    wse.setParcial1(soap.getPropertyAsString(3).toString());
                                    wse.setParcial2(soap.getPropertyAsString(4).toString());
                                    wse.setProfesorMateria(soap.getPropertyAsString(5)
                                            .toString());
                                    wse.setSeguimiento(soap.getPropertyAsString(6).toString());

                                    webServiceEntity.add(wse);
                                }
                                _IWebService.NotasLitas(webServiceEntity);
                            }
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

    public void getListadoClase(String token) {
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
                            "WS_Docente?wsdl", getNameSpace(), "Traer_ListadoClasesMateria", new SOAPListener() {

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

                            List<ListaMateria> webServiceEntity = new ArrayList<ListaMateria>();
                            if (result != null) {
                                for (SoapObject soap : result) {
                                    ListaMateria wse = new ListaMateria();
                                    wse.setAula(soap.getPropertyAsString(0).toString());
                                    wse.setHorario(soap.getPropertyAsString(2).toString());
                                    wse.setNombreMateria((soap.getPropertyAsString(3).toString()));
                                    wse.setSede(soap.getPropertyAsString(4).toString());
                                    webServiceEntity.add(wse);

                                }
                                _IWebService.ListaMateria(webServiceEntity);
                            }
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

    public void Login(String user, String password) {
        try {

            if (!isNetworkAvailable()) {
                _IWebService.onInternetFail();
            } else {
                if (getRestUrl() != "") {
                    SOAPHandler httpGet = new SOAPHandler(getRestUrl(),
                            "WS_Estudiante?wsdl", getNameSpace(), "getId_Session_Estudiante", new SOAPListener() {

                        @Override
                        public void onRequestFinish(
                                Object SOAPResponse) {
                            SoapPrimitive resultado_xml = (SoapPrimitive) SOAPResponse;
                            if (SOAPResponse != null) {
                                _IWebService.onAuthenticate(resultado_xml.toString());
                            } else {
                                _IWebService.onAuthenticationFail();
                            }
                        }

                    });

                    httpGet.addSOAPParam("identificacion", user);
                    httpGet.addSOAPParam("password", password);
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
