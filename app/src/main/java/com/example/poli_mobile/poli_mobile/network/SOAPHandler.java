package com.example.poli_mobile.poli_mobile.network;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

import org.apache.http.NameValuePair;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class SOAPHandler extends AsyncTask<Void, Integer, Object> {

    private ArrayList<NameValuePair> _queryStringParams;
    private ArrayList<NameValuePair> _contentParams;
    private Map<String, Object> _SOAPParams;
    private ArrayList<NameValuePair> _headers;

    private final String _url;
    private RequestMethod _verb;
    private String _content;
    private String _requestContentType = null;
    private String NAMESPACE;
    private String METHOD_NAME;

    private final SOAPListener _SOAPListener;
    private final HttpResponse _httpResponse;


    public enum RequestMethod {
        SOAP
    }

    public SOAPHandler(String url, String route, SOAPListener listener) {
        this(url + "/" + route, listener);
        _httpResponse.Route = route;
    }

    public SOAPHandler(String url, String route, String NameSpace, String Method, SOAPListener listener) {
        this(url + "/" + route, listener);
        _httpResponse.Route = route;
        this.NAMESPACE = NameSpace;
        this.METHOD_NAME = Method;

    }


    public void setContentType(String contentType) {
        _requestContentType = contentType;
    }

    public String getUrl() {
        return _url;
    }

    public SOAPHandler(String url, SOAPListener listener) {
        _url = url;
        _queryStringParams = new ArrayList<NameValuePair>();
        _contentParams = new ArrayList<NameValuePair>();
        _headers = new ArrayList<NameValuePair>();
        _content = null;
        _SOAPListener = listener;
        _httpResponse = new HttpResponse();
        _SOAPParams = new HashMap<String, Object>();
    }


    public void addSOAPParam(String name, String value) {
        _SOAPParams.put(name, value);
    }


    public void setContent(String content) {
        _content = content;
    }


    public void ExecuteSOAP() {
        _verb = RequestMethod.SOAP;
        Execute(_verb);
    }

    @SuppressLint("NewApi")
    public void Execute(RequestMethod method) {
        _verb = method;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            this.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                    (Void[]) null);
        } else {
            this.execute((Void[]) null);
        }
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Object doInBackground(Void... params) {
        Object response = null;
        try {
            switch (_verb) {

                case SOAP: {
                    SoapObject requestSoap = new SoapObject(NAMESPACE, METHOD_NAME);

                    if (!_SOAPParams.isEmpty()) {
                        for (Map.Entry<String, Object> entry : _SOAPParams.entrySet()) {
                            requestSoap.addProperty(entry.getKey(), entry.getValue());
                        }
                    }

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                            SoapEnvelope.VER10);
                    envelope.dotNet = false;

                    envelope.setOutputSoapObject(requestSoap);

                    HttpTransportSE transporte = new HttpTransportSE(_url);
                    response = null;
                    Vector<SoapObject> result = null;
                    try {
                        transporte.call("", envelope);
                        if (envelope.getResponse() != null) {
                            response = envelope.getResponse();
                            System.out.println(":)");
                        }
                    } catch (Exception e) {
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onCancelled() {
        this.cancel(true);
    }

    @Override
    protected void onPostExecute(Object result) {
        this.cancel(true);

        if (_SOAPListener != null) {
            _SOAPListener.onRequestFinish(result);
        }
    }

}
