package com.barFinalTest.barFinalTest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

public class Settings extends FragmentActivity{
	
	EditText name;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.settings);
	 }
}
