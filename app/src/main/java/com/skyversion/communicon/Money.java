package com.skyversion.communicon;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ss on 2016-08-14.
 */
public class Money extends Activity {
    private TextView tv;
    private EditText editText;
    private final float startSize = 40;
    private final float endSize = 20;
    private final int aniDuration = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_activity);

        editText = (EditText)findViewById(R.id.money);
//        editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(editText.getText().length()<=0){
//                    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                        @Override
//                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                            Toast.makeText(getApplicationContext(), v.getText().toString(), Toast.LENGTH_SHORT).show();
//                            return true;
//                        }
//                    });
//                }
//            }
//        });

//        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
//        animator.setDuration(aniDuration);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float aniValue = (float)animation.getAnimatedValue();
//                tv.setTextSize(aniValue);
//            }
//        });
//        animator.start();
    }
}