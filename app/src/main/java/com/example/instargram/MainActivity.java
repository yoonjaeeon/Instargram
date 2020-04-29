package com.example.instargram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnRegist;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegist = findViewById(R.id.btnRegist);

        final EditText textId= findViewById(R.id.textId);
        final EditText textPw= findViewById(R.id.textPw);
        imageView= findViewById(R.id.imageView);
        btnLogin.setOnClickListener(new View.OnClickListener() {//로그인 버튼
            @Override
            public void onClick(View v) {
                DAO dao = new DAO();
                String Id = textId.getText().toString();
                String Pw = textPw.getText().toString();

                EditText editId = (EditText) findViewById(R.id.textId);
                String id = editId.getText().toString();

                EditText editTPw = (EditText) findViewById(R.id.textPw);
                String pw = editTPw.getText().toString();
                VO vo = new VO();
//                vo.set_id(Integer.parseInt());
                vo.setId(id);
                vo.setPw(pw);

                Boolean a = new DAO().loginCheck(getApplicationContext(),vo);
                if(a == true){
                    Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                    startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(MainActivity.this, "로그인 실패(ID,PW 확인)", Toast.LENGTH_SHORT);
                    toast.show();

                }



            }



        });//end of login



        btnRegist.setOnClickListener(new View.OnClickListener() { //회원가입버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistActivity.class);
                startActivity(intent);
            }
        });//end of submit
    }
}
