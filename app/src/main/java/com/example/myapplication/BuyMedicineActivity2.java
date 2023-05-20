package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity2 extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Uprise-D3 60K Capsule","","","","50"},
                    {"HealthVit Chromium Picolinate 200mcg Capsules","","","","394"},
                    {"Vitamin B Complex Capsules","","","","445"},
                    {"Dolo 650 Tablets","","","","30"},
                    {"Crocin 650 Advance Tablet","","","","50"},
                    {"Strepsils Medicated Lozenges for sore throat","","","","45"},
                    {"Tata 1mg Calcium + Vitamin D3","","","","30"},
                    {"Feronica -XT Tablet","","","","137"}
            };
    private String[] package_details = {

            " The treatment of vitamin D deficiency and osteoporosis\n"+
                    "It helps the body to absorb calcium\n" +
                    "Calcium is essential for maintaining strong and healthy bones",
            "Chromium is an essential trace mineral that plays an important role in helping insulin regulate blood glucose",
                    "Provides relief from Vitamin B deficiencies\n" +
                    "Maintains healthy nervous system",
            "It promotes health as well as skin benifits\n" +
                    "It helps reduce skin blemish and pigmentation\n" +
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays.",
            "Dolo 650 tablets helps relieve pain and fever by blocking the release of certain chemical messages\n" +
                    "Helps relief fever and brings down the high temperature\n" +
                    "Suitable for the people with a heart condition or high blood pressure",
            "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n" +
                    "Provides a warm and comforting feelings during sore throat",
            "Reduce the risk of calcium deficiency,Rickets,and Osteoporosis\n" +
                    "Promotes mobility and flexibilty of joints",
            "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"

    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine2);

        lst =findViewById(R.id.editTextBMDdMultiline);
        btnBack=findViewById(R.id.buttonBMBack);
        btnGoToCart=findViewById(R.id.buttonBMGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity2.this,CartBuyMedicineActivity2.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity2.this, HomeActivity2.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<packages.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Cons Fee:" + packages[i][4] + "+/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(BuyMedicineActivity2.this,BuyMedicineDetailsActivity2.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);

            }
        });

    }
}