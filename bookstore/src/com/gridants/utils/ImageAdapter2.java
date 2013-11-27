package com.gridants.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gridants.crossword.R;

public class ImageAdapter2 extends BaseAdapter {
	private Context context;
	private final String[] titlearray;
	private final String[] originalpricearray;
	private final String[] offeredpricearray;
	private final String[] imageid;
	
	private String[] desc;

	public ImageAdapter2(Context context, String[] titlearray,
			String[] originalpricearray, String[] offeredpricearray,
		String[] imageid,String desc[]) {
		this.context = context;
		this.titlearray = titlearray;
		this.originalpricearray = originalpricearray;
		this.offeredpricearray = offeredpricearray;
		this.imageid = imageid;
		this.desc=desc;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);

			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.viewitem2, null);

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
			//image.setImageResource(imageid[position]);
			
			
			URL url = null;
			try {
				url = new URL("http://crossword.gridants.webfactional.com/photos/".concat((imageid[position].substring(8))));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Bitmap bmp = null;
			try {
				bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			image.setImageBitmap(bmp);



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
