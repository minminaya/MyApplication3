package com.minminaya.qiuming.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.minminaya.qiuming.App;
import com.minminaya.qiuming.R;
import com.minminaya.qiuming.model.MeizituModel;
import com.minminaya.qiuming.util.DownLoadImage;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Niwa on 2017/7/5.
 */

public class PicDetailActivity extends AppCompatActivity {


    @Bind(R.id.viewpager)
    ViewPager viewpager;
    ArrayList<View> views;
    @Bind(R.id.btn_download)
    Button btnDownload;
    private boolean isFirst = true;
    MeizituModel meizituModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pic_detail_activity);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //获取数据
        Bundle bundle = getIntent().getExtras();
        meizituModel = (MeizituModel) bundle.getSerializable("PPO");

        Log.e("PicDetailActivity:", "" + meizituModel.getWebTitle());
        Log.e("PicDetailActivity:", "" + meizituModel.getPicInfos().get(0).getPicUrl());

        views = new ArrayList<View>();
        for (int i = 0; i < meizituModel.getPicInfos().size(); i++) {
            PhotoView ph = new PhotoView(this);
            ph.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(App.getINSTANCE()).load(meizituModel.getPicInfos().get(i).getPicUrl()).into(ph);
            views.add(ph);
        }
        Toast.makeText(App.getINSTANCE(), "右滑", Toast.LENGTH_SHORT).show();
        viewpager.setAdapter(pagerAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == meizituModel.getPicInfos().size() - 1) {
                    Toast.makeText(App.getINSTANCE(), "End", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    int currentPosition;
    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            //获取当前窗体界面数
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            //断是否由对象生成界面
            return view == object;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            container.addView(views.get(position));
            currentPosition = position;
            return views.get(position);
        }

    };

    @OnClick(R.id.btn_download)
    public void onViewClicked() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DownLoadImage.downLoad(meizituModel.getPicInfos().get(currentPosition - 1).getPicUrl(), "--" + currentPosition + "--" + meizituModel.getWebTitle() + ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/pic");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Toast.makeText(this, "图片保存在外置存储Picture文件夹...", Toast.LENGTH_SHORT).show();
    }


}
