package xyz.yhsj.yhui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.lidroid.xutils.util.LogUtils;
import xyz.yhsj.yhui.R;

import java.util.ArrayList;

/**
 * Created by LOVE on 2015/5/30.
 */
public class Adapter_RecyclerView_DB extends RecyclerView.Adapter<Adapter_RecyclerView_DB.ViewHolder> {
    public ArrayList<String> datas = null;

    public Adapter_RecyclerView_DB(ArrayList<String> datas) {
        this.datas = datas;
    }

    /**
     * 创建新View，被LayoutManager所调用
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_recyclerview_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);

        vh.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("更多。。。。");
            }
        });

        return vh;
    }

    /**
     * 将数据与界面进行绑定的操作
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(datas.get(position));
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(datas.get(position));

    }

    /**
     * 获取数据的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }


    /**
     * 自定义的ViewHolder，持有每个Item的的所有界面元素
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private ImageButton more;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
            more = (ImageButton) view.findViewById(R.id.more);
        }
    }


}