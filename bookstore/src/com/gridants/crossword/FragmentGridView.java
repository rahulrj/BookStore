package com.gridants.crossword;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.gridants.utils.ImageAdapter;

public class FragmentGridView extends SherlockFragment {

	String current_category;

	GridView gridView;

	TextView path;

	static final String[] titlearray = new String[] { "The Alchemist",
			"A Warrior's Life", "Manuscript Found in Accra",
			"Like The Flowing River", "Veronika Decides To Die",
			"Witch Of Portobello", "The Fifth Mountain",
			"Manual Of The Warrior Of Light",
			"By The River Piedra I Sat Down And Wept", "The Valkyries",
			"The Zahir" };

	static final String[] originalpricearray = new String[] { "\u20b9. 20",
			"\u20b9. 944", "\u20b9. 299", "\u20b9. 350", "\u20b9. 350",
			"\u20b9. 350", "\u20b9. 885", "\u20b9. 350", "\u20b9. 275",
			"\u20b9. 275", "\u20b9. 330" };

	static final String[] offeredpricearray = new String[] { "\u20b9. 15",
			"\u20b9. 717", "\u20b9. 236", "\u20b9. 325", "\u20b9. 325",
			"\u20b9. 325", "\u20b9. 673", "\u20b9. 325", "\u20b9. 250",
			"\u20b9. 250", "\u20b9. 295" };

	static final Integer[] imageid = { R.drawable.s0, R.drawable.s1,
			R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
			R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9,
			R.drawable.s10

	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.gridviewlayout, container, false);

		gridView = (GridView) view.findViewById(R.id.gridView);
		
		try
		{

		//gridView.setAdapter(new ImageAdapter(view.getContext(),global,
				//originalpricearray, offeredpricearray, imageid));
		
		for(int i=0;i<global.catimageUrl.length;i++)
		{
			Log.d("the global author",global.catauthor[i]);
			Log.d("the global title",global.cattitle[i]);
		}
		
		gridView.setAdapter(new ImageAdapter(view.getContext(), global.cattitle,
				global.catprice, global.catprice2, global.catimageUrl,global.catdesc));

		path = (TextView) view.findViewById(R.id.path);
		path.setText(CategorySelected.message);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				
				Intent i=new Intent(v.getContext(),BookDetailsPage.class);
				String img=global.catimageUrl[position];
				i.putExtra("imageh",img);
				i.putExtra("authorh",global.catauthor[position]);
				i.putExtra("titleh",global.cattitle[position]);
				
				
				i.putExtra("priceh",global.catprice[position]);
				i.putExtra("price2h",global.catprice2[position]);
				i.putExtra("desch",global.catdesc[position]);
				startActivity(i);

				startActivity(i);

			}
		});
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 

		return view;
	}

}