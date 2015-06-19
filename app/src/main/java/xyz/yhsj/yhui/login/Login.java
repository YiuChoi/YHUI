package xyz.yhsj.yhui.login;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import xyz.yhsj.yhui.R;
import xyz.yhsj.yhui.base.HttpUtils_Client;
import xyz.yhsj.yhui.base.JsonUtil;
import xyz.yhsj.yhui.base.YH_Activity;
import xyz.yhsj.yhui.login.model.User;
import xyz.yhsj.yhutils.tools.keyboard.KeyBoardUtils;
import xyz.yhsj.yhutils.tools.sp.SharePreferenceUtil;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends YH_Activity {

    public static final String CHECKBOX_RE_PSD = "checkbox_re_psd";
    public static final String USERNAME = "username";
    public static final String PASSORD = "password";
    @ViewInject(R.id.username)
    private TextInputLayout mUserName;
    @ViewInject(R.id.password)
    private TextInputLayout mPassword;
    @ViewInject(R.id.signin_button)
    private AppCompatButton mSignInButton;
    @ViewInject(R.id.title)
    private TextView title;
    @ViewInject(R.id.login_form)
    private ScrollView scrollView;
    @ViewInject(R.id.re_password)
    private AppCompatCheckBox checkBox_re_psd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        ViewUtils.inject(this);
        init();
    }

    /**
     * 初始化
     */
    private void init() {


        //用来使错误条显示
        mUserName.setError(" ");
        mPassword.setError(" ");

        //监听键盘回车事件
        mPassword.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                KeyBoardUtils.closeKeybord(mUserName.getEditText(), Login.this);
                attemptLogin();
                return true;
            }
        });

        //登录按钮监听
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        //点击空白处关闭键盘
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                KeyBoardUtils.closeKeybord(mUserName.getEditText(), Login.this);
                return false;
            }
        });

        //这里得到保存的用户名密码
        checkBox_re_psd.setChecked(SharePreferenceUtil.getBoolean(Login.this, CHECKBOX_RE_PSD, true));
        if (checkBox_re_psd.isChecked()) {
            mUserName.getEditText().setText(SharePreferenceUtil.getString(Login.this, USERNAME, ""));
            mPassword.getEditText().setText(SharePreferenceUtil.getString(Login.this, PASSORD, ""));
        }

    }


    /**
     * 登录
     */
    public void attemptLogin() {

        //重置错误信息
        mUserName.setError(" ");
        mPassword.setError(" ");

        // 获取登录信息
        String email = mUserName.getEditText().getText().toString();
        String password = mPassword.getEditText().getText().toString();

        //是否取消登录
        boolean cancel = false;
        View focusView = null;


        // 校验密码
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("密码为空");
            focusView = mPassword;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPassword.setError("密码太短");
            focusView = mPassword;
            cancel = true;
        }

        // 校验用户名
        if (TextUtils.isEmpty(email)) {
            mUserName.setError("邮箱为空");
            focusView = mUserName;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mUserName.setError("邮箱无效");
            focusView = mUserName;
            cancel = true;
        }

        if (cancel) {
            //使无效的输入框获取焦点
            focusView.requestFocus();
        } else {
            //登录
            UserLoginTask(email, password);

        }
    }

    /**
     * username是否有效
     *
     * @return
     */
    private boolean isEmailValid(String username) {
        //是否包含@
//        return username.contains("@");
        return true;
    }

    /**
     * 密码是否有效
     *
     * @return
     */
    private boolean isPasswordValid(String password) {
//        return password.length() > 4;
        return true;
    }

    public void UserLoginTask(final String username, final String password) {

        //拼装参数
        RequestParams params = new RequestParams();
        params.addBodyParameter("account", username);
        params.addBodyParameter("password", password);


        HttpUtils_Client.post(Login.this, true, "http://182.92.96.58:8005/yrt/login", params, new HttpUtils_Client.OnRequestCallBack() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //成功登陆后保存账号密码
                SharePreferenceUtil.setValue(Login.this, CHECKBOX_RE_PSD, checkBox_re_psd.isChecked());
                SharePreferenceUtil.setValue(Login.this, USERNAME, username);
                SharePreferenceUtil.setValue(Login.this, PASSORD, password);

                LogUtils.i(responseInfo.result);

                User user = (User) JsonUtil.jsonToBean(responseInfo.result, User.class);


                LogUtils.i(user.getIsPass());


//                startActivity(new Intent(Login.this, MainActivity.class));
//                finish();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Snackbar.make(scrollView, "登录失败，请稍后再试", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

            @Override
            public void netStatus(boolean isConnected, String netType) {
                if (!isConnected) {
                    Snackbar.make(scrollView, "没网搞个锤子？", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }

            }
        });


    }

}



