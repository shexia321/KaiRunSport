package cn.kairun.kairunsport.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.kairun.kairunsport.R;
import cn.kairun.kairunsport.myview.CustomerScrollView;
import cn.kairun.kairunsport.myview.SleepCustomView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SleepFragment extends Fragment {


    private SleepCustomView sleepCustomView;
    private CustomerScrollView customerScrollView;
    public void startDraw(){
        sleepCustomView.startDraw();
    }
    public void initDraw(){
        sleepCustomView.initDraw();
    }
    private LinearLayout linearLayoutLoad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);
        linearLayoutLoad = (LinearLayout)view.findViewById(R.id.layout_linearload);
        sleepCustomView = (SleepCustomView)view.findViewById(R.id.cks_myview);
        customerScrollView = (CustomerScrollView)view.findViewById(R.id.cs_scrollview);
        customerScrollView.setOnRefreshListener(new CustomerScrollView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                linearLayoutLoad.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

}
