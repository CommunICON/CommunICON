package com.skyversion.communicon;

import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by ss on 2016-08-15.
 */
public class Common {
    private int selectedMainImgId, selectedSubImgId;
    private GridAdapter gridAdapter;
    private subGridAdapter gridSubAdapter;
    private ArrayList<IconList> iconLists = new ArrayList<IconList>();
    private final ArrayList<BookmarkList> bookmarkLists = new ArrayList<BookmarkList>();
    private ImageView mainSrc, subSrc;
    private Integer iconSelectedPos = null;
    public boolean isBookmark;

    public int selectedLocale = 0;
    // 선택한 언어 설정값

    public String[] where = new String[]{"where", "どこ", "где"};
    public String[] when = new String[]{"when", "いつ", "когда"};
    public String[] how_much = new String[]{"how much", "いくら", "сколько"};

    private int mainId = 0, subId = 0;
    private String mainText, subText;

    public void setSelectedMainImgId(int selectedMainImgId){
        this.selectedMainImgId = selectedMainImgId;
    } // 선택한 메인 이미지 아이디 설정

    public void resetSelectedImgId(){
        this.selectedMainImgId = -1;
        this.selectedSubImgId = -1;
    } // 메인 이미지 아이디 값 초기화

    public void setSelectedSubImgId(int selectedSubImgId){
        this.selectedSubImgId = selectedSubImgId;
    } // 선택한 서브 이미지 아이디 설정

    public void setMainSrc(ImageView mainSrc){
        this.mainSrc = mainSrc;
    }

    public void setSubSrc(ImageView subSrc){
        this.subSrc = subSrc;
    }

    public void setGridAdapter(GridAdapter gridAdapter){
        this.gridAdapter = gridAdapter;
    } // grid adapter 추가

    public void setSubGridAdapter(subGridAdapter gridAdapter){
        this.gridSubAdapter = gridAdapter;
    }

    public void setIconSelectedPos(int pos){
        iconSelectedPos = pos;
    }

    public int getSelectedMainImgId(){
        return selectedMainImgId;
    } // 선택한 메인 이미지 id값 반환

    public int getSelectedSubImgId(){
        return selectedSubImgId;
    }// 선택한 서브 이미지 id값 반환

    public GridAdapter getGridAdapter(){
        return gridAdapter;
    } // gird adapter 반환

    public subGridAdapter getGridSubAdapter() {return gridSubAdapter;}

    public ImageView getMainSrc(){
        return mainSrc;
    }

    public ImageView getSubSrc(){
        return subSrc;
    }

    public int getIconSelectedPos(){
        return iconSelectedPos;
    }

    public ArrayList<IconList> getIconLists(){
        return iconLists;
    }

    public ArrayList<BookmarkList> getBookmarkLists(){ return bookmarkLists; }

    public void addIconLists(IconList iconList){
        iconLists.add(iconList);
    } // 아이콘 리스트 추가

    public void addBookmarkList(BookmarkList bookmarkList){
        bookmarkLists.add(bookmarkList);
    }

    public void setMainId(int mainId){ this.mainId = mainId; } // main 아이콘 리소스 id 설정

    public void setSubId(int subId){ this.subId = subId; }

    public void setIconLists(ArrayList<IconList> iconLists){
        this.iconLists = iconLists;
    }
//
//    public int getMainId(){ return mainId; } // main 아이콘 리소스 id 반환
//
//    public int getSubId(){ return subId; }

    public void setMainText(String mainText){this.mainText = mainText;} // main 아이콘 이름 설정

    public void setSubText(String subText) {this.subText = subText;} // sub 아이콘 이름 설정

    public String getMainText(){return mainText;} // main 아이콘 이름 반환

    public String getSubText(){return subText;} // sub 아이콘 이름 반환
}
