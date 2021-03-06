package com.example.poli_mobile.poli_mobile.ui;

import java.util.List;

import com.example.poli_mobile.R;
import com.example.poli_mobile.R.id;
import com.example.poli_mobile.R.layout;
import com.example.poli_mobile.poli_mobile.listeners.HorarioListener;
import com.example.poli_mobile.poli_mobile.network.NetworkManager;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;
import com.example.poli_mobile.poli_mobile_adaptadores.HorarioAdapter;
import com.example.poli_mobile.poli_mobile_entidades.HorarioSemestreActual;
import com.example.poli_mobile.poli_mobile_ws.WsHorario;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class LunesFragment extends Fragment implements HorarioListener,
		OnRefreshListener {

	private ListView lvLunes;
	private SwipeRefreshLayout srLunes;
	private View v;
	private LayoutInflater inflater;
	private ViewGroup container;
	private ImageView ivScheduleBackground;
	public TextView tvScheduleBackground;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if(ApplicationSession.rlayout!=null)
		{
			ApplicationSession.rlayout.setBackgroundColor(Color.parseColor("#43a047"));
		}
		this.container = container;
		this.inflater = inflater;
		v = new View(getActivity());
		v = inflater.inflate(layout.list_horario, container, false);
		srLunes = (SwipeRefreshLayout) v.findViewById(id.swipe_container);
		lvLunes = (ListView) v.findViewById(id.lvVodooCoupons);
		srLunes.setOnRefreshListener(this);
		tvScheduleBackground = (TextView) v.findViewById(id.tvScheduleBackground);
		ivScheduleBackground = (ImageView) v.findViewById(id.ivScheduleBackground);
		callService();

		return v;
	}

	public void callService() {
		NetworkManager.getNManagerInstance().setHorarioListener(this);
		NetworkManager.getNManagerInstance().getHorario("Lunes");
	}

	@Override
	public void HorarioListo(List<HorarioSemestreActual> lHorario) {
		if(lHorario.size()==0){
			ivScheduleBackground.setVisibility(View.VISIBLE);
			tvScheduleBackground.setVisibility(View.VISIBLE);
		}
		else
		{
			lvLunes.setAdapter(new HorarioAdapter(getActivity(), layout.horarioitem, lHorario));
		}
	}

	@Override
	public void HorarioFallido() {
		ivScheduleBackground.setVisibility(View.VISIBLE);
		tvScheduleBackground.setVisibility(View.VISIBLE);
	}

	@Override
	public void onRefresh() {
		callService();
	}

}
