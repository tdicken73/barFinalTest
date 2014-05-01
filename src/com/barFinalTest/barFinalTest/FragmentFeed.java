package com.barFinalTest.barFinalTest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class FragmentFeed extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_feed, container, false);

		Button postButton = (Button) v.findViewById(R.id.postButton);
		final EditText postText = (EditText) v.findViewById(R.id.postText);
		ListView postList = (ListView) v.findViewById(R.id.postList);

		final List<String> myList = new ArrayList<String>();
		
		if(v.getContext()==null)System.out.println("-------------------------");
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(v
				.getContext(), android.R.layout.simple_list_item_1, myList);
		postList.setAdapter(adapter);

		postButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (postText.getText().toString() != "") {
					myList.add(postText.getText().toString());
					adapter.notifyDataSetChanged();
				}
			}
		});

		return v;
	}

	public static FragmentFeed newInstance() {

		FragmentFeed f = new FragmentFeed();

		return f;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

}
