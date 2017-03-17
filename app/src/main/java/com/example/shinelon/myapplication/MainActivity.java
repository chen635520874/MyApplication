package com.example.shinelon.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.shinelon.myapplication.bean.Tab;
import com.example.shinelon.myapplication.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

import fragment.CartFragment;
import fragment.CategoryFragment;
import fragment.HomeFragment;
import fragment.HotFragment;
import fragment.MineFragment;

public class MainActivity extends AppCompatActivity {
    private LayoutInflater mInflater;
    private FragmentTabHost mTabHost;
    private List<Tab> mTabs = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInflater = LayoutInflater.from(this);

        initTab();
    }
//    进行封装
    private void initTab() {

        Tab home = new Tab(R.string.home,R.drawable.selector_icon_home,HomeFragment.class);
        Tab hot = new Tab(R.string.hot,R.drawable.selector_icon_hot, HotFragment.class);
        Tab category = new Tab(R.string.category,R.drawable.selector_icon_category, CategoryFragment.class);
        Tab cart = new Tab(R.string.cart,R.drawable.selector_icon_cart, CartFragment.class);
        Tab mine = new Tab(R.string.mine,R.drawable.selector_icon_mine, MineFragment.class);

        mTabs.add(home);
        mTabs.add(hot);
        mTabs.add(category);
        mTabs.add(cart);
        mTabs.add(mine);

        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

      for (Tab tab:mTabs){

          TabHost.TabSpec tabSpec= mTabHost.newTabSpec(getString(tab.getTitle()));




          tabSpec.setIndicator( buildIndicator(tab));
          mTabHost.addTab(tabSpec,tab.getFragment(),null);
      }
//        可以去掉布局之间的小线条“|”
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTab(0);

    }
    private View buildIndicator(Tab tab){
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView) view.findViewById(R.id.icon);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());

        return view;
    }

}
