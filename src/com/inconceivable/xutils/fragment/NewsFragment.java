package com.inconceivable.xutils.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.inconceivable.xutils.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created
 * Author: yuqi
 * Email：inconceivable_man@163.com
 * Date: 2015/10/9
 */
public class NewsFragment extends Fragment {

    @ViewInject(R.id.main_txt_info)
    private TextView txtInfo;

    public NewsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.news_fragment, container, false);
        ViewUtils.inject(this, inflate);
        return inflate;
    }
}