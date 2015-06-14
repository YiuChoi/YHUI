package xyz.yhsj.yhui.base;

import android.content.Context;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.util.PreferencesCookieStore;

/**
 * Created by LOVE on 2015/6/12 012.
 */
public class HttpUtils_Base {

    public static HttpUtils httpUtils = init();

    private static PreferencesCookieStore cookieStore;

    private static HttpUtils init() {

        if (httpUtils == null) {
            httpUtils = new HttpUtils();

            if (cookieStore != null) {
                httpUtils.configCookieStore(cookieStore);
            } else {
                cookieStore = new PreferencesCookieStore(App_Application.getContext());
                httpUtils.configCookieStore(cookieStore);
            }
        }
        return httpUtils;
    }


}
