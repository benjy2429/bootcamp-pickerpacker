package com.sky.bootcamp.pickerpacker.tabs;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sky.bootcamp.pickerpacker.adapters.OrderLineAdapter;
import com.sky.bootcamp.pickerpacker.models.OrderLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bca23 on 25/07/15.
 */
public class PickTab extends ListFragment {

    OrderLineAdapter orderLineAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<OrderLine> testOrders = new ArrayList<>();
        testOrders.add(new OrderLine("iPhone 6", "123456789"));
        testOrders.add(new OrderLine("iPhone 7", "235326"));
        testOrders.add(new OrderLine("iPhone 8", "5786585689"));
        testOrders.add(new OrderLine("iPhone 9", "6735735"));
        testOrders.add(new OrderLine("iPhone 10", "24562546"));
        testOrders.add(new OrderLine("iPhone 6", "123456789"));
        testOrders.add(new OrderLine("iPhone 7", "235326"));
        testOrders.add(new OrderLine("iPhone 8", "5786585689"));
        testOrders.add(new OrderLine("iPhone 9", "6735735"));
        testOrders.add(new OrderLine("iPhone 10", "24562546"));

        orderLineAdapter = new OrderLineAdapter(inflater);
        orderLineAdapter.updateData(testOrders);

        setListAdapter(orderLineAdapter);

        // Add swipe to refresh functionality
        final View listFragmentView = super.onCreateView(inflater, container, savedInstanceState);

        mSwipeRefreshLayout = new ListFragmentSwipeRefreshLayout(container.getContext());
        mSwipeRefreshLayout.addView(listFragmentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSwipeRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiateRefresh();
            }
        });

        return mSwipeRefreshLayout;

    }

    private class ListFragmentSwipeRefreshLayout extends SwipeRefreshLayout {

        public ListFragmentSwipeRefreshLayout(Context context) {
            super(context);
        }

        /**
         * As mentioned above, we need to override this method to properly signal when a
         * 'swipe-to-refresh' is possible.
         *
         * @return true if the {@link android.widget.ListView} is visible and can scroll up.
         */
        @Override
        public boolean canChildScrollUp() {
            final ListView listView = getListView();
            if (listView.getVisibility() == View.VISIBLE) {
                return canListViewScrollUp(listView);
            } else {
                return false;
            }
        }

    }

    private static boolean canListViewScrollUp(ListView listView) {
        if (android.os.Build.VERSION.SDK_INT >= 14) {
            // For ICS and above we can call canScrollVertically() to determine this
            return ViewCompat.canScrollVertically(listView, -1);
        } else {
            // Pre-ICS we need to manually check the first visible item and the child view's top
            // value
            return listView.getChildCount() > 0 &&
                    (listView.getFirstVisiblePosition() > 0
                            || listView.getChildAt(0).getTop() < listView.getPaddingTop());
        }
    }

    private void initiateRefresh() {
        new DummyBackgroundTask().execute();
    }


    private void onRefreshComplete() {
        // Stop the refreshing indicator
        setRefreshing(false);
    }

    private class DummyBackgroundTask extends AsyncTask<Void, Void, Boolean> {

        static final int TASK_DURATION = 3 * 1000; // 3 seconds

        @Override
        protected Boolean doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            // Tell the Fragment that the refresh has completed
            onRefreshComplete();
        }

    }

    public void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.setRefreshing(refreshing);
    }
}
