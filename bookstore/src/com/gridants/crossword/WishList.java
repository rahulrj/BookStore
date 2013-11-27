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

import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.gridants.utils.ItemDetails;
import com.gridants.utils.WishListAdapter;

public class WishList extends SherlockActivity {
	ListView wishlist;
	ArrayList<ItemDetails> wishlist_details;
	
	
	JsonArray array;
	int image[];
	String description[];
	String title[],author[],Price[],Price2[];
	String imageUrl[];
	
	String titleid;
	int identity;

	 public static String api_url = "http://crossword.gridants.webfactional.com/art/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wish_list);
try
{
		wishlist = (ListView) this.findViewById(R.id.wish_list);
		func();

		wishlist_details = GetSearchResults();

		wishlist.setAdapter(new WishListAdapter(this, wishlist_details));
		
		

		wishlist.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Object o = wishlist.getItemAtPosition(position);
				ItemDetails obj_itemDetails = (ItemDetails) o;
				Toast.makeText(WishList.this,
						"You have chosen : " + " " + obj_itemDetails.getName(),
						Toast.LENGTH_LONG).show();

				// startActivity(new
				// Intent(v.getContext(),BookDetailsPage.class));

			}
		});

		ActionBar bar = getSupportActionBar();
		bar.setHomeButtonEnabled(true);
		bar.setTitle("Wishlist");
		bar.setIcon(R.drawable.cross);
		
}
catch(Exception e)
{
	e.printStackTrace();
}

	}
	
	
	
	
	
	
	
	
	
	
	
	void func() {
		 HttpClient httpClient = new DefaultHttpClient();
	        HttpGet httpGet = new HttpGet(api_url+"app/?format=json&limit=0");
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
	        array= rootObject.getAsJsonArray("objects");
	         
	         //Log.d("the size of array",array.size());
	         
	         
	         Iterator iterator = array.iterator();
	         
	         image=new int[array.size()];
	         author=new String[array.size()];
	         title=new String[array.size()];
	         Price=new String[array.size()];
	        Price2=new String[array.size()];
	        description=new String[array.size()];
	       imageUrl=new String[array.size()];
	         
	         int ij=0;
	         while(iterator.hasNext()){
	          JsonElement json2 = (JsonElement)iterator.next();
	          Gson gson = new Gson();
	          Country recipe = gson.fromJson(json2, Country.class);
	         // recipesDao.createIfNotExists(recipe);
	          //Log.d(LOG_TAG, "--------------------------------created recipe");
	        image[ij]=recipe.getid();
	          author[ij]=recipe.getauthor();
	          title[ij]=recipe.gettitle();
	          Price[ij]=recipe.getPrice();
	          Price2[ij]=recipe.getPrice2();
	          description[ij]=recipe.getdescription();
	          imageUrl[ij]=recipe.getimageUrl();
	         
	          
	          Log.d("prics uu",Price[ij]);
	          
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

	 
	 
	 
	 
	 
	 
	 

	
	
	

	private ArrayList<ItemDetails> GetSearchResults() {
		ArrayList<ItemDetails> wishlist_item = new ArrayList<ItemDetails>();
		
		
		
		for(int i=0;i<image.length;i++)
		{
			wishlist_item.add(new ItemDetails(title[i],
					author[i], "\u20b9"+Price[i], "\u20b9"+Price2[i],imageUrl[i],description[i]));
		}


		/*wishlist_item.add(new ItemDetails("And The Mountains Echoed",
				"Khaled Hosseini", "\u20b9. 479", "\u20b9. 510", 1));
		wishlist_item.add(new ItemDetails("Inferno", "Robert Langdon",
				"\u20b9. 562", "\u20b9. 600", 2));
		wishlist_item.add(new ItemDetails("The Secret Of The Nagas",
				"Amish Tripathi", "\u20b9. 191", "\u20b9. 210", 3));
		wishlist_item.add(new ItemDetails("The Lost: A Gaunt's Ghost Omnibus",
				"Dan Abnett", "\u20b9. 673", "\u20b9. 710", 4));
		wishlist_item.add(new ItemDetails("A Thousand Splendid Suns",
				"Khaled Hosseini", "\u20b9. 399", "\u20b9. 430", 5));
		wishlist_item.add(new ItemDetails("Digital Fortress", "Robert Langdon",
				"\u20b9. 717", "\u20b9. 760", 6));
		wishlist_item.add(new ItemDetails("The Immortals Of Meluha",
				"Amish Tripathi", "\u20b9. 192", "\u20b9. 210", 7));
		wishlist_item.add(new ItemDetails("The Kite Runner", "Khaled Hosseini",
				"\u20b9. 415", "\u20b9. 440", 8));
		wishlist_item.add(new ItemDetails("Omnibus", "Robert Langdon",
				"\u20b9. 595", "\u20b9. 650", 9));
		wishlist_item.add(new ItemDetails("The Oath of the Vayuputras",
				"Amish Tripathi", "\u20b9. 262", "\u20b9. 295", 10));*/

		return wishlist_item;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.book_details_page, menu);
		return true;
	}

}