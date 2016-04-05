package cn.kairun.kairunsport.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.kairun.kairunsport.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SleepStatisticsFragment extends Fragment {


    public SleepStatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sleep_statistics, container, false);
    }

}
