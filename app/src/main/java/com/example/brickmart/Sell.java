package com.example.brickmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sell extends AppCompatActivity {

    Button cement,bricks,paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sell);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cement=findViewById(R.id.cement);
        bricks=findViewById(R.id.bricks);
        paint=findViewById(R.id.paint);

        String validusername=getIntent().getStringExtra("name");
        String validpassword=getIntent().getStringExtra("password");

        cement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sell.this,DetailEntryCement.class);
                intent.putExtra("name",validusername);
                intent.putExtra("password",validpassword);
                startActivity(intent);
            }
        });

        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sell.this,DetailEntryPaint.class);
                intent.putExtra("name",validusername);
                intent.putExtra("password",validpassword);
                startActivity(intent);
            }
        });

        bricks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sell.this,DetailEntryBricks.class);
                intent.putExtra("name",validusername);
                intent.putExtra("password",validpassword);
                startActivity(intent);
            }
        });

    }
}