package com.example.instargram;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.InputStream;

public class ContentActivity extends AppCompatActivity implements AutoPermissionsListener {
    Button btnPhoto;
    Button btnSub;
    EditText txtWrite;
    ImageView photoView;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        txtWrite = findViewById(R.id.txtWrite);
        btnPhoto=findViewById(R.id.btnPhoto);
        btnSub=findViewById(R.id.btnSub);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openGallery();
            }
        });//end of btnPhoto

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewActivity viewActivity = new ViewActivity();

                imageView2 = findViewById(R.id.imageView2);
                txtWrite = findViewById(R.id.txtWrite);

                viewActivity.setTextView(txtWrite);
                viewActivity.setPhotoView(imageView2);

                Toast toast = Toast.makeText(ContentActivity.this, "게시글 작성 완료", Toast.LENGTH_SHORT);
                toast.show();

//                BitmapDrawable d = (BitmapDrawable)(ImageView)view.findViewById(R.id.imageView2)).getDrawable();
//                Bitmap b = d.getBitmap();

//                imageView2.setImageBitmap(b);
//
//                Intent intent = new Intent();
//                intent.putExtra("bm", (Bitmap)b);




                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(intent);

            }
        });//end of btnSub
        AutoPermissions.Companion.loadAllPermissions(this,101); //permission 권한을 물어보는 창을 띄움
    }//end of onCreate

    //갤러리 앱 여는 메서드
    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 101); //ForResult = 결과값을 가져옴
    }//end of openGallery

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageView2 = findViewById(R.id.imageView2);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Uri fileUri= data.getData();
            ContentResolver resolver = getContentResolver();
            try{
                InputStream instream = resolver.openInputStream(fileUri); //이미지 오픈
                Bitmap imageBitmap = BitmapFactory.decodeStream(instream); //비트맵으로 전환
                imageView2.setImageBitmap(imageBitmap); //이미지 뷰에 넣음
                instream.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }//end of onActivityResult



    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
    //권한 승인을 위한 오버라이딩 (gradle에 다운받은것을 사용하기위해)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

}//end of Class
