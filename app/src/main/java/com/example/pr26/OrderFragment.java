package com.example.pr26;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;


public class OrderFragment extends Fragment {

    private TextView tv_Name;
    private TextView tv_Price;
    private ImageView image;
    private Spinner spinner;
    public static OrderFragment newInstance(String name, String price) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("price", price);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        tv_Name = view.findViewById(R.id.tvName);
        tv_Price = view.findViewById(R.id.tvPrice);
        image = view.findViewById(R.id.image);
        spinner = view.findViewById(R.id.spinner);
        int drawable = R.drawable.coffee;
        int array = R.array.coffee;
        String name = getArguments().getString("name","");
        String price = getArguments().getString("price","0 рублей");
        if (name == "Торт") {
            drawable = R.drawable.cake;
            array = R.array.cake;
        }else if(name == "Тост с яичницой"){
            drawable = R.drawable.combo;
            array = R.array.combo;
        }else if(name == "Чай с Лимоном"){
            drawable = R.drawable.tea;
            array = R.array.tea;
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(OrderFragment.this.getContext(),
                array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tv_Name.setText(name);
        tv_Price.setText(price);
        spinner.setAdapter(adapter);
        image.setImageResource(drawable);
        return view;
    }
}