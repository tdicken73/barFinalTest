package com.foodcycle.foodcycle;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.foodcycle.foodcycle.MainActivity.SectionsPagerAdapter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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

public class AddShopList extends FragmentActivity 
{
	//MySLDatabase db;
	//FoodSL food;
	//FoodSL test;
	//int id;
	Button mButton;
	EditText mEdit1; //Name of food
	EditText mEdit2; //Count
	ArrayList<String> shoppingList;

	public String url = "http://api.upcdatabase.org/json/b2745fe37892074199c784546f5f053f/";
	private ProgressDialog pDialog;


	public AddShopList()
	{
		super();
		//db.getWritableDatabase();
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_inventory);

		//food = new FoodSL();
		//test = new FoodSL();
		//These two lines crash
		//food.setCount(0); 


		//db = new MySLDatabase(this);

		//DEBUG
		Log.e("FOODCYLCE", "MySLDatabase!");

		mButton = (Button)findViewById(R.id.add_submit);
		mEdit1 = (EditText)findViewById(R.id.addTypeField);
		mEdit2 = (EditText)findViewById(R.id.addCountField);

		mButton.setOnClickListener(
				new View.OnClickListener()
				{
					public void onClick(View view)
					{

						
						if ((isEmpty(mEdit1) == false) && (isEmpty(mEdit2) == false)) {
							String entry = mEdit1.getText().toString();
							int amount = Integer.parseInt(mEdit2.getText().toString());
							//food.setType(entry);
							//food.setCount(amount + food.getCount());
							//food.getId(); 
							//db.addFoodSL(food);
							shoppingList.add(entry);
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


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_scan, menu);

		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_camera:
			//scan
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	public class GetContacts extends AsyncTask<Void, Void, Void> {
		public String response;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(AddShopList.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					//JSONObject jsonObj = new JSONObject(jsonStr);
					JSONObject jsonObj = ((JSONObject)new JSONObject(jsonStr));
					response = jsonObj.getString("itemname");

				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}



		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data
			 * */
			mEdit1.setText(response);
			mEdit2.setText("1");



		}

	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve scan result
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanningResult != null) {
			//we have a result
			String scanContent = scanningResult.getContents();
			url = url + scanContent;
			new GetContacts().execute();
		}

		else{
			Toast toast = Toast.makeText(getApplicationContext(), 
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}

	}

	public boolean isEmpty(EditText mInput){
		if(mInput.length() == 0)
			return true;
		else
			return false;
	}
}
