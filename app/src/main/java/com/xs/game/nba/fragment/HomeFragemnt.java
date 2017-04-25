package com.xs.game.nba.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xs.game.nba.R;

/**
 * Created by a on 2017/4/25.
 */

public class HomeFragemnt extends BaseFragment {
    private View view = null;

    public HomeFragemnt() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (view != null) {
//            ViewGroup parent = (ViewGroup) view.getParent();
//            if (parent != null) {
//                parent.removeView(view);
//            }
//            return view;
//        }
        view = inflater.inflate(R.layout.fragment_home, null);
        return view;
    }
}
