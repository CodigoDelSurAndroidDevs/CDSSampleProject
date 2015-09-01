package com.codigodelsur.androidsampleproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewFlipper;

import com.codigodelsur.androidsampleproject.R;


/**
 * Based on https://github.com/smbarne/AndroidReusableUI.
 */
public class StateView extends ViewFlipper {

    private int mIndexLoading, mIndexContent, mIndexEmpty;
    private int mLoadingResourceId, mContentResourceId, mEmptyResourceId;
    private boolean hadAttrs;

    private View loadingView;

    public View getLoadingView() {
        return loadingView;
    }

    public boolean isShowingLoadingView() {
        return getDisplayedChild() == mIndexLoading;
    }

    private View contentView;

    public View getContentView() {
        return contentView;
    }

    public boolean isShowingContentView() {
        return getDisplayedChild() == mIndexContent;
    }

    private View emptyView;

    public View getEmptyView() {
        return emptyView;
    }

    public boolean isShowingEmptyView() {
        return (getDisplayedChild() == mIndexEmpty);
    }

    public StateView(Context context) {
        super(context);
        init(context, null);
    }

    public StateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        hadAttrs = (attrs != null);
        if (attrs != null) {
            TypedArray styledAttributes = getContext().getApplicationContext().obtainStyledAttributes(attrs, R.styleable.StateView);
            // Load the resource IDs for each property from the styled attributes set
            mLoadingResourceId = styledAttributes.getResourceId(R.styleable.StateView_loadingResource, 0);
            mContentResourceId = styledAttributes.getResourceId(R.styleable.StateView_contentResource, 0);
            mEmptyResourceId = styledAttributes.getResourceId(R.styleable.StateView_emptyResource, 0);

            styledAttributes.recycle();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        loadingView = inflateAndAddResource(mLoadingResourceId);
        contentView = inflateAndAddResource(mContentResourceId);
        emptyView = inflateAndAddResource(mEmptyResourceId);

        cacheViewIndices();
    }

    /**
     * Update the cached view indices for all states of the Loading Banana Peel view flipper.
     */
    protected void cacheViewIndices() {
        mIndexLoading = indexOfChild(loadingView);
        mIndexContent = indexOfChild(contentView);
        mIndexEmpty = indexOfChild(emptyView);
    }

    /**
     * Inflate and set the content view based on a layout resource.
     *
     * @param loadingViewLayoutResourceId the content view's layout resource id.
     * @return the inflated content view.
     */
    public View setLoadingView(int loadingViewLayoutResourceId) {
        if (loadingViewLayoutResourceId != 0) {
            View contentView = LayoutInflater.from(getContext()).inflate(loadingViewLayoutResourceId, this, false);
            return setLoadingView(contentView);
        }
        return null;
    }

    /**
     * Inflate and set the content view based on a layout resource.
     *
     * @param contentViewLayoutResourceId the content view's layout resource id.
     * @return the inflated content view.
     */
    public View setContentView(int contentViewLayoutResourceId) {
        if (contentViewLayoutResourceId != 0) {
            View contentView = LayoutInflater.from(getContext()).inflate(contentViewLayoutResourceId, this, false);
            return setContentView(contentView);
        }
        return null;
    }

    /**
     * Inflate and set the content view based on a layout resource.
     *
     * @param emptyViewLayoutResourceId the content view's layout resource id.
     * @return the inflated content view.
     */
    public View setEmptyView(int emptyViewLayoutResourceId) {
        if (emptyViewLayoutResourceId != 0) {
            View contentView = LayoutInflater.from(getContext()).inflate(emptyViewLayoutResourceId, this, false);
            return setEmptyView(contentView);
        }
        return null;
    }

    /**
     * Set the content view.
     *
     * @param loadingView the content view.
     * @return the set content view.
     */
    public View setLoadingView(View loadingView) {
        if (this.loadingView != null) {
            removeView(this.loadingView);
        }

        this.loadingView = loadingView;

        addView(loadingView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        cacheViewIndices();

        return this.loadingView;
    }

    /**
     * Set the content view.
     *
     * @param contentView the content view.
     * @return the set content view.
     */
    public View setContentView(View contentView) {
        if (this.contentView != null) {
            removeView(this.contentView);
        }

        this.contentView = contentView;

        addView(contentView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        cacheViewIndices();

        return this.contentView;
    }

    /**
     * Set the content view.
     *
     * @param emptyView the content view.
     * @return the set content view.
     */
    public View setEmptyView(View emptyView) {
        if (this.emptyView != null) {
            removeView(this.emptyView);
        }

        this.emptyView = emptyView;

        addView(emptyView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        cacheViewIndices();

        return this.emptyView;
    }

    /**
     * Set the current state of the view flipper to show the content view.
     */
    public void showLoading() {
        this.post(new Runnable() {
            @Override
            public void run() {
                setDisplayedChild(mIndexLoading);
            }
        });
    }

    /**
     * Set the current state of the view flipper to show the content view.
     */
    public void showContent() {
        this.post(new Runnable() {
            @Override
            public void run() {
                setDisplayedChild(mIndexContent);
            }
        });
    }

    /**
     * Set the current state of the view flipper to show the content view.
     */
    public void showEmpty() {
        this.post(new Runnable() {
            @Override
            public void run() {
                setDisplayedChild(mIndexEmpty);
            }
        });
    }

    /**
     * Inflate the given layout and add it to the view hierarchy.  Sets the member variable
     * for referencing the inflated banana peel view.
     *
     * @param layoutResourceId - The layout resource id.
     * @return the inflated View.
     */
    private View inflateAndAddResource(int layoutResourceId) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View content = inflater.inflate(layoutResourceId, this, false);
        addView(content);
        cacheViewIndices();
        return content;
    }

}
