package com.skyversion.communicon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by ss on 2016-08-15.
 */
public class GridAdapter extends BaseAdapter {
    LayoutInflater inflater;
    private ArrayList<BookmarkList> bookmarkLists;

    public GridAdapter(Context context, int layout, ArrayList<BookmarkList> bookmarkLists){
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bookmarkLists = bookmarkLists;
    }
    @Override
    public int getCount() {
        return bookmarkLists.size();
    }

    @Override
    public Object getItem(int position) {
        return bookmarkLists.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView = inflater.inflate(R.layout.row, null);

        ImageView mainImg = (ImageView) convertView.findViewById(R.id.mainImg);
        ImageView subImg = (ImageView) convertView.findViewById(R.id.subImg);

        mainImg.setImageResource(bookmarkLists.get(position).getMainImg());
        subImg.setImageResource(bookmarkLists.get(position).getSubImg());
        return convertView;
    } // gridview column print

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateContent(ArrayList<BookmarkList> bookmarkList){
        this.notifyDataSetChanged();
    }
}