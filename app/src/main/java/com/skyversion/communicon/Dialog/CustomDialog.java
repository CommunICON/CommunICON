package com.skyversion.communicon.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.skyversion.communicon.R;

import graphics.CanvasView;

/**
 * Created by ss on 2016-08-16.
 */
public class CustomDialog extends Dialog {
    private ImageView colorImgView;

    private Button cancelBtn;
    private Button confirmBtn;
    private ImageView colorView;
    private Paint mPaint;
    private ImageView black;
    private ImageView red;
    private ImageView green;


    private CanvasView canvas;

    private color_Flag colorFlag;
    public enum color_Flag {
        BLACK,
        RED,
        GREEN;
    }

    public CustomDialog(Context context, CanvasView cv){
        super(context);

        canvas = cv;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // contentView 이전에 해줘야 한다.

        setContentView(R.layout.custom_dialog);

        init();
        start();

        WindowManager.LayoutParams lp = getWindow().getAttributes( ) ;
        WindowManager wm = ((WindowManager)context.getApplicationContext().getSystemService(context.getApplicationContext().WINDOW_SERVICE)) ;
        lp.width =  (int)( wm.getDefaultDisplay().getWidth( ) * 0.9 );
        getWindow().setAttributes( lp ) ;
    } // 커스텀 다이얼로그를 생성할 때 크기를 지정해줘야만이 반영된다.
    // onCreate 사용금지

    private void init(){

        cancelBtn = (Button)findViewById(R.id.cancelBtn);
        confirmBtn = (Button)findViewById(R.id.confirmBtn);

        black = (ImageView)findViewById(R.id.black);
        red = (ImageView)findViewById(R.id.red);
        green = (ImageView)findViewById(R.id.green);

        black.setOnClickListener(new ImageViewOnClickListener());
        red.setOnClickListener(new ImageViewOnClickListener());
        green.setOnClickListener(new ImageViewOnClickListener());
    }

    private class ImageViewOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.black:
                    colorFlag=color_Flag.BLACK;
                    canvas.setColor(1);
                    Toast.makeText(getContext(),"11",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.red:
                    colorFlag=color_Flag.RED;
                    canvas.setColor(2);
                    break;
                case R.id.green:
                    colorFlag=color_Flag.GREEN;
                    canvas.setColor(3);
                    break;
            }

            dismiss();
        }
    }

    private void start(){
        cancelBtn.setOnClickListener(new CancelButtonClickListener());
        confirmBtn.setOnClickListener(new ConfirmButtonClickListener());
    }

    class CancelButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            CustomDialog.this.dismiss();
        }
    }

    class ConfirmButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            switch (colorFlag){
                case BLACK:
                    canvas.setColor(1);
                    break;
                case RED:
                    canvas.setColor(2);
                    break;
                case GREEN:
                    canvas.setColor(3);
                    break;
            }

            CustomDialog.this.dismiss();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            dismiss();
        }

        return super.onTouchEvent(event);
    } // 외부 터치 시 palette 선택 해제

    public CanvasView getCanvas() {
        return this.canvas;
    }
}













//
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.skyversion.communicon.R;
//
///**
// * Created by ss on 2016-08-16.
// */
//public class CustomDialog extends Dialog{
//    private ImageView colorImgView;
//
//    private Button cancelBtn;
//    private Button confirmBtn;
//    private ImageView colorView;
//    private Paint mPaint;
//    private ImageView black;
//    private ImageView red;
//    private ImageView green;
//
//    public CustomDialog(Context context, ImageView colorView, Paint mPaint){
//        super(context);
//        this.colorView = colorView;
//        this.mPaint = mPaint;
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        // contentView 이전에 해줘야 한다.
//
//        setContentView(R.layout.custom_dialog);
//
//        init();
//        start();
//
//        WindowManager.LayoutParams lp = getWindow().getAttributes( ) ;
//        WindowManager wm = ((WindowManager)context.getApplicationContext().getSystemService(context.getApplicationContext().WINDOW_SERVICE)) ;
//        lp.width =  (int)( wm.getDefaultDisplay().getWidth( ) * 0.9 );
//        getWindow().setAttributes( lp ) ;
//    } // 커스텀 다이얼로그를 생성할 때 크기를 지정해줘야만이 반영된다.
//    // onCreate 사용금지
//
//    private void init(){
//        colorImgView = (ImageView)findViewById(R.id.colorImageView);
//        cancelBtn = (Button)findViewById(R.id.cancelBtn);
//        confirmBtn = (Button)findViewById(R.id.confirmBtn);
//
//        black = (ImageView)findViewById(R.id.black);
//        red = (ImageView)findViewById(R.id.red);
//        green = (ImageView)findViewById(R.id.material);
//
//        black.setOnClickListener(new ImageViewOnClickListener());
//        red.setOnClickListener(new ImageViewOnClickListener());
//        green.setOnClickListener(new ImageViewOnClickListener());
//    }
//
//    private class ImageViewOnClickListener implements View.OnClickListener{
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()){
//                case R.id.black:
//                    mPaint.setColor(Color.BLACK);
//                    break;
//                case R.id.red:
//                    mPaint.setColor(Color.RED);
//                    break;
//                case R.id.material:
//                    mPaint.setColor(Color.GREEN);
//                    break;
//            }
//
//            dismiss();
//        }
//    }
//
//    private void start(){
//        cancelBtn.setOnClickListener(new CancelButtonClickListener());
//        confirmBtn.setOnClickListener(new ConfirmButtonClickListener());
//    }
//
//    class CancelButtonClickListener implements View.OnClickListener{
//        @Override
//        public void onClick(View view) {
//            colorView.setImageResource(R.drawable.palette);
//            CustomDialog.this.dismiss();
//        }
//    }
//
//    class ConfirmButtonClickListener implements View.OnClickListener{
//        @Override
//        public void onClick(View view) {
//            colorView.setImageResource(R.drawable.palette);
//            CustomDialog.this.dismiss();
//        }
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if(event.getAction() == MotionEvent.ACTION_DOWN){
//            colorView.setImageResource(R.drawable.palette);
//            dismiss();
//        }
//
//        return super.onTouchEvent(event);
//    } // 외부 터치 시 palette 선택 해제
//}