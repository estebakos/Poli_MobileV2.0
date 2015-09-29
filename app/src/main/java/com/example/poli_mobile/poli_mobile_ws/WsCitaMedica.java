package com.example.poli_mobile.poli_mobile_ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.poli_mobile.poli_mobile.listeners.CitaListener;
import com.example.poli_mobile.poli_mobile_entidades.CitaMedica;

import android.os.AsyncTask;

public class WsCitaMedica extends AsyncTask<String, Integer, Boolean> {

	private String sessionId;

	private Vector<SoapObject> result = null;
	private CitaListener lListener;

	public WsCitaMedica(String sessionId) {

		this.sessionId = sessionId;

	}

	protected Boolean doInBackground(String... params) {

		boolean resul = true;

		final String NAMESPACE = "http://Ws.APP_Polimovil.com.co/";
		final String URL = "http://10.100.187.142:8080/APP_Polimovil/WS_Estudiante?wsdl";
		final String METHOD_NAME = "Traer_Cita_Medica";
		final String SOAP_ACTION = "";

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("Session_Id", sessionId);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		envelope.dotNet = false;

		envelope.setOutputSoapObject(request);

		HttpTransportSE transporte = new HttpTransportSE(URL);
		Object response = null;

		try {
			transporte.call(SOAP_ACTION, envelope);

			//
			// resultado_xml =(List<SoapObject>)envelope.getResponse();
			// System.out.println(":)");
			// //objSoap = (SoapObject) envelope.getResponse();

			if (envelope.getResponse() != null) {
				// if receive only one object
				// ArrayList<HorarioSemestreActual>
				// person=(ArrayList<HorarioSemestreActual>)envelope.getResponse();
				// receive array of objects,directly call SoapDeserialize()
				// the first parameter is the Class of the objects in array the
				// second parameter is soapObject ,the same as above
				// This method return an array which is the result we need
				// ArrayList<HorarioSemestreActual> arrayList=new
				// ArrayList<HorarioSemestreActual>();
				// arrayList= new Deserialization().SoapDeserilize(,
				// object);(HorarioSemestreActual.class,(SoapObject)(envelope.getResponse()));

				response = envelope.getResponse();
				System.out.println(":)");
				resul = true;
			}
		} catch (Exception e) {
			resul = false;
		}

		if (response instanceof SoapObject) {
			result = new Vector();
			result.add((SoapObject) response);
		} else if (response instanceof Vector) {
			result = (Vector<SoapObject>) response;
		}

		return resul;
	}

	protected void onPostExecute(Boolean resultado) {

		if (resultado) {
			List<CitaMedica> webServiceEntity = new ArrayList<CitaMedica>();
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

					webServiceEntity.add(wse);
				
			}
			lListener.CitaLista(webServiceEntity);
		} else {

		}
	}

	public void setLoginListener(CitaListener lListener) {
		this.lListener = lListener;

	}

}