package com.copyalipaypassword.cc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * @author Created by cc on 17/5/25.
 * @fileName PasswordEditText
 * @githublink https://github.com/cc0819
 * @csdnlink http://blog.csdn.net/qq_25404567
 */

public class PasswordEditText extends EditText {
    private int clLine;
    private int clPoint;
    private int clBound;
    private int passwordLength;
    private int pointRadius;

    private Paint paintLine;
    private Paint paintPoint;
    private Paint paintBound;

    private int mWidth;
    private int mHeight;

    private int psdTextLength;

    public OnTextEndListener onTextEndListener;

    public void SetOnTextEndListener(OnTextEndListener onTextEndListener){
        this.onTextEndListener = onTextEndListener;
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initType(context,attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initType(context,attrs);
    }

    private void initType(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.passwordEditText);
        clLine = typedArray.getColor(R.styleable.passwordEditText_colorLine, Color.RED);
        clPoint = typedArray.getColor(R.styleable.passwordEditText_colorPoint, Color.BLUE);
        clBound = typedArray.getColor(R.styleable.passwordEditText_colorBound, Color.RED);
        passwordLength = typedArray.getInteger(R.styleable.passwordEditText_passwordLength, 4);
        pointRadius = typedArray.getInteger(R.styleable.passwordEditText_pointRadius, 10);
        init();
        //回收防内存泄漏
        typedArray.recycle();
    }



    private void init() {

        //设置获取焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //移除自带光标
        setCursorVisible(false);

        paintBound = new Paint();
        paintBound.setStrokeWidth(4);
        paintBound.setAntiAlias(true);
        paintBound.setColor(clBound);
        paintBound.setStyle(Paint.Style.STROKE);

        paintLine = new Paint();
        paintLine.setStrokeWidth(1);
        paintLine.setAntiAlias(true);
        paintLine.setColor(clLine);

        paintPoint = new Paint();
        paintPoint.setStrokeWidth(12);
        paintPoint.setAntiAlias(true);
        paintPoint.setColor(clPoint);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
//        drawRoundLine(canvas);
        drawDivisionLine(canvas);
        drawPassword(canvas);
    }

    //绘制边框
    private void drawRoundLine(Canvas canvas) {
        canvas.drawRoundRect(0,0,mWidth,mHeight,8,8,paintBound);
    }

    //绘制分割线
    private void drawDivisionLine(Canvas canvas) {
        for (int i = 1; i < passwordLength; i++) {
            float mX = mWidth * i / passwordLength;
            canvas.drawLine(mX, 8, mX, mHeight-8, paintLine);
        }
    }

    //绘制密码点
    private void drawPassword(Canvas canvas) {
        float cx, cy = mHeight / 2;
        float half = mWidth / passwordLength;
        for (int i = 0; i < psdTextLength; i++) {
            cx = half / 2 + half * i;
            canvas.drawCircle(cx, cy, pointRadius, paintPoint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        psdTextLength = text.toString().length();
        if (psdTextLength == passwordLength && passwordLength != 0){
            Log.e("info","---输入完成了---");
           onTextEndListener.onTextEndListener(text.toString());
        }
        invalidate();
    }




    public int getClLine() {
        return clLine;
    }

    public void setClLine(int clLine) {
        this.clLine = clLine;
    }

    public int getClPoint() {
        return clPoint;
    }

    public void setClPoint(int clPoint) {
        this.clPoint = clPoint;
    }

    public int getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    public int getPointRadius() {
        return pointRadius;
    }

    public void setPointRadius(int pointRadius) {
        this.pointRadius = pointRadius;
    }

    public void reset(){
        setText("");
        invalidate();
    }


    public  interface OnTextEndListener{
        void onTextEndListener(String string);
    }


}
