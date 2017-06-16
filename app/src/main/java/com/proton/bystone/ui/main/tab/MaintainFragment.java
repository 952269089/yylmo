package com.proton.bystone.ui.main.tab;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import com.proton.bystone.R;


import com.proton.library.ui.MTFBaseFragment;
import com.proton.library.ui.annotation.MTFFragmentFeature;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

@MTFFragmentFeature(layout = R.layout.fragment_maintain)
public class MaintainFragment extends MTFBaseFragment  {


     @Bind(R.id.list_view)
     PullToRefreshListView list_view;

    Handler handler = new Handler();
    Runnable update_thread = new Runnable()
    {
        public void run()
        {
            list_view.setAdapter(new Listview_two());
            list_view.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>()
            {

                @Override
                public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                    Toast.makeText(getActivity(), "下拉", Toast.LENGTH_SHORT).show();
                    new GetDataTask().execute();
                }
            });
        }
    };
    public MaintainFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MaintainFragment newInstance(Bundle args) {
        MaintainFragment fragment = new MaintainFragment();
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

}








