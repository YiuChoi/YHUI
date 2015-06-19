package xyz.yhsj.yhui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import xyz.yhsj.yhutils.tools.phone.NetWorkUtils;

/**
 * Created by LOVE on 2015/6/18.
 */
public class HttpUtils_Client {


    private static HttpUtils httpUtils = HttpUtils_Base.httpUtils;
    private static ProgressDialog pd;
    private static int onLoadingNum = 0;

    /**
     * @param context  上下文，不可为空
     * @param url      请求地址
     * @param params   参数
     * @param listener 回调函数
     */
    public static void post(@NonNull final Context context, String url, RequestParams params, final OnRequestCallBack listener) {

        post(context, false, url, params, listener);
    }


    /**
     * @param context            上下文，不可为空
     * @param showProgressDialog 是否显示进度条
     * @param url                请求地址
     * @param params             参数
     * @param listener           回调函数
     */
    public static void post(@NonNull final Context context, final boolean showProgressDialog, String url, RequestParams params, final OnRequestCallBack listener) {

        if (NetWorkUtils.isConnected(context)) {
            if (listener != null) {
                listener.netStatus(true, NetWorkUtils.getNetworkTypeName(context));
            }
        } else {
            if (listener != null) {
                listener.netStatus(false, "");
            }
            return;
        }


        if (showProgressDialog) {
            if (onLoadingNum == 0) {
                pd = ProgressDialog.show(context, null, "正在努力加载...");
            }
            onLoadingNum++;
        }


        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {

                if (showProgressDialog) {
                    onLoadingNum--;
                    if (onLoadingNum == 0) {
                        pd.dismiss();
                    }
                }

                if (listener != null) {
                    listener.onSuccess(responseInfo);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                if (showProgressDialog) {
                    onLoadingNum--;
                    if (onLoadingNum == 0) {
                        pd.dismiss();
                    }
                }

                if (listener != null) {
                    listener.onFailure(e, s);
                }
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {

                if (listener != null) {
                    listener.onLoading(total, current, isUploading);
                }
            }

        });

    }


    public interface OnRequestListener {

        void onSuccess(ResponseInfo<String> responseInfo);

        void onFailure(HttpException e, String s);

        void onLoading(long total, long current, boolean isUploading);

        void netStatus(boolean isConnected, String netType);

    }

    public static abstract class OnRequestCallBack implements OnRequestListener {


        public abstract void onSuccess(ResponseInfo<String> responseInfo);

        public abstract void onFailure(HttpException e, String s);

        public void onLoading(long total, long current, boolean isUploading) {
        }

        public void netStatus(boolean isConnected, String netType) {
        }

    }


}
