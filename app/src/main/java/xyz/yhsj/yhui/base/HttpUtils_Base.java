package xyz.yhsj.yhui.base;

import android.content.Context;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.util.PreferencesCookieStore;

/**
 * Created by LOVE on 2015/6/12 012.
 */
public class HttpUtils_Base {

    public static HttpUtils init(Context context) {

        HttpUtils httpUtils = new HttpUtils();

        PreferencesCookieStore cookieStore = new PreferencesCookieStore(context);

        httpUtils.configCookieStore(cookieStore);

        return httpUtils;
    }


}
