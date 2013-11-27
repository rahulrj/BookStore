package com.gridants.crossword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class Description extends Activity {

	String desc,author,title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description);

	
		Intent intent = getIntent();
		
		
		Bundle b=getIntent().getExtras();
		if(b!=null)
		{
		 desc = intent.getExtras().getString("desc");
		 author = intent.getExtras().getString("author");
		 title = intent.getExtras().getString("title");
		
		}
		TextView name = (TextView) findViewById(R.id.name);
		name.setText(title);

		TextView author2 = (TextView) findViewById(R.id.author);
		author2.setText(author);

		TextView description_view = (TextView) findViewById(R.id.description_view);
		description_view
				.setText(desc);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.description, menu);
		return true;
	}

}
