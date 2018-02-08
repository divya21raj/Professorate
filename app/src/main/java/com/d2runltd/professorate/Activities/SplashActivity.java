package com.d2runltd.professorate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.d2runltd.professorate.Professor;
import com.d2runltd.professorate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity
{
	private static ArrayList<Professor> profList;

	DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("profs");

	private TaskCompletionSource<DataSnapshot> profDBSource = new TaskCompletionSource<>();
	private Task profDBTask = profDBSource.getTask();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		profList = new ArrayList<>();

		databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
		{
			@Override
			public void onDataChange(DataSnapshot dataSnapshot)
			{
				profDBSource.setResult(dataSnapshot);
			}

			@Override
			public void onCancelled(DatabaseError databaseError)
			{
				// Failed to read value
				Log.w("DBError", "Failed to read value.", databaseError.toException());
			}
		});

		profDBTask.addOnCompleteListener(new OnCompleteListener()
		{
			@Override
			public void onComplete(@NonNull Task task)
			{
				DataSnapshot profData = (DataSnapshot) profDBTask.getResult();

				for(DataSnapshot snap: profData.getChildren())
				{
					Professor professor = snap.getValue(Professor.class);
					profList.add(professor);
				}

				finish();
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
			}
		});
	}

	public static ArrayList<Professor> getProfList()
	{
		return profList;
	}
}
