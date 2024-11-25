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

public class DisplayBricks extends AppCompatActivity {

    LinearLayout main;
    Mydatabase mydatabase;
    FrameLayout frameLayout;
    private int id=2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_bricks);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        frameLayout=findViewById(R.id.ScrollViewContainer);
        ScrollView scrollView=new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        main=findViewById(R.id.main);
        mydatabase=new Mydatabase(this,"Mydatabase.db",null,1);
        String cname="NAME";
        String cphone="PHONE";
        String cnumber="NUMBER";
        String cprice="PRICE";
        String ccompany="COMPANY";
        String cdistrict="DISTRICT";
        String cpin="PINCODE";


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

                String district= cursor.getString(cursor.getColumnIndexOrThrow(cdistrict));
                String pin=cursor.getString(cursor.getColumnIndexOrThrow(cpin));

                String data="\n\nName : "+name+"\nNumber of bricks : "+number+"\nPrice in total: "+price+"\nDistrict : "+district+"\nPincode : "+pin;
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

                call.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(call);





    }
            scrollView.addView(linearLayout);
            frameLayout.addView(scrollView);
}}}

