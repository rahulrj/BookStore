package com.gridants.utils;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gridants.crossword.R;

public class MobileArrayAdapter extends ArrayAdapter {
	private final Activity activity;
	private final List reviews;

	public MobileArrayAdapter(Activity activity, List objects) {
		super(activity, R.layout.reviews, objects);
		this.activity = activity;
		this.reviews = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ReviewobjectView revobjView = null;

		if (rowView == null) {
			// Get a new instance of the row layout view
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.reviews, null);

			// Hold the view objects in an object,
			// so they don't need to be re-fetched
			revobjView = new ReviewobjectView();
			revobjView.name = (TextView) rowView.findViewById(R.id.textView1);
			revobjView.date = (TextView) rowView.findViewById(R.id.textView3);
			revobjView.body = (TextView) rowView.findViewById(R.id.textView4);
			revobjView.followers = (TextView) rowView
					.findViewById(R.id.textView5);

			// Cache the view objects in the tag,
			// so they can be re-accessed later
			rowView.setTag(revobjView);
		} else {
			revobjView = (ReviewobjectView) rowView.getTag();
		}

		// Transfer the stock data from the data object
		// to the view objects
		Reviewobject currentReview = (Reviewobject) reviews.get(position);
		revobjView.name.setText(currentReview.getname());
		revobjView.date.setText(currentReview.getdate());
		revobjView.body.setText(currentReview.getbody());
		revobjView.followers.setText(currentReview.getfollowers()
				+ " people follow this");

		return rowView;
	}

	protected static class ReviewobjectView {
		protected TextView name;
		protected TextView date;
		protected TextView body;
		protected TextView followers;
	}
}

/*
 * public class MobileArrayAdapter extends ArrayAdapter<String> { private final
 * Context context; private final Reviewobject[] values;
 * 
 * 
 * public MobileArrayAdapter(Context context, Reviewobject[] values) {
 * super(context, R.layout.reviews, values); this.context = context; this.values
 * = values; }
 * 
 * @Override public View getView(int position, View convertView, ViewGroup
 * parent) { LayoutInflater inflater = (LayoutInflater) context
 * .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 * 
 * View rowView = inflater.inflate(R.layout.reviews, parent, false); TextView
 * name = (TextView) rowView.findViewById(R.id.textView1); TextView date =
 * (TextView) rowView.findViewById(R.id.textView3); TextView body = (TextView)
 * rowView.findViewById(R.id.textView4); TextView followers = (TextView)
 * rowView.findViewById(R.id.textView5); name.setText(values[position].name);
 * date.setText(date[position]); body.setText(body[position]);
 * followers.setText(followers[position]);
 * 
 * return rowView; } }
 */