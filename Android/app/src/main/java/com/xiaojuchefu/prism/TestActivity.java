package com.xiaojuchefu.prism;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import com.eastwood.common.adapter.QuickAdapter;
import com.eastwood.common.adapter.QuickRecyclerAdapter;
import com.eastwood.common.adapter.ViewHelper;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("请随意点击下面控件后返回");

        setContentView(R.layout.activity_test);

        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 50;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = LayoutInflater.from(TestActivity.this).inflate(R.layout.item_view, container, false);
                Button button = view.findViewById(R.id.btn);
                button.setText("viewpage button " + position);
                container.addView(view);
                return view;
            }
        });

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("BUTTON " + i);
        }
        final ListView listView = findViewById(R.id.listview);
        listView.setAdapter(new QuickAdapter<String>(this, R.layout.item_view, data) {
            @Override
            protected void convert(int position, ViewHelper helper, String item) {
                helper.setText(R.id.btn, item);
            }
        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("onEvent2: ", "onItemClick pos = " + i);
//            }
//        });
//        listView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int item= listView.pointToPosition((int) event.getX(), (int) event.getY());
//                Log.d("onTouch22 event: ", (int) event.getX() + " , " + (int) event.getY());
//                Log.d("onTouch22: ", "---> 现在点击了ListView中第"+(item)+"个Item");
//
//                int[] location = new int[2];
//                listView.getLocationOnScreen(location);
//
//                int index = listView.getChildCount() -1;
//                View itemView = listView.getChildAt(index);
//
//
//                int[] locationItem = new int[2];
//                itemView.getLocationOnScreen(locationItem);
//
//                int indexList = listView.pointToPosition((int) locationItem[0]- location[0], (int) locationItem[1] - location[1]);
//                Log.d("onTouch22: ", "last child indexList = " + indexList);
//
//                return false;
//            }
//        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new QuickRecyclerAdapter<String>(this, data) {
            @Override
            protected void convert(int position, ViewHelper helper, String item) {
                helper.setText(R.id.btn, item);
            }

            @Override
            protected int getItemType(int position) {
                return 0;
            }

            @Override
            protected int getItemLayoutId(int type) {
                return R.layout.item_view;
            }
        });


        findViewById(R.id.btn_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(TestActivity.this)
                        .setTitle("this is a dialog")
                        .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();
            }
        });
        findViewById(R.id.btn_start_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, TestActivity.class));
            }
        });
    }

}
