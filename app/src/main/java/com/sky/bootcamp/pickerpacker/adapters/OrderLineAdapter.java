package com.sky.bootcamp.pickerpacker.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sky.bootcamp.pickerpacker.R;
import com.sky.bootcamp.pickerpacker.models.OrderLine;

import java.util.ArrayList;

/**
 * Created by bca23 on 23/07/15.
 */
public class OrderLineAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<OrderLine> orders;

    public OrderLineAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
        orders = new ArrayList<OrderLine>();
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public OrderLine getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_orderline, null);
            holder = new ViewHolder();
            holder.product_name= (TextView) convertView.findViewById(R.id.text_product_name);
            holder.product_barcode= (TextView) convertView.findViewById(R.id.text_product_barcode);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        OrderLine order = getItem(position);

        Log.d("creating new order cell",order.getProductName());

        holder.product_name.setText(order.getProductName());
        holder.product_barcode.setText(order.getProductBarcode());

        return convertView;
    }

    private static class ViewHolder {
        public TextView product_name;
        public TextView product_barcode;
    }

    public void updateData(ArrayList<OrderLine> newOrders) {
        this.orders = newOrders;
        notifyDataSetChanged();
    }
}
