package com.codigodelsur.androidsampleproject.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codigodelsur.androidsampleproject.R;
import com.codigodelsur.androidsampleproject.adapter.ImageListAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewImages;
    private ImageListAdapter mImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerViewImages = (RecyclerView) findViewById(R.id.recycler_view_images);
        mRecyclerViewImages.setLayoutManager(new CustomLayoutManager(this));

        mImageAdapter = new ImageListAdapter();
        mRecyclerViewImages.setAdapter(mImageAdapter);



    }

    static class CustomLayoutManager extends LinearLayoutManager {

        public CustomLayoutManager(Context context) {
            super(context);
        }

        @Override
        public RecyclerView.LayoutParams generateDefaultLayoutParams() {
            return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.MATCH_PARENT);
        }
    }
}
