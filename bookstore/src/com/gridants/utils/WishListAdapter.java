package com.gridants.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gridants.crossword.R;

public class WishListAdapter extends BaseAdapter {
	private static ArrayList<ItemDetails> itemDetailsArrayList;

	private Integer[] imgid = { R.drawable.rsz_book1, R.drawable.rsz_book2,
			R.drawable.rsz_book3, R.drawable.rsz_book4, R.drawable.rsz_book5,
			R.drawable.rsz_book6, R.drawable.rsz_book7, R.drawable.rsz_book8,
			R.drawable.rsz_book9, R.drawable.rsz_book10 };

	private LayoutInflater l_Inflater;

	public WishListAdapter(Context context, ArrayList<ItemDetails> results) {
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
			convertView = l_Inflater.inflate(R.layout.wishlist_item, null);
			holder = new ViewHolder();

			holder.txt_itemName = (TextView) convertView
					.findViewById(R.id.name);
			holder.txt_author = (TextView) convertView
					.findViewById(R.id.author);

			holder.itemImage = (ImageView) convertView.findViewById(R.id.photo);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txt_itemName.setText(itemDetailsArrayList.get(position)
				.getName());
		holder.txt_author.setText(itemDetailsArrayList.get(position)
				.getAuthor());
		
		
		URL url = null;
		try {
			url = new URL("http://crossword.gridants.webfactional.com/photos/"+(itemDetailsArrayList.get(position).getImageNumber().substring(8)));
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