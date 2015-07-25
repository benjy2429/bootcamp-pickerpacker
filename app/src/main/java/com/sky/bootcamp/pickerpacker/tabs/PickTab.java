package com.sky.bootcamp.pickerpacker.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.bootcamp.pickerpacker.R;

/**
 * Created by bca23 on 25/07/15.
 */
public class PickTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_pick,container,false);
        return v;
    }
}
