package xyz.yhsj.yhui.base.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import xyz.yhsj.yhui.R;

/**
 * Created by LOVE on 2015/6/23 023.
 */
public class Dialog_header extends LinearLayout {

    private Context context;
    private TextView title;
    private TextView second_Title;

    public Dialog_header(Context context) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.dialog_header, this, true);
        init();
    }

    public Dialog_header(Context context, String title_Str) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.dialog_header, this, true);
        init();
        setTitle(title_Str);
    }


    private void init() {
        title = (TextView) findViewById(R.id.title);
        second_Title = (TextView) findViewById(R.id.second_title);
    }

    public TextView getTitle() {
        return title;
    }

    private void setTitle(String title_str) {
        if (title != null) {
            title.setText(title_str);
        }
    }

    public TextView getSecond_Title() {
        return second_Title;
    }

    private void setSecond_Title(String title_str) {
        if (second_Title != null) {
            second_Title.setText(title_str);
        }
    }
}
