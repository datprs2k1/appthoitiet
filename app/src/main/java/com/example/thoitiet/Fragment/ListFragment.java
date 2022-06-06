package com.example.thoitiet.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thoitiet.Adapter.ThanhPhoAdapter;
import com.example.thoitiet.Adapter.ThoiTietGioApdater;
import com.example.thoitiet.Model.ThanhPho;
import com.example.thoitiet.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    ThanhPhoAdapter thanhPhoAdapter;
    List<ThanhPho> list = new ArrayList<>();
    RecyclerView lvThanhPho;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        lvThanhPho = (RecyclerView) view.findViewById(R.id.lvThanhPho);

        ThanhPho thanhPho = new ThanhPho();
        thanhPho.setName("Hà Nội");
        list.add(thanhPho);

        thanhPho = new ThanhPho();
        thanhPho.setName("Nam Định");
        list.add(thanhPho);

        thanhPhoAdapter = new ThanhPhoAdapter(list, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        lvThanhPho.setLayoutManager(linearLayoutManager);
        lvThanhPho.setHasFixedSize(true);
        lvThanhPho.setAdapter(thanhPhoAdapter);

        return view;
    }
}