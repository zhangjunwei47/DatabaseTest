package com.example.happy.text.testcopy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2017/5/15.
 */

public class GridActivity extends Activity {
    GridView mGridView;
    GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        mGridView = (GridView) findViewById(R.id.grid_view);
        gridViewAdapter = new GridViewAdapter(GridActivity.this);
        mGridView.setAdapter(gridViewAdapter);
        initData();
    }

    private void initData() {
        List<String> dlist = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            String xxx = "分类" + i;
            dlist.add(xxx);
        }

        gridViewAdapter.updateData(dlist);
    }


}
