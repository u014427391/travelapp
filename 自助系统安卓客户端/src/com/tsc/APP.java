package com.tsc;

import android.app.Application;
/**
 * APPlication类
 * application是用来保存全局变量的，
 * 并且是在package创建的时候就跟着存在了。所以当我们需要创建全局变量的时候，
 * 不需 要再像j2se那样需要创建public权限的static变量，而直接在application中去实现。
 * 只需要调用Context的getApplicationContext或者Activity的getApplication方法来获得一个application对象，
 * 再做出相应 的处理。
 *	
 */
public class APP extends Application{

	
}
