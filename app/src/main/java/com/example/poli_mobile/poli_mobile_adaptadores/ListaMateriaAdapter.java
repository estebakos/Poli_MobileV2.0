package com.example.poli_mobile.poli_mobile_adaptadores;

import java.util.List;

import android.R.color;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.poli_mobile.R;
import com.example.poli_mobile.poli_mobile_entidades.HorarioSemestreActual;
import com.example.poli_mobile.poli_mobile_entidades.ListaMateria;

public class ListaMateriaAdapter extends ArrayAdapter<ListaMateria> {

	private int resource;
	private LayoutInflater inflater;
	private Context ctx;
	private List<ListaMateria> lMateria;

	public ListaMateriaAdapter(Context ctx, int resourceId,
			List<ListaMateria> lMateria) {

		super(ctx, resourceId, lMateria);
		resource = resourceId;
		inflater = LayoutInflater.from(ctx);
		this.ctx = ctx;
		this.lMateria = lMateria;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/* create a new view of my layout and inflate it in the row */
		convertView = inflater.inflate(resource, null);

		/* Extract the city's object to show */
		ListaMateria materia = lMateria.get(position);


		/* Take the TextView from layout and set the city's wiki link */
		TextView txtAula = (TextView) convertView.findViewById(R.id.tvAula);
		txtAula.setText(materia.getAula());

		TextView txtHora = (TextView) convertView.findViewById(R.id.tvHora);
		txtHora.setText(materia.getHorario());

		TextView txtSede = (TextView) convertView.findViewById(R.id.tvSede);
		txtSede.setText(materia.getSede());

		TextView txtMateria = (TextView) convertView
				.findViewById(R.id.tvMateria);
		txtMateria.setText(materia.getNombreMateria());


		return convertView;
	}
}