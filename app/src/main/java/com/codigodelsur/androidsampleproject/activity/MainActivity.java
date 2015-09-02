package com.codigodelsur.androidsampleproject.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codigodelsur.androidsampleproject.R;
import com.codigodelsur.androidsampleproject.adapter.ImageListAdapter;
import com.codigodelsur.androidsampleproject.network.ApiManager;
import com.codigodelsur.androidsampleproject.response.ImageSearchResponse;
import com.codigodelsur.androidsampleproject.view.StateView;

import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerViewImages;
    private ImageListAdapter mImageAdapter;
    private StateView mStateView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

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

        getContent();

        mSwipeRefreshLayout = (SwipeRefreshLayout) mStateView.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getContent();
            }
        });


    }

    private void getContent() {

        String query = "colors";

        //Set a random page in order to
        // get different content (or an empty result)
        // every time we refresh the list

        int page = new Random().nextInt(10), resultsPerPage = 15;

        ApiManager.getInstance().search(
                query,
                page,
                resultsPerPage, new Callback<ImageSearchResponse>() {
                    @Override
                    public void success(ImageSearchResponse imageSearchResponse, Response response) {
                        processSuccessResponse(imageSearchResponse);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        processErrorResponse(error);
                    }
                });

    }

    private void processErrorResponse(RetrofitError error) {
        mStateView.showContent();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void processSuccessResponse(ImageSearchResponse imageSearchResponse) {
        if (!imageSearchResponse.images.isEmpty()) {
            mImageAdapter.addAll(imageSearchResponse.images);
            mStateView.showContent();
        } else {
            mStateView.showEmpty();
        }

        mSwipeRefreshLayout.setRefreshing(false);
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
