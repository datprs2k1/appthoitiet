package com.example.thoitiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thoitiet.Model.DataResponse;
import com.example.thoitiet.Model.LWeather;
import com.example.thoitiet.Model.Weather;
import com.example.thoitiet.api.ApiService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView tvNhietDo, tvViTri, tvCamGiac, tvThoiGian, tvChiTiet;
    RecyclerView lvThoiTietGio;
    List<LWeather> listGio = new ArrayList<>();
    ThoiTietGioApdater thoiTietGioAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }

    private void getWeather() {
        ApiService.apiService.getWeather("hanoi", "956a223fbccb7f85cf307e66442e7f8f", "vi", "metric", "8").enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                DataResponse dataResponse = response.body();

                LWeather weather = dataResponse.getList().get(1);
                String nhietDo = weather.getMain().getTemp();
                String viTri = dataResponse.getCity().getName();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("EEEE HH:mm");
                String dt_txt = weather.getDt_txt();
                String camGiac = weather.getMain().getFeels_like();
                String thoiGian = "";
                try {
                    Date dt = simpleDateFormat.parse(dt_txt);
                    thoiGian = simpleDateFormat1.format(dt);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                listGio = dataResponse.getList();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
                lvThoiTietGio.setLayoutManager(linearLayoutManager);
                lvThoiTietGio.setHasFixedSize(true);
                lvThoiTietGio.setAdapter(new ThoiTietGioApdater(listGio));
                tvNhietDo.setText(""+Math.round(Double.valueOf(nhietDo))+ "°C");
                tvViTri.setText(viTri);
                tvThoiGian.setText(thoiGian);
                tvCamGiac.setText("Cảm giác như " + Math.round(Double.valueOf(camGiac)) + "°C");
                tvChiTiet.setText(weather.getWeather().get(0).getDescription());
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.d("test", call.toString());
            }
        });
    }
}