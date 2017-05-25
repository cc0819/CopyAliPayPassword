# CopyAliPayPassword
模仿支付宝支付密码
展示效果

![image](https://github.com/cc0819/CopyAliPayPassword/blob/master/art/show.gif)

1、可以设置密码位数

2、每个格子能输入一位的数字

3、背景框、分割线、圆点颜色可以设置

4、位数输入满后可直接进行提示或后续操作


<?xml version="1.0" encoding="utf-8"?>  
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"  
    xmlns:tools="http://schemas.android.com/tools"  
    xmlns:psw="http://schemas.android.com/apk/res-auto"  
    android:layout_width="match_parent"  
    android:layout_height="match_parent"  
    tools:context="com.copyalipaypassword.cc.MainActivity">  
  
    <com.copyalipaypassword.cc.PasswordEditText  
        android:id="@+id/psdEditText"  
        android:layout_width="match_parent"  
        android:layout_height="60dp"  
        android:layout_marginTop="10dp"  
        android:layout_marginLeft="10dp"  
        android:layout_marginRight="10dp"  
        android:background="@drawable/bg_alipay"  
        android:maxLength="6"  
        psw:passwordLength = "6"  
        />  
</RelativeLayout>  


这里需要注意的是

passwordLength设置的长度要和maxLength设置的长度要一样。

拿设置6位密码来举例：

如果输入已经到6位还是继续输入的话，虽然试图中没有变化，但是你打印长度可以看出来还是继续增长的，

其次在自定义控件中我想设置maxLength根据用户设置的passwordLength来的，奈何实在没有找到。

