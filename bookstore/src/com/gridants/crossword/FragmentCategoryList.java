package com.gridants.crossword;

import android.content.Intent;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.gridants.utils.MySimpleArrayAdapter;
import com.gridants.utils.StringIdentifier;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import android.net.ParseException;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class FragmentCategoryList extends SherlockFragment {

	View view;
	//int identity;  //to identify the calling activity
	StringIdentifier mStringIdentifier;
	String[] categories1;
	int position;
	String message;
	String booktitle;
	String bookcat;

	
	
	JsonArray array;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		position = CategorySelected.position;
		message = CategorySelected.message;

		view = inflater.inflate(R.layout.fragment1, container, false);
		global.globalidentity = -1;

		String title = (String) getActivity().getTitle();
		
		Log.d("titleis",title);
		mStringIdentifier = new StringIdentifier(title);
		global.globalidentity = mStringIdentifier.getValue();
        Toast.makeText(view.getContext(), "rahul", Toast.LENGTH_LONG).show();
		
		Log.d("globalid",String.valueOf(global.globalidentity));
		
		final ListView fonelv = (ListView) view.findViewById(R.id.fonelv);
		
		try
		{

		if (global.globalidentity == 1) {
			message = null;
			categories1 = new String[] { "Music", "Movies", "Art",
					"Body n Mind", "Business", "Cookings", "Relationships",
					"Fiction", "Health n Fitness", "Kids Fiction",
					"Kids Non Fiction", "Reference", "Relegion", "Self Help",
					"Social Science", "Travel" };
		} else {
			// populate dynamically from database using postion and message
			categories1 = new String[global.catimage.length] ;
			//categories1=global.catsubcat;
			
	        List<String> list = Arrays.asList(global.catsubcat);
	        Set<String> set = new HashSet<String>(list);
	 
	        System.out.print("Remove duplicate result: ");
	 
	        //
	        // Create an array to convert the Set back to array.
	        // The Set.toArray() method copy the value in the set to the
	        // defined array.
	        //
	        String[] result = new String[set.size()];
	        set.toArray(result);
	        for (String s : result) {
	            System.out.print(s + ", ");
	        }
	            
	            categories1=result;
			
			
			
		}
		
		Toast.makeText(view.getContext(), "rahu"+global.globalidentity, Toast.LENGTH_LONG).show();
		//global.globalidentity=1;
	
	 final String[] categories = categories1;
		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(
				view.getContext(), categories);
		fonelv.setAdapter(adapter);

		fonelv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {

				bookcat = categories[position];
				bookcat=bookcat.replaceAll("\\s","");
				Log.d("bookcat",bookcat);
				Log.d("value of id",String.valueOf(global.globalidentity));
				Log.d("value of id",String.valueOf(global.subcat));
				
			
			
			
				if(global.globalidentity==1)
				{
					//global.subcat=1;
				
					func("http://crossword.gridants.webfactional.com/booking?category="+bookcat);
					
					Intent i = new Intent(v.getContext(), CategorySelected.class);
					i.putExtra("Position", position);
					String messagefinal = "";
					
					
					if (message == null) {
						messagefinal = bookcat;
					} else if (message != null) {
						messagefinal = message + " > " + bookcat;
					}
					i.putExtra("Name", messagefinal);

					startActivity(i);
				}
				else
				{global.globalidentity=1;
					
					func("http://crossword.gridants.webfactional.com/booking2?category2="+bookcat);
					
					Intent i = new Intent(v.getContext(), CategorySelected.class);
					i.putExtra("Position", position);
					String messagefinal = "";
					
					
					if (message == null) {
						messagefinal = bookcat;
					} else if (message != null) {
						messagefinal = message + " > " + bookcat;
					}
					
					
					i.putExtra("Name", messagefinal);

					startActivity(i);
					
					
				
				}
				
			}
				

			
		});

		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return view;
		
	}
	
	
	
	
	

	void func(String api_url) {
		 HttpClient httpClient = new DefaultHttpClient();
	        HttpGet httpGet = new HttpGet(api_url);
	        HttpResponse response = null;
	        try{
	         
	         response = httpClient.execute(httpGet);
	         
	        }
	        
	        catch(Exception e)
	        {
	         System.out.println(e.toString());
	        }
	        
	        if(response!=null)
	        {
	         HttpEntity entity = response.getEntity();
	         String apiResponse = null;
	         try 
	         
	         {
	          apiResponse = _getResponseBody(entity);
	          
	          Log.d("the obtained value is",apiResponse);
	         } 
	         
	         
	         catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	         JsonObject json_data=null; 

	        /* JsonElement json = new JsonParser().parse(apiResponse);
	         JsonObject rootObject = json.getAsJsonObject();
	         JsonArray array= rootObject.getAsJsonArray("objects");
	         Iterator iterator = array.iterator();*/
	         
	         
	         //array.
	         
	      
	         JsonElement json = new JsonParser().parse(apiResponse);
	         JsonObject rootObject = json.getAsJsonObject();
	        array= rootObject.getAsJsonArray("fields");
	         
	         Log.d("the size of array",String.valueOf(array.size()));
	         
	         
	         Iterator iterator = array.iterator();
	         
	         global.catimage=new int[array.size()];
	         global.catauthor=new String[array.size()];
	         global.cattitle=new String[array.size()];
	         global.catprice=new String[array.size()];
	       global.catprice2=new String[array.size()];
	       global.catsubcat=new String[array.size()];
	       global.catdesc=new String[array.size()];
	       global.catimageUrl=new String[array.size()];
	         
	         int ij=0;
	         while(iterator.hasNext()){
	          JsonElement json2 = (JsonElement)iterator.next();
	          Gson gson = new Gson();
	          Country recipe = gson.fromJson(json2, Country.class);
	         // recipesDao.createIfNotExists(recipe);
	          //Log.d(LOG_TAG, "--------------------------------created recipe");
	          global.catimage[ij]=recipe.getid();
	          global.catauthor[ij]=recipe.getauthor();
	          global.cattitle[ij]=recipe.gettitle();
	         global.catprice[ij]=recipe.getPrice();
	          global.catprice2[ij]=recipe.getPrice2();
	          global.catsubcat[ij]=recipe.getcategory2();
	          global.catdesc[ij]=recipe.getdescription();
	          global.catimageUrl[ij]=recipe.getimageUrl();
	          if(global.globalidentity==2)
	        	  global.catsubcat[ij]="";
	          
	          if(global.catsubcat.length>0)
	          {
	        	  
	          }
	          
	          
	         
	          
	          Log.d("the rahul url",global.catimageUrl[ij]);
	          
	          ij++;
	         }
	         
	        
	         
	         
	     
	         } 
	
}
	
	
	
	
	
	
	
	public static String _getResponseBody(final HttpEntity entity) throws IOException, ParseException {

		  if (entity == null) { throw new IllegalArgumentException("HTTP entity may not be null"); }

		  InputStream instream = entity.getContent();

		  if (instream == null) { return ""; }

		  if (entity.getContentLength() > Integer.MAX_VALUE) { throw new IllegalArgumentException(

		  "HTTP entity too large to be buffered in memory"); }

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
	 
	 public static String getContentCharSet(final HttpEntity entity) throws ParseException {

		  if (entity == null) { throw new IllegalArgumentException("HTTP entity may not be null"); }

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