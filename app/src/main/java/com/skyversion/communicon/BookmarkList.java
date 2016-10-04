package com.skyversion.communicon;

/**
 * Created by ss on 2016-08-17.
 */
public class BookmarkList {
    private int mainImg;
    private int subImg;
//    private String mainImgText;
//    private String subImgText;
    private String[] mainText;
    private String[] subText;
    private boolean isChecked;

//    public BookmarkList(int mainImg, int subImg, String mainImgText, String subImgText, boolean isChecked){
//        this.mainImg = mainImg;
//        this.subImg = subImg;
//        this.mainImgText = mainImgText;
//        this.subImgText = subImgText;
//        this.isChecked = isChecked;
//    }

    public BookmarkList(int mainImg, int subImg, String[] mainText, String[] subText, boolean isChecked){
        this.mainImg = mainImg;
        this.subImg = subImg;
        this.mainText = mainText;
        this.subText = subText;
        this.isChecked = isChecked;
    }

    public int getMainImg(){
        return mainImg;
    }

    public int getSubImg() { return subImg; }

    public String getMainImgText(int index) { return mainText[index]; }

    public String getSubImgText(int index) { return subText[index]; }

    public boolean isChecked(){
        return isChecked;
    }
}
