package xyz.yhsj.yhui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import xyz.yhsj.yhui.R;
import xyz.yhsj.yhui.base.YH_Activity;
import xyz.yhsj.yhui.login.Login;
import xyz.yhsj.yhui.main.MainActivity;
import xyz.yhsj.yhutils.tools.sp.SharePreferenceUtil;

public class Welcome extends YH_Activity {
    private static final int TIME = 1500;
    private static final int GO_HOME = 1000;
    private static final int GO_LOGIN = 1001;
    private static final int GO_GUIDE = 1002;

    private static final String KEY_IS_FIRS_TIN = "is_first_in";

    private Jump_Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_main);

        handler = new Jump_Handler();

        jumpAty();
    }

    /**
     * activity跳转判断
     */
    private void jumpAty() {
        if (SharePreferenceUtil.getBoolean(getApplicationContext(), KEY_IS_FIRS_TIN, true)) {
            handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
        } else {
            handler.sendEmptyMessageDelayed(GO_LOGIN, TIME);
        }
        //自动登录时直接GO_HOME

        SharePreferenceUtil.setValue(getApplication(), KEY_IS_FIRS_TIN, false);

    }

    /**
     * 跳转到主页
     */
    private void goHome() {
        startActivity(new Intent(Welcome.this, MainActivity.class));
        finish();
    }

    /**
     * 跳转到登录
     */
    private void Login() {
        startActivity(new Intent(Welcome.this, Login.class));
        finish();
    }

    /**
     * 跳转到引导
     */
    private void goGuide() {
        Intent i = new Intent(Welcome.this, Welcome_Guide.class);
        startActivity(i);
        finish();
    }

    /**
     * 跳转线程
     */
    class Jump_Handler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_LOGIN:
                    Login();
                    break;

                case GO_GUIDE:
                    goGuide();
                    break;

            }
        }
    }


}
