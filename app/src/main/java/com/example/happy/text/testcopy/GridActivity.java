package com.example.happy.text.testcopy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2017/5/15.
 */

public class GridActivity extends Activity {
    ListView mListView;
    ListViewAdapter listViewAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mListView = (ListView)this.findViewById(R.id.myListView);
        listViewAdapter = new ListViewAdapter(GridActivity.this);
        mListView.setAdapter(listViewAdapter);
        listViewAdapter.init();
    }

}
