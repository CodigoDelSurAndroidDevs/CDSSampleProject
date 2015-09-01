package com.codigodelsur.androidsampleproject.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codigodelsur.androidsampleproject.R;
import com.codigodelsur.androidsampleproject.adapter.ImageListAdapter;
import com.codigodelsur.androidsampleproject.network.ApiManager;
import com.codigodelsur.androidsampleproject.response.HighResResponse;
import com.codigodelsur.androidsampleproject.view.StateView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewImages;
    private ImageListAdapter mImageAdapter;
    private StateView mStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStateView = (StateView) findViewById(R.id.state_view);

        mRecyclerViewImages = (RecyclerView) mStateView.findViewById(R.id.recycler_view_images);
        mRecyclerViewImages.setLayoutManager(new CustomLayoutManager(this));

        mImageAdapter = new ImageListAdapter();
        mRecyclerViewImages.setAdapter(mImageAdapter);

        mStateView.showLoading();

        ApiManager.getInstance().search("colors", new Callback<HighResResponse>() {
            @Override
            public void success(HighResResponse highResResponse, Response response) {
                processSuccessResponse(highResResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                processErrorResponse(error);
            }
        });

    }

    private void processErrorResponse(RetrofitError error) {
        mStateView.showContent();
    }

    private void processSuccessResponse(HighResResponse highResResponse) {
        if (!highResResponse.images.isEmpty()) {
            mImageAdapter.addAll(highResResponse.images);
            mStateView.showContent();
        } else {
            mStateView.showEmpty();
        }
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
