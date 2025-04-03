package com.example.schoolstudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etId, etClass, etPhone;
    RadioGroup rgYear, rgMajor;
    Button btnSubmit, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etId = findViewById(R.id.etId);
        etClass = findViewById(R.id.etClass);
        etPhone = findViewById(R.id.etPhone);
        rgYear = findViewById(R.id.rgYear);
        rgMajor = findViewById(R.id.rgMajor);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = etName.getText().toString();
                String maSo = etId.getText().toString();
                String lopHoc = etClass.getText().toString();
                String soDt = etPhone.getText().toString();

                int selectedYear = rgYear.getCheckedRadioButtonId();
                RadioButton radioYear = findViewById(selectedYear);
                String namHoc = (radioYear != null) ? radioYear.getText().toString() : "Chưa chọn";

                int selectedMajor = rgMajor.getCheckedRadioButtonId();
                RadioButton radioMajor = findViewById(selectedMajor);
                String chuyenNganh = (radioMajor != null) ? radioMajor.getText().toString() : "Chưa chọn";

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("hoTen", hoTen);
                intent.putExtra("maSo", maSo);
                intent.putExtra("lopHoc", lopHoc);
                intent.putExtra("soDt", soDt);
                intent.putExtra("namHoc", namHoc);
                intent.putExtra("chuyenNganh", chuyenNganh);
                startActivity(intent);
            }
        });
    }
}
