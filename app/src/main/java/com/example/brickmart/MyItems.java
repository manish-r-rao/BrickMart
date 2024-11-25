package com.example.brickmart;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MyItems extends AppCompatActivity {

    LinearLayout linearLayout;
    FrameLayout frameLayout;
    Mydatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_items);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        frameLayout=findViewById(R.id.ScrollViewContainer);
        ScrollView scrollView=new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout=new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        String user=getIntent().getStringExtra("name");
        mydatabase=new Mydatabase(this,"Mydatabase.db",null,1);
        Cursor cursor=mydatabase.getDetails(user);

        AlertDialog.Builder alert=new AlertDialog.Builder(MyItems.this);
        alert.setMessage("Do you want to delete this item?");


        String name,color,company,number,item;
        boolean items=false;
        if(cursor!=null)
        {
            while (cursor.moveToNext())
            {
                TextView tx=new TextView(this);
                String id=(cursor.getString(cursor.getColumnIndexOrThrow("ID")));
                String cement="1";
                String bricks="2";
                String paint="3";
                if(id != null && id.equals(cement))
                {
                    items=true;
                    int textViewId = View.generateViewId();
                    tx.setId(textViewId);
                    name=cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
                    company=cursor.getString(cursor.getColumnIndexOrThrow("COMPANY"));
                    number=cursor.getString(cursor.getColumnIndexOrThrow("NUMBER"));
                    item="CEMENT";
                    String data="\nName:"+name+"\nItem:"+item+"\nNumber of bags:"+number+"\nCompany:"+company;
                    tx.setTextColor(Color.WHITE);
                    tx.setTextSize(16);

                    tx.setText(data);
                    tx.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    tx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    int newid= tx.getId();
                                    TextView textView=findViewById(newid);
                                    String data=textView.getText().toString();
                                    String[] array=data.split("\n");
                                    String dname="",dnumber="",dcompany="";
                                    for (String line : array)
                                    {
                                        String[] keyValue = line.split(":");
                                        if (keyValue.length == 2)
                                        {
                                            String key = keyValue[0];
                                            String value = keyValue[1];

                                            if (key.equalsIgnoreCase("Name"))
                                            {
                                                dname = value;
                                            } else if (key.equalsIgnoreCase("Number of bags"))
                                            {
                                                dnumber = value;
                                            } else if (key.equalsIgnoreCase("Company"))
                                            {
                                                dcompany = value;
                                            }
                                        }
                                    }
//                                    for(int j=0;j< array.length;j++)
//                                    {
//                                        String[] newarray=array[j].split(":");
//                                        if(j==1)
//                                        {
//                                            dname=newarray[1];
//                                        }
//                                        else if(j==5)
//                                        {
//                                            dcompany=newarray[1];
//                                        }
//                                        else if(j==7)
//                                        {
//                                            dnumber=newarray[1];
//                                        }
//                                    }
                                    mydatabase.deleteItem(user,dname,dnumber,dcompany,1);
                                    ViewGroup parent=(ViewGroup) tx.getParent();
                                    parent.removeView(tx);

                                }
                            });
                            alert.setNegativeButton("No",null);
                            alert.show();

                        }
                    });


                }
                if(id != null && id.equals(bricks))
                {
                    items=true;

                    int textViewId = View.generateViewId();
                    tx.setId(textViewId);
                    name=cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
                    number=cursor.getString(cursor.getColumnIndexOrThrow("NUMBER"));
                    item="BRICKS";
                    String data="\nName:"+name+"\nItem:"+item+"\nNumber of bricks:"+number;
                    tx.setTextColor(Color.WHITE);
                    tx.setTextSize(16);

                    tx.setText(data);
                    tx.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    tx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    int newid= tx.getId();
                                    TextView textView=findViewById(newid);
                                    String data=textView.getText().toString();
                                    String[] array=data.split("\n");
                                    String dname="",dnumber="",dcompany=null;
                                    for (String line : array)
                                    {
                                        String[] keyValue = line.split(":");
                                        if (keyValue.length == 2)
                                        {
                                            String key = keyValue[0];
                                            String value = keyValue[1];

                                            if (key.equalsIgnoreCase("Name"))
                                            {
                                                dname = value;
                                            } else if (key.equalsIgnoreCase("Number of bricks"))
                                            {
                                                dnumber = value;
                                            }
                                        }
                                    }
//                                    for(int j=0;j< array.length;j++)
//                                    {
//                                        String[] newarray=array[j].split(":");
//                                        if(j==1)
//                                        {
//                                            dname=newarray[1];
//                                        }
//                                        else if(j==5)
//                                        {
//                                            dcompany=newarray[1];
//                                        }
//                                        else if(j==7)
//                                        {
//                                            dnumber=newarray[1];
//                                        }
//                                    }
                                    mydatabase.deleteItem(user,dname,dnumber,dcompany,2);
                                    ViewGroup parent=(ViewGroup) tx.getParent();
                                    parent.removeView(tx);

                                }
                            });
                            alert.setNegativeButton("No",null);
                            alert.show();

                        }
                    });


                }
                if(id != null && id.equals(paint))
                {
                    items=true;

                    int textViewId = View.generateViewId();
                    tx.setId(textViewId);
                    name=cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
                    company=cursor.getString(cursor.getColumnIndexOrThrow("COMPANY"));
                    number=cursor.getString(cursor.getColumnIndexOrThrow("NUMBER"));
                    item="PAINT";
                    String data="\nName:"+name+"\nItem:"+item+"\nLitres:"+number+"\nCompany:"+company;
                    tx.setTextColor(Color.WHITE);
                    tx.setTextSize(16);

                    tx.setText(data);
                    tx.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    tx.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    int newid= tx.getId();
                                    TextView textView=findViewById(newid);
                                    String data=textView.getText().toString();
                                    String[] array=data.split("\n");
                                    String dname="",dnumber="",dcompany="";
                                    for (String line : array)
                                    {
                                        String[] keyValue = line.split(":");
                                        if (keyValue.length == 2)
                                        {
                                            String key = keyValue[0];
                                            String value = keyValue[1];

                                            if (key.equalsIgnoreCase("Name"))
                                            {
                                                dname = value;
                                            } else if (key.equalsIgnoreCase("Litres"))
                                            {
                                                dnumber = value;
                                            } else if (key.equalsIgnoreCase("Company"))
                                            {
                                                dcompany = value;
                                            }
                                        }
                                    }
//                                    for(int j=0;j< array.length;j++)
//                                    {
//                                        String[] newarray=array[j].split(":");
//                                        if(j==1)
//                                        {
//                                            dname=newarray[1];
//                                        }
//                                        else if(j==5)
//                                        {
//                                            dcompany=newarray[1];
//                                        }
//                                        else if(j==7)
//                                        {
//                                            dnumber=newarray[1];
//                                        }
//                                    }
                                    mydatabase.deleteItem(user,dname,dnumber,dcompany,3);
                                    ViewGroup parent=(ViewGroup) tx.getParent();
                                    parent.removeView(tx);

                                }
                            });
                            alert.setNegativeButton("No",null);
                            alert.show();

                        }
                    });


                }
                linearLayout.addView(tx);



            }
        }
        if(!items)
        {
            Toast.makeText(this, "You have no items for sale.", Toast.LENGTH_SHORT).show();
        }
        else

        {
            Toast.makeText(this, "Click on the item to delete", Toast.LENGTH_SHORT).show();

        }
        scrollView.addView(linearLayout);
        frameLayout.addView(scrollView);
    }
}