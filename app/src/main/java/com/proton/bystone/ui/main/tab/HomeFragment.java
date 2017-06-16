package com.proton.bystone.ui.main.tab;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.proton.bystone.HorizontalScrollMenu.MainActivity_hh;
import com.proton.bystone.R;
import com.proton.bystone.bean.Resp;
import com.proton.bystone.bean.Student;
import com.proton.bystone.net.HttpClients;
import com.proton.bystone.ui.test.TestActivity;
import com.proton.bystone.utils.MDbUtils;
import com.proton.bystone.utils.UtilsOkHttp;
import com.proton.bystone.viewpagertest.MainActivityviewpager;
import com.proton.library.ui.MTFBaseFragment;
import com.proton.library.ui.annotation.MTFFragmentFeature;
import com.squareup.picasso.Picasso;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;



@MTFFragmentFeature(layout = R.layout.fragment_home)
public class HomeFragment extends MTFBaseFragment {
    @Bind(R.id.list_view_home)
    PullToRefreshListView list_view;

    @Bind(R.id.viewPager_home)
    ViewPager viewPager_home;
    Handler handler = new Handler();
    Runnable update_thread = new Runnable()
    {
        public void run()
        {
            viewPager_home.setAdapter(new viewpager_adapter());
            list_view.setAdapter(new HomeFragment.Listview_two());
            list_view.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>()
            {

                @Override
                public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                    Toast.makeText(getActivity(), "下拉", Toast.LENGTH_SHORT).show();
                    new HomeFragment.GetDataTask().execute();
                }
            });
        }
    };
    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(Bundle args) {
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initialize() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                handler.post(update_thread);

            }
        }).start();

    }

    // 异步方式模拟请求数据
    private class GetDataTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //list_view.notifyDataSetChanged();// 刷新Adapter
            list_view.onRefreshComplete();// 告诉它 我们已经在后台数据请求完毕
            Toast.makeText(getActivity(), "完成了", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Integer doInBackground(Integer... params) {

            try {
                Thread.sleep(2000);//暂停一下 只是为了效果更加明显
            } catch (Exception e) {
                e.printStackTrace();
            }
            publishProgress(0);//通知前台线程
            return 0;
        }
    }
    class Listview_two extends BaseAdapter {
        @Override
        public int getCount() {
            return 15;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi = View.inflate(getActivity(), R.layout.listviewfist3, null);

            ImageView image = (ImageView) vi.findViewById(R.id.svrc2);

            Picasso.with(getActivity()).load("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png").into(image);

            return vi;
        }

    }


    class viewpager_adapter extends PagerAdapter
    {
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.mipmap.eye_gray_1);
            viewPager_home.addView(imageView);
            return imageView;
        }
    }
}
