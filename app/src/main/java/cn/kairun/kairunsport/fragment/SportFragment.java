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
import cn.kairun.kairunsport.myview.SportCustomView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportFragment extends Fragment {

    private SportCustomView sportCustomView;
    private CustomerScrollView customerScrollView;
    private LinearLayout linearLayoutLoad;
    public void startDraw(){

        sportCustomView.startDraw();
    }

    public void initDraw(){
        sportCustomView.initDraw();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);
        linearLayoutLoad = (LinearLayout)view.findViewById(R.id.layout_linearload);
        sportCustomView = (SportCustomView)view.findViewById(R.id.cks_myview);
        customerScrollView = (CustomerScrollView)view.findViewById(R.id.cs_scrollview);
        customerScrollView.setOnRefreshListener(new CustomerScrollView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                linearLayoutLoad.setVisibility(View.VISIBLE);
            }
        });
        return view ;
    }

}
