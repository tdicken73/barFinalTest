package com.barFinalTest.barFinalTest;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RemoveMyBar extends FragmentActivity {

	MyBarDatabase db;
	Bar bar;
	Bar test;
	int id;
	Button mButton;
	EditText mEdit1; //Name of food

	public String url = "http://api.upcdatabase.org/json/b2745fe37892074199c784546f5f053f/";
	private ProgressDialog pDialog;


	public RemoveMyBar()
	{
		super();
		//db.getWritableDatabase();
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remove_mybar);

		bar = new Bar();
		//These two lines crash
		//food.setCount(0);
		//food.setId(id); 

		db = new MyBarDatabase(this);

		//DEBUG
		//Log.e("FOODCYLCE", "MyDatabase!");

		mButton = (Button)findViewById(R.id.remove_submit);
		mEdit1 = (EditText)findViewById(R.id.removeNameField);


		mButton.setOnClickListener(
				new View.OnClickListener()
				{
					public void onClick(View view)
					{
						if ((isEmpty(mEdit1) == false)) {
							String entry = mEdit1.getText().toString();

							bar = db.getMyBar(entry);
											
							Log.d("BAR SET", bar.toString());
							db.deleteMyBar(bar);	
							
							Intent intent = new Intent(getApplicationContext(), MainActivity.class);
							startActivity(intent);	

						} else {
							Toast.makeText(getApplicationContext(), "Fields are Empty!!", Toast.LENGTH_SHORT).show();
						}


						//Log.v("EditText", mEdit1.getText().toString());
					}
				});


		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}	


	public boolean isEmpty(EditText mInput){
		if(mInput.length() == 0)
			return true;
		else
			return false;
	}
}

