package com.demo.tooltiplayout.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.demo.tooltiplayout.fragment.BlankFragment;
import com.demo.tooltiplayout.R;
import com.demo.tooltiplayout.interfaces.FragmentRequester;
import com.nhaarman.supertooltips.ToolTip;
import com.nhaarman.supertooltips.ToolTipRelativeLayout;
import com.nhaarman.supertooltips.ToolTipView;

public class MainActivity extends AppCompatActivity implements FragmentRequester ,View.OnClickListener{
    ToolTipRelativeLayout rlToolTip;
    ToolTip toolTip;
    FragmentRequester fragmentRequester = new FragmentRequester() {
        @Override
        public Fragment getFragment() {
            return new BlankFragment();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        rlToolTip = (ToolTipRelativeLayout) findViewById(R.id.rlToolTip);
        findViewById(R.id.btnCenter).setOnClickListener(this);
        findViewById(R.id.btnTopStart).setOnClickListener(this);
        findViewById(R.id.btnBottomEnd).setOnClickListener(this);
    }

    private void loadFragment(View anchore) {
        View view = getLayoutInflater().inflate(R.layout.layout_container, null);
        toolTip = new ToolTip().withContentView(view).withAnimationType(ToolTip.AnimationType.NONE);
        final ToolTipView myToolTipView = rlToolTip.showToolTipForView(toolTip, anchore);

        myToolTipView.setOnToolTipViewClickedListener(new ToolTipView.OnToolTipViewClickedListener() {
            @Override
            public void onToolTipViewClicked(ToolTipView toolTipView) {
                myToolTipView.remove();
                Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public Fragment getFragment() {
        return fragmentRequester.getFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCenter:
            case R.id.btnTopStart:
            case R.id.btnBottomEnd:
                fragmentRequester = new FragmentRequester() {
                    @Override
                    public Fragment getFragment() {
                        return new BlankFragment();
                    }
                };
                loadFragment(v);
                break;
        }
    }
}
