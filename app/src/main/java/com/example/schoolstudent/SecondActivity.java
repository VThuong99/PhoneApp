package com.example.schoolstudent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView tvName, tvId, tvClass, tvPhone, tvYear, tvMajor;
    Button btnBack, btnCall, btnText, btnCamera;
    ImageView stuPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvName = findViewById(R.id.tvName2);
        tvId = findViewById(R.id.tvId2);
        tvClass = findViewById(R.id.tvClass2);
        tvPhone = findViewById(R.id.tvPhone2);
        tvYear = findViewById(R.id.tvYear2);
        tvMajor = findViewById(R.id.tvMajor2);
        btnBack = findViewById(R.id.btnBack);
        btnCall = findViewById(R.id.btnCall);
        btnText = findViewById(R.id.btnText);
        btnCamera = findViewById(R.id.btnCamera);
        stuPhoto = findViewById(R.id.imageView3);

        Intent intent = getIntent();
        String hoTen = intent.getStringExtra("hoTen");
        String maSo = intent.getStringExtra("maSo");
        String lopHoc = intent.getStringExtra("lopHoc");
        String soDt = intent.getStringExtra("soDt");
        String namHoc = intent.getStringExtra("namHoc");
        String chuyenNganh = intent.getStringExtra("chuyenNganh");

        tvName.setText("HỌ VÀ TÊN: " + hoTen);
        tvId.setText("MSSV: " + maSo);
        tvClass.setText("LỚP: " + lopHoc);
        tvPhone.setText("SĐT: " + soDt);
        tvYear.setText("SINH VIÊN NĂM THỨ: " + namHoc);
        tvMajor.setText("CHUYÊN NGÀNH: " + chuyenNganh);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soDt != null && !soDt.isEmpty()) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + soDt));
                    startActivity(callIntent);
                }
            }
        });

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soDt != null && !soDt.isEmpty()) {
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.setData(Uri.parse("smsto:" + soDt));
                    startActivity(smsIntent);
                }
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, CameraActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == SecondActivity.RESULT_OK && data != null) {
            byte[] byteArray = data.getByteArrayExtra("captured_image");
            if (byteArray != null) {
                Bitmap capturedImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                stuPhoto.setImageBitmap(capturedImage);
            }
        }
    }
}
