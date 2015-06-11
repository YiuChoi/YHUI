package xyz.yhsj.yhui.welcome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import xyz.yhsj.yhui.R;
import xyz.yhsj.yhui.base.YH_Activity;

public class Welcome extends YH_Activity {
    private static final int TIME = 1500;
    private static final int GO_HOME = 1000;
    private static final int GO_LOGIN = 1001;
    private static final int GO_GUIDE = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }

    /**
     * Ìø×ªÏß³Ì
     */
    class jump_Handler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;

                case GO_LOGIN:
                    Login(username, password);
                    break;

                case GO_GUIDE:
                    goGuide();
                    break;

            }
        }
    }


}
