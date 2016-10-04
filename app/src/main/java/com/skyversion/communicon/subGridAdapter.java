package com.skyversion.communicon;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ss on 2016-08-17.
 */
public class subGridAdapter extends BaseAdapter{
    LayoutInflater inflater;
    private ArrayList<IconList> iconLists;
    private ArrayList<IconList> list = null;

    public subGridAdapter(Context context, int layout, ArrayList<IconList> iconLists){
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.iconLists = iconLists;
        list = new ArrayList<IconList>();
        list.addAll(iconLists);
    }

    @Override
    public int getCount() {
        return iconLists.size();
    }

    @Override
    public Object getItem(int position) {
        return iconLists.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView = inflater.inflate(R.layout.row_sub, null);

        ImageView mainImg = (ImageView) convertView.findViewById(R.id.searchIcon);

        mainImg.setImageResource(iconLists.get(position).getImg());

        return convertView;
    } // gridview column print

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void updateContent(ArrayList<IconList> iconLists){
        this.notifyDataSetChanged();
    }

    public void filter(String query){
        query = query.toLowerCase(Locale.getDefault());

        iconLists.clear();

        if(query.length() == 0)
            iconLists.addAll(list);

        else{
            for(IconList il : list){
                if(il.getText(MainActivity.common.selectedLocale).toLowerCase(Locale.getDefault()).contains(query))
                    iconLists.add(il);
            }
        }

        notifyDataSetChanged();
    }
}