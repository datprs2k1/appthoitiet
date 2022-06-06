package com.example.thoitiet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thoitiet.CityDetailActivity;
import com.example.thoitiet.Model.ThanhPho;
import com.example.thoitiet.R;

import java.util.List;

public class ThanhPhoAdapter extends RecyclerView.Adapter<ThanhPhoAdapter.ThanhPhoHolder>{

    List<ThanhPho> list;
    Context mContext;

    public ThanhPhoAdapter(List<ThanhPho> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ThanhPhoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_thanh_pho, parent, false);
        return new ThanhPhoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhPhoHolder holder, int position) {
        ThanhPho thanhPho = list.get(position);

        holder.tvName.setText(thanhPho.getName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(thanhPho);
            }
        });

    }

    private void onClickGoToDetail(ThanhPho thanhPho) {
        Intent intent = new Intent(mContext, CityDetailActivity.class);
        intent.putExtra("name", thanhPho.getName());
        mContext.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThanhPhoHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
       private TextView tvName;

        public ThanhPhoHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.layoutitem);
        }
    }
}
