package com.swiperefreshlayout_demo;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.swiperefreshlayout_demo.adapter.GridView_Recycler_Adapter;

public class GridLayout_Fragment extends Fragment {
	private static View view;
	private static RecyclerView gridRecyclerView;
	private static GridView_Recycler_Adapter adapter;
	private static ArrayList<Data_Model> gridArrayList;
	// Images int array from drawable folders
	private static final int[] images = { R.drawable.tajmahal,
			R.drawable.hawamahal, R.drawable.golden, R.drawable.shore,
			R.drawable.shivaji, R.drawable.lotus, R.drawable.victoria,
			R.drawable.brihadishwara, R.drawable.mahabodhi };

	// String array for title, location,year
	String[] getTitle, getLocation, getYear;

	private static SwipeRefreshLayout gridSwipeRefresh;

	public GridLayout_Fragment() {
		// Empty constructor is neccessary
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.gridlayout_fragment, container, false);
		init();
		populatRecyclerView();
		setSwipeRefreshView();
		return view;
	}

	// Initialize all variables and views
	private void init() {

		// Get the title , location and year from string.xml in array form
		getTitle = getActivity().getResources().getStringArray(R.array.title);
		getLocation = getActivity().getResources().getStringArray(
				R.array.location);
		getYear = getActivity().getResources().getStringArray(
				R.array.constructed_year);

		gridArrayList = new ArrayList<Data_Model>();

		// Find the id of recycler view
		gridRecyclerView = (RecyclerView) view
				.findViewById(R.id.grid_recyclerview);
		gridRecyclerView.setHasFixedSize(true);// set it fixed size
		// Set layout manager, we need grid layout manager here for gridview
		gridRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),
				2));// "2" denotes the no. of spancount i.e. no. of items in a
					// row and setting layout manager is neccessary

	}

	// Set refresh view method for setting refresh view
	private void setSwipeRefreshView() {
		gridSwipeRefresh = (SwipeRefreshLayout) view
				.findViewById(R.id.gridlayout_refreshview);

		// set color schemes on refresh view
		gridSwipeRefresh.setColorSchemeResources(R.color.blue, R.color.red,
				R.color.orange, R.color.pink, R.color.yellow, R.color.black,
				R.color.cyan);

		// implement refresh listener
		gridSwipeRefresh.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {

				// call this method for repopulating recycler view with new data
				refreshRecyclerView();
			}
		});
	}

	// Populate the listview with data
	private void populatRecyclerView() {

		for (int i = 0; i < getTitle.length; i++) {
			// add the items one by one in arraylist
			gridArrayList.add(new Data_Model(getTitle[i], getLocation[i],
					getYear[i], images[i]));
		}
		adapter = new GridView_Recycler_Adapter(getActivity(), gridArrayList);

		// set adapter over recyclerview
		gridRecyclerView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

	}

	// Random generator method this will generate int nos.
	private int RandomGenerator() {
		Random random = new Random();
		int randomNum = random.nextInt(9);

		return randomNum;
	}

	// Method for repopulating recycler view
	private void refreshRecyclerView() {

		// Handler to show refresh for a period of time you can use async task
		// while commnunicating serve
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				// Loop for 3 items
				for (int i = 0; i < 3; i++) {
					int value = RandomGenerator();// Random value

					// add random data to arraylist
					gridArrayList.add(new Data_Model(getTitle[value],
							getLocation[value], getYear[value], images[value]));
				}
				adapter.notifyDataSetChanged();// notify adapter
				gridSwipeRefresh.setRefreshing(false);// set swipe refreshing
														// false

				// Toast for task completion
				Toast.makeText(getActivity(), "Items refreshed.",
						Toast.LENGTH_SHORT).show();

			}
		}, 5000);
	}

}
