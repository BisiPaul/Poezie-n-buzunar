package com.bistronic.poezieinbuzunar.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bistronic.poezieinbuzunar.R;

import java.util.ArrayList;

/**
 * Created by Larisa on 15.09.2017.
 */
public class PoemAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Poem> mDataSource;

    public PoemAdapter(Context context, ArrayList<Poem> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_view_single_row, parent, false);

        // Get title element (poem title)
        TextView titleTextView = (TextView) rowView.findViewById(R.id.poem_title);

        // Get subtitle element (author)
        TextView subtitleTextView = (TextView) rowView.findViewById(R.id.poem_subtitle);

        Poem poem = (Poem) getItem(position);
        titleTextView.setText(poem.title);
        subtitleTextView.setText(poem.author);

        return rowView;
    }
}
