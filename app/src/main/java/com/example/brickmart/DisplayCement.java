package com.example.brickmart;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayCement extends AppCompatActivity {
    LinearLayout main;
    Mydatabase mydatabase;
    private int id=1;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_cement);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        main=findViewById(R.id.main);
        mydatabase=new Mydatabase(this,"Mydatabase.db",null,1);
        String cname="NAME";
        String cphone="PHONE";
        String cnumber="NUMBER";
        String cprice="PRICE";
        String ccompany="COMPANY";
        String cdistrict="DISTRICT";
        String cpin="PINCODE";
        frameLayout=findViewById(R.id.ScrollViewContainer);
        ScrollView scrollView=new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        Cursor cursor=mydatabase.getDetails(id);

        if(cursor!=null)
        {
            while(cursor.moveToNext())
            {
                TextView tx=new TextView(this);
                Button call=new Button(this);


                String name= cursor.getString(cursor.getColumnIndexOrThrow(cname));
                String phone= cursor.getString(cursor.getColumnIndexOrThrow(cphone));
                String number=cursor.getString(cursor.getColumnIndexOrThrow(cnumber));
                String price= cursor.getString(cursor.getColumnIndexOrThrow(cprice));
                String company=cursor.getString(cursor.getColumnIndexOrThrow(ccompany));
                String district= cursor.getString(cursor.getColumnIndexOrThrow(cdistrict));
                String pin=cursor.getString(cursor.getColumnIndexOrThrow(cpin));

                String data="\n\nName : "+name+"\nNumber of bags : "+number+"\nPrice per bag : "+price+"\nCompany : "+company+"\nDistrict : "+district+"\nPincode : "+pin;
                tx.setText(data);
                tx.setTextSize(16);
                tx.setTextColor(Color.WHITE);
                tx.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT  ));
                linearLayout.addView(tx);
                String[] namearray=name.split(" ");
                call.setText("CALL "+namearray[0]);

                call.setTextSize(16);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                        startActivity(intent);
                    }
                });


                // Add Button to the layout
                call.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(call);
            }
        }
        scrollView.addView(linearLayout);
        frameLayout.addView(scrollView);





    }
}