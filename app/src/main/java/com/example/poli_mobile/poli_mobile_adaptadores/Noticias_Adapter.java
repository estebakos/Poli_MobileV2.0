package com.example.poli_mobile.poli_mobile_adaptadores;

import java.util.ArrayList;

import org.jsoup.Jsoup;

import com.example.poli_mobile.R;
import com.example.poli_mobile.R.id;
import com.example.poli_mobile.R.layout;
import com.example.poli_mobile_entidades.Noticia;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Noticias_Adapter extends ArrayAdapter<Object> {
	Activity context;
	private final ArrayList<Noticia> noticias;

	public Noticias_Adapter(Activity context, ArrayList<Noticia> noticias) {
		super(context, layout.list_rss);
		this.context = context;
		this.noticias = noticias;

	}

	@Override
	public int getCount() {
		return noticias.size();
	}
	
	private static class PlaceHolder {

		TextView title;
		TextView time;
		TextView content;

		ImageView picture;

		public static PlaceHolder generate(View convertView) {
			PlaceHolder placeHolder = new PlaceHolder();
			placeHolder.title = (TextView) convertView
					.findViewById(id.noticia_textview_title);

			// placeHolder.time = (TextView) convertView
			// .findViewById(R.id.noticia_textview_time);
			placeHolder.content = (TextView) convertView
					.findViewById(id.noticia_textview_content);

			//
			placeHolder.picture = (ImageView) convertView
					.findViewById(id.noticia_imageView);
			return placeHolder;
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PlaceHolder placeHolder;
		View v = convertView;
		if (convertView == null) {

			convertView = View.inflate(context, layout.item_noticias, null);
			placeHolder = PlaceHolder.generate(convertView);

			convertView.setTag(placeHolder);

		} else {
			placeHolder = (PlaceHolder) convertView.getTag();
		}
		if (noticias.get(position).getTitulo() != null) {
			placeHolder.title.setText(noticias.get(position).getTitulo());
		}
		if (noticias.get(position).getImagen() != null) {
			placeHolder.picture.setImageDrawable(noticias.get(position)
					.getImagen());
		}
		// placeHolder.time.setText(""
		// + noticias.get(position).getFecha().getDate() + "/"
		// + noticias.get(position).getFecha().getMonth());
		if (noticias.get(position).getResumen() != null) {
			String resumen = html2text(noticias.get(position).getResumen());
			placeHolder.content.setText(resumen);
		}
		// imageLoader.get(noticias.get(position).getImagen(),
		// ImageLoader.getImageListener(placeHolder.picture,
		// R.drawable.ic_launcher, R.drawable.ic_launcher));
		return convertView;
	}

	public static String html2text(String html) {
		return Jsoup.parse(html).text();
	}

}