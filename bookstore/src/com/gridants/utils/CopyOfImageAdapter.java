package com.gridants.utils;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gridants.crossword.R;
import com.gridants.utils.ItemListBaseAdapter.ViewHolder;

public class CopyOfImageAdapter extends BaseAdapter {
	private Context context;
	private final String[] titlearray;
	private final String[] originalpricearray;
	private final String[] offeredpricearray;
	private final Integer[] imageid;

	public CopyOfImageAdapter(Context context, String[] titlearray,
			String[] originalpricearray, String[] offeredpricearray,
			Integer[] imageid) {
		this.context = context;
		this.titlearray = titlearray;
		this.originalpricearray = originalpricearray;
		this.offeredpricearray = offeredpricearray;
		this.imageid = imageid;
	}

	
	
	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.viewitem, null);

			// set value into textview
			TextView title = (TextView) gridView.findViewById(R.id.title);
			title.setText(titlearray[position]);

			TextView originalprice = (TextView) gridView
					.findViewById(R.id.originalprice);
			originalprice.setText(originalpricearray[position]);

			originalprice.setPaintFlags(originalprice.getPaintFlags()
					| Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

			TextView offeredprice = (TextView) gridView
					.findViewById(R.id.offeredprice);
			offeredprice.setText(offeredpricearray[position]);

			ImageView image = (ImageView) gridView.findViewById(R.id.image);
			image.setImageResource(imageid[position]);

			// String mobile = mobileValues[position];

		} else {
			gridView = (View) convertView;
		}

		return gridView;
	}

	
	
	
	@Override
	public int getCount() {
		return titlearray.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
