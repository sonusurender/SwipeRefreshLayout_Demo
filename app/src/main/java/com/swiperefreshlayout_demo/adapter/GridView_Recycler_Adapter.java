package com.swiperefreshlayout_demo.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.swiperefreshlayout_demo.Data_Model;
import com.swiperefreshlayout_demo.R;
import com.swiperefreshlayout_demo.holders.GridView_Holder;
import com.swiperefreshlayout_demo.holders.RecyclerView_OnClickListener.OnClickListener;

public class GridView_Recycler_Adapter extends
		RecyclerView.Adapter<GridView_Holder> { // Recyclerview will always
												// extend to recyclerview
												// adapter
	private ArrayList<Data_Model> arrayList;
	private Context context;

	public GridView_Recycler_Adapter(Context context,
			ArrayList<Data_Model> arrayList) {
		this.context = context;
		this.arrayList = arrayList;

	}

	@Override
	public int getItemCount() {
		return (null != arrayList ? arrayList.size() : 0);

	}

	@Override
	public void onBindViewHolder(GridView_Holder holder, int position) {

		// Now in this method the items will set and click listener will occur
		final Data_Model model = arrayList.get(position);

		GridView_Holder gridHolder = (GridView_Holder) holder;// Holder

		Bitmap image = BitmapFactory.decodeResource(context.getResources(),
				model.getImage());// Converting drawable into bitmap

		// setting data over views
		gridHolder.grid_title.setText(model.getTitle());
		gridHolder.grid_location.setText(model.getLocation());
		gridHolder.grid_date.setText(model.getYear());
		gridHolder.grid_imageView.setImageBitmap(image);

		// Implementing click listener
		gridHolder.setClickListener(new OnClickListener() {

			@Override
			public void OnItemClick(View view, int position) {
				switch (view.getId()) {

				case R.id.grid_layout:

					// Toast will shown when layout is clicked
					Toast.makeText(context,
							"You have clicked " + model.getTitle(),
							Toast.LENGTH_LONG).show();
					break;

				}

			}

		});

	}

	@Override
	public GridView_Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

		// This method will inflate the layout and return as viewholder
		LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

		ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
				R.layout.grid_customview, viewGroup, false);
		GridView_Holder gridHolder = new GridView_Holder(mainGroup);
		return gridHolder;

	}

}
