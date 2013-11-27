package com.gridants.crossword;

//import com.actionbarsherlock.sample.demos.R;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;

public class Music extends SherlockActivity implements
		SearchView.OnQueryTextListener, SearchView.OnSuggestionListener {

	private static final int MENU_WISH = Menu.FIRST;

	private static final String[] COLUMNS = { BaseColumns._ID,
			SearchManager.SUGGEST_COLUMN_TEXT_1, };

	private SuggestionsAdapter mSuggestionsAdapter;

	public boolean onCreateOptionsMenu(Menu menu) {
		// Used to put dark icons on light action bar
		boolean isLight = R.style.Theme_Sherlock == R.style.Theme_Sherlock_Light;

		// Create the search view
		SearchView searchView = new SearchView(getSupportActionBar()
				.getThemedContext());
		searchView.setQueryHint("Search for countries…");
		searchView.setOnQueryTextListener(this);
		searchView.setOnSuggestionListener(this);

		if (mSuggestionsAdapter == null) {
			MatrixCursor cursor = new MatrixCursor(COLUMNS);
			cursor.addRow(new String[] { "1", "'Murica" });
			cursor.addRow(new String[] { "2", "Canada" });
			cursor.addRow(new String[] { "3", "Denmark" });
			mSuggestionsAdapter = new SuggestionsAdapter(getSupportActionBar()
					.getThemedContext(), cursor);
		}

		searchView.setSuggestionsAdapter(mSuggestionsAdapter);

		menu.add("Search")
				.setIcon(
						isLight ? R.drawable.ic_search_inverse
								: R.drawable.abs__ic_search)
				.setActionView(searchView)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		menu.add(0, MENU_WISH, Menu.NONE, R.string.application_name)
				.setIcon(R.drawable.chk2)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case MENU_WISH:

			// ------------------------------------------------------------------------------------------------------------//

			// startActivity(new
			// Intent(Music.this,ListViewImagesActivity.class));

			// ------------------------------------------------------------------------------------------------------------//
			break;

		}
		return false;
	}

	// int tvl,lv=0;

	public void onCreate(Bundle savedInstanceState) {

		setTheme(R.style.Theme_Sherlock);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book);

		// TextView tv1 = (TextView) findViewById(R.id.bookk);

		// TextView tv2 = (TextView) findViewById(R.id.mm);

		/*
		 * tv1.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * 
		 * //Toast.makeText(getApplicationContext(),title.getText().toString(),
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * Intent ii= new Intent(bookshelfdet.this,LatestappActivity.class);
		 * startActivity(ii);
		 * 
		 * 
		 * 
		 * }
		 * 
		 * });
		 * 
		 * tv2.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * 
		 * //Toast.makeText(getApplicationContext(),title.getText().toString(),
		 * Toast.LENGTH_SHORT).show();
		 * 
		 * Intent ii= new Intent(bookshelfdet.this,LatestappActivity.class);
		 * startActivity(ii);
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * });
		 */

		int tvl = 0;

		int numRow = 14;
		int numCol = 3;

		// String
		// partialNames[]={"Art","Body n Mind","Business","Cookings","Relationships","Fiction","Health n Fitness","Kids Fiction","Kids Non Fiction","Reference","Relegion","Self Help","Social Science","Travel"};

		TableLayout tblLayout = (TableLayout) findViewById(R.id.tblLayout);

		for (int i = 0; i < numRow; i++) {
			tvl = 0;
			HorizontalScrollView HSV = new HorizontalScrollView(this);
			HSV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT));

			TableRow tblRow = new TableRow(this);
			tblRow.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					280));
			// tblRow.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
			// LayoutParams.WRAP_CONTENT));
			tblRow.setBackgroundResource(R.drawable.bookshelf);

			for (int j = 0; j < numCol; j++) {

				LinearLayout lv = new LinearLayout(this);
				lv.setOrientation(LinearLayout.VERTICAL);
				ImageView imageView = new ImageView(this);

				imageView.setLayoutParams(new TableRow.LayoutParams(180, 180));

				// imageView.setLayoutParams(new
				// LayoutParams(LayoutParams.FILL_PARENT,
				// LayoutParams.FILL_PARENT));
				imageView.setImageResource(R.drawable.and);

				TextView textView = new TextView(this);
				textView.setText("Java Tester");
				textView.setTextColor(Color.parseColor("#000000"));

				// final TextView tvv=new TextView(this);
				// tvv.setLayoutParams(new LayoutParams(200,45));
				// tvv.setText(partialNames[i]);
				// tvv.setTextColor(Color.BLACK);
				// tvv.setTypeface(Typeface.DEFAULT_BOLD);
				// tvv.setClickable(true);

				/*
				 * tvv.setOnClickListener(new View.OnClickListener() {
				 * 
				 * @Override public void onClick(View v) { // do you work here
				 * 
				 * if(tvv.getText().toString().equalsIgnoreCase(("Art"))) {
				 * 
				 * 
				 * } } }); tvv.setTextSize(20);
				 */
				// tvv.setX(30);
				// tvv.setTop(5);

				textView.setSingleLine(false);
				textView.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
				// textView.setLayoutParams(new
				// LayoutParams(LayoutParams.WRAP_CONTENT,
				// LayoutParams.WRAP_CONTENT));

				if (tvl == 0) {
					// lv.addView(tvv);
					lv.addView(imageView);
					lv.addView(textView);
					// lv.setPadding(0,5,0,0);
					tvl++;
				}

				else {

					lv.addView(imageView);
					lv.addView(textView);

					// lv.setPadding(0, 50, 0, 0);

				}

				tblRow.addView(lv, j);
				// tblRow.addView(textView,j);
			}

			HSV.addView(tblRow);
			tblLayout.addView(HSV, i);
		}
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		return true;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		return false;
	}

	@Override
	public boolean onSuggestionSelect(int position) {
		return false;
	}

	@Override
	public boolean onSuggestionClick(int position) {
		Cursor c = (Cursor) mSuggestionsAdapter.getItem(position);
		String query = c.getString(c
				.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1));
		return true;
	}

	private class SuggestionsAdapter extends CursorAdapter {

		public SuggestionsAdapter(Context context, Cursor c) {
			super(context, c, 0);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(android.R.layout.simple_list_item_1,
					parent, false);
			return v;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView tv = (TextView) view;
			final int textIndex = cursor
					.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
			tv.setText(cursor.getString(textIndex));
		}
	}

}