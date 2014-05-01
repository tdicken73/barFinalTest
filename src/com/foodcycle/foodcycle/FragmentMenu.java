package com.foodcycle.foodcycle;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class FragmentMenu extends Fragment {

	View view;

	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View v = inflater.inflate(R.layout.fragment_menu, container, false);
	        
	       
	        //get the image view 
	        ImageView ib = (ImageView) v.findViewById(R.id.inventory_button);  
	        ib.setOnClickListener(new OnClickListener()
	        {
	             @Override
	             public void onClick(View v)
	             {
	                switch (v.getId()) {
		            case R.id.inventory_button:
		            	Intent i = new Intent();
		            	i.setClass(getActivity(), ShoppingList.class);
		            	startActivity(i);
		            	break;
	                } 
	             }
	        }); 
	        
	        ImageView rb = (ImageView) v.findViewById(R.id.recipes_button);  
	        rb.setOnClickListener(new OnClickListener()
	        {
	             @Override
	             public void onClick(View v)
	             {
	                switch (v.getId()) {
		            case R.id.recipes_button:
		            	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.allrecipes.com"));
		            	startActivity(browserIntent);
		            	break;
	                } 
	             }
	        }); 
	        
	        ImageView sb = (ImageView) v.findViewById(R.id.social_button);  
	        sb.setOnClickListener(new OnClickListener()
	        {
	             @Override
	             public void onClick(View v)
	             {
	                switch (v.getId()) {
		            case R.id.social_button:
		            	Toast.makeText(getActivity(), "Social feature not yet avaliable", Toast.LENGTH_SHORT).show();
		            	break;
	                } 
	             }
	        });
	        
	        ImageView social = (ImageView) v.findViewById(R.id.settings_button);  
	        social.setOnClickListener(new OnClickListener()
	        {
	             @Override
	             public void onClick(View v)
	             {
	                switch (v.getId()) {
		            case R.id.settings_button:
		            	Intent i = new Intent();
		            	i.setClass(getActivity(), Settings.class);
		            	startActivity(i);
		            	break;
	                } 
	             }
	        });
       return v;
   }


	 
	 public static FragmentMenu newInstance() {

	    	FragmentMenu f = new FragmentMenu();
	        
	        return f;
	    }
	 
	 
}

