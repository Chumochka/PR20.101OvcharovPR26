package com.example.pr26;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import java.lang.reflect.Array;

public class CatalogFragment extends Fragment implements View.OnClickListener {

    private String mParam1;
    private String mParam2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        Button btnCoffee = view.findViewById(R.id.btnCoffee);
        Button btnCake = view.findViewById(R.id.btnCake);
        Button btnCombo = view.findViewById(R.id.btnCombo);
        Button btnTea = view.findViewById(R.id.btnTea);
        btnCoffee.setOnClickListener(this);
        btnCake.setOnClickListener(this);
        btnCombo.setOnClickListener(this);
        btnTea.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCoffee:
                transaction("Кофе с карамелью", 254, "@drawable/coffee","@array/coffee");
                break;
            case R.id.btnCake:
                transaction("Шоколадный тор", 250, "@drawable/cake","@array/cake");
                break;
            case R.id.btnCombo:
                transaction("Тост с яичницой", 320, "@drawable/combo", "@array/combo");
                break;
            case R.id.btnTea:
                transaction("Чай с Лимоном", 70, "@drawable/tea","@array/tea");
                break;
        }
    }
    public void transaction(String name, Integer price, String drawable, String array){
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        OrderFragment orderFragment = OrderFragment.newInstance(name, price, drawable, array);
        fragmentTransaction.replace(R.id.fragmentContainerView, orderFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}