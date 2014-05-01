package com.foodcycle.foodcycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentHungry extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		    View v = inflater.inflate(R.layout.fragment_hungry, container, false);

  
		    ImageView hb = (ImageView) v.findViewById(R.id.hungry_button);  
	        hb.setOnClickListener(new OnClickListener()
	        {
	             @Override
	             public void onClick(View v)
	             {
	                switch (v.getId()) {
		            case R.id.hungry_button:
		            	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:/m.allrecipes.com/recipes/1947/everyday-cooking/quick-and-easy"));
		            	startActivity(browserIntent);
		            	break;
	                } 
	             }
	        });
	        
	        ImageView bb = (ImageView) v.findViewById(R.id.imageViewBreakfast);  
	        bb.setOnClickListener(new OnClickListener()
	        {
	             @Override
	             public void onClick(View v)
	             {
	                switch (v.getId()) {
		            case R.id.imageViewBreakfast:
		            	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.allrecipes.com/recipes/78/breakfast-and-brunch"));
		            	startActivity(browserIntent);
		            	break;
	                } 
	             }
	        }); 
	        
	        ImageView lb = (ImageView) v.findViewById(R.id.imageViewLunch);  
	        lb.setOnClickListener(new OnClickListener()
	        {
	             @Override
	             public void onClick(View v)
	             {
	                switch (v.getId()) {
		            case R.id.imageViewLunch:
		            	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.allrecipes.com/"));
		            	startActivity(browserIntent);
		            	break;
	                } 
	             }
	        }); ImageView db = (ImageView) v.findViewById(R.id.imageViewDinner);  
	        db.setOnClickListener(new OnClickListener()
	        {
	             @Override
	             public void onClick(View v)
	             {
	                switch (v.getId()) {
		            case R.id.imageViewDinner:
		            	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.allrecipes.com/recipes/80/main-dish"));
		            	startActivity(browserIntent);
		            	break;
	                } 
	             }
	        }); ImageView sb = (ImageView) v.findViewById(R.id.imageViewSnacks);  
	        sb.setOnClickListener(new OnClickListener()
	        {
	             @Override
	             public void onClick(View v)
	             {
	                switch (v.getId()) {
		            case R.id.imageViewSnacks:
		            	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.allrecipes.com/recipes/76/appetizers-and-snacks"));
		            	startActivity(browserIntent);
		            	break;
	                } 
	             }
	        });

		    return v;
		}

		public static FragmentHungry newInstance() {

			FragmentHungry f = new FragmentHungry();


		    return f;
		}
}
