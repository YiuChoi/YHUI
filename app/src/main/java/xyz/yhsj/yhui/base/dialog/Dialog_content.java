package xyz.yhsj.yhui.base.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import xyz.yhsj.yhui.R;

/**
 * Created by LOVE on 2015/6/23 023.
 */
public class Dialog_Content extends LinearLayout {

    private Context context;
    private TextView info;

//    public Dialog_Content(Context context) {
//        super(context);
//        this.context = context;
//        LayoutInflater.from(context).inflate(R.layout.dialog_content, this, true);
//        init();
//    }

    public Dialog_Content(Context context, String info_Str) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.dialog_content, this, true);
        init();
        setInfo(info_Str);
    }


    private void init() {
        info = (TextView) findViewById(R.id.info);
    }

    public TextView getTitle() {
        return info;
    }

    private void setInfo(String title_str) {
        if (info != null) {
            info.setText(title_str);
        }
    }


}
