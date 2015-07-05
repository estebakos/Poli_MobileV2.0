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
import com.example.poli_mobile_entidades.HorarioSemestreActual;

public class HorarioAdapter extends ArrayAdapter<HorarioSemestreActual> {

	private int resource;
	private LayoutInflater inflater;
	private Context ctx;
	private List<HorarioSemestreActual> lhorario;

	public HorarioAdapter(Context ctx, int resourceId,
			List<HorarioSemestreActual> lhorario) {

		super(ctx, resourceId, lhorario);
		resource = resourceId;
		inflater = LayoutInflater.from(ctx);
		this.ctx = ctx;
		this.lhorario = lhorario;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/* create a new view of my layout and inflate it in the row */
		convertView = (RelativeLayout) inflater.inflate(resource, null);

		/* Extract the city's object to show */
		HorarioSemestreActual horario = lhorario.get(position);

		RelativeLayout relativeHorario = (RelativeLayout) convertView
				.findViewById(R.id.linearHorario);

		/* Take the TextView from layout and set the city's name */
		TextView txtName = (TextView) convertView.findViewById(R.id.tvProfesor);
		txtName.setText(horario.getNomProfesor());

		/* Take the TextView from layout and set the city's wiki link */
		TextView txtAula = (TextView) convertView.findViewById(R.id.tvAula);
		txtAula.setText(horario.getAula());

		TextView txtHora = (TextView) convertView.findViewById(R.id.tvHora);
		txtHora.setText(horario.getHorario());

		TextView txtSede = (TextView) convertView.findViewById(R.id.tvSede);
		txtSede.setText(horario.getNomSede());

		TextView txtMateria = (TextView) convertView
				.findViewById(R.id.tvMateria);
		txtMateria.setText(horario.getNomMateria());

		relativeHorario.setBackgroundDrawable(ctx.getResources().getDrawable(
				R.drawable.selector_item_schedule_white));

		return convertView;
	}
}