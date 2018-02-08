package com.d2runltd.professorate.Activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.d2runltd.professorate.ProfAdapter;
import com.d2runltd.professorate.Professor;
import com.d2runltd.professorate.R;
import com.d2runltd.professorate.UtilityMethods;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener
{
	private RecyclerView profRecyclerView;
	private RecyclerView.Adapter profAdapter;
	private RecyclerView.LayoutManager layoutManager;

	DatabaseReference profDatabaseReference = FirebaseDatabase.getInstance().getReference("profs");

	static private ArrayList<Professor> profList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		profList = SplashActivity.getProfList();

		profRecyclerView = (RecyclerView) findViewById(R.id.prof_recycler_view);

		// use this setting to improve performance if you know that changes
		// in content do not change the layout size of the RecyclerView
		profRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		layoutManager = new LinearLayoutManager(this);
		profRecyclerView.setLayoutManager(layoutManager);

		// specify an adapter (see also next example)
		profAdapter = new ProfAdapter(profList, getApplicationContext());
		profRecyclerView.setAdapter(profAdapter);

		profDatabaseReference.addChildEventListener(new ChildEventListener()
		{
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s)
			{
				Professor professor = dataSnapshot.getValue(Professor.class);
				UtilityMethods.updateProfList(profList, professor);

				profAdapter.notifyDataSetChanged();
			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s)
			{
				Professor professor = dataSnapshot.getValue(Professor.class);
				UtilityMethods.updateProfList(profList, professor);

				profAdapter.notifyDataSetChanged();
			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot)
			{
				Professor professor = dataSnapshot.getValue(Professor.class);
				UtilityMethods.removeFromProfList(profList, professor);

				profAdapter.notifyDataSetChanged();
			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s)
			{
				//ISDK
			}

			@Override
			public void onCancelled(DatabaseError databaseError)
			{
				// Failed to read value
				Log.w("DBError", "Failed to read value.", databaseError.toException());
			}
		});
	}

	@Override
	public void onBackPressed()
	{
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START))
		{
			drawer.closeDrawer(GravityCompat.START);
		} else
		{
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item)
	{
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_camera)
		{
			// Handle the camera action
		} else if (id == R.id.nav_gallery)
		{

		} else if (id == R.id.nav_slideshow)
		{

		} else if (id == R.id.nav_manage)
		{

		} else if (id == R.id.nav_share)
		{

		} else if (id == R.id.nav_send)
		{

		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	public static ArrayList<Professor> getProfList()
	{
		return profList;
	}

	public static void setProfList(ArrayList<Professor> profList)
	{
		MainActivity.profList = profList;
	}
}
