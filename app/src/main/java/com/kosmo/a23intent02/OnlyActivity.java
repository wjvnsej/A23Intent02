package com.kosmo.a23intent02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OnlyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only);

        /*
        메인액티비티에서 전달한 부가데이터를 가져온다. 저장시 사용한 키값을 이용한다.
         */
        String user = getIntent().getStringExtra("USER");
        String pass = getIntent().getStringExtra("PASS");

        //저장된 부가데이터를 텍스트뷰에 설정함
        ((TextView)findViewById(R.id.textview_only)).setText(
                String.format("아이디 : %s, 비밀번호 : %s", user, pass)
        );

        //메인액티비티로 돌아가기 버튼으로 액티비티를 종료함
        ((Button)findViewById(R.id.btn_finish)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
