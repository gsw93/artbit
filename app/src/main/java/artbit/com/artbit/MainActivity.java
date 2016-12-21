package artbit.com.artbit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by JoenSeonHee on 2016-12-19.
 */

public class MainActivity extends AppCompatActivity {
    private EditText idInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button signUpButton;

    private boolean endFlag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);// 화면을 결정하는 놈
        setContentView(R.layout.activity_main);
        initialize();
        signUpButton = (Button) findViewById(R.id.sign_up_button);
        loginButton.setOnClickListener(new LoginButtonListener());
        signUpButton.setOnClickListener(new SingUpButtonListener());
    }

    @Override
    public void onBackPressed() {
        // 뒤로 가기 버튼을 눌렀을때 종료 확인 문구를 띄운 후 종료한다.
        if(endFlag==false){
            Snackbar.make(signUpButton, "종료 하시겠습니까?", Snackbar.LENGTH_SHORT).show();
            endFlag = true;
        }
        else
            super.onBackPressed();  // -> 종료시키는 코드
    }

    private class SingUpButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 화면 전환
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }

    private void initialize() {
        idInput = (EditText) findViewById(R.id.id_input);
        passwordInput = (EditText) findViewById(R.id.password_input);
        loginButton = (Button) findViewById(R.id.login_button);
    }

    private class LoginButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // E-mail 가져오기1
            String email = idInput.getText().toString();
            // Password 가져오기
            String password = passwordInput.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "빈칸을 채워주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            // 서버의 DB와 비교
            // 서버에 요청해야할 것
            // id 와 password를 확인할 수 있는 주소
            // id와 password 를 전달하는 키값
            //id랑 password가 맞을때 반환해 주는 값 = TRUE
            // POST or GET
            // email : gsw19
            // password : artbit123

            Handler handler = new Handler(new LoginHandler());
            Thread thread = new LoginThread(handler, email, password);
            thread.start();
        }
    }

    private class LoginHandler implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                idInput.setText("");
                passwordInput.setText("");
            }

            return true;
        }
    }
}
