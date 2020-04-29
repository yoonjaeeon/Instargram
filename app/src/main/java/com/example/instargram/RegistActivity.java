package com.example.instargram;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class RegistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        Button btnNew;
        ImageView imageLogo;

        final EditText txtNewName;
        final EditText txtNewId;
        final EditText txtNewPw;


        btnNew = findViewById(R.id.btnNew);
        imageLogo = findViewById(R.id.imageLogo);
        txtNewName = findViewById(R.id.txtNewName);
        txtNewId = findViewById(R.id.txtNewId);
        txtNewPw = findViewById(R.id.txtNewPw);


        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_value();

            }
        });




    }

    private void save_value(){

        VO vo = new VO();
        EditText editTextName = (EditText) findViewById(R.id.txtNewName) ;
        String name = editTextName.getText().toString() ;

        EditText editTextId = (EditText) findViewById(R.id.txtNewId) ;
        String id = editTextId.getText().toString() ;

        EditText editTextPw = (EditText) findViewById(R.id.txtNewPw) ;
        String pw = editTextName.getText().toString() ;


        vo.setName((name));
        vo.setId(id);
        vo.setPw(pw);

        new DAO().insert(getApplicationContext(),vo);


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
}
