package com.example.pr26;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BasketFragment extends Fragment {

    private TextView tvClientName;
    private TextView tvClientPhone;
    private TextView tvProductName1;
    private TextView tvProductName2;
    private TextView tvProductDesc1;
    private TextView tvProductDesc2;
    private TextView tvPrice1;
    private TextView tvPrice2;
    private TextView tvCount1;
    private TextView tvCount2;

    private int intPrice1 =254;
    private int intPrice2 =254;

    private ImageView imageView;
    private ImageView imageView2;

    private ImageButton btnMinus1;
    private ImageButton btnPlus1;
    private ImageButton btnMinus2;
    private ImageButton btnPlus2;

    private TextView tvPriceAll;
    private Button btnBuy;

    private int count1 = 1;
    private int count2 = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);

        tvClientName = view.findViewById(R.id.tvClientName);
        tvClientPhone = view.findViewById(R.id.tvClientPhone);

        tvProductName1 = view.findViewById(R.id.tvProductName1);
        tvProductDesc1 = view.findViewById(R.id.tvProductDesc1);
        tvPrice1=view.findViewById(R.id.tvPrice1);
        imageView = view.findViewById(R.id.image);
        tvCount1 = view.findViewById(R.id.tvCount1);
        btnMinus1 = view.findViewById(R.id.btnMinus1);
        btnPlus1 = view.findViewById(R.id.btnPlus1);

        tvProductName2 = view.findViewById(R.id.tvProductName2);
        tvProductDesc2 = view.findViewById(R.id.tvProductDesc2);
        tvPrice2=view.findViewById(R.id.tvPrice2);
        imageView2 = view.findViewById(R.id.image2);
        tvCount2 = view.findViewById(R.id.tvCount2);
        btnMinus2 = view.findViewById(R.id.btnMinus2);
        btnPlus2 = view.findViewById(R.id.btnPlus2);

        tvPriceAll = view.findViewById(R.id.tvPriceAll);
        btnBuy = view.findViewById(R.id.btnBuy);

        SQLiteDatabase db = getActivity().getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS client_Orders2 (name TEXT, cost TEXT,addItem TEXT, sugar INTEGER,adds INTEGER,deliver INTEGER,clientName TEXT,clientPhone TEXT)");
        Cursor query = db.rawQuery("SELECT * FROM client_Orders2;", null);
        query.moveToPosition(query.getCount()-2);
        String name = query.getString(0);
        String price = query.getString(1);
        int sugar = query.getInt(3);
        int adds = query.getInt(4);
        int deliver = query.getInt(5);
        tvProductName1.setText(name);
        int drawable = R.drawable.coffee;
        if (name.equals("Торт")) {
            drawable = R.drawable.cake;
            intPrice1=250;
        }else if(name.equals("Тост с яичницой")){
            drawable = R.drawable.combo;
            intPrice1=320;
        }else if(name.equals("Чай с Лимоном")){
            drawable = R.drawable.tea;
            intPrice1=70;
        }
        //Торт
        imageView.setImageResource(drawable);
        tvPrice1.setText(price);
        tvProductDesc1.setText("");
        if(sugar == 1){
            tvProductDesc1.append("Сахар\n");
        }
        if (adds == 1){
            tvProductDesc1.append("Добавки\n");
        }
        if(deliver == 1){
            tvProductDesc1.append("Доставка");
        }else {
            tvProductDesc1.append("Самовывоз");
        }
        query.moveToNext();
        String name2 = query.getString(0);
        String price2 = query.getString(1);
        sugar = query.getInt(3);
        adds = query.getInt(4);
        deliver = query.getInt(5);
        tvProductName2.setText(name2);
        int drawable2 = R.drawable.coffee;
        if (name2.equals("Торт")) {
            drawable2 = R.drawable.cake;
            intPrice2 = 250;
        }else if(name2.equals("Тост с яичницой")){
            drawable2 = R.drawable.combo;
            intPrice2 = 320;
        }else if(name2.equals("Чай с Лимоном")){
            drawable2 = R.drawable.tea;
            intPrice2 = 70;
        }
        imageView2.setImageResource(drawable2);
        tvPrice2.setText(price2);
        tvProductDesc2.setText("");
        if(sugar == 1){
            tvProductDesc2.append("Сахар\n");
        }
        if (adds == 1){
            tvProductDesc2.append("Добавки\n");
        }
        if(deliver == 1){
            tvProductDesc2.append("Доставка");
        }else {
            tvProductDesc2.append("Самовывоз");
        }
        String clientName = query.getString(6);
        String clientPhone = query.getString(7);
        tvClientName.setText(clientName);
        tvClientPhone.setText(clientPhone);
        intPrice1 = intPrice1 + intPrice2;
        tvPriceAll.setText(intPrice1 + " руб.");
        query.close();
        db.close();
        btnPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count1 < 9){
                    count1=count1+1;
                    tvCount1.setText(count1+"");
                }

            }
        });
        btnPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count2<9){
                    count2=count2+1;
                    tvCount2.setText(count2+"");
                }

            }
        });
        btnMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count1>1){
                    count1=count1-1;
                    tvCount1.setText(count1+"");
                }
            }
        });
        btnMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count2>1){
                    count2=count2-1;
                    tvCount2.setText(count2+"");
                }
            }
        });
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:55.044526, 82.941082"));
                startActivity(intent);
            }
        });
        return view;
    }
}