package com.gridants.crossword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.gridants.utils.ImageAdapter;
import com.gridants.utils.ImageAdapter2;

public class GridViewActivity extends Activity {

	GridView gridView;

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

	static final int[] imageid = { R.drawable.s0, R.drawable.s1, R.drawable.s2,
			R.drawable.s3, R.drawable.s4, R.drawable.s5, R.drawable.s6,
			R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10

	};

	String imageh[], authorh[], titleh[], priceh[], price2h[], desch[];
	//int img[];
	String recomm,newarr;

	String no[] = {};
TextView path;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridviewlayout);

		Intent intent = getIntent();
		// Intent intent = getIntent();

		try
		{
		Bundle b = getIntent().getExtras();
		if (b != null) {

			imageh = intent.getStringArrayExtra("imageh");

			authorh = intent.getStringArrayExtra("authorh");

			titleh = intent.getStringArrayExtra("titleh");

			priceh = intent.getStringArrayExtra("priceh");

			price2h = intent.getStringArrayExtra("price2h");
			desch = intent.getStringArrayExtra("desch");
			recomm=intent.getExtras().getString("recom");

		}

		/*img = new int[imageh.length];
		for (int i = 0; i < img.length; i++) {
			img[i] = Integer.parseInt(imageh[i]);
		}*/

		
		 path=(TextView)findViewById(R.id.path);
		 path.setText(recomm);
		// String[] myStrings = intent.getStringArrayExtra("strings");

		gridView = (GridView) findViewById(R.id.gridView);

		gridView.setAdapter(new ImageAdapter2(this, titleh, priceh, price2h,
				imageh, desch));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Intent i = new Intent(v.getContext(), BookDetailsPage.class);
				String img = imageh[position];
				i.putExtra("imageh", img);
				i.putExtra("authorh", authorh[position]);
				i.putExtra("titleh", titleh[position]);

				i.putExtra("priceh", priceh[position]);
				i.putExtra("price2h", price2h[position]);
				i.putExtra("desch", desch[position]);
				startActivity(i);

				startActivity(i);

			}
		});
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}