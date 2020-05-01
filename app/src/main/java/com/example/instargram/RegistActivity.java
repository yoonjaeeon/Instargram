package com.example.instargram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        Button btnNew;
        Button btnId;
        ImageView imageLogo;

        final EditText txtNewName;
        final EditText txtNewId;
        final EditText txtNewPw;


        btnNew = findViewById(R.id.btnNew);
        btnId = findViewById(R.id.btnID);
        imageLogo = findViewById(R.id.imageLogo);
        txtNewName = findViewById(R.id.txtNewName);
        txtNewId = findViewById(R.id.txtNewId);
        txtNewPw = findViewById(R.id.txtNewPw);



        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_value();

            }
        });//end of btnNew

        btnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VO vo = new VO();
                DAO dao = new DAO();
                EditText editTextId = (EditText) findViewById(R.id.txtNewId) ;
                String id = editTextId.getText().toString();

                vo.setId(id);

                Boolean a = new DAO().idCheck(getApplicationContext(),vo);
                if(a == true){
                    Toast toast = Toast.makeText(RegistActivity.this, "ID 중복", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(RegistActivity.this, "ID 사용 가능", Toast.LENGTH_SHORT);
                    toast.show();

                }

            }
        });
    }//end of oncreate



//    private void checkId(){
//        VO vo = new VO();
//
//        EditText editTextId = (EditText) findViewById(R.id.txtNewId) ;
//        String id = editTextId.getText().toString() ;
//
//        new DAO().idCheck(getApplicationContext(),vo,id);
//    }//end of checkId

    private void save_value(){

        VO vo = new VO();
        EditText editTextName = (EditText) findViewById(R.id.txtNewName) ;
        String name = editTextName.getText().toString() ;

        EditText editTextId = (EditText) findViewById(R.id.txtNewId) ;
        String id = editTextId.getText().toString() ;

        EditText editTextPw = (EditText) findViewById(R.id.txtNewPw) ;
        String pw = editTextPw.getText().toString() ;


        vo.setName(name);
        vo.setId(id);
        vo.setPw(pw);

        new DAO().insert(getApplicationContext(),vo);

        if (name == null || name.isEmpty() || id == null || id.isEmpty() || pw == null || pw.isEmpty()){
            Toast toast = Toast.makeText(RegistActivity.this, "텍스트를 입력하세요", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Toast toast = Toast.makeText(RegistActivity.this, "회원가입 완료", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }//end of save_value


}//end of class

