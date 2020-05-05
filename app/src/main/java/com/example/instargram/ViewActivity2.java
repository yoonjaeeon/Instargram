package com.example.instargram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ViewActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view2);

        Button btnNewContent2;
        ImageView photoView2;
        TextView textView2;

        btnNewContent2 = findViewById(R.id.btnNewContent2);
        photoView2 = findViewById(R.id.photoView2);
        textView2 = findViewById(R.id.textView2);

        Intent intent=getIntent();
        String $text = intent.getExtras().getString("$text");
        Bitmap photo = intent.getParcelableExtra("image");
        textView2.setText($text);
        photoView2.setImageBitmap(photo);

        btnNewContent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                startActivity(intent);
            }
        });//end of btnNewContent
    }//end of onCreate


}
