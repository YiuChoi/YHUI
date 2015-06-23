package xyz.yhsj.yhui.base.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import xyz.yhsj.yhui.R;

/**
 * Created by LOVE on 2015/6/23 023.
 */
public class Dialog_footer extends LinearLayout {

    private Context context;
    private TextView title;
    private Button close;
    private Button confirm;
    private onBtnClickListener listener;

    public Dialog_footer(Context context) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.dialog_footer, this, true);
        init();
    }


    private void init() {
        title = (TextView) findViewById(R.id.title);
        close = (Button) findViewById(R.id.footer_close);
        confirm = (Button) findViewById(R.id.footer_confirm);

    }

    public TextView getTitle() {
        return title;
    }

    private void setTitle(String title_str) {
        if (title != null) {
            title.setText(title_str);
        }
    }

    public Button getClose() {
        return close;
    }


    public Button getConfirm() {
        return confirm;
    }


    public void setListener(String close_str, String confirm_Str, onBtnClickListener mlistener) {
        this.listener = mlistener;

        if (close_str != null) {
            getClose().setText(close_str);

            getClose().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.setOnCloseClickListener(v);
                    }
                }
            });

        } else {
            getClose().setVisibility(View.GONE);
        }


        if (confirm_Str != null) {
            getConfirm().setText(confirm_Str);

            getConfirm().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.setOnConfirmClickListener(v);
                    }
                }
            });
        }

    }

    public interface onBtnClickListener {

        void setOnCloseClickListener(View v);

        void setOnConfirmClickListener(View v);
    }

}
