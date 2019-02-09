package com.swiperefreshlayout_demo;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.swiperefreshlayout_demo.adapter.ListView_Recycler_Adapter;

public class LinearLayout_Fragment extends Fragment {
	private static View view;
	private static RecyclerView listRecyclerView;
	private static ArrayList<Data_Model> listArrayList;
	private static ListView_Recycler_Adapter adapter;

	// Images array for images
	private static final int[] images = { R.drawable.tajmahal,
			R.drawable.hawamahal, R.drawable.golden, R.drawable.shore,
			R.drawable.shivaji, R.drawable.lotus, R.drawable.victoria,
			R.drawable.brihadishwara, R.drawable.mahabodhi };

	// String array for title, location, year
	String[] getTitle, getLocation, getYear;

	private static SwipeRefreshLayout listSwipeRefresh;

	public LinearLayout_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.linearlayout_fragment, container,
				false);
		init();
		populatRecyclerView();
		setSwipeRefreshView();
		return view;
	}

	// Initialize the view
	private void init() {

		// Getting the string array from strings.xml
		getTitle = getActivity().getResources().getStringArray(R.array.title);
		getLocation = getActivity().getResources().getStringArray(
				R.array.location);
		getYear = getActivity().getResources().getStringArray(
				R.array.constructed_year);
		listArrayList = new ArrayList<Data_Model>();

		listRecyclerView = (RecyclerView) view
				.findViewById(R.id.linear_recyclerview);
		listRecyclerView.setHasFixedSize(true);
		listRecyclerView
				.setLayoutManager(new LinearLayoutManager(getActivity()));// for
																			// linear
																			// data
																			// display
																			// we
																			// use
																			// linear
																			// layoutmanager

	}

	// Set swipe refresh method
	private void setSwipeRefreshView() {

		// Find the id of refresh view
		listSwipeRefresh = (SwipeRefreshLayout) view
				.findViewById(R.id.linearlayout_refreshview);

		// set color schemes
		listSwipeRefresh.setColorSchemeResources(R.color.blue, R.color.red,
				R.color.orange, R.color.pink, R.color.yellow, R.color.black,
				R.color.cyan);

		// finally implement refresh listener
		listSwipeRefresh.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {

				// repopulate recyclerview with new data
				refreshRecyclerView();
			}
		});
	}

	// populate the list view by adding data to arraylist
	private void populatRecyclerView() {

		for (int i = 0; i < getTitle.length; i++) {
			listArrayList.add(new Data_Model(getTitle[i], getLocation[i],
					getYear[i], images[i]));
		}
		adapter = new ListView_Recycler_Adapter(getActivity(), listArrayList);
		listRecyclerView.setAdapter(adapter);// set adapter on recyclerview
		adapter.notifyDataSetChanged();// Notify the adapter

	}

	// random generator method for generating data in int nos.
	private int RandomGenerator() {
		Random random = new Random();
		int randomNum = random.nextInt(9);

		return randomNum;
	}

	// Repopulate recyclerview method
	private void refreshRecyclerView() {

		// Handler to show refresh for a period of time you can use async task
		// while commnunicating server
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				// Add 3 datas
				for (int i = 0; i < 3; i++) {
					int value = RandomGenerator();// random value

					// add random data to arraylist
					listArrayList.add(new Data_Model(getTitle[value],
							getLocation[value], getYear[value], images[value]));
				}
				adapter.notifyDataSetChanged();// notify adapter
				listSwipeRefresh.setRefreshing(false);// set refreshing false

				// Toast for task is completed
				Toast.makeText(getActivity(), "Items refreshed.",
						Toast.LENGTH_SHORT).show();

			}
		}, 5000);
	}
}
