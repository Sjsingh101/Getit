package com.example.simranjeet.base64;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class ItemsListAdapter extends BaseAdapter {

    static class ViewHolder {
        CheckBox checkBox;
        ImageView icon;
        TextView text;
    }

    private Context context;
    private List<Item> list;

    ItemsListAdapter(Context c, List<Item> l) {
        context = c;
        list = l;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean isChecked(int position) {
        return list.get(position).checked;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        // reuse views
        ViewHolder viewHolder = new ViewHolder();
        if (rowView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row, null);

            viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.rowCheckBox);
            viewHolder.text = (TextView) rowView.findViewById(R.id.rowTextView);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

      //  viewHolder.checkBox.setChecked(list.get(position).checked);

        final String itemStr = list.get(position).ItemString;
        viewHolder.text.setText(itemStr);

        viewHolder.checkBox.setTag(position);



        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean newState = !list.get(position).getChecked();
                list.get(position).checked = newState;
                Toast.makeText(context,
                        itemStr + "setOnClickListener\nchecked: " + newState,
                        Toast.LENGTH_LONG).show();
            }
        });

        viewHolder.checkBox.setChecked(isChecked(position));

        return rowView;
    }
}
