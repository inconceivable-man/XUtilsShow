package com.inconceivable.xutils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.inconceivable.xutils.annotation.CodeAuthor;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnCompoundButtonCheckedChange;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class MainActivity extends Activity {

    @ViewInject(R.id.main_txt_info)
    private TextView textView;

    @ViewInject(R.id.btn)
    private Button btn;

    //相当于string包含了一个CodeAuthor
    @CodeAuthor(R.string.app_name)
    private String string;

    @ResInject(id = R.string.app_name, type = ResType.String)
    private String appName;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ViewUtils.inject(this);
        textView.setText("你好 by ViewUtils");

        btn.setText("电我一下");

        //进行反射的处理，获取string成员变量
        Class c = this.getClass();
        try {
            //取变量
            Field field = c.getDeclaredField("string");
            field.setAccessible(true);
            //从成员变量声明中获取注解
            CodeAuthor annotation = field.getAnnotation(CodeAuthor.class);
            int v = annotation.value();

            //从资源取字符串
            String str = getString(v);

            //赋值
            field.set(this, "Author: " + str);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Log.d("CodeAuthor", "string = " + string);
        Log.d("CodeAuthor", "appName = " + appName);
    }

    @OnClick(R.id.main_txt_info)
    public void clickShowToast(View v) {
        Toast.makeText(this, "吐司", Toast.LENGTH_SHORT).show();
    }

    @OnCompoundButtonCheckedChange(R.id.chk_test)
    public void checkChange(CompoundButton checkBox, boolean isChecked) {
        Toast.makeText(this, "设置选中", Toast.LENGTH_SHORT).show();
    }


    private void test() {

    }
}
