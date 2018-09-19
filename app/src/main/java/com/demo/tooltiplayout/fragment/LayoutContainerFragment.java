package com.demo.tooltiplayout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.tooltiplayout.R;
import com.demo.tooltiplayout.interfaces.FragmentRequester;

public class LayoutContainerFragment extends Fragment {
    FragmentRequester fragmentRequester;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fl_container, container, false);
        getChildFragmentManager().beginTransaction().replace(R.id.frameLayout, fragmentRequester.getFragment()).commit();
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentRequester) {
            fragmentRequester = (FragmentRequester) context;
        } else throw new RuntimeException("Implement FragmentRequester");
    }
}
