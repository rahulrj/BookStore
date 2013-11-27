package com.gridants.utils;
 
import java.util.ArrayList;

import com.gridants.crossword.R;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class Copy_2_of_ImageAdapter extends BaseAdapter
{
    private ArrayList<String> titlearray;
    private ArrayList<String> originalpricearray;
    private ArrayList<String> offeredpricearray;
    private ArrayList<Integer> imageid;
    private Activity activity;
 
    public Copy_2_of_ImageAdapter(Activity activity,
    					ArrayList<String> titlearray,
			    		ArrayList<String> originalpricearray,
			    		ArrayList<String> offeredpricearray,
			    		ArrayList<Integer> imageid) {
    	
        super();
        this.titlearray = titlearray;
        this.originalpricearray = originalpricearray;
        this.offeredpricearray = offeredpricearray;
        this.imageid = imageid;
        this.activity = activity;
    }
 
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return titlearray.size();
    }
 
    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return titlearray.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
 
    public static class ViewHolder
    {
        
        public TextView title;
        public TextView originalprice;
        public TextView offeredprice;
        public ImageView image;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();
 
        if(convertView==null)
        {
            view = new ViewHolder();
            
            convertView = inflator.inflate(R.layout.viewitem, null);
         
            view.title = (TextView) convertView.findViewById(R.id.title);
            view.originalprice = (TextView) convertView.findViewById(R.id.originalprice);
            view.offeredprice = (TextView) convertView.findViewById(R.id.offeredprice);
            view.image = (ImageView) convertView.findViewById(R.id.image);
 
            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }
 
        
        
        view.title.setText(titlearray.get(position));

        view.originalprice.setText(originalpricearray.get(position));

        view.originalprice.setPaintFlags(view.originalprice.getPaintFlags()
				| Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

		view.offeredprice.setText(offeredpricearray.get(position));

		view.image.setImageResource(imageid.get(position));

 
        return convertView;
    }
}