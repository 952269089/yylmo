package com.proton.bystone.ui.main.tab;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.assist.deque.LIFOLinkedBlockingDeque;
import com.proton.bystone.R;
import com.proton.bystone.bean.Testd;
import com.proton.bystone.ui.main.MainActivity;
import com.proton.bystone.ui.register.registerActivity;
import com.proton.bystone.utils.UtilsOkHttp;
import com.proton.bystone.viewpagertest.MainActivityviewpager;
import com.proton.library.ui.MTFBaseFragment;
import com.proton.library.ui.annotation.MTFFragmentFeature;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

import static android.app.Activity.RESULT_OK;


@MTFFragmentFeature(layout = R.layout.fragment_home)
public class HomeFragment extends MTFBaseFragment {
    @Bind(R.id.list_view_home)
    PullToRefreshListView list_view;

    ViewPager vp;

    GridView  gridview;
    ListView  lv;
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

        //View ho = LayoutInflater.from(getActivity()).inflate(R.layout.ho_view, null);
        View ho = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main_viewpager, null);
        vp=(ViewPager)ho.findViewById(R.id.viewPager);
        vp.setAdapter(new viewpager_adapter());
        lv=list_view.getRefreshableView();
        lv.addHeaderView(ho);

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


                /*
                 oKHTTP进行封装，到时会后只需要把参数放入
                 测试一
                 */
               /* UtilsOkHttp okhttppost=new UtilsOkHttp();
                String url="http://172.16.13.2:8080/SpringMVC/HelloController/hello9.json";//这里填入rul
                Map list = new HashMap();
                list.put("id",11);
                list.put("name",222);
                *//*MainActivity ma=(MainActivity)getActivity();*//*

                okhttppost.OkHttpPost(url, list);*/


                /*
                测试二
                startActivityForResult
                 */
                /*Intent intent=new Intent();
                intent.setClass(getActivity(), MainActivityviewpager.class);
                Bundle bundle=new Bundle();
                String str1="aaaaaa";
                bundle.putString("str1", str1);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);//这里采用startActivityForResult来做跳转，此处的0为一个依据，可以写其他的值，但一定要>=0
*/
                /*
                测试三
                 */
                MainActivity ma=(MainActivity)getActivity();
                HomeFragment zuce = ma.zuce();
                zuce.testhown("344343");

            }
        }).start();


    }

    //TEST测试
    public  void testhown(String d)
    {
        Log.e("DDD",d);

    }

    /**
     * 测试
     *
     *
     * @param data
     */
    public void getdata(String  data)
    {

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                /*Bundle b=data.getExtras(); //data为B中回传的Intent
                String str=b.getString("str1");//str即为回传的值*/

                break;
            default:
                break;
        }
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
    class viewpager_adapter extends PagerAdapter
    {
        @Override
        public int getCount() {
            return 4;
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
            ImageView  Image=new ImageView(getActivity());
            Image.setImageResource(R.mipmap.eye_gray_1);
            vp.addView(Image);

            return Image;
        }
    }

    class Listview_two extends BaseAdapter {
        @Override
        public int getCount() {
            return 7;
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

            ImageView image = (ImageView) vi.findViewById(R.id.home_image);

            //Picasso.with(getActivity()).load("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png").into(image);

            return vi;
        }
    }
    }
