package cn.kairun.kairunsport.activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Random;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import cn.kairun.kairunsport.R;
import cn.kairun.kairunsport.fragment.SleepStatisticsFragment;
import cn.kairun.kairunsport.fragment.SportStatisticsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Random random;//用于产生随机数
    private Typeface mTf;
    private BarChart chart;
    private BarData data;
    private BarDataSet dataSet;
    private TextView tv_sport,tv_sleep,tv_sharp,tv_mouth_statistics;
    private ImageView iv_exit;
    private SportStatisticsFragment sportStatisticsFragment;
    private SleepStatisticsFragment sleepStatisticsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        tv_sport = (TextView)findViewById(R.id.tv_sport);
        tv_sleep = (TextView)findViewById(R.id.tv_sleep);
        tv_sharp = (TextView)findViewById(R.id.tv_sleep);
        tv_mouth_statistics = (TextView)findViewById(R.id.tv_mouth_statistics);
        iv_exit = (ImageView)findViewById(R.id.iv_exit);
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        chart = (BarChart) findViewById(R.id.chart1);
        tv_sport.setOnClickListener(this);
        tv_sleep.setOnClickListener(this);
        tv_sharp.setOnClickListener(this);
        tv_mouth_statistics.setOnClickListener(this);
        iv_exit.setOnClickListener(this);
        showSelectedFragment(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_sport:
                showSelectedFragment(0);
                break;
            case R.id.tv_sleep:
                showSelectedFragment(1);
                break;
            case R.id.tv_sharp:
                break;
            case R.id.tv_mouth_statistics:
                break;
            case R.id.iv_exit:
                finish();
                break;
        }
    }

    protected void showSelectedFragment(int position)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        hideFragment(fragmentTransaction); //隐藏fragment
        switch (position)
        {
            case 0:
                if (sportStatisticsFragment == null)
                {
                    sportStatisticsFragment = new SportStatisticsFragment();
                    fragmentTransaction.add(R.id.id_content_container, sportStatisticsFragment);
                } else
                {
                    fragmentTransaction.show(sportStatisticsFragment);
                }
                break;
            case 1:
                if (sleepStatisticsFragment == null)
                {
                    sleepStatisticsFragment = new SleepStatisticsFragment();
                    fragmentTransaction.add(R.id.id_content_container, sleepStatisticsFragment);
                } else
                {
                    fragmentTransaction.show(sleepStatisticsFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    protected void hideFragment(FragmentTransaction fragmentTransaction)
    {
        if (sportStatisticsFragment != null) {
            fragmentTransaction.hide(sportStatisticsFragment);
        }
        if (sleepStatisticsFragment != null) {
            fragmentTransaction.hide(sleepStatisticsFragment);
        }

    }
}
