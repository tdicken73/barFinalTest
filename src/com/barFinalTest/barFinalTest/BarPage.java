package com.barFinalTest.barFinalTest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class BarPage extends FragmentActivity  {

	//private static final String KEY_POSITION="position";
	//private int position=-1;

	ListView lv;
	ArrayAdapter<String> arrayAdapter;

	//SwipeDismissListViewTouchListener touchListener;
	
	ArrayList<String> shoppingList = new ArrayList<String>();
	final String KEY_LIST = "LIST_";
	int shoppingListSize;
	String temp;
	
//	static String getTitle(Context ctxt, int position) {
//		return(String.format(ctxt.getString(R.string.inventory), position + 1));
//	}
	
	//saving arrayList!
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		for (int i = 0; i < shoppingListSize; i++)
		{
			savedInstanceState.putString(KEY_LIST + Integer.toString(i), shoppingList.get(i));
			Log.d("save: " + KEY_LIST + Integer.toString(i), shoppingList.get(i));
		}
		
		super.onSaveInstanceState(savedInstanceState);
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null)
		{
			for (int i = 0; i < shoppingListSize; i++)
			{
				shoppingList.add(savedInstanceState.getString(KEY_LIST+Integer.toString(i)));
				//savedInstanceState.putString(KEY_LIST + Integer.toString(i), shoppingList.get(i));
			}
		}
		
		setContentView(R.layout.barpage);
		
		lv = (ListView) findViewById(R.id.ListView2);
		
		//add in shopping list items from intent
		if (getIntent().hasExtra("ADD_TO_SHOPPING_LIST"))
		{
			temp = getIntent().getExtras().getString("ADD_TO_SHOPPING_LIST");
			Log.d("intentPayload", temp);
			shoppingList.add(temp);
		}
		
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shoppingList);
		
		lv.setAdapter(arrayAdapter);
//		
//		if (shoppingList.size() == 0)
//			shoppingList.add("No items in shopping list!");
		
		//SetListViewAdapter();
		//shoppingList.add("LARRY");
		
		
		
		
		
//		lv.setOnItemClickListener(new OnItemClickListener()
//		{
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//			{
//				int itemPosition = position;
//				String itemValue = (String)lv.getItemAtPosition(position);
//			}
//		});
		
		Log.d("HERE!", "----");
		//get the image view 
		//ImageButton ib = (ImageButton) findViewById(R.id.action_add);
		
//		ib.setOnClickListener(new OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				switch (v.getId()) {
//				case R.id.action_add:
//					Intent ai = new Intent();
//					ai.setClass(getApplicationContext(), AddShopList.class);
//					startActivityForResult(ai,0);
//
//					break;
//				case R.id.action_minus:
//					Intent ri = new Intent();
//					ri.setClass(getApplicationContext(), RemoveShopList.class);
//					startActivity(ri);
//				} 
//			}
//		});
//
//		ImageButton rib = (ImageButton) findViewById(R.id.action_minus);  
//		rib.setOnClickListener(new OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				switch (v.getId()) {
//				case R.id.action_minus:
//					Intent ri = new Intent();
//					ri.setClass(getApplicationContext(), RemoveShopList.class);
//					startActivity(ri);
//					break;
//				} 
//			}
//		});



//		if ((position % 2)==0) {
//			setHasOptionsMenu(true);
//		}
//
//		return v;
	}

//	private void SetListViewAdapter() {
//		//List<String> all = shoppingList;
//				
//		if(shoppingList.size()>0) // check if list contains items..
//		{    
//
//			arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, shoppingList);
//			lv.setAdapter(arrayAdapter);;
//
//
//			// Set up ListView example
//			int i = shoppingList.size();
//			String[] items = new String[i];
//
//			touchListener =
//					new SwipeDismissListViewTouchListener(
//							lv,
//							new SwipeDismissListViewTouchListener.DismissCallbacks() {
//								@Override
//								public boolean canDismiss(int position) {
//									return true;
//								}
//								
//								@Override
//								public void onDismiss(ListView listView, int[] reverseSortedPositions) {
//									for (int position : reverseSortedPositions) {
//										swipeFlagged(position);
//										Log.d("Swipe!", "AWW YEAH");
//									}
//									arrayAdapter.notifyDataSetChanged();
//								}
//							});
//			lv.setOnTouchListener(touchListener);
//			// Setting this scroll listener is required to ensure that during ListView scrolling,
//			// we don't look for swipes.
//			lv.setOnScrollListener(touchListener.makeScrollListener());
//
//					// Set up ViewGroup
//					final ViewGroup dismissableContainer = (ViewGroup) findViewById(R.id.dismissable_container);
//					for (int i1 = 0; i1 < items.length; i1++) {
//						final Button dismissableButton = new Button(getApplicationContext());
//						dismissableButton.setLayoutParams(new ViewGroup.LayoutParams(
//								ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//						dismissableButton.setOnClickListener(new View.OnClickListener() {
//							@Override
//							public void onClick(View view) {
//								Toast.makeText(getApplicationContext(), "Item Removed", Toast.LENGTH_SHORT).show();
//							}
//						});
//						// Created swipe-to-dismiss touch listener.
//						dismissableButton.setOnTouchListener(new SwipeDismissTouchListener(
//								dismissableButton,
//								null,
//								new SwipeDismissTouchListener.DismissCallbacks() {
//									@Override
//									public boolean canDismiss(Object token) {
//										return true;
//									}
//
//									@Override
//									public void onDismiss(View view, Object token) {
//										dismissableContainer.removeView(dismissableButton);
//									}
//								}));
//
//						dismissableContainer.addView(dismissableButton);
//					}
//
//		}  
//		
//	}

//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//		inflater.inflate(R.menu.activity_scan, menu);
//
//		super.onCreateOptionsMenu(menu, inflater);
//	}

//	protected void onListItemClick(ListView listView, View view, int position, long id) {
//
//		Toast.makeText(getApplicationContext(), "Item Removed", Toast.LENGTH_SHORT).show();
//	}
//
//
//	public static ShoppingList newInstance() 
//	{	
//		ShoppingList f = new ShoppingList();
//		return f;
//	}
//	
//	public void swipeFlagged(int pos)
//	{
//		String tStr;
//		tStr = arrayAdapter.getItem(pos);
//		String[] aStr = tStr.split("\\s");
//		
//		if (touchListener.getDirection() == "right")
//		{
//			//right = shopping list
//			this.shoppingList.add(aStr[0]);
//		}
//		else
//		{
//			//left = deletion
//			arrayAdapter.remove(arrayAdapter.getItem(pos));
//			Log.d("Swipe LEFT!", "AWW YEAH");
//		}
//	}
	
	public ArrayList<String> updatedShoppingList()
	{
		return this.shoppingList;
	}

}