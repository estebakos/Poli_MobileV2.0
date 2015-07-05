package com.example.poli_mobile.poli_mobile_ws;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.poli_mobile.poli_mobile.listeners.loginListener;

import android.os.AsyncTask;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;

public class WsLogin extends AsyncTask<String,Integer,Boolean> {
 
	private String id,cedula,password;
	
	private loginListener lListener;
	
	
	public WsLogin(String id,String password)
	{
		this.password = password;
		this.cedula = id;
	
	}
 
    protected Boolean doInBackground(String... params) {
 
        boolean resul = true;
 
    
    final String NAMESPACE = "http://Ws.APP_Polimovil.com.co/";
    final String URL = "http://10.100.187.142:8080/APP_Polimovil/WS_Estudiante?wsdl";
    final String METHOD_NAME = "getId_Session_Estudiante";
    final String SOAP_ACTION = "";		
 
    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    request.addProperty("identificacion",cedula);
    request.addProperty("password",password);
    
    SoapSerializationEnvelope envelope =
           new SoapSerializationEnvelope(SoapEnvelope.VER10);
    envelope.dotNet = false;
 
    envelope.setOutputSoapObject(request);
 
    HttpTransportSE transporte = new HttpTransportSE(URL);
 
    try
    {
        transporte.call(SOAP_ACTION, envelope);
        SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
        id = resultado_xml.toString();
 
    }
    catch (Exception e)
    {
        resul = false;
    }
 
       return resul;
   }
 
   protected void onPostExecute(Boolean result) {
 
        if (result)
        {
        	lListener.onSessionAuthenticated(id);
        }
        else
        {
            lListener.onSessionFailed();
        }
    }

public void setLoginListener(loginListener lListener) {
	this.lListener = lListener;
	
}
}