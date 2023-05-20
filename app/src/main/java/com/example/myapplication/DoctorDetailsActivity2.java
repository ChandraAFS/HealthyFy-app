package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity2 extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Ajit Saste", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 8979866237", "600"},
                    {"Doctor Name : Prasad Pawar", "Hospital Address : Niqdi", "Exp : 3yrs", "Mobile No : 8458876237", "300"},
                    {"Doctor Name : Amit Kohli", "Hospital Address : Agra", "Exp : 10yrs", "Mobile No : 7909878425", "400"},
                    {"Doctor Name : Praveen Senger", "Hospital Address : Agra", "Exp : 12yrs", "Mobile No : 7965324567", "800"},
                    {"Doctor Name : Arun Chauhan", "Hospital Address : Sikandra", "Exp : 7yrs", "Mobile No : 8193841709", "850"}

            };
private String[][] doctor_details2 =
        {
        {"Doctor Name : Neelam Patil", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 8979845237", "600"},
        {"Doctor Name : Swati pawar", "Hospital Address : Nigdi", "Exp : 15yrs", "Mobile No : 7979866237", "500"},
        {"Doctor Name : Madhur Raj", "Hospital Address : Agra Cantt", "Exp : 8yrs", "Mobile No : 6397885641", "800"},
        {"Doctor Name : Mayuri Deshmukh", "Hospital Address : Chinchwad", "Exp : 5yrs", "Mobile No : 897985837", "200"},
        {"Doctor Name : Minakshi Panda", "Hospital Address : Katra", "Exp : 4yrs", "Mobile No : 7523866237", "600"}

        };
private String[][] doctor_details3 =
        {
        {"Doctor Name : Seema Patil", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 8979866237", "600"},
        {"Doctor Name : Pranab Parashar", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 8979866237", "600"},
        {"Doctor Name : Som Sharma", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 8979866237", "600"},
        {"Doctor Name : Rishi Chauhan", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 8979866237", "600"},
        {"Doctor Name : Chandra Mohan", "Hospital Address : Rajpur", "Exp : 5yrs", "Mobile No : 8979866237", "600"}

        };
private String[][] doctor_details4 =
        {
        {"Doctor Name : Ram Raghu", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 8978656237", "600"},
        {"Doctor Name : Anil Kumbhle", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 7239866237", "600"},
        {"Doctor Name : Ajay Kushwah", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 7479866237", "600"},
        {"Doctor Name : Rajesh Solanki", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9755656237", "600"},
        {"Doctor Name : Vijay Chauhan", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9874676237", "600"}

        };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Harendra Kumar", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 7909866237", "600"},
                    {"Doctor Name : Atul Sharma", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 8047946237", "600"},
                     {"Doctor Name : Brijesh Pandit", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 8344866237", "600"},
                    {"Doctor Name : Harsh Lawaniya", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 9233462337", "600"},
                     {"Doctor Name : Aditi Sharma", "Hospital Address : Pimpri", "Exp : 5yrs", "Mobile No : 7905366237", "600"}
            };
    TextView tv;
    Button btn;
    String[][] doctor_details ={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details2);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonBMDdBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians") == 0)
            doctor_details = doctor_details1;
        else if (title.compareTo("Dietician") == 0)
            doctor_details = doctor_details2;
        else if (title.compareTo("Dentist") == 0)
            doctor_details = doctor_details3;
        else if (title.compareTo("Surgeon") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity2.this, FindDoctorActivity2.class));
            }
        });
        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fee:"+doctor_details[i][4]+"+/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        ListView lst = findViewById(R.id.editTextBMDdMultiline);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity2.this, BookAppointmentActivity2.class);
                it.putExtra("text1",title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);

            }
        });
    }
}