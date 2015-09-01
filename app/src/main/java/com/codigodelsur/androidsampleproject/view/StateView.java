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

    private int indexLoading, indexContent, indexEmpty;
    private int LoadingResourceId, ContentResourceId, EmptyResourceId;
    private boolean hadAttrs;

    private View loadingView;

    public View getLoadingView() {
        return loadingView;
    }

    public boolean isShowingLoadingView() {
        return getDisplayedChild() == indexLoading;
    }

    private View contentView;

    public View getContentView() {
        return contentView;
    }

    public boolean isShowingContentView() {
        return getDisplayedChild() == indexContent;
    }

    private View emptyView;

    public View getEmptyView() {
        return emptyView;
    }

    public boolean isShowingEmptyView() {
        return (getDisplayedChild() == indexEmpty);
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
            LoadingResourceId = styledAttributes.getResourceId(R.styleable.StateView_loadingResource, 0);
            ContentResourceId = styledAttributes.getResourceId(R.styleable.StateView_contentResource, 0);
            EmptyResourceId = styledAttributes.getResourceId(R.styleable.StateView_emptyResource, 0);

            // Set the default animations for changing between View Flipper children
/*            this.setAnimateFirstView(false);
            this.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_fade_in));
            this.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_fade_out));*/

            styledAttributes.recycle();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

/*        peopleView = inflateAndAddResource(R.layout.view_people);
        groupView = inflateAndAddResource(R.layout.view_groups);
        weevView = inflateAndAddResource(R.layout.view_weevs);*/

        cacheViewIndices();
    }

    /**
     * Update the cached view indices for all states of the Loading Banana Peel view flipper.
     */
    protected void cacheViewIndices() {
        indexLoading = indexOfChild(loadingView);
        indexContent = indexOfChild(contentView);
        indexEmpty = indexOfChild(emptyView);
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
                setDisplayedChild(indexLoading);
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
                setDisplayedChild(indexContent);
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
                setDisplayedChild(indexEmpty);
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
