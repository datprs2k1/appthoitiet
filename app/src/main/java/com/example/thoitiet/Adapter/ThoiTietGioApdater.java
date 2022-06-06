package com.example.thoitiet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thoitiet.DetailActivity;
import com.example.thoitiet.Model.LWeather;
import com.example.thoitiet.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ThoiTietGioApdater extends RecyclerView.Adapter<ThoiTietGioApdater.ThoiTietGioHolder>{

    List<LWeather> list;
    Context mContext;
    String thanhPho;

    public ThoiTietGioApdater(List<LWeather> list, Context mContext, String thanhPho) {
        this.list = list;
        this.mContext = mContext;
        this.thanhPho = thanhPho;
    }

    @NonNull
    @Override
    public ThoiTietGioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ngay, parent, false);

        return new ThoiTietGioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThoiTietGioHolder holder, int position) {
        LWeather weather = list.get(position);
        String nhietDo = weather.getMain().getTemp();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        Date a = new Date(Long.valueOf(weather.getDt()) * 1000L);
        String thoiGian = simpleDateFormat.format(a);
        String dt_txt = weather.getDt_txt();

        holder.tvNhietDo.setText(""+Math.round(Double.valueOf(nhietDo)) + "°C");
        holder.tvThoiGian.setText(thoiGian);
        Picasso.get().load("http://openweathermap.org/img/wn/" + weather.getWeather().get(0).getIcon() + "@2x.png").into(holder.imgIcon);

        holder.listNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("thoitiet", weather);
                intent.putExtras(bundle);
                intent.putExtra("thanhPho", thanhPho);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ThoiTietGioHolder extends RecyclerView.ViewHolder {

        private TextView tvThoiGian;
        private TextView tvNhietDo;
        private ImageView imgIcon;
        private LinearLayout listNgay;

        public ThoiTietGioHolder(@NonNull View itemView) {
            super(itemView);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGian);
            tvNhietDo = itemView.findViewById(R.id.tvNhietDo);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            listNgay = (LinearLayout) itemView.findViewById(R.id.listNgay);
        }
    }
}
