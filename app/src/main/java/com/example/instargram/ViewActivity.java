package com.example.instargram;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;


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

//        String content= "";
//        Intent intent=getIntent();
//
//            content = intent.getExtras().getString("content");
//            textView.setText(content);

//        이미지뷰 uri 가져와서 bitmap으로 전환하기
        ContentResolver resolver = getContentResolver();
        try{
            VO vo = new VO();
            DAO dao  = new DAO();
            String uri = String.valueOf(new DAO().uri(getApplicationContext(),vo));
            Uri myUri = Uri.parse(uri);

            String content = String.valueOf(new DAO().content(getApplicationContext(),vo));


            InputStream instream = resolver.openInputStream(myUri); //이미지 오픈
            Bitmap imageBitmap = BitmapFactory.decodeStream(instream); //비트맵으로 전환
            photoView.setImageBitmap(imageBitmap); //이미지 뷰에 넣음
            instream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        btnNewContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                startActivity(intent);


            }
        });//end of btnNewContent
    }//end of onCreate

}
