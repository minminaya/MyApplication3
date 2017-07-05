package com.minminaya.qiuming;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.minminaya.qiuming.adpter.MeiziTuRecyclerViewAdapter;
import com.minminaya.qiuming.http.NetWork;
import com.minminaya.qiuming.model.MeizituModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    XRecyclerView recyclerView;
    @Bind(R.id.content_main)
    RelativeLayout contentMain;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.layout)
    RelativeLayout layout;
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.text11)
    TextView text11;

    private int loadOffset = 2;

    private MeiziTuRecyclerViewAdapter meiziTuRecyclerViewAdapter;
    private List<MeizituModel> meizituModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        meiziTuRecyclerViewAdapter = new MeiziTuRecyclerViewAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        //确保每一个Item添加或者删除尺寸不再重新计算
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(meiziTuRecyclerViewAdapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                loadOffset = 1;
                loadPic(12, loadOffset);
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                if (loadOffset == 1) {
                    loadOffset = 2;
                }
                loadPic(12, loadOffset++);
                recyclerView.loadMoreComplete();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            loadPic(12, 1);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.exit) {
            super.onBackPressed();
        }else  if(id == R.id.info){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    Observer<List<MeizituModel>> observer = new Observer<List<MeizituModel>>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(List<MeizituModel> value) {
            Log.e("MainActivity", "title:" + value.get(0).getWebTitle());
            Log.e("MainActivity", "url:" + value.get(0).getPicInfos().get(0).getPicUrl());
            Log.e("MainActivity", "size:" + value.size());
//            meizituModels = value;
            for (MeizituModel meizituModel :
                    value) {
                meizituModels.add(meizituModel);
            }
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            Log.e("MainActivity", "错误");
        }

        @Override
        public void onComplete() {
            meiziTuRecyclerViewAdapter.setMeizituModels(meizituModels);
            meiziTuRecyclerViewAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        }
    };

    private void loadPic(int size, int offset) {
        NetWork.getMeizituApi()
                .loadMeiziPic(size, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @OnClick(R.id.layout)
    public void onViewClicked() {
        text11.setVisibility(View.GONE);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(img, "scaleX", 1f, 2f, 1f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(img, "scaleY", 1f, 2f, 1f);

        anim1.setDuration(1000);
        anim2.setDuration(1000);
        anim1.start();
        anim2.start();
        loadPic(12, 1);
    }
}
