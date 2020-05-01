package com.example.instargram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class ViewActivity extends AppCompatActivity {

    Button btnNewContent;
    ImageView photoView;
    TextView textView;

    public ImageView getPhotoView() {
        return photoView;
    }

    public void setPhotoView(ImageView photoView) {
        this.photoView = photoView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
        btnNewContent = findViewById(R.id.btnNewContent);
        photoView = findViewById(R.id.photoView);
        textView = findViewById(R.id.textView);

        btnNewContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                startActivity(intent);
            }
        });//end of btnNewContent
    }//end of onCreate

}
