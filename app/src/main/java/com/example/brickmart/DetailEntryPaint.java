package com.example.brickmart;

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

public class DetailEntryPaint extends AppCompatActivity {

    EditText cname,cphone,cnumber,cprice,ccompany,cdistrict,cpincode,ccolor;
    Button Submit;
    Mydatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_entry_paint);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cname = findViewById(R.id.cname);
        cphone = findViewById(R.id.cphone);
        cnumber = findViewById(R.id.cnumber);
        cprice = findViewById(R.id.cprice);
        ccompany = findViewById(R.id.ccompany);
        ccolor = findViewById(R.id.ccolor);
        cdistrict = findViewById(R.id.pdistrict);
        cpincode = findViewById(R.id.ppin);
        Submit = findViewById(R.id.Submit);
        mydatabase = new Mydatabase(this, "Mydatabase.db", null, 1);

        int id = 3;
        String name = getIntent().getStringExtra("name");
        String password = getIntent().getStringExtra("password");

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namee, phone, number, price, company, district, pincode, color;
                namee = cname.getText().toString();
                phone = cphone.getText().toString();
                number = cnumber.getText().toString();
                price = cprice.getText().toString();
                company = ccompany.getText().toString();
                district = cdistrict.getText().toString();
                pincode = cpincode.getText().toString();
                color = ccolor.getText().toString();
                if (name.isEmpty() || phone.isEmpty() || number.isEmpty() || color.isEmpty() || price.isEmpty() || company.isEmpty() || district.isEmpty() || pincode.isEmpty()) {
                    Toast.makeText(DetailEntryPaint.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean result = mydatabase.insertdetails(name, password, namee, phone, number, price,color, company, district, pincode, id);
                    if (result)
                        Toast.makeText(DetailEntryPaint.this, "Data entered successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailEntryPaint.this, "Data entry unsuccessfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}