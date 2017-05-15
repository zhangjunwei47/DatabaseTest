package com.example.happy.text.testcopy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2017/5/15.
 */

public class GridViewAdapter extends BaseAdapter {
    Context context;
    List<String> myListData = new ArrayList<>();
    List<String> myListDataSon = new ArrayList<>();
    public static final int MIN_LINE = 2;
    public static final int LINE_NUMBER = 3;
    boolean isShowAll = false;

    public GridViewAdapter(Context context) {
        this.context = context;
    }

    public void showOther() {
        isShowAll = !isShowAll;
        notifyDataSetChanged();
    }

    public void updateData(List<String> myData) {
        myListData = myData;
        if (myListData != null && myListData.size() > MIN_LINE * LINE_NUMBER) {
            for (int i = 0; i < MIN_LINE * LINE_NUMBER - 1; i++) {
                myListDataSon.add(myListData.get(i));
            }
        } else {
            isShowAll = true;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (isShowAll) {
            return myListData.size() + LINE_NUMBER - myListData.size() % 3;
        } else {
            return myListDataSon.size() + 1;
        }
    }

    @Override
    public Object getItem(int position) {
        if (isShowAll) {
            return myListData.get(position);
        } else {
            return myListDataSon.get(position);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final GridViewHolder gridViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview, null);
            gridViewHolder = new GridViewHolder(convertView);
            convertView.setTag(gridViewHolder);
        } else {
            gridViewHolder = (GridViewHolder) convertView.getTag();
        }
        gridViewHolder.mImageView.setVisibility(View.GONE);
        if (isShowAll || myListData.size() < MIN_LINE * LINE_NUMBER) {
            String content = " ";
            if (position < myListData.size()) {
                content = myListData.get(position);
            }
            gridViewHolder.mTextView.setText(content);
            if (position >= myListData.size() && (position + 1) % 3 == 0 && myListData.size() > MIN_LINE * LINE_NUMBER) {
                gridViewHolder.mImageView.setVisibility(View.VISIBLE);
                gridViewHolder.mImageView.setBackgroundResource(R.mipmap.bt_radio_detail_textview_collapse);
            }
        } else {
            String content = " ";
            if (position < myListDataSon.size()) {
                content = myListDataSon.get(position);
            }
            gridViewHolder.mTextView.setText(content);
            if (position == myListDataSon.size()) {
                gridViewHolder.mImageView.setVisibility(View.VISIBLE);
                gridViewHolder.mImageView.setBackgroundResource(R.mipmap.bt_radio_detail_textview_expandable);
            }
        }
        gridViewHolder.position = position;
        gridViewHolder.grid_item_main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = gridViewHolder.position;
                if (isShowAll) {
                    if (index >= myListData.size() && (index + 1) % 3 == 0 && myListData.size() > MIN_LINE * LINE_NUMBER) {
                        showOther();
                    } else {
                        if (index < myListData.size()) {
                            Toast.makeText(context, "选择了: " + myListData.get(index), Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (index == myListDataSon.size()) {
                        showOther();
                    } else {
                        Toast.makeText(context, "选择了: " + myListDataSon.get(index), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        convertView.setTag(gridViewHolder);
        return convertView;
    }

    public class GridViewHolder {
        private RelativeLayout grid_item_main_layout;
        private TextView mTextView;
        private ImageView mImageView;
        public int position;

        GridViewHolder(View view) {
            grid_item_main_layout = (RelativeLayout) view.findViewById(R.id.grid_item_main_layout);
            mTextView = (TextView) view.findViewById(R.id.text_view);
            mImageView = (ImageView) view.findViewById(R.id.image_view);
        }
    }
}
