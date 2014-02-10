package com.kinvey.sample.tictac;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kinvey.sample.tictac.fragments.LoginFragment;
import com.kinvey.sample.tictac.fragments.RegisterFragment;

public class UserActivity extends ActionBarActivity implements TabListener{
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Note there is no call to SetContentView(...) here.
		// Check out onTabSelected()-- the selected tab and associated fragment
		// is given android.R.id.content as it's parent view, which *is* the content
		// view. Basically, this approach gives the fragments under the tabs the
		// complete window available to our activity without any unnecessary
		// layout inflation.
		setUpTabs();

		

	}

	// on pause force-dismisses dialogs to avoid window leaks.
	@Override
	public void onPause() {
		super.onPause();
	}

	
	
	
	public void setUpTabs() {

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		android.support.v7.app.ActionBar.Tab loginTab = getSupportActionBar().newTab().setText("Login").setTabListener(this);
		getSupportActionBar().addTab(loginTab);

		android.support.v7.app.ActionBar.Tab registerTab = getSupportActionBar().newTab().setText("Register").setTabListener(this);
		getSupportActionBar().addTab(registerTab);

	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_user, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_user_logout:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    
    private void logout(){
    	TicTac.getClient(this).user().logout();
    	this.finish();
    }

	// -------------Actionbar.TabListener methods
	@Override
	public void onTabReselected(android.support.v7.app.ActionBar.Tab arg0,
			FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction tr) {
		Fragment toShow = null;
		if (tab.getPosition() == 0){
			toShow = new LoginFragment();
		}else{
			toShow = new RegisterFragment();
		}
		tr.replace(android.R.id.content, toShow); // <----- Content view is set here					
	}

	@Override
	public void onTabUnselected(android.support.v7.app.ActionBar.Tab arg0,
			FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
	


}
