package com.gridants.crossword;


import java.util.ArrayList;

import java.util.Arrays;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.devsmart.android.ui.HorizontalListView;

public class Books extends SherlockActivity implements
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
				.setIcon(R.drawable.ic_search)
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

			break;

		}
		return false;
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

		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(android.R.layout.simple_list_item_1,
					parent, false);
			return v;
		}

		public void bindView(View view, Context context, Cursor cursor) {
			TextView tv = (TextView) view;
			final int textIndex = cursor
					.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
			tv.setText(cursor.getString(textIndex));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listviewdemo);

		HorizontalListView listview = (HorizontalListView) findViewById(R.id.listview);

		final ListView lvc = (ListView) findViewById(R.id.listcateg);

		// HorizontalListView listview2 = (HorizontalListView)
		// findViewById(R.id.listview1);

		listview.setAdapter(mAdapter);
		// listview2.setAdapter(mAdapter);

		String categories[] = { "Art", "Body n Mind", "Business", "Cookings",
				"Relationships", "Fiction", "Health n Fitness", "Kids Fiction",
				"Kids Non Fiction", "Reference", "Relegion", "Self Help",
				"Social Science", "Travel" };

		ArrayList<String> categoryList = new ArrayList<String>();
		categoryList.addAll(Arrays.asList(categories));

		// Create ArrayAdapter using the planet list.
		ArrayAdapter listAdapter = new ArrayAdapter<String>(this,
				R.layout.simple_list, categoryList);

		// Add more categories. If you passed a String[] instead of a
		// List<String>
		// into the ArrayAdapter constructor, you must not add more items.
		// Otherwise an exception will occur.

		// Set the ArrayAdapter as the ListView's adapter.
		lvc.setAdapter(listAdapter);

		lvc.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String selectedFromList = (lvc.getItemAtPosition(position)
						.toString());

				// if(selectedFromList.equalsIgnoreCase("Art"))
				// {

				startActivity(new Intent(view.getContext(),
						GridViewActivity.class));

				// }
			}
		});

	}

	private static String[] dataObjects = new String[] { "Book #1", "Book #2",
			"Book #3", "Book #4", "Book #5", "Book #6", "Book #7", "Book #8" };

	private BaseAdapter mAdapter = new BaseAdapter() {

		private OnClickListener mOnButtonClicked = new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						Books.this);
				builder.setMessage("hello from " + v);
				builder.setPositiveButton("Cool", null);
				builder.show();

			}
		};

		@Override
		public int getCount() {
			return dataObjects.length;
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
			TextView title = (TextView) retval.findViewById(R.id.title);
			// Button button = (Button) retval.findViewById(R.id.clickbutton);
			// button.setOnClickListener(mOnButtonClicked);
			title.setText(dataObjects[position]);

			return retval;
		}

	};

}
