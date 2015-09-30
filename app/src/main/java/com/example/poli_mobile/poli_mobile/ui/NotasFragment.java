package com.example.poli_mobile.poli_mobile.ui;

import java.util.List;

import com.example.poli_mobile.R.id;
import com.example.poli_mobile.R.layout;
import com.example.poli_mobile.poli_mobile.listeners.NotasListener;
import com.example.poli_mobile.poli_mobile.utilidades.ApplicationSession;;
import com.example.poli_mobile.poli_mobile_adaptadores.NotasAdapter;
import com.example.poli_mobile.poli_mobile_entidades.NotaMateria;
import com.example.poli_mobile.poli_mobile_ws.WsNotas;

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

public class NotasFragment extends Fragment implements
		NotasListener, OnRefreshListener {

	private ListView lvLunes;
	private SwipeRefreshLayout srLunes;
	private ImageView ivScheduleBackground;
	public TextView tvScheduleBackground;

	public NotasFragment newInstance(String text) {
		NotasFragment mFragment = new NotasFragment();
		Bundle mBundle = new Bundle();
		//mBundle.putString(Constant.TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = new View(getActivity());
		v = inflater.inflate(layout.list_notas, container, false);
		if (ApplicationSession.rlayout != null) {
			ApplicationSession.rlayout.setBackgroundColor(Color
					.parseColor("#b3e5fc"));
		}
		srLunes = (SwipeRefreshLayout) v.findViewById(id.swipe_container);
		lvLunes = (ListView) v.findViewById(id.lvNotas);
		srLunes.setOnRefreshListener(this);
		tvScheduleBackground = (TextView) v
				.findViewById(id.tvScheduleBackground);
		ivScheduleBackground = (ImageView) v
				.findViewById(id.ivScheduleBackground);
		callService();

		return v;
	}

	public void callService() {
		WsNotas wshora = new WsNotas(ApplicationSession.SessionStudent);
		wshora.setLoginListener(this);

		wshora.execute();
	}

	@Override
	public void onRefresh() {
		callService();
	}


	@Override
	public void LNotas(List<NotaMateria> lNotaMateria) {
		if (lNotaMateria.size() == 0) {
			ivScheduleBackground.setVisibility(View.VISIBLE);
			tvScheduleBackground.setVisibility(View.VISIBLE);
		} else {
			lvLunes.setAdapter(new NotasAdapter(getActivity(),
					layout.nota_item, lNotaMateria));
		}
		
	}

	@Override
	public void LNotaFallida() {
		ivScheduleBackground.setVisibility(View.VISIBLE);
		tvScheduleBackground.setVisibility(View.VISIBLE);
		
	}

}
