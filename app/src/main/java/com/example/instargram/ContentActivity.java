package com.example.instargram;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
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
    ImageView imageView2;
    Bitmap bitmap;
    Uri uri;
    String imagePath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageView2 = findViewById(R.id.imageView2);
        System.out.print("\n==================================");
        if (requestCode == 101 && resultCode == RESULT_OK) {
            txtWrite = findViewById(R.id.txtWrite);
            Uri fileUri = data.getData();
            System.out.print("\n 33333==================================");
            imagePath = getRealPathFromURI(fileUri);
            System.out.print("\n 4444444"+imagePath);
            ContentResolver resolver = getContentResolver();
            try {
                InputStream instream = resolver.openInputStream(fileUri); //이미지 오픈
                Bitmap imageBitmap = BitmapFactory.decodeStream(instream); //비트맵으로 전환
                imageView2.setImageBitmap(imageBitmap); //이미지 뷰에 넣음
                instream.close();

                System.out.print("\n 555555====");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//end of onActivityResult


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        txtWrite = findViewById(R.id.txtWrite);
        btnPhoto = findViewById(R.id.btnPhoto);
        btnSub = findViewById(R.id.btnSub);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });//end of btnPhoto

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView2 = findViewById(R.id.imageView2);

                VO vo = new VO();
                String sTxtWrite = txtWrite.getText().toString();
                vo.setContent(sTxtWrite);
                vo.setUri(imagePath);
                new DAO().insertContent(getApplicationContext(), vo);

                Toast toast = Toast.makeText(ContentActivity.this, "게시글 작성 완료", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(getApplicationContext(), ViewActivity2.class);
                intent.putExtra("$text", sTxtWrite); /*송신*/
                intent.putExtra("image", bitmap);
                startActivity(intent);
            }
        });//end of btnSub
        AutoPermissions.Companion.loadAllPermissions(this, 101); //permission 권한을 물어보는 창을 띄움
    }//end of onCreate

    //갤러리 앱 여는 메서드
    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 101); //ForResult = 결과값을 가져옴
    }//end of openGallery

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {}

    //권한 승인을 위한 오버라이딩 (gradle에 다운받은것을 사용하기위해)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    private String getRealPathFromURI(Uri contentUri) {
        if (contentUri.getPath().startsWith("/storage")) {
            return contentUri.getPath();
        }
        String id = DocumentsContract.getDocumentId(contentUri).split(":")[1];
        String[] columns = {MediaStore.Files.FileColumns.DATA};
        String selection = MediaStore.Files.FileColumns._ID + " = " + id;
        Cursor cursor = getContentResolver().query(MediaStore.Files.getContentUri("external"), columns, selection, null, null);
        try {
            int columnIndex = cursor.getColumnIndex(columns[0]);
            if (cursor.moveToFirst()) {
                return cursor.getString(columnIndex);
            }
        } finally {
            cursor.close();
        }
        return null;
    }


}//end of Class
