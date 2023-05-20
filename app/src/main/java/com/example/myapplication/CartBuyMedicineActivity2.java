package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity2 extends AppCompatActivity {

    HashMap<String,String> item1;
    ArrayList list1;
    SimpleAdapter sa1;
    TextView tvTotal1;
    ListView lst1;
    private DatePickerDialog datePickerDialog1;
    private TimePickerDialog timePickerDialog1;
    private Button dateButton1, btnCheckout1,btnBack1;
    private String[][] packages1 ={};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine2);

        dateButton1 = findViewById(R.id.buttonBMCartDate1);
        btnCheckout1 = findViewById(R.id.buttonBMCartCheckout1);
        btnBack1 = findViewById(R.id.buttonBMCartBack1);
        tvTotal1=findViewById(R.id.textViewBMCartTotalCost1);
        lst1=findViewById(R.id.listViewBMCart1);
        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username", "").toString();


        Database db=new Database(getApplicationContext(),"MyApplication", null, 1);

        float totalAmount=0;
        ArrayList dbData=db.getCartData(username,"medicine");
        // Toast.makeText(getApplicationContext(),""+dbData,Toast.LENGTH_LONG).show();

        packages1=new String[dbData.size()][];
        for(int i=0;i<packages1.length;i++){
            packages1[i]=new String[5];
        }

        for(int i=0;i<dbData.size();i++){
            String arrData=dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            packages1[i][0]=strData[0];
            packages1[i][4]="Cost: "+strData[1]+"/-";
            totalAmount= totalAmount + Float.parseFloat(strData[1]);
        }
        tvTotal1.setText("Total Cost:"+totalAmount);
        list1=new ArrayList();
        for(int i=0;i<packages1.length;i++) {
            item1 =new HashMap<String,String>();
            item1.put("line1", packages1[i][0]);
            item1.put("line2", packages1[i][1]);
            item1.put("line3", packages1[i][2]);
            item1.put("line4", packages1[i][3]);
            item1.put("line5", packages1[i][4]);
            list1.add(item1);
        }
        sa1 = new SimpleAdapter(this,list1,
                R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst1.setAdapter(sa1);

        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartBuyMedicineActivity2.this, BuyMedicineActivity2.class));
            }
        });
        btnCheckout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it= new Intent(CartBuyMedicineActivity2.this, BuyMedicineBookActivity2.class);
                it.putExtra("price",tvTotal1.getText());
                it.putExtra("date",dateButton1.getText());
                startActivity(it);

            }
        });


        //datepicker
        initDatePicker();
        dateButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog1.show();

            }
        });

    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                dateButton1.setText(i2 + "/" + i1 + "/" + i);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog1 = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog1.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);

    }
}