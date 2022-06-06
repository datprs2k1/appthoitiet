package com.example.thoitiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.thoitiet.Adapter.ThoiTietGioApdater;
import com.example.thoitiet.Model.DataResponse;
import com.example.thoitiet.Model.LWeather;
import com.example.thoitiet.api.ApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityDetailActivity extends AppCompatActivity {
    TextView tvNhietDo, tvViTri, tvCamGiac, tvThoiGian, tvChiTiet, tvDoAm, tvMay, tvGio, tvMatTroiMoc, tvMatTroiLan;
    EditText edtThanhPho;
    RecyclerView lvThoiTietGio;
    List<LWeather> listGio = new ArrayList<>();
    ThoiTietGioApdater thoiTietGioAdapter;
    String ThanhPho = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);

        Intent intent = getIntent();

        ThanhPho = intent.getStringExtra("name");

        maps();

        getWeather();

    }

    private void maps() {
        tvNhietDo = (TextView) findViewById(R.id.tvNhietDo);
        tvViTri = (TextView) findViewById(R.id.tvViTri);
        tvCamGiac = (TextView) findViewById(R.id.tvCamGiac);
        tvThoiGian = (TextView) findViewById(R.id.tvThoiGian);
        lvThoiTietGio = (RecyclerView) findViewById(R.id.lvThoiTietGio);
        tvChiTiet = (TextView) findViewById(R.id.tvChiTiet);
        tvDoAm = (TextView) findViewById(R.id.tvDoAm);
        tvMay = (TextView) findViewById(R.id.tvMay);
        tvGio = (TextView) findViewById(R.id.tvGio);
        tvMatTroiMoc = (TextView) findViewById(R.id.tvMatTroiMoc);
        tvMatTroiLan = (TextView) findViewById(R.id.tvMatTroiLan);
        edtThanhPho = (EditText) findViewById(R.id.edtThanhPho);
    }

    private void getWeather() {
        ApiService.apiService.getWeather(!ThanhPho.isEmpty() ? ThanhPho : "Hà Nội", "956a223fbccb7f85cf307e66442e7f8f", "vi", "metric", "12").enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                DataResponse dataResponse = response.body();

                LWeather weather = dataResponse.getList().get(0);
                String nhietDo = weather.getMain().getTemp();
                String viTri = dataResponse.getCity().getName();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE HH:mm");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
                Date a = new Date(Long.valueOf(weather.getDt()) * 1000L);
                String thoiGian = simpleDateFormat.format(a);
                String camGiac = weather.getMain().getFeels_like();
                String doAm = weather.getMain().getHumidity();
                int may = weather.getClouds().getAll();
                String gio = weather.getWind().getSpeed();
                Long matTroiMoc = Long.valueOf(dataResponse.getCity().getSunrise());
                Long matTroiLan = Long.valueOf(dataResponse.getCity().getSunset());
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
                simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
                Date sunrise = new Date(matTroiMoc * 1000L);
                Date sunset = new Date(matTroiLan * 1000L);


                String MatTroiMoc = simpleDateFormat2.format(sunrise);
                String MatTroiLan = simpleDateFormat2.format(sunset);

                String May;
                if(may <= 25) {
                    May = "Ít";
                } else if(may <= 50) {
                    May = "Rải rác";
                } else if (may <= 84) {
                    May = "Nhiều";
                } else {
                    May = "U ám";
                }

                listGio = dataResponse.getList();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CityDetailActivity.this, RecyclerView.HORIZONTAL, false);
                lvThoiTietGio.setLayoutManager(linearLayoutManager);
                lvThoiTietGio.setHasFixedSize(true);
                lvThoiTietGio.setAdapter(new ThoiTietGioApdater(listGio, CityDetailActivity.this, viTri));
                tvNhietDo.setText(""+Math.round(Double.valueOf(nhietDo))+ "°C");
                tvViTri.setText(viTri);
                tvThoiGian.setText(thoiGian);
                tvCamGiac.setText("Cảm giác như " + Math.round(Double.valueOf(camGiac)) + "°C");
                tvChiTiet.setText(weather.getWeather().get(0).getDescription());
                tvDoAm.setText("Độ ẩm\n" + doAm + "%");
                tvMay.setText("Mây\n" + May);
                tvGio.setText("Gió\n" + gio+" m/s");
                tvMatTroiMoc.setText("Mặt trời mọc\n" + MatTroiMoc);
                tvMatTroiLan.setText("Mặt trời lặn\n" + MatTroiLan);
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.d("test", call.toString());
            }
        });
    }
}