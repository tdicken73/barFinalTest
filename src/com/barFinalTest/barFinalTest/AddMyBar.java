package com.barFinalTest.barFinalTest;


import com.barFinalTest.barFinalTest.MainActivity.SectionsPagerAdapter;

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

public class AddMyBar extends FragmentActivity 
{
	MyBarDatabase db;
	Bar bar;
	//Food test;
	int id;
	Button mButton;
	EditText mEdit1; //Name of Bar
	EditText mEdit2; //Type of Bar

	public String url = "http://api.upcdatabase.org/json/b2745fe37892074199c784546f5f053f/";
	private ProgressDialog pDialog;


	public AddMyBar()
	{
		super();
		//db.getWritableDatabase();
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_mybar);

		bar = new Bar();
		//test = new Food();
		//These two lines crash
		//food.setCount(0); 


		db = new MyBarDatabase(this);

		//DEBUG
		Log.e("BARHOPPER", "MyDatabase!");

		mButton = (Button)findViewById(R.id.add_submit);
		mEdit1 = (EditText)findViewById(R.id.addNameField);
		mEdit2 = (EditText)findViewById(R.id.addWebsiteField);

		mButton.setOnClickListener(
				new View.OnClickListener()
				{
					public void onClick(View view)
					{

						
						if ((isEmpty(mEdit1) == false) && (isEmpty(mEdit2) == false)) {
							String n = mEdit1.getText().toString();
							String t = mEdit2.getText().toString();
							bar.setName(n);
							bar.setWebsite(t);
							bar.getId(); 
							db.addMyBar(bar);
							Intent intent = new Intent(getApplicationContext(), MainActivity.class);
							startActivity(intent);


						} else {
							Toast.makeText(getApplicationContext(), "Fields are Empty!!", Toast.LENGTH_SHORT).show();
						}

						//test = db.getFood(1);

						//ImageView imageView = (ImageView) view;
						//imageView.setContentDescription(test.getType());

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
