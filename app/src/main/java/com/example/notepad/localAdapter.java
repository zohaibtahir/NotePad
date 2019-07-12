package com.example.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class localAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<utility> finalList;
    localAdapter(Context context,ArrayList<utility> finalList){
        this.context=context;
        this.finalList=finalList;
    }
    @Override
    public int getCount() {
        return finalList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView dateview,titleview,notnoview;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_temp,null);
        dateview = v.findViewById(R.id.datetime);
        titleview=v.findViewById(R.id.titleitem);
        notnoview = v.findViewById(R.id.notno);
        dateview.setText(finalList.get(i).getDateTime());
        titleview.setText(finalList.get(i).getTitleText());
        notnoview.setText(String.valueOf(i+1));
        return v;
    }
}
