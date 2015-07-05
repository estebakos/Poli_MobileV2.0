package com.example.poli_mobile.poli_mobile.ui;

import br.liveo.utils.Constant;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.poli_mobile.R;
import com.example.poli_mobile.R.layout;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AcercaDe extends android.support.v4.app.Fragment {

	public AcercaDe newInstance(String text) {
		AcercaDe mFragment = new AcercaDe();
		Bundle mBundle = new Bundle();
		mBundle.putString(Constant.TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v =  inflater.inflate(layout.acerca_de, container, false);
		SliderLayout sldGallery = (SliderLayout)v.findViewById(R.id.NuevoSlider);
		DefaultSliderView df = new DefaultSliderView(getActivity());
		df.image(getActivity().getResources().getResourceName(R.drawable.background_oval_shadow));
		sldGallery.addSlider(df);
		sldGallery.setBackgroundColor(Color.BLACK);
		return v;		
	}

}
