package com.proton.bystone.HorizontalScrollMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.proton.bystone.R;

import com.proton.bystone.HorizontalScrollMenu.RVPIndicator;

public class MainActivity_hh extends FragmentActivity {
	private List<Fragment> mTabContents = new ArrayList<Fragment>();
	private FragmentPagerAdapter mAdapter;
	private ViewPager mViewPager;
	private List<String> mDatas = Arrays.asList("军事", "政治", "娱乐", "头条", "段子",
			"视频", "直播");

	private RVPIndicator mIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity_hh);

		initView();
		initDatas();
		// 设置Tab上的标题
		mIndicator.setTabItemTitles(mDatas);//mDatas
		mViewPager.setAdapter(mAdapter);
		// 设置关联的ViewPager

		mIndicator.setViewPager(mViewPager, 1);

		// PageChange监听
		/*
		 * mIndicator.setOnPageChangeListener(new PageChangeListener() {
		 * 
		 * @Override public void onPageSelected(int position) { // TODO
		 * Auto-generated method stub Log.w("TAG", "onPageSelected"); }
		 * 
		 * @Override public void onPageScrolled(int position, float
		 * positionOffset, int positionOffsetPixels) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * @Override public void onPageScrollStateChanged(int state) { // TODO
		 * Auto-generated method stub
		 * 
		 * } });
		 */
	}

	private void initDatas() {

		for (String data : mDatas) {
			VpSimpleFragment fragment = VpSimpleFragment.newInstance(data);
			mTabContents.add(fragment);
		}

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return mTabContents.size();
			}

			@Override
			public Fragment getItem(int position) {
				return mTabContents.get(position);
			}
		};
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.id_vp);
		mIndicator = (RVPIndicator) findViewById(R.id.id_indicator);
	}

}
