package artbit.com.artbit;

import android.os.Handler;
import android.util.Log;

import com.google.gson.JsonObject;
import artbit.com.artbit.util.SqlConnector;

/**
 * Created by user on 2016-12-20.
 */

public class LoginThread extends Thread {
    private Handler handler;
    private String email;
    private String password;


    public LoginThread(Handler handler, String email, String password) {
        this.handler = handler;
        this.email = email;
        this.password = password;
    }

    @Override
    public void run() {
        super.run();

        //Json Object 만들기
        JsonObject object = new JsonObject();
        object.addProperty("email", email);
        object.addProperty("password", password);

        String response = SqlConnector.getInstance().post("http://203.255.81.47/login2.php", object.toString());
        Log.i("TEST", response);
        // Handler 를 통해서 Message를 전달
        if(response.equals("True"))
            handler.sendEmptyMessage(1);
        else
            handler.sendEmptyMessage(0);

    }
}
