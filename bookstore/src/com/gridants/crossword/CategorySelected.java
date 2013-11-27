package com.gridants.crossword;

import android.app.SearchManager;



import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.view.ViewPager;

import android.util.Log;

import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.gridants.utils.SuggestionsAdapter;
import com.gridants.utils.TabsAdapter;

public class CategorySelected extends SherlockFragmentActivity implements
		SearchView.OnQueryTextListener, SearchView.OnSuggestionListener {
	public static int position = 3;
	public static String message;
	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;
	TextView tabCenter;
	TextView tabText;


	int[] isFinal = { 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1 };

	

	private static final int MENU_WISH = Menu.FIRST;

	private static final String[] COLUMNS = { BaseColumns._ID,
			SearchManager.SUGGEST_COLUMN_TEXT_1, };

	private SuggestionsAdapter mSuggestionsAdapter;

	public boolean onCreateOptionsMenu(Menu menu) {
		// Used to put dark icons on light action bar
		// boolean isLight = R.style.Theme_Sherlock ==
		// R.style.Theme_Sherlock_Light;

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


			 startActivity(new Intent(this,WishList.class));


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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(R.string.title_activity_category);

		Intent intent = getIntent();
		message = intent.getStringExtra("Name");
		position = intent.getIntExtra("Position", 0);

		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);

		setContentView(mViewPager);

		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		getSupportActionBar().setIcon(R.drawable.cross);

		// here the background of the action bar and stacked action bar is set

		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(0xff000000));

		getSupportActionBar().setStackedBackgroundDrawable(
				getResources().getDrawable(R.drawable.stackedtabdual));

		mTabsAdapter = new TabsAdapter(this, mViewPager);

		
		
		
		Log.d("globalid",String.valueOf(global.globalidentity));
		if(global.globalidentity==1 && isFinal[position]==0)
		{

			mTabsAdapter.addTab(
					bar.newTab().setText(
							getResources().getString(R.string.label1)),
					FragmentCategoryList.class, null);
		}

		else if(global.globalidentity==2)
		{
			
			for(int i=0;i<global.catsubcat.length;i++)
				Log.d("catsubcat",global.catsubcat[i]);
			if(global.catsubcat.length>0)http://crossword.gridants.webfactional.com/booking?category="+bookcat
			{
				mTabsAdapter.addTab(
						bar.newTab().setText(
								getResources().getString(R.string.label1)),
						FragmentCategoryList.class, null);
			}
		}
		

		
		mTabsAdapter.addTab(bar.newTab().setText("Best Selling"),
				FragmentGridView.class, null); // change fragment to grid.
		
		
		mTabsAdapter.addTab(bar.newTab().setText("Top"), // have a list view
															// frag.
				FragmentListTop.class, null);
		
		
		mTabsAdapter.addTab(bar.newTab().setText("New Releases"),
				FragmentGridView.class, null); // have a grid view frag.


		if (isFinal[position] == 0) {
			mViewPager.setCurrentItem(1);
		}

	}
}