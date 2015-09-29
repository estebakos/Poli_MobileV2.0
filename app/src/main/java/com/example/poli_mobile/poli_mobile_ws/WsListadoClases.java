package com.example.poli_mobile.poli_mobile_ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.poli_mobile.poli_mobile.listeners.HorarioListener;
import com.example.poli_mobile.poli_mobile.listeners.ListadorMateriaListener;
import com.example.poli_mobile.poli_mobile_entidades.HorarioSemestreActual;
import com.example.poli_mobile.poli_mobile_entidades.ListaMateria;
import com.example.poli_mobile.poli_mobile_entidades.ListadoClasesMateria;

import android.os.AsyncTask;

public class WsListadoClases extends AsyncTask<String, Integer, Boolean> {

	private String id;
	private SoapObject objSoap;
	private Vector<SoapObject> result = null;
	private ListadorMateriaListener lListener;

	public WsListadoClases(String id) {

		this.id = id;
		this.objSoap = null;

	}

	protected Boolean doInBackground(String... params) {

		boolean resul = true;

		final String NAMESPACE = "http://Ws.APP_Polimovil.com.co/";
		final String URL = "http://10.100.187.142:8080/APP_Polimovil/WS_Docente?wsdl";
		final String METHOD_NAME = "Traer_ListadoClasesMateria";
		final String SOAP_ACTION = "";

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("id_session", id);

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
			List<ListaMateria> webServiceEntity = new ArrayList<ListaMateria>();
			if(result!=null){
			for (SoapObject soap : result) {
				
				ListaMateria wse = new ListaMateria();
				wse.setAula(soap.getPropertyAsString(0).toString());
				wse.setHorario(soap.getPropertyAsString(2).toString());
				wse.setNombreMateria((soap.getPropertyAsString(3).toString()));
				wse.setSede(soap.getPropertyAsString(4).toString());				webServiceEntity.add(wse);
				
			}
			lListener.LMateriaLista(webServiceEntity);
			}
		} else {

		}
	}
	
	public void setLoginListener(ListadorMateriaListener lListener) {
		this.lListener = lListener;

	}

}