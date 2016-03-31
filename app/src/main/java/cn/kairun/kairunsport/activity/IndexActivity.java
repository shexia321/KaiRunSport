package cn.kairun.kairunsport.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kairun.kairunsport.R;
import cn.kairun.kairunsport.adapter.FragAdapter;
import cn.kairun.kairunsport.adapter.ListViewMenuAdapter;
import cn.kairun.kairunsport.fragment.SleepFragment;
import cn.kairun.kairunsport.fragment.SportFragment;
import cn.kairun.kairunsport.myview.MyViewPager;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener ,ViewPager.OnPageChangeListener{
    private DrawerLayout drawer;
    private LinearLayout leftLinear;
    private LinearLayout rightLinear;
//    private NavigationView leftNavigation;
//    private NavigationView rightNavigation;
    private List<Map<String, Object>> listItems;
    private ListView listView;
    private ImageView iv_bracelet;
    private ImageView iv_own;
    List fragmentList;
    SportFragment sportFragment;
    SleepFragment sleepFragment;
    ViewPager viewPager;
    MyViewPager myViewPager;
    private ImageView[] points;   //底部小点的图片
    private int currentIndex;     //记录当前选中位置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initView();
    }

    public void initView(){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        iv_bracelet = (ImageView)findViewById(R.id.iv_bracelet);
        iv_own = (ImageView)findViewById(R.id.iv_own);
        leftLinear = (LinearLayout)findViewById(R.id.nav_left);
        rightLinear = (LinearLayout)findViewById(R.id.nav_right);
        myViewPager = (MyViewPager)findViewById(R.id.viewpager);
        listView = (ListView)findViewById(R.id.lv_menu);
        listItems = getListItems();
        ListViewMenuAdapter listViewMenuAdapter = new ListViewMenuAdapter(this,listItems);
        listView.setAdapter(listViewMenuAdapter);
//        leftNavigation = (NavigationView)findViewById(R.id.nav_left);
//        rightNavigation = (NavigationView)findViewById(R.id.nav_right);
        iv_bracelet.setOnClickListener(this);
        iv_own.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        fragmentList = new ArrayList<Fragment>();
        sportFragment = new SportFragment();
        sleepFragment = new SleepFragment();
        fragmentList.add(sportFragment);
        fragmentList.add(sleepFragment);
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragmentList);
        myViewPager.setpagerCount(fragmentList.size());
        myViewPager.setAdapter(adapter);
        myViewPager.addOnPageChangeListener(this);
        initPoint();
    }
    private List<Map<String, Object>> getListItems() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        int[] titleimagearray = {R.drawable.duanxintuisong,R.drawable.lvseshezhitubiao,R.drawable.yundongguiji,R.drawable.weixinpengyouquan,R.drawable.zhuangban,R.drawable.guanyuwo};
        String[] titleTextArray = {"我的信息","设置目标","运动轨迹","朋友圈","个性装扮","关于"};
        for (int i = 0; i < titleimagearray.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("imagetitle",titleimagearray[i]);
            map.put("title", titleTextArray[i]);               //鍥剧墖璧勬簮
            map.put("smallarrow", R.drawable.xiaojiantou);
            listItems.add(map);//鐗╁搧鏍囬
        }
        return listItems;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    //左边菜单开关事件
    public void openLeftLayout() {
        if (drawer.isDrawerOpen(leftLinear)) {
            drawer.closeDrawer (leftLinear);
        } else {
            drawer.openDrawer(leftLinear);
        }
    }

    // 右边菜单开关事件

    public void openRightLayout() {
        if (drawer.isDrawerOpen(rightLinear)) {
            drawer.closeDrawer(rightLinear);
        } else {
            drawer.openDrawer(rightLinear);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_bracelet:
                openLeftLayout();
                break;
            case R.id.iv_own:
                openRightLayout();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        setCurDot(position);
    }

    @Override
    public void onPageSelected(int position) {
        myViewPager.setCurrentIndex(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 初始化底部小点
     */
    private void initPoint(){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llayout_dot);
        points = new ImageView[2];
        //循环取得小点图片
        for (int i = 0; i < points.length; i++) {
            //得到一个LinearLayout下面的每一个子元素
            points[i] = (ImageView) linearLayout.getChildAt(i);
            //默认都设为灰色
            points[i].setEnabled(true);
            //给每个小点设置监听
            points[i].setOnClickListener(this);
            //设置位置tag，方便取出与当前位置对应
            points[i].setTag(i);
        }

        //设置当面默认的位置
        currentIndex = 0;
        //设置为白色，即选中状态
        points[currentIndex].setEnabled(false);
    }

    /**
     * 设置当前页面的位置
     */
    private void setCurView(int position){
        if (position < 0 || position >= points.length) {
            return;
        }
        viewPager.setCurrentItem(position);
    }

    /**
     * 设置当前的小点的位置
     */
    private void setCurDot(int positon){
        if (positon < 0 || positon > points.length - 1 || currentIndex == positon) {
            return;
        }
        points[positon].setEnabled(false);
        points[currentIndex].setEnabled(true);

        currentIndex = positon;
    }
}
