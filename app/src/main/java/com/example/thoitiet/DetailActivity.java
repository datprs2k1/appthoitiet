package com.example.thoitiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class DetailActivity extends AppCompatActivity {

    TextView tvNhietDo, tvViTri, tvCamGiac, tvThoiGian, tvChiTiet, tvDoAm, tvMay, tvGio;
    EditText edtThanhPho;
    String ThanhPho = "";
    private LWeather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        weather = (LWeather) bundle.get("thoitiet");

        ThanhPho = getIntent().getStringExtra("thanhPho");

        maps();

        getWeather();
    }

    private void maps() {
        tvNhietDo = (TextView) findViewById(R.id.tvNhietDo);
        tvViTri = (TextView) findViewById(R.id.tvViTri);
        tvCamGiac = (TextView) findViewById(R.id.tvCamGiac);
        tvThoiGian = (TextView) findViewById(R.id.tvThoiGian);
        tvChiTiet = (TextView) findViewById(R.id.tvChiTiet);
        tvDoAm = (TextView) findViewById(R.id.tvDoAm);
        tvMay = (TextView) findViewById(R.id.tvMay);
        tvGio = (TextView) findViewById(R.id.tvGio);
        edtThanhPho = (EditText) findViewById(R.id.edtThanhPho);
    }

    private void getWeather() {

        String nhietDo = weather.getMain().getTemp();
        String viTri = ThanhPho;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        Date a = new Date(Long.valueOf(weather.getDt()) * 1000L);
        String thoiGian = simpleDateFormat.format(a);
        String camGiac = weather.getMain().getFeels_like();
        String doAm = weather.getMain().getHumidity();
        int may = weather.getClouds().getAll();
        String gio = weather.getWind().getSpeed();

        String May;
        if (may <= 25) {
            May = "Ít";
        } else if (may <= 50) {
            May = "Rải rác";
        } else if (may <= 84) {
            May = "Nhiều";
        } else {
            May = "U ám";
        }

        tvNhietDo.setText("" + Math.round(Double.valueOf(nhietDo)) + "°C");
        tvViTri.setText(viTri);
        tvThoiGian.setText(thoiGian);
        tvCamGiac.setText("Cảm giác như " + Math.round(Double.valueOf(camGiac)) + "°C");
        tvChiTiet.setText(weather.getWeather().get(0).getDescription());
        tvDoAm.setText("Độ ẩm\n" + doAm + "%");
        tvMay.setText("Mây\n" + May);
        tvGio.setText("Gió\n" + gio + " m/s");

    }
}