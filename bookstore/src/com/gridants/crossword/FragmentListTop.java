package com.gridants.crossword;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
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
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.gridants.utils.ItemDetails;
import com.gridants.utils.ItemListBaseAdapter;
import com.gridants.utils.StringIdentifier;

public class FragmentListTop extends SherlockFragment {

	ListView lvv;
	ArrayList<ItemDetails> image_details;

	int id;
	StringIdentifier mStringIdentifier;

	// ListView lvv;
	// ArrayList<ItemDetails> image_details;
	String title[], author[], Price[], Price2[];
	// int id[];
	JsonArray array;
	int image[];
	String description[];
	
	String imageUrl[];

	String titleid;
	int identity;

	public static String api_url = "http://crossword.gridants.webfactional.com/art/";

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment3, container, false);
		
		Toast.makeText(view.getContext(), "no", Toast.LENGTH_LONG).show();

		// ListView lv=(ListView)view.findViewById(R.id.lv1);

		lvv = (ListView) view.findViewById(R.id.lisv);

		titleid = (String) getActivity().getTitle();
		mStringIdentifier = new StringIdentifier(titleid);
		identity = mStringIdentifier.getValue();
		
		try
		{

		if (identity == 1) {
			func();
			image_details = GetSearchResults();
		} else

			image_details = GetSearchResults2();

		// final ListView lv1 = (ListView) findViewById(R.id.listV_main);

		// func();
		lvv.setAdapter(new ItemListBaseAdapter(view.getContext(), image_details));

		lvv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Object o = lvv.getItemAtPosition(position);
				ItemDetails obj_itemDetails = (ItemDetails) o;

				if (identity == 1) {
					Intent i = new Intent(v.getContext(), BookDetailsPage.class);
					String img = imageUrl[position];
					i.putExtra("imageh", img);
					i.putExtra("authorh", author[position]);
					i.putExtra("titleh", title[position]);

					i.putExtra("priceh", Price[position]);
					i.putExtra("price2h", Price2[position]);
					i.putExtra("desch", description[position]);
					startActivity(i);

				}

				else {
					Intent i = new Intent(v.getContext(), BookDetailsPage.class);
					String img =global.catimageUrl[position];
					i.putExtra("imageh", img);
					i.putExtra("authorh", global.catauthor[position]);
					i.putExtra("titleh", global.cattitle[position]);

					i.putExtra("priceh", global.catprice[position]);
					i.putExtra("price2h", global.catprice2[position]);
					i.putExtra("desch", global.catdesc[position]);
					startActivity(i);
				}

				// startActivity(new Intent(v.getContext(),
				// BookDetailsPage.class));

			}
		});

		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return view;
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
			 * 
			 * JsonObject rootObject = json.getAsJsonObject(); JsonArray array=
			 * rootObject.getAsJsonArray("objects"); Iterator iterator =
			 * array.iterator();
			 */

			// array.

			JsonElement json = new JsonParser().parse(apiResponse);
			JsonObject rootObject = json.getAsJsonObject();
			array = rootObject.getAsJsonArray("objects");

			// Log.d("the size of array",array.size());

			Iterator iterator = array.iterator();

			image = new int[array.size()];
			author = new String[array.size()];
			title = new String[array.size()];
			Price = new String[array.size()];
			Price2 = new String[array.size()];
			description = new String[array.size()];
			imageUrl = new String[array.size()];
			int ij = 0;
			while (iterator.hasNext()) {
				JsonElement json2 = (JsonElement) iterator.next();
				Gson gson = new Gson();
				Country recipe = gson.fromJson(json2, Country.class);
				// recipesDao.createIfNotExists(recipe);
				// Log.d(LOG_TAG,
				// "--------------------------------created recipe");
				image[ij] = recipe.getid();
				author[ij] = recipe.getauthor();
				title[ij] = recipe.gettitle();
				Price[ij] = recipe.getPrice();
				Price2[ij] = recipe.getPrice2();
				description[ij] = recipe.getdescription();

				imageUrl[ij] = recipe.getimageUrl();

				Log.d("prics uu", Price[ij]);

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

	private ArrayList<ItemDetails> GetSearchResults() {
		ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

		/*
		 * results.add(new ItemDetails("And The Mountains Echoed",
		 * "Khaled Hosseini", "\u20b9. 479", "\u20b9. 510", 1)); results.add(new
		 * ItemDetails("Inferno", "Robert Langdon", "\u20b9. 562",
		 * "\u20b9. 600", 2)); results.add(new
		 * ItemDetails("The Secret Of The Nagas", "Amish Tripathi",
		 * "\u20b9. 191", "\u20b9. 210", 3)); results.add(new
		 * ItemDetails("The Lost: A Gaunt's Ghost Omnibus", "Dan Abnett",
		 * "\u20b9. 673", "\u20b9. 710", 4)); results.add(new
		 * ItemDetails("A Thousand Splendid Suns", "Khaled Hosseini",
		 * "\u20b9. 399", "\u20b9. 430", 5)); results.add(new
		 * ItemDetails("Digital Fortress", "Robert Langdon", "\u20b9. 717",
		 * "\u20b9. 760", 6)); results.add(new
		 * ItemDetails("The Immortals Of Meluha", "Amish Tripathi",
		 * "\u20b9. 192", "\u20b9. 210", 7)); results.add(new
		 * ItemDetails("The Kite Runner", "Khaled Hosseini", "\u20b9. 415",
		 * "\u20b9. 440", 8)); results.add(new ItemDetails("Omnibus",
		 * "Robert Langdon", "\u20b9. 595", "\u20b9. 650", 9)); results.add(new
		 * ItemDetails("The Oath of the Vayuputras", "Amish Tripathi",
		 * "\u20b9. 262", "\u20b9. 295", 10));
		 */

		for (int i = 0; i < image.length; i++) {
			results.add(new ItemDetails(title[i], author[i], "\u20b9"
					+ Price[i], "\u20b9" + Price2[i], imageUrl[i], description[i]));
		}

		return results;
	}

	private ArrayList<ItemDetails> GetSearchResults2() {
		ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

		/*
		 * results.add(new ItemDetails("And The Mountains Echoed",
		 * "Khaled Hosseini", "\u20b9. 479", "\u20b9. 510", 1)); results.add(new
		 * ItemDetails("Inferno", "Robert Langdon", "\u20b9. 562",
		 * "\u20b9. 600", 2)); results.add(new
		 * ItemDetails("The Secret Of The Nagas", "Amish Tripathi",
		 * "\u20b9. 191", "\u20b9. 210", 3)); results.add(new
		 * ItemDetails("The Lost: A Gaunt's Ghost Omnibus", "Dan Abnett",
		 * "\u20b9. 673", "\u20b9. 710", 4)); results.add(new
		 * ItemDetails("A Thousand Splendid Suns", "Khaled Hosseini",
		 * "\u20b9. 399", "\u20b9. 430", 5)); results.add(new
		 * ItemDetails("Digital Fortress", "Robert Langdon", "\u20b9. 717",
		 * "\u20b9. 760", 6)); results.add(new
		 * ItemDetails("The Immortals Of Meluha", "Amish Tripathi",
		 * "\u20b9. 192", "\u20b9. 210", 7)); results.add(new
		 * ItemDetails("The Kite Runner", "Khaled Hosseini", "\u20b9. 415",
		 * "\u20b9. 440", 8)); results.add(new ItemDetails("Omnibus",
		 * "Robert Langdon", "\u20b9. 595", "\u20b9. 650", 9)); results.add(new
		 * ItemDetails("The Oath of the Vayuputras", "Amish Tripathi",
		 * "\u20b9. 262", "\u20b9. 295", 10));
		 */

		for (int i = 0; i < global.catimage.length; i++) {
			results.add(new ItemDetails(global.cattitle[i],
					global.catauthor[i], "\u20b9" + global.catprice[i],
					"\u20b9" + global.catprice2[i], global.catimageUrl[i],
					global.catdesc[i]));
		}

		return results;
	}

}