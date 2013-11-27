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

import android.app.Activity;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.gridants.utils.MobileArrayAdapter;
import com.gridants.utils.Reviewobject;

public class Reviews extends Activity {
	
	public static String api_url = "http://crossword.gridants.webfactional.com/reviewfetch?Product_id=7697";
	String date[],user[],body[];
	ArrayList<Reviewobject> reviews;
JsonArray array;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reviewslistview);

		TextView name = (TextView) findViewById(R.id.name);
		name.setText("The Alchemist");

		TextView author = (TextView) findViewById(R.id.author);
		author.setText("by" + "Paulo Coelho");

		ListView newlist = (ListView) findViewById(R.id.listview);
		 reviews = new ArrayList<Reviewobject>();
		
		
		try
		{
		
	func();	
		
		
		
		
		
		func2();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

		newlist.setAdapter(new MobileArrayAdapter(this, reviews));
		// newlist.setListAdapter(new MobileArrayAdapter(this, reviews));
	}
	
	
	
	
	
	
	
	
	
	
	
	

	void func() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(api_url);
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
			array = rootObject.getAsJsonArray("fields");

			// Log.d("the size of array",array.size());

			Iterator iterator = array.iterator();

			date = new String[array.size()];
			body = new String[array.size()];
			user = new String[array.size()];
			

			int ij = 0;
			while (iterator.hasNext()) {
				JsonElement json2 = (JsonElement) iterator.next();
				Gson gson = new Gson();
				reviewclass recipe = gson.fromJson(json2, reviewclass.class);
				// recipesDao.createIfNotExists(recipe);
				// Log.d(LOG_TAG,
				// "--------------------------------created recipe");
				date[ij] = recipe.getdate();
				body[ij] = recipe.getbody();
				user[ij] = recipe.getuser();
				

				Log.d("here is user", user[ij]);

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

	
	void func2()
	
	{
		
		for(int i=0;i<array.size();i++)
		{
			Log.d("user is",user[i]);
			reviews.add(new Reviewobject(
					user[i],
					date[i],
					body[i],"33"));
		}

		
	}
	
	
	
}

/*
 * public class Reviews extends ListActivity {
 * 
 * 
 * static final Reviewobject[] reviews = new Reviewobject[] { "Android", "iOS",
 * "WindowsMobile", "Blackberry"};
 * 
 * @Override public void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * 
 * setListAdapter(new MobileArrayAdapter(this, reviews));
 * 
 * }
 * 
 * @Override protected void onListItemClick(ListView l, View v, int position,
 * long id) {
 * 
 * //get selected items //String selectedValue = (String)
 * getListAdapter().getItem(position); //Toast.makeText(this, selectedValue,
 * Toast.LENGTH_SHORT).show();
 * 
 * }
 * 
 * }
 */