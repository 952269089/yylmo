package com.proton.bystone.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.proton.bystone.R;

import com.proton.bystone.search.MainActivity_Search;
import com.proton.bystone.ui.main.tab.HomeFragment;
import com.proton.bystone.ui.main.tab.MaintainFragment;
import com.proton.bystone.ui.main.tab.MeFragment;
import com.proton.bystone.ui.main.tab.ShopFragment;
import com.proton.bystone.ui.main.tab.TestFragment;
import com.proton.bystone.ui.register.registerActivity;
import com.proton.library.ui.MTFBaseActivity;
import com.proton.library.ui.annotation.MTFActivityFeature;
import com.proton.library.widget.MTFTitleView;

import butterknife.Bind;


@MTFActivityFeature(layout = R.layout.activity_main, status_bar_color = R.color.colorPrimaryDark)
public class MainActivity extends MTFBaseActivity {

    private int index = 0;       //touched index
    private int currentIndex = 0;//current selected


    //Fragment
    private HomeFragment homeFragment;
    private MaintainFragment maintainFragment;
    private ShopFragment shopFragment;
    private MeFragment meFragment;
    //private TestFragment  tFragment;
    private Fragment[] fragments;
    private RelativeLayout[] layouts;//set background

    //click area
    @Bind(R.id.home_home_layout)
    RelativeLayout homeLayout;

    @Bind(R.id.home_maintain_layout)
    RelativeLayout maintainLayout;

    @Bind(R.id.home_shop_layout)
    RelativeLayout shopLayout;

    @Bind(R.id.home_me_layout)
    RelativeLayout meLayout;

    @Bind(R.id.home_search)
    EditText edtx_search;//搜索

    /*@Bind(R.id.home)
    RelativeLayout mehome;*/

    @Bind(R.id.titleView)
    MTFTitleView titleView;

    Activity mActivity;

    public Activity getActivity()
    {
        mActivity= getActivity();
        return mActivity;
    }
    @Override
    public void initialize(Bundle savedInstanceState) {

        //fragment init
        homeFragment    = new HomeFragment();
        maintainFragment = new MaintainFragment();
        shopFragment    = new ShopFragment();
        meFragment      = new MeFragment();
       /* tFragment      = new TestFragment();*/

        fragments = new Fragment[]{homeFragment, maintainFragment, shopFragment, meFragment };

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, homeFragment,"FRAGMENT_LEFT_MENU")
                .add(R.id.fragment_container, maintainFragment)
                .add(R.id.fragment_container, shopFragment)
                .add(R.id.fragment_container, meFragment)

                .hide(maintainFragment).hide(shopFragment)
                .hide(meFragment).show(homeFragment).commit();

        //layout
        layouts = new RelativeLayout[]{homeLayout, maintainLayout, shopLayout, meLayout};
        layouts[0].setBackgroundColor(getResources().getColor(R.color.mtf_gray_700));

        //搜索
        edtx_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(MainActivity.this,MainActivity_Search.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void backPressed() {
        animFinish();
    }

    /**
     * tab click
     * @param view
     */
    public void tabClick(View view) {
        switch (view.getId()){
            case R.id.home_home_layout:
                index = 0;
                titleView.setTitleText("Home");
                break;
            case R.id.home_maintain_layout:
                index = 1;
                titleView.setTitleText("Maintain");
                break;
            case R.id.home_shop_layout:
                index = 2;
                titleView.setTitleText("Shop");
                break;
            case R.id.home_me_layout:
                index = 3;
                titleView.setTitleText("Me");
                break;

        }

        //
        if(index != currentIndex){
            getSupportFragmentManager().beginTransaction()
                    .hide(fragments[currentIndex])
                    .show(fragments[index]).commit();
            layouts[currentIndex].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            layouts[index].setBackgroundColor(getResources().getColor(R.color.mtf_gray_700));
            currentIndex = index;
        }
    }
//测试
   public  HomeFragment zuce()
    {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment =(HomeFragment) supportFragmentManager.findFragmentByTag("FRAGMENT_LEFT_MENU");
        return homeFragment;

    }



}
