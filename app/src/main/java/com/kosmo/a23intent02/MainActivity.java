package com.kosmo.a23intent02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //부가데이터를 보낼 때 응답을 받기위한 코드
    public  static final int REQUEST_CODE = 0;

    // 위젯선언
    private Button btnOnlyData, btnDataReqResp;
    private EditText editUser, editPass;
    private TextView textViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯얻기
        btnOnlyData = (Button)findViewById(R.id.btn_only_data);
        btnDataReqResp = (Button)findViewById(R.id.btn_data_req_resp);
        editUser = (EditText)findViewById(R.id.edit_user);
        editPass = (EditText)findViewById(R.id.edit_pass);
        textViewMain = (TextView)findViewById(R.id.textview_main);

        //버튼에 리스너 부착
        btnOnlyData.setOnClickListener(listener);
        btnDataReqResp.setOnClickListener(listener);
    }

    //리스너 부착을 위한 이벤트 핸들러
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //인텐트를 통해 실행 할 액티비티를 설정한다.
            Intent intent = new Intent(v.getContext(), OnlyActivity.class);

            //부가데이터를 인텐트에 추가함
            intent.putExtra("USER", editUser.getText().toString());
            intent.putExtra("PASS", editPass.getText().toString());

            if (v == btnOnlyData) {
                //OnlyActivity를 실행한다. 단 데이터 전달만 하고 돌려받지 않음
                startActivity(intent);
            }
            else if(v == btnDataReqResp){
                /*
                부가데이터를 전달한 후 결과값을 돌려받음.
                startActivityForResult(인텐트, 요청코드)
                    : 요청코드는 인텐트를 받을 액티비티에서 식별자로 사용하거나
                       혹은 인텐트를 전달한 액티비티에서 식별자로 사용된다.
                 */
                intent.setClass(MainActivity.this, ResultActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        }
    };

    /*
    부가데이터를 전달했던 액티비티로부터 결과값을 받기위해 아래 메소드를
    오버라이딩 한다. 해당 메소드는 setResult()메소드를 호출하면 자동으로 콜백된다.
        매개변수
            requestCode : 내가 보낸 인텐트 확인용(요청코드)
            resultCode : 인텐트를 받은 액티비티에서 보낸 코드(응답코드)
            data : 인텐트를 받은 액티비티에서 보낸 인텐트(부가데이터 확인)

     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //내가보낸 요청코드를 받은 액티비티인지 확인 후 텍스트뷰에 출력
        if(requestCode == REQUEST_CODE){
            if(resultCode == ResultActivity.RESULT_CODE_FAIL_ID){
                textViewMain.setText(data.getStringExtra("FAIL_ID"));
            }
            else if(resultCode == ResultActivity.RESULT_CODE_FAIL_PWD){
                textViewMain.setText(data.getStringExtra("FAIL_PWD"));
            }
            else if(resultCode == Activity.RESULT_OK) {
                textViewMain.setText(data.getStringExtra("SUCCESS"));
            }
        }
    }
}























