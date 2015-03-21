package com.example.project_basketballstats;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

@SuppressLint("NewApi")
public class Tab extends Activity  {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String id =  getIntent().getExtras().getString("UserID");
		Log.d("Tab",id);
		setContentView(R.layout.tab);

		final ActionBar tabBar = getActionBar();
		tabBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Game game = new Game();
		Bundle args = new Bundle();
		args.putString("Type", "game");
		game.setArguments(args);
		tabBar.addTab(tabBar.newTab().setText("Game")
				.setTabListener(new TabListener(game)));

		Team team = new Team();
		Bundle arg = new Bundle();
		arg.putString("Type", "team");
		team.setArguments(arg);
		tabBar.addTab(tabBar.newTab().setText("Team")
				.setTabListener(new TabListener(team)));
		
		Frag settings = new Frag();
		//Bundle arg = new Bundle();
		arg.putString("Type", "settings");
		settings.setArguments(arg);
		tabBar.addTab(tabBar.newTab().setText("Settings")
				.setTabListener(new TabListener(settings)));
		
		/*Options settings = new Options();
		tabBar.addTab(tabBar.newTab().setText("settings")
				.setTabListener(new TabListener(settings)));*/
		
		Frag logout = new Frag();
		Bundle a = new Bundle();
		a.putString("Type", "logout");
		logout.setArguments(a);
		tabBar.addTab(tabBar.newTab().setText("Logout")
				.setTabListener(new TabListener(logout)));
		

	}

	public static class TabListener implements ActionBar.TabListener {
		private final Fragment mFragment;
		
		public TabListener(PreferenceFragment f) {
			mFragment = f;
		}

		public TabListener(Fragment fragment) {
			mFragment = fragment;
		}

		@Override
		public void onTabReselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
		}

		@Override
		public void onTabSelected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
			if (null != mFragment) {
				ft.replace(R.id.fragment_container, mFragment);
			}
		}

		@Override
		public void onTabUnselected(android.app.ActionBar.Tab tab, FragmentTransaction ft) {
			if (null != mFragment)
				ft.remove(mFragment);
		}

}
	
}
