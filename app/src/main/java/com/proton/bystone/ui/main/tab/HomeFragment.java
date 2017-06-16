package com.proton.bystone.ui.main.tab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.proton.bystone.R;
import com.proton.library.ui.MTFBaseFragment;
import com.proton.library.ui.annotation.MTFFragmentFeature;

import butterknife.Bind;


@MTFFragmentFeature(layout = R.layout.fragment_home)
public class HomeFragment extends MTFBaseFragment {
    @Bind(R.id.list_view_home)
    PullToRefreshListView list_view;

    ViewPager viewById;
    GridView gridview;
    Handler handler = new Handler();
    Runnable update_thread = new Runnable()
    {
        public void run()
        {

            list_view.setAdapter(new HomeFragment.Listview());
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

                ILoadingLayout startLabels = list_view
                        .getLoadingLayoutProxy(true, false);
                startLabels.setPullLabel("疯狂刷新");// 刚下拉时，显示的提示
                startLabels.setRefreshingLabel("疯狂刷新");// 刷新时
                startLabels.setReleaseLabel("疯狂刷新");// 下来达到一定距离时，显示的提示

                ILoadingLayout endLabels = list_view.getLoadingLayoutProxy(
                        false, true);
                endLabels.setPullLabel("疯狂刷新");// 刚下拉时，显示的提示
                endLabels.setRefreshingLabel("疯狂刷新");// 刷新时
                endLabels.setReleaseLabel("疯狂刷新");// 下来达到一定距离时，显示的提示

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
                Thread.sleep(500);//暂停一下 只是为了效果更加明显
            } catch (Exception e) {
                e.printStackTrace();
            }
            publishProgress(0);//通知前台线程
            return 0;
        }
    }
    class Listview extends BaseAdapter {
        @Override
        public int getCount() {
            return 1;
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
            View vi = View.inflate(getActivity(), R.layout.home_listview, null);
            viewById = (ViewPager)vi.findViewById(R.id.viewPager_home);
            viewById.setAdapter(new viewpager_adapter());
            //Picasso.with(getActivity()).load("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png").into(image);

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
            View vi = View.inflate(getActivity(), R.layout.home_gradview, null);
            gridview=(GridView)vi.findViewById(R.id.home_gview);
            gridview.setAdapter(new Gradview_adapter());
            viewById.addView(gridview);
            return gridview;
        }
    }

    class Gradview_adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 8;
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
            View vi = View.inflate(getActivity(), R.layout.home_listview_gradview, null);
            TextView hometitle=(TextView)vi.findViewById(R.id.home_gradview_shoptitle);


            return vi;
        }
    }



    }
