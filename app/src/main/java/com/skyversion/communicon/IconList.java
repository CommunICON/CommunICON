package com.skyversion.communicon;

/**
 * Created by ss on 2016-08-14.
 */
public class IconList {
    private int img;
    private String subImgText;
    private String rus;
    private String jpy;

    public IconList(int img, String subImgText, String rus, String jpy){
        this.img = img;
        this.subImgText = subImgText;
        this.rus = rus;
        this.jpy = jpy;
    }

    public int getImg(){
        return img;
    }

    public String getText(int index) {
        String text = null;

        switch (index){
            case 0:
                text = subImgText;
                break;
            case 1:
                text = rus;
                break;
            case 2:
                text = jpy;
                break;
        }

        return text;
    }

    public String[] getAllText(){
        String[] text = new String[]{subImgText, rus, jpy};

        return text;
    }
}