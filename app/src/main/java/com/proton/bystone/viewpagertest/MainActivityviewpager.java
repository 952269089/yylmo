package com.proton.bystone.viewpagertest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.proton.bystone.R;
import com.proton.bystone.ui.main.MainActivity;

public class MainActivityviewpager extends Activity {

	private ViewPager mViewPager;
	// ��¼��ǰ��ҳ��
	private int mCount = 0;
	// ��ʼ
	public static final int START = -1;
	// ֹͣ
	public static final int STOP = -2;
	// ����
	public static final int UPDATE = -3;
	// ���ܴ������ĵ�ǰҳ����
	public static final int RECORD = -4;
	private List<ImageView> mList;
	private MyPagerAdapter mAdapter;
	private List<String> urlList;
	private ImageView dot1, dot2, dot3, dot0;
	private ImageView[] dots = new ImageView[4];
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case START:
				handler.sendEmptyMessageDelayed(UPDATE, 3000);
				break;
			case STOP:
				handler.removeMessages(UPDATE);
				break;
			case UPDATE:
				mCount++;
				mViewPager.setCurrentItem(mCount);
				break;
			case RECORD:
				mCount = msg.arg1;
				break;

			default:
				break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_viewpager);
		initView();
		init();
		setListener();
		mAdapter = new MyPagerAdapter(mList);
		mViewPager.setAdapter(mAdapter);
		int i = Integer.MAX_VALUE / 2 % mList.size();
		// Ĭ�����м䣬���û��������߽�
		mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - i);
		handler.sendEmptyMessage(START);
	}

	private void init() {
		// TODO Auto-generated method stub
		urlList = new ArrayList<String>();
		mList = new ArrayList<ImageView>();
		urlList.add("http://tupian.enterdesk.com/2015/saraxuss/04/17/gou/1/3.jpg");
		urlList.add("http://s2.nuomi.bdimg.com/upload/deal/2014/1/V_L/623682-1391756281052.jpg");
		urlList.add("http://media.chunyuyisheng.com/media/images/2013/05/15/9173ce6d26d9.jpg");
		urlList.add("http://img1.imgtn.bdimg.com/it/u=1856251628,4049763171&fm=21&gp=0.jpg");
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).displayer(new RoundedBitmapDisplayer(50))
				.displayer(new FadeInBitmapDisplayer(100)).cacheOnDisk(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();

		ImageView imageView;
		for (int i = 0; i < 4; i++) {
			imageView = new ImageView(MainActivityviewpager.this);
			imageView.setScaleType(ScaleType.FIT_XY);
			// ʹ�õ�ImageLoader�������ͼƬ��������Application���嵥�ļ���������ʹ��
			ImageLoader.getInstance().displayImage(urlList.get(i), imageView,
					options);
			mList.add(imageView);
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					System.out.println("==========m==" + mCount % mList.size());
					// ����д���ͼƬ�Ĳ��� mCount % mList.size()�������ĵڼ���ͼƬ
				}
			});
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		dot0 = (ImageView) findViewById(R.id.dot1);
		dot1 = (ImageView) findViewById(R.id.dot2);
		dot2 = (ImageView) findViewById(R.id.dot3);
		dot3 = (ImageView) findViewById(R.id.dot4);
		dots[0] = dot0;
		dots[1] = dot1;
		dots[2] = dot2;
		dots[3] = dot3;
		dot0.setSelected(true);


		//测试onActivityResult
		Button bt = (Button) findViewById(R.id.textView);
		bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.putExtra("OK", "OKOK");
				setResult(RESULT_OK, intent);

				finish();
			}
		});
	}

	private void setListener() {
		// TODO Auto-generated method stub
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				handler.sendMessage(Message.obtain(handler, RECORD, arg0, 0));
				// �����ǿ��Ƶ�ı仯
				int j = arg0 % mList.size();
				for (int i = 0; i < dots.length; i++) {
					dots[i].setSelected(false);
				}
				dots[j].setSelected(true);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				switch (arg0) {
				// ������ʱ�õ�ǰ�ֲ�ֹͣ
				case ViewPager.SCROLL_STATE_DRAGGING:
					handler.sendEmptyMessage(STOP);
					break;
				// ����ֹͣʱ�����ֲ�
				case ViewPager.SCROLL_STATE_IDLE:
					handler.sendEmptyMessage(START);
					break;
				}
			}
		});

	}
}
