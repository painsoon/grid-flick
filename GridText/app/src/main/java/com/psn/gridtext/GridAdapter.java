package com.psn.gridtext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Author: shinianPan on 2017/4/1.
 * email : snow_psn@163.com
 */

public class GridAdapter extends BaseAdapter {


    private Context context;
    private int count;
    private int changId=-1;

    public GridAdapter(Context context,int count) {
        this.context = context;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RouteHodler routeHodler;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.route_view_layout
                    ,parent,false);
            routeHodler = new RouteHodler();
            routeHodler.pointView = (MyCircleImageView) convertView.findViewById(R.id.point_view);
            routeHodler.routeView = (ImageView) convertView.findViewById(R.id.route_view);
            convertView.setTag(routeHodler);
        }else{
            routeHodler = (RouteHodler) convertView.getTag();
        }
        if(position==changId){
            routeHodler.pointView.startFlick();
        }else{
            routeHodler.pointView.stopFlick();
        }

        return convertView;
    }

    private class RouteHodler{
        MyCircleImageView pointView;
        ImageView routeView;
    }

    public void setChangId(int position){
        this.changId = position;
    }

}
