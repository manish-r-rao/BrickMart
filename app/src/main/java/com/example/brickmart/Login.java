package com.example.brickmart;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    EditText name,password;
    Button login;
    Mydatabase Mydb;
    MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name=findViewById(R.id.username);
        password=findViewById(R.id.lpassword);
        login=findViewById(R.id.loginbutton);
        Mydb=new Mydatabase(this,"Mydatabase.db",null,1);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=name.getText().toString().trim();
                String userpassword=password.getText().toString().trim();
                boolean ismatched=false;
                if(username.isEmpty() || userpassword.isEmpty())
                {
                    Toast.makeText(Login.this, "Invalid data!!", Toast.LENGTH_SHORT).show();
                }
                else {
                Cursor cursor= Mydb.validate(name.getText().toString().trim(),password.getText().toString().trim());
                if(cursor!=null)
                {
                    while(cursor.moveToNext())
                    {
                        if(username.equals(cursor.getString(cursor.getColumnIndexOrThrow("USER"))) && userpassword.equals(cursor.getString(cursor.getColumnIndexOrThrow("PASSWORD"))))
                        {
                            ismatched=true;
                            Intent intent=new Intent(Login.this, MainActivity.class);
                            intent.putExtra("name",username);
                            intent.putExtra("password",userpassword);
                            startActivity(intent);

                        }


                    }
                    cursor.close();

                }
                }
                if(ismatched==false)
                    Toast.makeText(Login.this, "Invalid details!!!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}