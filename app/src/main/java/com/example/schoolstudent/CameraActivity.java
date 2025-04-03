package com.example.schoolstudent;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;

public class CameraActivity extends AppCompatActivity {
    ImageButton imgpic;
    ImageView imghinh;
    Button btnback3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imghinh = findViewById(R.id.imghinh);
        imgpic = findViewById(R.id.imgpic);
        btnback3 = findViewById(R.id.btnback3);

        imgpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pic_intent = new Intent(ACTION_IMAGE_CAPTURE);
                if (ActivityCompat.checkSelfPermission(CameraActivity.this,
                        android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CameraActivity.this,
                            new String[]{android.Manifest.permission.CAMERA}, 1);
                    return;
                }
                startActivityForResult(pic_intent, 99);
            }
        });

        btnback3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == SecondActivity.RESULT_OK) {
            Bitmap picture = (Bitmap) data.getExtras().get("data");

            imghinh.setImageBitmap(picture);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            picture.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent intent = new Intent();
            intent.putExtra("captured_image", byteArray);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
