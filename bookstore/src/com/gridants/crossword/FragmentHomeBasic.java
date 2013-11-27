package com.gridants.crossword;

import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.internal.view.View_HasStateListenerSupport;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FragmentHomeBasic extends SherlockFragment implements
		View.OnClickListener {

	String authorhome[], titlehome[], Pricehome[], Price2home[], deschome[];

	String authorhomenew[], titlehomenew[], Pricehomenew[], deschomenew[],
			Price2homenew[];
	int imagehome[];
	int imagehomenew[];

	URL urlhome[], urlhomenew[];
	
	
	
	
	String[] imageUrlhome,imageUrlhomenew;
	public static String api_url = "http://crossword.gridants.webfactional.com/art/";
	JsonArray arrayhome;
	JsonArray arrayhomenew;
	Bitmap bmp1, bmp2, bmp3, bmp4, bmp5, bmp6 = null;
	RelativeLayout recommended;
	RelativeLayout new_arrivals;
	
	
	
	
	
	
	ImageView reccom1, reccom2, reccom3, newarr1, newarr2, newarr3;
	
	
	
	
	TextView reccom_title1, reccom_title2, reccom_title3, newarr_title1,
			newarr_title2, newarr_title3;
	
	
	
	TextView reccom_price_original1, reccom_price_original2,
			reccom_price_original3, newarr_price_original1,
			newarr_price_original2, newarr_price_original3;
	
	
	
	
	TextView reccom_price_offered1, reccom_price_offered2,
			reccom_price_offered3, newarr_price_offered1,
			newarr_price_offered2, newarr_price_offered3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater
				.inflate(R.layout.fragment2, container, false);
		
		Toast.makeText(view.getContext(), "bb", Toast.LENGTH_LONG).show();

		func();
		
		try
		{
			
		

		urlhome = new URL[arrayhome.size()];
		for (int i = 0; i < arrayhome.size(); i++)
			try {
				urlhome[i] = new URL(
						"http://crossword.gridants.webfactional.com/photos/"+imageUrlhome[i].substring(8)
								);
				Log.d("urlhome", urlhome[i].toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		

		recommended = (RelativeLayout) view.findViewById(R.id.more1);
		new_arrivals = (RelativeLayout) view.findViewById(R.id.more2);

		recommended.setOnClickListener(this);
		new_arrivals.setOnClickListener(this);

		reccom1 = (ImageView) view.findViewById(R.id.reccom_pic1);
		reccom2 = (ImageView) view.findViewById(R.id.reccom_pic2);
		reccom3 = (ImageView) view.findViewById(R.id.reccom_pic3);
		newarr1 = (ImageView) view.findViewById(R.id.newarr_pic1);
		newarr2 = (ImageView) view.findViewById(R.id.newarr_pic2);
		newarr3 = (ImageView) view.findViewById(R.id.newarr_pic3);

		try {
			bmp1 = BitmapFactory.decodeStream(urlhome[0].openConnection()
					.getInputStream());
			bmp2 = BitmapFactory.decodeStream(urlhome[1].openConnection()
					.getInputStream());
			bmp3 = BitmapFactory.decodeStream(urlhome[2].openConnection()
					.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reccom1.setImageBitmap(bmp1);
		reccom2.setImageBitmap(bmp2);
		reccom3.setImageBitmap(bmp3);

		func2();

		urlhomenew = new URL[arrayhomenew.size()];
		for (int i = 0; i < arrayhomenew.size(); i++)
			try {
				urlhomenew[i] = new URL(
						"http://crossword.gridants.webfactional.com/photos/"+imageUrlhomenew[i].substring(8));
				Log.d("urlhomenew", urlhomenew[i].toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		try {
			bmp4 = BitmapFactory.decodeStream(urlhomenew[0].openConnection()
					.getInputStream());
			bmp5 = BitmapFactory.decodeStream(urlhomenew[1].openConnection()
					.getInputStream());
			bmp6 = BitmapFactory.decodeStream(urlhomenew[2].openConnection()
					.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newarr1.setImageBitmap(bmp4);
		newarr2.setImageBitmap(bmp5);
		newarr3.setImageBitmap(bmp6);

		// set image resource to load images from database

		reccom1.setOnClickListener(this);
		reccom2.setOnClickListener(this);
		reccom3.setOnClickListener(this);
		newarr1.setOnClickListener(this);
		newarr2.setOnClickListener(this);
		newarr3.setOnClickListener(this);

		reccom_title1 = ((TextView) view.findViewById(R.id.reccom_title1));
		reccom_title2 = (TextView) view.findViewById(R.id.reccom_title2);
		reccom_title3 = (TextView) view.findViewById(R.id.reccom_title3);
		newarr_title1 = (TextView) view.findViewById(R.id.newarr_title1);
		newarr_title2 = (TextView) view.findViewById(R.id.newarr_title2);
		newarr_title3 = (TextView) view.findViewById(R.id.newarr_title3);

		reccom_price_original1 = (TextView) view
				.findViewById(R.id.reccom_price_original1);
		reccom_price_original2 = (TextView) view
				.findViewById(R.id.reccom_price_original2);
		reccom_price_original3 = (TextView) view
				.findViewById(R.id.reccom_price_original3);
		newarr_price_original1 = (TextView) view
				.findViewById(R.id.newarr_price_original1);
		newarr_price_original2 = (TextView) view
				.findViewById(R.id.newarr_price_original2);
		newarr_price_original3 = (TextView) view
				.findViewById(R.id.newarr_price_original3);

		reccom_price_offered1 = (TextView) view
				.findViewById(R.id.reccom_price_offered1);
		reccom_price_offered2 = (TextView) view
				.findViewById(R.id.reccom_price_offered2);
		reccom_price_offered3 = (TextView) view
				.findViewById(R.id.reccom_price_offered3);
		newarr_price_offered1 = (TextView) view
				.findViewById(R.id.newarr_price_offered1);
		newarr_price_offered2 = (TextView) view
				.findViewById(R.id.newarr_price_offered2);
		newarr_price_offered3 = (TextView) view
				.findViewById(R.id.newarr_price_offered3);

		/*
		 * reccom_title1.setText(titlehome[0]);
		 * reccom_title2.setText(titlehome[1]);
		 * reccom_title3.setText(titlehome[2]);
		 * newarr_title1.setText(titlehome[3]);
		 * newarr_title2.setText(titlehome[4]);
		 * newarr_title3.setText(titlehome[5]);
		 */

		reccom_title1.setText((titlehome[0].length() < 18) ? titlehome[0]
				: titlehome[0].subSequence(0, 15) + "..");
		reccom_title2.setText((titlehome[1].length() < 18) ? titlehome[1]
				: titlehome[1].subSequence(0, 15) + "..");
		reccom_title3.setText((titlehome[2].length() < 18) ? titlehome[2]
				: titlehome[2].subSequence(0, 15) + "..");
		newarr_title1.setText((titlehomenew[0].length() < 18) ? titlehomenew[0]
				: titlehomenew[0].subSequence(0, 15) + "..");
		newarr_title2.setText((titlehomenew[1].length() < 18) ? titlehomenew[1]
				: titlehomenew[1].subSequence(0, 15) + "..");
		newarr_title3.setText((titlehomenew[2].length() < 18) ? titlehomenew[2]
				: titlehomenew[2].subSequence(0, 15) + "..");

		reccom_price_original1.setText("\u20b9"+Pricehome[0]);
		reccom_price_original2.setText("\u20b9"+Pricehome[1]);
		reccom_price_original3.setText("\u20b9"+Pricehome[2]);
		newarr_price_original1.setText("\u20b9"+Pricehomenew[0]);
		newarr_price_original2.setText("\u20b9"+Pricehomenew[1]);
		newarr_price_original3.setText("\u20b9"+Pricehomenew[2]);

		reccom_price_original1.setPaintFlags(reccom_price_original1
				.getPaintFlags()
				| Paint.STRIKE_THRU_TEXT_FLAG
				| Paint.ANTI_ALIAS_FLAG);
		reccom_price_original2.setPaintFlags(reccom_price_original2
				.getPaintFlags()
				| Paint.STRIKE_THRU_TEXT_FLAG
				| Paint.ANTI_ALIAS_FLAG);
		reccom_price_original3.setPaintFlags(reccom_price_original3
				.getPaintFlags()
				| Paint.STRIKE_THRU_TEXT_FLAG
				| Paint.ANTI_ALIAS_FLAG);
		newarr_price_original1.setPaintFlags(newarr_price_original1
				.getPaintFlags()
				| Paint.STRIKE_THRU_TEXT_FLAG
				| Paint.ANTI_ALIAS_FLAG);
		newarr_price_original2.setPaintFlags(newarr_price_original2
				.getPaintFlags()
				| Paint.STRIKE_THRU_TEXT_FLAG
				| Paint.ANTI_ALIAS_FLAG);
		newarr_price_original3.setPaintFlags(newarr_price_original3
				.getPaintFlags()
				| Paint.STRIKE_THRU_TEXT_FLAG
				| Paint.ANTI_ALIAS_FLAG);

		reccom_price_offered1.setText("\u20b9"+Price2home[0]);
		reccom_price_offered2.setText("\u20b9"+Price2home[1]);
		reccom_price_offered3.setText("\u20b9"+Price2home[2]);
		newarr_price_offered1.setText("\u20b9"+Price2homenew[0]);
		newarr_price_offered2.setText("\u20b9"+Price2homenew[1]);
		newarr_price_offered3.setText("\u20b9"+Price2homenew[2]);
		
		
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return view;
	}

	@Override
	public void onClick(View v) {
		
		try
		{

		if (v == reccom1) {
			Intent i = new Intent(v.getContext(), BookDetailsPage.class);
			String img = imageUrlhome[0];
			i.putExtra("imageh", img);
			i.putExtra("authorh", authorhome[0]);
			i.putExtra("titleh", titlehome[0]);
			i.putExtra("priceh", Pricehome[0]);
			i.putExtra("desch", deschome[0]);

			startActivity(i);

		}

		else if (v == reccom2) {
			Intent i = new Intent(v.getContext(), BookDetailsPage.class);
			String img = imageUrlhome[1];
			i.putExtra("imageh", img);
			i.putExtra("authorh", authorhome[1]);
			i.putExtra("titleh", titlehome[1]);
			i.putExtra("priceh", Pricehome[1]);
			i.putExtra("price2h", Price2home[1]);
			i.putExtra("desch", deschome[1]);
			startActivity(i);
		}

		else if (v == reccom3) {
			Intent i = new Intent(v.getContext(), BookDetailsPage.class);
			String img = imageUrlhome[2];
			i.putExtra("imageh", img);
			i.putExtra("authorh", authorhome[2]);
			i.putExtra("titleh", titlehome[2]);
			i.putExtra("priceh", Pricehome[2]);
			i.putExtra("price2h", Price2home[2]);
			i.putExtra("desch", deschome[2]);
			startActivity(i);
		}

		if (v == newarr1) {
			Intent i = new Intent(v.getContext(), BookDetailsPage.class);
			String img = imageUrlhomenew[0];
			i.putExtra("imageh", img);
			i.putExtra("authorh", authorhome[0]);
			i.putExtra("titleh", titlehome[0]);
			i.putExtra("priceh", Pricehome[0]);
			i.putExtra("price2h", Price2home[0]);
			i.putExtra("deschn", deschomenew[0]);
			startActivity(i);
		}

		else if (v == newarr2) {
			Intent i = new Intent(v.getContext(), BookDetailsPage.class);
			String img = imageUrlhomenew[1];
			i.putExtra("imageh", img);
			i.putExtra("authorh", authorhome[1]);
			i.putExtra("titleh", titlehome[1]);
			i.putExtra("priceh", Pricehome[1]);
			i.putExtra("price2h", Price2home[1]);
			i.putExtra("deschn", deschomenew[1]);
			startActivity(i);
		}

		else if (v == newarr3) {
			Intent i = new Intent(v.getContext(), BookDetailsPage.class);
			String img = imageUrlhomenew[2];
			i.putExtra("imageh", img);
			i.putExtra("authorh", authorhome[2]);
			i.putExtra("titleh", titlehome[2]);
			i.putExtra("priceh", Pricehome[2]);
			i.putExtra("price2h", Price2home[2]);
			i.putExtra("deschn", deschomenew[2]);
			startActivity(i);
		}

		if (v == recommended) {

			Intent intent = new Intent(v.getContext(), GridViewActivity.class);
			// String[] myStrings = new String[] {"test", "test2"};
			// String imh[]=Arrays.toString(imagehome);

			String imh[] = new String[imageUrlhome.length];
			for (int i = 0; i < imageUrlhome.length; i++)
				imh[i] = imageUrlhome[i];
			intent.putExtra("imageh", imh);
			intent.putExtra("authorh", authorhome);
			intent.putExtra("titleh", titlehome);
			intent.putExtra("priceh", Pricehome);
			intent.putExtra("price2h", Price2home);

			intent.putExtra("desch", deschome);
			intent.putExtra("recom", "Recommended For You");

			startActivity(intent);

			// startActivity(this,Grid);
		}

		if (v == new_arrivals) {

			Intent intent = new Intent(v.getContext(), GridViewActivity.class);
			// String[] myStrings = new String[] {"test", "test2"};
			// String imh[]=Arrays.toString(imagehome);

			String imh[] = new String[imageUrlhomenew.length];
			for (int i = 0; i < imageUrlhomenew.length; i++)
				imh[i] = imageUrlhomenew[i];
			intent.putExtra("imageh", imh);
			intent.putExtra("authorh", authorhomenew);
			intent.putExtra("titleh", titlehomenew);
			intent.putExtra("priceh", Pricehomenew);
			intent.putExtra("price2h", Price2homenew);

			intent.putExtra("desch", deschomenew);
			intent.putExtra("recom", "New Arrivals");

			startActivity(intent);
			
			

			// startActivity(this,Grid);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	void func() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(api_url + "app/?format=json&limit=0");
		HttpResponse response = null;
		try {

			response = httpClient.execute(httpGet);

		}

		catch (Exception e) {
			System.out.println(e.toString());
		}

		if (response != null) {
			HttpEntity entity = response.getEntity();
			String apiResponse = null;
			try

			{
				apiResponse = _getResponseBody(entity);

				Log.d("the obtained value is", apiResponse);
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JsonObject json_data = null;

			/*
			 * JsonElement json = new JsonParser().parse(apiResponse);
			 * JsonObject rootObject = json.getAsJsonObject(); JsonArray array=
			 * rootObject.getAsJsonArray("objects"); Iterator iterator =
			 * array.iterator();
			 */

			// array.

			JsonElement json = new JsonParser().parse(apiResponse);
			JsonObject rootObject = json.getAsJsonObject();
			arrayhome = rootObject.getAsJsonArray("objects");

			// Log.d("the size of array",array.size());

			Iterator iterator = arrayhome.iterator();

			imagehome = new int[arrayhome.size()];
			authorhome = new String[arrayhome.size()];
			titlehome = new String[arrayhome.size()];
			Pricehome = new String[arrayhome.size()];
			Price2home = new String[arrayhome.size()];
			deschome = new String[arrayhome.size()];
			imageUrlhome=new String[arrayhome.size()];

			int ij = 0;
			while (iterator.hasNext()) {
				JsonElement json2 = (JsonElement) iterator.next();
				Gson gson = new Gson();
				Country recipe = gson.fromJson(json2, Country.class);
				// recipesDao.createIfNotExists(recipe);
				// Log.d(LOG_TAG,
				// "--------------------------------created recipe");
				imagehome[ij] = recipe.getid();
				authorhome[ij] = recipe.getauthor();
				titlehome[ij] = recipe.gettitle();
				Pricehome[ij] = recipe.getPrice();
				Price2home[ij] = recipe.getPrice2();
				deschome[ij] = recipe.getdescription();
				imageUrlhome[ij] = recipe.getimageUrl();

				Log.d("prics uu", Pricehome[ij]);

				ij++;
			}

		}

	}

	void func2() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(api_url + "app/?format=json&limit=0");
		HttpResponse response = null;
		try {

			response = httpClient.execute(httpGet);

		}

		catch (Exception e) {
			System.out.println(e.toString());
		}

		if (response != null) {
			HttpEntity entity = response.getEntity();
			String apiResponse = null;
			try

			{
				apiResponse = _getResponseBody2(entity);

				Log.d("the obtained value is", apiResponse);
			}

			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JsonObject json_data = null;

			/*
			 * JsonElement json = new JsonParser().parse(apiResponse);
			 * JsonObject rootObject = json.getAsJsonObject(); JsonArray array=
			 * rootObject.getAsJsonArray("objects"); Iterator iterator =
			 * array.iterator();
			 */

			// array.

			JsonElement json = new JsonParser().parse(apiResponse);
			JsonObject rootObject = json.getAsJsonObject();
			arrayhomenew = rootObject.getAsJsonArray("objects");

			// Log.d("the size of array",array.size());

			Iterator iterator = arrayhomenew.iterator();

			imagehomenew = new int[arrayhomenew.size()];
			authorhomenew = new String[arrayhomenew.size()];
			titlehomenew = new String[arrayhomenew.size()];
			Pricehomenew = new String[arrayhomenew.size()];
			Price2homenew = new String[arrayhomenew.size()];
			deschomenew = new String[arrayhomenew.size()];
			imageUrlhomenew = new String[arrayhomenew.size()];

			int ij = 0;
			while (iterator.hasNext()) {
				JsonElement json2 = (JsonElement) iterator.next();
				Gson gson = new Gson();
				Country recipe = gson.fromJson(json2, Country.class);
				// recipesDao.createIfNotExists(recipe);
				// Log.d(LOG_TAG,
				// "--------------------------------created recipe");
				imagehomenew[ij] = recipe.getid();
				authorhomenew[ij] = recipe.getauthor();
				titlehomenew[ij] = recipe.gettitle();
				Pricehomenew[ij] = recipe.getPrice();
				Price2homenew[ij] = recipe.getPrice2();
				deschomenew[ij] = recipe.getdescription();
				imageUrlhomenew[ij] = recipe.getimageUrl();

				Log.d("prics uu", Pricehomenew[ij]);

				ij++;
			}

		}

	}

	public static String _getResponseBody(final HttpEntity entity)
			throws IOException, ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}

		InputStream instream = entity.getContent();

		if (instream == null) {
			return "";
		}

		if (entity.getContentLength() > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(

			"HTTP entity too large to be buffered in memory");
		}

		String charset = getContentCharSet(entity);

		if (charset == null) {

			charset = HTTP.DEFAULT_CONTENT_CHARSET;

		}

		Reader reader = new InputStreamReader(instream, charset);

		StringBuilder buffer = new StringBuilder();

		try {

			char[] tmp = new char[1024];

			int l;

			while ((l = reader.read(tmp)) != -1) {

				buffer.append(tmp, 0, l);

			}

		} finally {

			reader.close();

		}

		return buffer.toString();

	}

	public static String getContentCharSet(final HttpEntity entity)
			throws ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}

		String charset = null;

		if (entity.getContentType() != null) {

			HeaderElement values[] = entity.getContentType().getElements();

			if (values.length > 0) {

				NameValuePair param = values[0].getParameterByName("charset");

				if (param != null) {

					charset = param.getValue();

				}

			}

		}

		return charset;

	}

	public static String _getResponseBody2(final HttpEntity entity)
			throws IOException, ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}

		InputStream instream = entity.getContent();

		if (instream == null) {
			return "";
		}

		if (entity.getContentLength() > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(

			"HTTP entity too large to be buffered in memory");
		}

		String charset = getContentCharSet2(entity);

		if (charset == null) {

			charset = HTTP.DEFAULT_CONTENT_CHARSET;

		}

		Reader reader = new InputStreamReader(instream, charset);

		StringBuilder buffer = new StringBuilder();

		try {

			char[] tmp = new char[1024];

			int l;

			while ((l = reader.read(tmp)) != -1) {

				buffer.append(tmp, 0, l);

			}

		} finally {

			reader.close();

		}

		return buffer.toString();

	}

	public static String getContentCharSet2(final HttpEntity entity)
			throws ParseException {

		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}

		String charset = null;

		if (entity.getContentType() != null) {

			HeaderElement values[] = entity.getContentType().getElements();

			if (values.length > 0) {

				NameValuePair param = values[0].getParameterByName("charset");

				if (param != null) {

					charset = param.getValue();

				}

			}

		}

		return charset;

	}

}