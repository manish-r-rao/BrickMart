package com.example.brickmart;
import android.content.Intent;
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

public class Register extends AppCompatActivity {

    EditText username,password,repassword;
    Button register,login;
    Mydatabase Mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        username=findViewById(R.id.name);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);

        register=findViewById(R.id.register);
        login=findViewById(R.id.login);
        Mydb=new Mydatabase(this,"Mydatabase.db",null,1);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if(username.getText().toString().equals("") || password.getText().toString().equals("") || repassword.getText().toString().equals(""))
                {
                    Toast.makeText(Register.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else if(!password.getText().toString().equals(repassword.getText().toString()))
                {
                    Toast.makeText(Register.this, "Password entered doesn't match", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int result=Mydb.alreadyThere(username.getText().toString(),password.getText().toString());
                    if(result==-1)
                        Toast.makeText(Register.this, "Username not available, use any other username or try to login", Toast.LENGTH_LONG).show();

                    else
                    {
                        boolean resultt=Mydb.insertdata(username.getText().toString().trim(),password.getText().toString().trim());
                        if(resultt)
                            Toast.makeText(Register.this, "Saved user details, click on Login", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Register.this, "Detail entry unsuccessfull", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

    }
}