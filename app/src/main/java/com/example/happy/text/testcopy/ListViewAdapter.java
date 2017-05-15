package com.example.happy.text.testcopy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2017/5/15.
 */

public class ListViewAdapter extends BaseAdapter {
    Context context;
    List<ListViewShow> myData = new ArrayList<>();

    public ListViewAdapter(Context context) {
        this.context = context;
    }

    public void init() {
        ListViewShow listViewShow = new ListViewShow();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("搞笑" + i);
        }
        listViewShow.setListData(list);
        listViewShow.setType("搞笑");

        ListViewShow listViewShow1 = new ListViewShow();
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list1.add("军事" + i);
        }
        listViewShow1.setListData(list1);
        listViewShow1.setType("军事");


        ListViewShow listViewShow2 = new ListViewShow();
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list2.add("新闻" + i);
        }
        listViewShow2.setListData(list2);
        listViewShow2.setType("新闻");


        ListViewShow listViewShow3 = new ListViewShow();
        List<String> list3 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list3.add("糗事" + i);
        }
        listViewShow3.setListData(list3);
        listViewShow3.setType("糗事");
        myData.add(listViewShow);
        myData.add(listViewShow1);
        myData.add(listViewShow2);
        myData.add(listViewShow3);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return myData.size();
    }

    @Override
    public Object getItem(int position) {
        return myData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder listViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_gridview, null);
            listViewHolder = new ListViewHolder(convertView);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ListViewHolder) convertView.getTag();
        }
        ListViewShow listViewShow = myData.get(position);
      //  listViewHolder.textView.setText(listViewShow.getType());
        listViewHolder.setGridViewData(listViewShow.getListData());
        return convertView;
    }

    public class ListViewHolder {
        ImageView imageView;
        TextView textView;
        MyGridView gridView;

        GridViewAdapter gridViewAdapter;

        public void setGridViewData(List<String> dataList) {
            gridViewAdapter.updateData(dataList);
        }

        public ListViewHolder(View view) {
          //  textView = (TextView) view.findViewById(R.id.titleText);
            gridView = (MyGridView) view.findViewById(R.id.grid_view);
            gridViewAdapter = new GridViewAdapter(context);
            gridView.setAdapter(gridViewAdapter);
        }

    }

}
