package com.example.poli_mobile.poli_mobile.ui;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;

import com.example.poli_mobile.R;
import com.example.poli_mobile.R.id;
import com.example.poli_mobile.R.layout;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile.utilidades.XMLRss;
import com.example.poli_mobile.poli_mobile_adaptadores.Noticias_Adapter;
import com.example.poli_mobile.poli_mobile_entidades.Noticia;

import br.liveo.fragments.DownloadFragment;
import br.liveo.utils.Constant;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Facultades extends android.support.v4.app.Fragment {
	private Noticias_Adapter adapter;
	public ArrayList<Noticia> Array_Noticias;
	public ArrayList<Noticia> Array_NoticiasOff;
	public ListView lvNoticias;
	String ruta;

	public Facultades newInstance(String text){
		Facultades mFragment = new Facultades();		
		Bundle mBundle = new Bundle();
		mBundle.putString(Constant.TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = new View(getActivity());
		v = inflater.inflate(layout.facultades, container, false);
		if(ApplicationSession.rlayout!=null)
		{
			ApplicationSession.rlayout.setBackgroundColor(Color.parseColor("#607d8b"));
		}
		//http://politecnicojic.edu.co/index.php?option=com_content&view=category&layout=blog&id=35&Itemid=335&format=feed&type=rss
		ruta = "http://www.mecd.gob.es/rss/actualidad";
		lvNoticias = (ListView)v.findViewById(id.lvNoticias);
		Array_Noticias = new ArrayList<Noticia>();
		if (isOnline()) {
			rellenarNoticias();
		}

		return v;

	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getActivity()
				.getApplication()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) {
			return true;
		}
		return false;
	}

	private class DescargarNoticias extends AsyncTask<String, Void, Boolean> {

		private final String feedUrl;
		private final Context ctx;

		public DescargarNoticias(Context c, String url) {
			this.feedUrl = url;
			this.ctx = c;
		}

		@Override
		protected Boolean doInBackground(final String... params) {
			XMLRss parser = new XMLRss(feedUrl, getActivity()
					.getApplicationContext());
			if (isOnline()) {
				Array_Noticias = parser.parse();
			}

			return true;
		}

		@Override
		protected void onPostExecute(Boolean success) {
			if (success) {
				try {
					inicializarListView(Array_Noticias);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				Toast.makeText(ctx, "Error en la lectura", Toast.LENGTH_LONG)
						.show();
			}
		}

	}

	private void inicializarListView(final ArrayList<Noticia> array) {
		if (array.size() > 0) {
			String[] noticias = new String[array.size()];
			for (int i = 0; i < array.size(); i++) {
				noticias[i] = array.get(i).getTitulo();
			}
			List<Noticia> lista = (List<Noticia>)array;
			adapter = new Noticias_Adapter(getActivity(),array);
			lvNoticias.setAdapter(adapter);

		}

	}

	private void rellenarNoticias() {
		if (isOnline()) {
			new DescargarNoticias(getActivity().getApplicationContext(), ruta)
					.execute();
		}

	}
}
