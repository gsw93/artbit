package artbit.com.artbit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by user on 2016-12-20.
 */

public class SignUpActivity extends AppCompatActivity {

    private EditText input_mail;
    private EditText input_password;
    private EditText input_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 보여줄 화면을 결정하는 함수.
        setContentView(R.layout.activity_signup);

        input_mail = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);
        input_name = (EditText) findViewById(R.id.input_name);
    }


}
