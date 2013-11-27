package com.gridants.crossword;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devsmart.android.ui.HorizontalListView;

public class BookDetailsPage extends Activity implements View.OnClickListener {

	LinearLayout review_layout;
	TextView no_of_votes;

	LinearLayout description_layout;
	TextView description;

	ImageView pic;
	TextView title;
	TextView author;
	TextView offeredprice;
	TextView originalprice;
	TextView availability;
	
	
	
	String imageh,authorh,titleh,priceh,price2h,desch;

	LinearLayout locate_on_map;

	LinearLayout add_to_wishlist;
	int wishlist_status = 0;
	ImageView wish_img;
	TextView wish_text;

	LinearLayout add_to_cart;
	int cart_status = 0;
	ImageView cart_img;
	TextView cart_text;

	RatingBar rating;

	private static String[] titlearray = new String[] { "A Warrior's Life",
			"Manuscript Found in Accra", "Like The Flowing River",
			"Veronika Decides To Die", "Witch Of Portobello",
			"The Fifth Mountain", "Manual Of The Warrior Of Light",
			"By The River Piedra I Sat Down And Wept", "The Valkyries",
			"The Zahir" };

	private static String[] originalpricearray = new String[] { "\u20b9. 944",
			"\u20b9. 299", "\u20b9. 350", "\u20b9. 350", "\u20b9. 350",
			"\u20b9. 885", "\u20b9. 350", "\u20b9. 275", "\u20b9. 275",
			"\u20b9. 330" };

	private static String[] offeredpricearray = new String[] { "\u20b9. 717",
			"\u20b9. 236", "\u20b9. 325", "\u20b9. 325", "\u20b9. 325",
			"\u20b9. 673", "\u20b9. 325", "\u20b9. 250", "\u20b9. 250",
			"\u20b9. 295" };

	private Integer[] imageid = { R.drawable.s1, R.drawable.s2, R.drawable.s3,
			R.drawable.s4, R.drawable.s5, R.drawable.s6, R.drawable.s7,
			R.drawable.s8, R.drawable.s9, R.drawable.s10 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookdetailspage);

		int isavailable = 1;
		
		
		
		Intent intent = getIntent();
		
		Bundle b=getIntent().getExtras();
		if(b!=null)
		{
	imageh = intent.getExtras().getString("imageh");
			 authorh = intent.getExtras().getString("authorh");
			 titleh = intent.getExtras().getString("titleh");
			priceh = intent.getExtras().getString("priceh");
	price2h = intent.getExtras().getString("price2h");
			desch = intent.getExtras().getString("desch");
			
			Toast.makeText(getApplicationContext(), desch, Toast.LENGTH_LONG).show();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		try
		{
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		String shortdescription = "The Alchemist is the magical tale of Santiago, an Andalusian shepherd boy who yearns to travel in search of a worldly treasure as extravagant as any ever found.";

		rating = (RatingBar) findViewById(R.id.rating);
		rating.setRating(4);

		no_of_votes = (TextView) findViewById(R.id.no_of_votes);
		no_of_votes.setText("(73)");

		pic = (ImageView) findViewById(R.id.pic);
		title = (TextView) findViewById(R.id.title);
		author = (TextView) findViewById(R.id.author);
		offeredprice = (TextView) findViewById(R.id.offeredprice);
		originalprice = (TextView) findViewById(R.id.originalprice);
		availability = (TextView) findViewById(R.id.availability);
		
		//Log.d("imageh is",imageh);
		URL url = null;
		try {
			url = new URL("http://crossword.gridants.webfactional.com/photos/"+(imageh.substring(imageh.lastIndexOf('/'), imageh.length())));
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
		pic.setImageBitmap(bmp);



		//pic.setImageResource(R.drawable.s0);
		title.setText(titleh);
		author.setText("by" + authorh);
		offeredprice.setText("\u20b9. " + priceh);
		originalprice.setText("\u20b9. " +price2h);

		originalprice.setPaintFlags(originalprice.getPaintFlags()
				| Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

		if (isavailable == 1) {
			availability.setText("In Stock");
			availability.setTextColor(Color.GREEN);
		}

		else {
			availability.setText("Out Of Stock");
			availability.setTextColor(Color.RED);
		}

		review_layout = (LinearLayout) findViewById(R.id.review_layout);
		review_layout.setOnClickListener(this);

		description_layout = (LinearLayout) findViewById(R.id.description_layout);
		description_layout.setOnClickListener(this);

		description = (TextView) findViewById(R.id.description);
		description
				.setText(desch);
		//description.setText((shortdescription.length() < 95) ? shortdescription
				//: shortdescription.subSequence(0, 92) + "...");

		locate_on_map = (LinearLayout) findViewById(R.id.locate_on_map);
		locate_on_map.setOnClickListener(this);
		add_to_wishlist = (LinearLayout) findViewById(R.id.add_to_wishlist);
		add_to_wishlist.setOnClickListener(this);
		add_to_cart = (LinearLayout) findViewById(R.id.add_to_cart);
		add_to_cart.setOnClickListener(this);

		wish_img = (ImageView) findViewById(R.id.wish_img);
		wish_text = (TextView) findViewById(R.id.wish_text);

		cart_img = (ImageView) findViewById(R.id.cart_img);
		cart_text = (TextView) findViewById(R.id.cart_text);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
		
		try
		{
		HorizontalListView listview = (HorizontalListView) findViewById(R.id.similar_books);
		listview.setAdapter(mAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				startActivity(new Intent(BookDetailsPage.this,
						BookDetailsPage.class));
			}
		});
		
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	private BaseAdapter mAdapter = new BaseAdapter() {

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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View retval = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.viewitem, null);

			ImageView image = (ImageView) retval.findViewById(R.id.image);
			image.setImageResource(imageid[position]);

			TextView title = (TextView) retval.findViewById(R.id.title);
			title.setText(titlearray[position]);

			TextView originalprice = (TextView) retval
					.findViewById(R.id.originalprice);
			originalprice.setText(originalpricearray[position]);

			originalprice.setPaintFlags(originalprice.getPaintFlags()
					| Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

			TextView offeredprice = (TextView) retval
					.findViewById(R.id.offeredprice);
			offeredprice.setText(offeredpricearray[position]);

			return retval;
		}

	};

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.description_layout:
			Intent i = new Intent(this, Description.class);
			
			i.putExtra("desc",desch);
			i.putExtra("author",authorh);
			i.putExtra("title",titleh);
			startActivity(i); 
			break;

		case R.id.review_layout:
			startActivity(new Intent(BookDetailsPage.this, Reviews.class));
			break;

		case R.id.locate_on_map:
			startActivity(new Intent(BookDetailsPage.this, MapDisplay.class));
			break;

		case R.id.add_to_wishlist:

			if (wishlist_status == 0) {
				
				
				String ids=imageh;
				//int idd=Integer.parseInt(ids);
				String productid="product";
				String api_url = "http://crossword.gridants.webfactional.com/select/";
				
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(api_url+"?user=2657&prod_idd=rahul");
				HttpResponse response = null;
				try {

					response = httpClient.execute(httpGet);

				}

				catch (Exception e) {
					System.out.println(e.toString());
				}
				
				Toast.makeText(getApplicationContext(),
						title.getText() + " added to wishlist",
						Toast.LENGTH_SHORT).show();
				wishlist_status = 1;
				wish_img.setImageResource(R.drawable.ic_action_wishlist_blue);
				wish_text.setText("Remove From Wishlist");
			}

			else {
				Toast.makeText(getApplicationContext(),
						title.getText() + " removed from wishlist",
						Toast.LENGTH_SHORT).show();
				wishlist_status = 0;
				wish_img.setImageResource(R.drawable.ic_action_wishlist100);
				wish_text.setText("Add To Wishlist");
			}

			break;

		case R.id.add_to_cart:

			if (cart_status == 0) {
				Toast.makeText(getApplicationContext(),
						title.getText() + " added to cart", Toast.LENGTH_SHORT)
						.show();
				cart_status = 1;
				cart_img.setImageResource(R.drawable.ic_action_cart_blue);
				cart_text.setText("Remove From Cart");
			}

			else {
				Toast.makeText(getApplicationContext(),
						title.getText() + " removed from cart",
						Toast.LENGTH_SHORT).show();
				cart_status = 0;
				cart_img.setImageResource(R.drawable.ic_action_cart_white);
				cart_text.setText("Add To Cart");
			}

			break;

		default:
			break;
		}

	}

}