package com.inconceivable.xutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created
 * Author: yuqi
 * Email：inconceivable_man@163.com
 * Date: 2015/10/9
 */
//指定注解应用的位置
@Target(ElementType.FIELD)
//指定注解的作用范围，RUNTIME代表在运行时可以获取注解
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeAuthor {
    //代表当前注解包含一个叫做value的属性
    //在注解设置属性时直接就是 value = xxx
    //注解中 value这个名称属于默认属性，不需要写出来
    //CodeAuthor（3）
    int value();

}
