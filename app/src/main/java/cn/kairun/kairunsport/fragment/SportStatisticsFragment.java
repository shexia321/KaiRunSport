package cn.kairun.kairunsport.fragment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Random;

import cn.kairun.kairunsport.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportStatisticsFragment extends Fragment {
    private Random random;//用于产生随机数
    private Typeface mTf;
    private BarChart chart;
    private BarData data;
    private BarDataSet dataSet;

    public SportStatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport_statistics, container, false);
        mTf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        chart = (BarChart) view.findViewById(R.id.chart1);
        Legend mLegend = chart.getLegend(); // 设置比例图标示
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.BLACK);// 颜色
        chart.setTouchEnabled(false); // 设置是否可以触摸
        chart.setDragEnabled(false);// 是否可以拖拽
        chart.setScaleEnabled(false);// 是否可以缩放
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
        /**图表具体设置*/
        ArrayList<BarEntry> entries = new ArrayList<>();//显示条目
        ArrayList<String> xVals = new ArrayList<String>();//横坐标标签
        random=new Random();//随机数
        for(int i=0;i<=24;i++){
            float profit= random.nextFloat()*1000;
            //entries.add(BarEntry(float val,int positon);
            entries.add(new BarEntry(profit,i));
            xVals.add((i)+":00");
        }
        dataSet = new BarDataSet(entries, "步数");
        //0.6980  0.1333  0.1333
        dataSet.setColor(Color.rgb(255,0,0));
        //dataSet.setColors(ColorTemplate.PASTEL_COLORS );
        data = new BarData(xVals, dataSet);
        chart.setData(data);
        //设置Y方向上动画animateY(int time);
        chart.animateY(4000);
        //图表描述
        chart.setDescription("");
        return view;
    }
}
