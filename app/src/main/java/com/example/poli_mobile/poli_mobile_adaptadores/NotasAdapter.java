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
import com.example.poli_mobile.poli_mobile_entidades.NotaMateria;

public class NotasAdapter extends ArrayAdapter<NotaMateria> {

	private int resource;
	private LayoutInflater inflater;
	private Context ctx;
	private List<NotaMateria> lMateria;

	public NotasAdapter(Context ctx, int resourceId,
			List<NotaMateria> lMateria) {

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
		convertView = (RelativeLayout) inflater.inflate(resource, null);

		/* Extract the city's object to show */
		NotaMateria materia = lMateria.get(position);

		RelativeLayout relativeHorario = (RelativeLayout) convertView
				.findViewById(R.id.linearHorario);


		/* Take the TextView from layout and set the city's wiki link */
		TextView txtMateria = (TextView) convertView.findViewById(R.id.tvMateria);
		txtMateria.setText(materia.getNomMateria());

		TextView txtProfesor = (TextView) convertView.findViewById(R.id.tvProfesor);
		txtProfesor.setText(materia.getProfesorMateria());

		TextView txtSegundoParcial =(TextView)convertView.findViewById(R.id.tvPrimerParcial);
		txtSegundoParcial.setText(materia.getParcial2());
		
		TextView txtSeguimiento = (TextView) convertView.findViewById(R.id.tvSeguimiento);
		txtSeguimiento.setText(materia.getSeguimiento());
		
		
		TextView txtPrimerPArcial = (TextView) convertView.findViewById(R.id.tvFinal);
		txtPrimerPArcial.setText(materia.getParcial1());
		
		TextView txtNotaFinal = (TextView) convertView.findViewById(R.id.tvNotaFinal);
		txtNotaFinal.setText(materia.getNotafinal());


		relativeHorario.setBackgroundDrawable(ctx.getResources().getDrawable(
				R.drawable.selector_item_schedule_white));

		return convertView;
	}
}