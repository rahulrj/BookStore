package com.gridants.utils;


import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gridants.crossword.R;

public class ItemListBaseAdapter extends BaseAdapter {
	private static ArrayList<ItemDetails> itemDetailsArrayList;

	private LayoutInflater l_Inflater;

	public ItemListBaseAdapter(Context context, ArrayList<ItemDetails> results) {
		itemDetailsArrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itemDetailsArrayList.size();
	}

	public Object getItem(int position) {
		return itemDetailsArrayList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.listdetails, null);
			holder = new ViewHolder();
			holder.txt_itemName = (TextView) convertView
					.findViewById(R.id.name);
			holder.txt_author = (TextView) convertView
					.findViewById(R.id.author);
			holder.txt_itemPrice = (TextView) convertView
					.findViewById(R.id.price);
			holder.txt_originalPrice = (TextView) convertView
					.findViewById(R.id.price2);
			holder.txt_originalPrice.setPaintFlags(holder.txt_originalPrice
					.getPaintFlags()
					| Paint.STRIKE_THRU_TEXT_FLAG
					| Paint.ANTI_ALIAS_FLAG);
			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txt_itemName.setText(itemDetailsArrayList.get(position)
				.getName());
		holder.txt_author.setText(itemDetailsArrayList.get(position)
				.getAuthor());
		holder.txt_itemPrice.setText(itemDetailsArrayList.get(position)
				.getPrice());
		holder.txt_originalPrice.setText(itemDetailsArrayList.get(position)
				.getPrice2());
		
		
		Log.d("thge url for image is",String.valueOf(itemDetailsArrayList.get(position).getImageNumber()));
		
	//	Toast.makeText(convertView.getContext(),String.valueOf(itemDetailsArrayList.get(position).getImageNumber()),Toast.LENGTH_LONG).show();
		URL url = null;
		try {
			
			String str=itemDetailsArrayList.get(position).getImageNumber();
			url = new URL("http://crossword.gridants.webfactional.com/photos/"+(itemDetailsArrayList.get(position).getImageNumber().substring(str.lastIndexOf('/'), str.length())));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Toast.makeText(convertView.getContext(), url.toString(), Toast.LENGTH_LONG).show();
		Bitmap bmp = null;
		try {
			bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		holder.itemImage.setImageBitmap(bmp);

		//holder.itemImage.setImageResource(imgid[itemDetailsArrayList.get(
			//	position).getImageNumber() - 1]);
		// imageLoader.DisplayImage("http://192.168.1.28:8082/ANDROID/images/BEVE.jpeg",
		// holder.itemImage);

		return convertView;
	}

	static class ViewHolder {
		TextView txt_itemName;
		TextView txt_author;
		TextView txt_itemPrice;
		TextView txt_originalPrice;
		ImageView itemImage;
	}
}