package com.kosmo.a23intent02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    /*
    Result 코드로 사용 할 상수 선언.
    메인액티비티에서 받은 응답을 구분하기 위한 용도로 사용됨
     */
    public static final int RESULT_CODE_FAIL_ID = 1;
    public static final int RESULT_CODE_FAIL_PWD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        /*
        메인에서 전달한 부가데이터를 받아서 텍스트뷰에 설정함
         */
        final Intent intent = getIntent();
        final String user = intent.getStringExtra("USER");
        final String pass = intent.getStringExtra("PASS");

        //텍스트뷰에 출력하기
        ((TextView)findViewById(R.id.textview_for_result)).setText(
                String.format("아이디 : %s, 패스워드 : %s", user, pass)
        );

        //결과값 전송 버튼에 리스너 부착
        ((Button)findViewById(R.id.btn_finish_for_result)).setOnClickListener(new View.OnClickListener() {

            /*
            아이디/패스워드가 일치하는지를 판단한 후 setResult()를 통해
            결과값을 메인액티비티로 반환한다.
            setResult(결과코드, 인텐트) 형태로 호출하면
            자동으로 인텐트를 보낸 액티비티의 onActivityResult()
            메소드가 콜백된다.
             */
            @Override
            public void onClick(View v) {
                if(!"kosmo".equals(user)){
                    intent.putExtra("FAIL_ID", "아이디 오류");
                    setResult(RESULT_CODE_FAIL_ID, intent);
                }
                else if(!"1234".equals(pass)) {
                    intent.putExtra("FAIL_PWD", "비번 오류");
                    setResult(RESULT_CODE_FAIL_PWD, intent);
                }
                else {
                    intent.putExtra("SUCCESS", user + "님 반갑습니다.");
                    setResult(Activity.RESULT_OK, intent);
                }
                finish();
            }
        });

    }
}
