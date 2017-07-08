package com.minminaya.qiuming.adpter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minminaya.qiuming.App;
import com.minminaya.qiuming.R;
import com.minminaya.qiuming.activity.PicDetailActivity;
import com.minminaya.qiuming.model.MeizituModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Niwa on 2017/7/5.
 */

public class MeiziTuRecyclerViewAdapter extends RecyclerView.Adapter<MeiziTuRecyclerViewAdapter.ViewHoldeA> {


    public void setMeizituModels(List<MeizituModel> meizituModels) {
        this.meizituModels = meizituModels;
    }

    private List<MeizituModel> meizituModels;

    public MeiziTuRecyclerViewAdapter() {
    }

    @Override
    public ViewHoldeA onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_item, parent, false);
        ViewHoldeA holdeA = new ViewHoldeA(view);
        return holdeA;
    }

    @Override
    public void onBindViewHolder(ViewHoldeA holder, final int position) {
        if (meizituModels.size() != 0) {
            Glide.with(App.getINSTANCE()).load(meizituModels.get(position).getPicInfos().get(0).getPicUrl()).into(holder.img);
            holder.tv.setText(meizituModels.get(position).getWebTitle());


//            holder.img.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(App.getINSTANCE(), PicDetailActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("PPO", meizituModels.get(position));
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtras(bundle);
//
//                    App.getINSTANCE().startActivity(intent);
//
//                }
//            });

            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(App.getINSTANCE(), PicDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PPO", meizituModels.get(position));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(bundle);

                    App.getINSTANCE().startActivity(intent);

                }
            };

            holder.img.setOnClickListener(onClickListener);
            holder.tv.setOnClickListener(onClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return meizituModels.size() == 0 ? 0 : meizituModels.size();
    }

    class ViewHoldeA extends RecyclerView.ViewHolder {
        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.tv)
        TextView tv;

        public ViewHoldeA(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
