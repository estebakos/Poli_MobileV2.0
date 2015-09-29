package com.example.poli_mobile.poli_mobile_ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.poli_mobile.poli_mobile.listeners.HorarioListener;
import com.example.poli_mobile.poli_mobile.listeners.NotasListener;
import com.example.poli_mobile.poli_mobile_entidades.HorarioSemestreActual;
import com.example.poli_mobile.poli_mobile_entidades.NotaMateria;

import android.os.AsyncTask;

public class WsNotas extends AsyncTask<String, Integer, Boolean> {

	private String cedula, id;
	private SoapObject objSoap;
	private Vector<SoapObject> result = null;
	private NotasListener lListener;
	private String dia;

	public WsNotas(String id) {

		this.cedula = id;
		this.objSoap = null;

	}

	protected Boolean doInBackground(String... params) {

		boolean resul = true;

		final String NAMESPACE = "http://Ws.APP_Polimovil.com.co/";
		final String URL = "http://10.100.187.142:8080/APP_Polimovil/WS_Estudiante?wsdl";
		final String METHOD_NAME = "Traer_Notas_Materia_Semestre_Actual";
		final String SOAP_ACTION = "";

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("id_Session", cedula);

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
			List<NotaMateria> webServiceEntity = new ArrayList<NotaMateria>();
			for (SoapObject soap : result) {
				
					NotaMateria wse = new NotaMateria();
					wse.setNomMateria(soap.getPropertyAsString(0).toString());
					wse.setNotafinal(soap.getPropertyAsString(1).toString());
					wse.setParcial1(soap.getPropertyAsString(2).toString());
					wse.setParcial2(soap.getPropertyAsString(3).toString());
					wse.setProfesorMateria(soap.getPropertyAsString(4)
							.toString());
					wse.setSeguimiento(soap.getPropertyAsString(5).toString());

					webServiceEntity.add(wse);
				}
			
			lListener.LNotas(webServiceEntity);
		} else {

		}
	}

	public void setLoginListener(NotasListener lListener) {
		this.lListener = lListener;

	}

}