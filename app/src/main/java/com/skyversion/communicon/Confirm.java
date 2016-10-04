package com.skyversion.communicon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ss on 2016-08-14.
 */
public class Confirm extends Activity {
    private ImageView mainImg;
    private ImageView subImg;
    private Integer index;
    private boolean isIconified = false;
    private TextView mainImgText, subImgText;
    private String main = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_layout);

        Intent intent = getIntent();
        index = intent.getIntExtra("pos", -1);
        isIconified = intent.getBooleanExtra("isIconified", false);
        init();
    }

    private void init(){
        mainImg = (ImageView)findViewById(R.id.selectedMainImgView);
        subImg = (ImageView)findViewById(R.id.selectedSubImgView);

        mainImgText = (TextView)findViewById(R.id.mainImgText);
        subImgText = (TextView)findViewById(R.id.subImgText);

        main = null;
        int value;
        if(MainActivity.common.isBookmark)
            value = MainActivity.common.getIconSelectedPos();
        else
            value = MainActivity.common.selectedLocale;

        switch (value){
            case 0:
                main = MainActivity.common.when[MainActivity.common.selectedLocale];
                break;
            case 1:
                main = MainActivity.common.where[MainActivity.common.selectedLocale];
                break;
            case 2:
                main = MainActivity.common.how_much[MainActivity.common.selectedLocale];
                break;
        }

        if(isIconified){
            mainImgText.setText(MainActivity.common.getMainText());
            subImgText.setText(MainActivity.common.getBookmarkLists().get(MainActivity.common.getIconSelectedPos()).getSubImgText(MainActivity.common.selectedLocale));
        }else{
//            int ss = MainActivity.common.getIconSelectedPos();
//            ArrayList<IconList> aa = MainActivity.common.getIconLists();
//            IconList dsf = aa.get(ss);
//            String sub =dsf.getText(pos);
//            MainActivity.common.setMainText(main);
//            MainActivity.common.setSubText(sub);
            mainImgText.setText(MainActivity.common.getMainText());
            subImgText.setText(MainActivity.common.getIconLists().get(MainActivity.common.getIconSelectedPos()).getText(MainActivity.common.selectedLocale));
        }


//        Bitmap src = BitmapFactory.decodeResource(getResources(), MainActivity.common.getSelectedMainImgId());
//        Bitmap des = Bitmap.createScaledBitmap(src, mainImg.getWidth(),mainImg.getHeight(),true);

//        mainImg.setImageBitmap(des);

//        mainImg.setImageResource(R.drawable.ic_android_black_24dp);
        mainImg.setImageResource(MainActivity.common.getSelectedMainImgId());
        subImg.setImageResource(MainActivity.common.getSelectedSubImgId());
    }

    public void confirm(View v){
        Intent intent;

        switch(MainActivity.common.getMainText()){
            case "where":
            case "どこ":
            case "где":
                intent = new Intent(Confirm.this, DrawPicture.class);
                break;
            case "when":
            case "いつ":
            case "когда":
                intent = new Intent(Confirm.this, Date.class);
                    break;
            case "how much":
            case "いくら":
            case "сколько":
                intent = new Intent(Confirm.this, Money.class);
                break;
            default:
                intent = new Intent(Confirm.this, Money.class);
                break;
        }

        startActivity(intent);
    } // confirm button click is main icon intent

    public void cancel(View v){
        finish();
    } // cancel button click is back
}
