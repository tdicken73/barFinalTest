package com.barFinalTest.barFinalTest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MyBar extends Fragment {

	View v;
	private static final String KEY_POSITION = "position";
	private int position = -1;

	MyBarDatabase db;
	ListView lv;

	ArrayAdapter<String> arrayAdapter;

	SwipeDismissListViewTouchListener touchListener;

	ArrayList<String> shoppingList = new ArrayList<String>();

	static String getTitle(Context ctxt, int position) {
		return (String.format(ctxt.getString(R.string.mybar), position + 1));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		v = inflater.inflate(R.layout.mybar, container, false);
		lv = (ListView) v.findViewById(R.id.ListView1);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Bar bar = db.getMyBar((String) arg0.getItemAtPosition(arg2));
				String website = bar.getWebsite();
				if (!website.startsWith("http://")
						&& !website.startsWith("https://"))
					website = "http://" + website;
				System.out.println("-------------"+website);
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(website));
				startActivity(browserIntent);

			}

		});

		// get the image view
		ImageButton ib = (ImageButton) v.findViewById(R.id.action_add);
		ib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.action_add:
					Intent ai = new Intent();
					ai.setClass(getActivity(), AddMyBar.class);
					startActivityForResult(ai, 0);

					break;
				case R.id.action_minus:
					Intent ri = new Intent();
					ri.setClass(getActivity(), RemoveMyBar.class);
					startActivity(ri);
				}
			}
		});

		ImageButton rib = (ImageButton) v.findViewById(R.id.action_minus);
		rib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.action_minus:
					Intent ri = new Intent();
					ri.setClass(getActivity(), RemoveMyBar.class);
					startActivity(ri);
					break;
				}
			}
		});

		if ((position % 2) == 0) {
			setHasOptionsMenu(true);
		}

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getView();
		SetListViewAdapter();

	}

	private void SetListViewAdapter() {
		db = new MyBarDatabase(getActivity());
		List<String> all = db.getAllMyBarStrings();

		if (all.size() > 0) // check if list contains items..
		{

			arrayAdapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, all);
			lv.setAdapter(arrayAdapter);
			;

			// Set up ListView example
			int i = all.size();
			String[] items = new String[i];

			touchListener = new SwipeDismissListViewTouchListener(lv,
					new SwipeDismissListViewTouchListener.DismissCallbacks() {
						@Override
						public boolean canDismiss(int position) {
							return true;
						}

						@Override
						public void onDismiss(ListView listView,
								int[] reverseSortedPositions) {
							for (int position : reverseSortedPositions) {
								swipeFlagged(position);
								Log.d("Swipe!", "AWW YEAH");
							}
							arrayAdapter.notifyDataSetChanged();
						}
					});
			lv.setOnTouchListener(touchListener);
			// Setting this scroll listener is required to ensure that during
			// ListView scrolling,
			// we don't look for swipes.
			lv.setOnScrollListener(touchListener.makeScrollListener());

			// Set up ViewGroup
			final ViewGroup dismissableContainer = (ViewGroup) v
					.findViewById(R.id.dismissable_container);
			for (int i1 = 0; i1 < items.length; i1++) {
				final Button dismissableButton = new Button(getActivity());
				dismissableButton.setLayoutParams(new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));

				dismissableButton
						.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								Toast.makeText(getActivity(), "Item Removed",
										Toast.LENGTH_SHORT).show();
							}
						});
				// Created swipe-to-dismiss touch listener.
				dismissableButton
						.setOnTouchListener(new SwipeDismissTouchListener(
								dismissableButton,
								null,
								new SwipeDismissTouchListener.DismissCallbacks() {
									@Override
									public boolean canDismiss(Object token) {
										return true;
									}

									@Override
									public void onDismiss(View view,
											Object token) {
										dismissableContainer
												.removeView(dismissableButton);
									}
								}));

				dismissableContainer.addView(dismissableButton);
			}

		}

		db.close();
	}


	protected void onListItemClick(ListView listView, View view, int position,
			long id) {

		Toast.makeText(getActivity(), "Item Removed", Toast.LENGTH_SHORT)
				.show();
	}

	public static MyBar newInstance() {
		MyBar f = new MyBar();
		return f;
	}

	public void swipeFlagged(int pos) {
		Bar temp;
		String tStr;
		tStr = arrayAdapter.getItem(pos);
		String[] aStr = tStr.split("\\s");

		if (touchListener.getDirection() == "right") {
			// right = shopping list
			// this.shoppingList.add(aStr[0]);
			Intent intent = new Intent(getActivity(), BarPage.class);
			intent.putExtra("ADD_TO_SHOPPING_LIST", aStr[0]);
			startActivity(intent);
		} else {
			// left = deletion
			temp = db.getMyBar(aStr[0]);
			arrayAdapter.remove(arrayAdapter.getItem(pos));
			db.deleteMyBar(temp);
			Log.d("Swipe LEFT!", "AWW YEAH");
		}
	}

	public ArrayList<String> getShoppingList() {
		return this.shoppingList;
	}

}
