package com.example.poli_mobile.poli_mobile.ui;

import java.util.HashMap;

import com.example.poli_mobile.R;
import com.example.poli_mobile.R.id;
import com.example.poli_mobile.R.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

public class HorarioFragment extends Fragment implements
		OnTabChangeListener {

	private TabHost mTabHost;
	private HashMap mapTabInfo = new HashMap();
	private TabInfo mLastTab = null;
	private View v;

	private class TabInfo {
		private String tag;
		private Class clss;
		private Bundle args;
		private Fragment fragment;

		TabInfo(String tag, Class clazz, Bundle args) {
			this.tag = tag;
			this.clss = clazz;
			this.args = args;
		}

	}

	static class TabFactory implements TabContentFactory {

		private final Context mContext;

		public TabFactory(Context context) {
			mContext = context;
		}

		public View createTabContent(String tag) {
			View v = new View(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}

	}

	public HorarioFragment newInstance(String text) {
		HorarioFragment mFragment = new HorarioFragment();
		Bundle mBundle = new Bundle();
		mFragment.setArguments(mBundle);
		return mFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	private void initialiseTabHost(Bundle args) {
//		mTabHost = (TabHost) v.findViewById(android.R.id.tabhost);
//		mTabHost.setup();
//		TabInfo tabInfo = null;
//		HorarioFragment.addTab((NavigationMain) getActivity(), this.mTabHost,
//				this.mTabHost.newTabSpec("Tab1").setIndicator("Lunes"),
//				(tabInfo = new TabInfo("Tab1", LunesFragment.class, args)));
//		this.mapTabInfo.put(tabInfo.tag, tabInfo);
//		HorarioFragment.addTab((NavigationMain) getActivity(), this.mTabHost,
//				this.mTabHost.newTabSpec("Tab2").setIndicator("Martes"),
//				(tabInfo = new TabInfo("Tab2", MartesFragment.class, args)));
//		this.mapTabInfo.put(tabInfo.tag, tabInfo);
//		HorarioFragment.addTab((NavigationMain) getActivity(), this.mTabHost,
//				this.mTabHost.newTabSpec("Tab3").setIndicator("Miércoles"),
//				(tabInfo = new TabInfo("Tab3", MiercolesFragment.class, args)));
//		this.mapTabInfo.put(tabInfo.tag, tabInfo);
//
//		HorarioFragment.addTab((NavigationMain) getActivity(), this.mTabHost,
//				this.mTabHost.newTabSpec("Tab4").setIndicator("Jueves"),
//				(tabInfo = new TabInfo("Tab4", JuevesFragment.class, args)));
//		this.mapTabInfo.put(tabInfo.tag, tabInfo);
//		HorarioFragment.addTab((NavigationMain) getActivity(), this.mTabHost,
//				this.mTabHost.newTabSpec("Tab5").setIndicator("Viernes"),
//				(tabInfo = new TabInfo("Tab5", ViernesFragment.class, args)));
//		this.mapTabInfo.put(tabInfo.tag, tabInfo);
//		HorarioFragment.addTab((NavigationMain) getActivity(), this.mTabHost,
//				this.mTabHost.newTabSpec("Tab6").setIndicator("Sábado"),
//				(tabInfo = new TabInfo("Tab6", SabadoFragment.class, args)));
//		this.mapTabInfo.put(tabInfo.tag, tabInfo);
//		HorarioFragment.addTab((NavigationMain) getActivity(), this.mTabHost,
//				this.mTabHost.newTabSpec("Tab7").setIndicator("Domingo"),
//				(tabInfo = new TabInfo("Tab7", DomingoFragment.class, args)));
//		this.mapTabInfo.put(tabInfo.tag, tabInfo);
//		// Default to first tab
//		this.onTabChanged("Tab1");
//		//
//		mTabHost.setOnTabChangedListener(this);
	}

/*	private static void addTab(NavigationMain activity, TabHost tabHost,
			TabHost.TabSpec tabSpec, TabInfo tabInfo) {
		// Attach a Tab view factory to the spec

		tabSpec.setContent(new TabFactory(activity));
		String tag = tabSpec.getTag();

		// Check to see if we already have a fragment for this tab, probably
		// from a previously saved state. If so, deactivate it, because our
		// initial state is that a tab isn't shown.
		tabInfo.fragment = activity.getSupportFragmentManager()
				.findFragmentByTag(tag);
		if (tabInfo.fragment != null && !tabInfo.fragment.isDetached()) {
			FragmentTransaction ft = activity.getSupportFragmentManager()
					.beginTransaction();
			ft.detach(tabInfo.fragment);
			ft.commit();
			activity.getSupportFragmentManager().executePendingTransactions();
		}

		tabHost.addTab(tabSpec);
	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = new View(getActivity());
		v = inflater.inflate(layout.fragment_horario, container, false);
		initialiseTabHost(savedInstanceState);
		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}


		return v;
	}

	public void onTabChanged(String tag) {
		TabInfo newTab = (TabInfo) this.mapTabInfo.get(tag);
		if (mLastTab != newTab) {
			FragmentTransaction ft = getChildFragmentManager()
					.beginTransaction();
			if (mLastTab != null) {
				if (mLastTab.fragment != null) {
					ft.detach(mLastTab.fragment);
				}
			}
			if (newTab != null) {
				if (newTab.fragment == null) {
					newTab.fragment = Fragment.instantiate(getActivity(),
							newTab.clss.getName(), newTab.args);
					ft.add(id.tab_2, newTab.fragment, newTab.tag);
				} else {
					ft.attach(newTab.fragment);
				}
			}

			mLastTab = newTab;
			ft.commit();
			getChildFragmentManager()
					.executePendingTransactions();
		}
	}

}
