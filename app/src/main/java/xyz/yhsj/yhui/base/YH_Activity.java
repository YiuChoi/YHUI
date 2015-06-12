package xyz.yhsj.yhui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.lidroid.xutils.HttpUtils;

/**
 * Created by LOVE on 2015/6/11 011.
 */
public class YH_Activity extends AppCompatActivity {
    public HttpUtils httpUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpUtils = HttpUtils_Base.init(getApplicationContext());

    }
}
