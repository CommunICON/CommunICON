package com.skyversion.communicon.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.skyversion.communicon.GridAdapter;
import com.skyversion.communicon.MainActivity;
import com.skyversion.communicon.R;

/**
 * Created by ss on 2016-08-15.
 */
public class BookmarkFragment extends Fragment {

    private GridView gridView;
//    private GridAdapter gridAdapter;
    // 그리드 뷰 어댑터
    private Integer beforePos, nowPos; // 이전, 현재위치
    private View beforeView;
    // 이전 뷰(이전에 선택했던 아이콘의 색상 을 원래대로 바꾸기 위함)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_fragment_listview, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        MainActivity.common.setGridAdapter(new GridAdapter(getActivity(), R.layout.row, MainActivity.common.getBookmarkLists()));
        gridView.setAdapter(MainActivity.common.getGridAdapter());

        gridView.setOnItemClickListener(new GridItemClickListener());
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.common.getBookmarkLists().remove(i);
                MainActivity.common.getGridAdapter().updateContent(MainActivity.common.getBookmarkLists());
                if(i == MainActivity.common.getBookmarkLists().size()){
                    MainActivity.common.getMainSrc().setImageDrawable(null);
                    MainActivity.common.getSubSrc().setImageDrawable(null);
                    MainActivity.common.resetSelectedImgId();
                    // 선택한 메인, 서브 이미지 아이디 제거
                } // 마지막 아이콘을 지울 시 selected icon 의 그림도 없어짐
                return false;
            }
        }); // 그리드 뷰의 선택 아이템을 길게 눌렀을 시

        return view;
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MainActivity.common.isBookmark = true;
            MainActivity.common.getMainSrc().setAdjustViewBounds(true);
            MainActivity.common.getSubSrc().setAdjustViewBounds(true);

            MainActivity.common.getMainSrc().setImageResource(MainActivity.common.getBookmarkLists().get(position).getMainImg());
            MainActivity.common.getSubSrc().setImageResource(MainActivity.common.getBookmarkLists().get(position).getSubImg());
            // 메인, 서브아이콘 변경

            ImageView mainImg = (ImageView)view.findViewById(R.id.mainImg);
            ImageView subImg = (ImageView)view.findViewById(R.id.subImg);
            // gridview 내에 있는 imageview 불러오기
            LinearLayout li = (LinearLayout)view.findViewById(R.id.gridViewLinear);

            if(beforePos == null)
                beforePos = nowPos = position;

            else{
                beforePos = nowPos;
                nowPos = position;
                ImageView beforeMainImg = (ImageView)beforeView.findViewById(R.id.mainImg);
                ImageView beforeSubImg = (ImageView)beforeView.findViewById(R.id.subImg);
                // 이전 view의 imageview 불러오기
                LinearLayout beforeLi = (LinearLayout)beforeView.findViewById(R.id.gridViewLinear);

                beforeMainImg.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
                beforeSubImg.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
                // 이전 imageview의 color 필터의 색 변경
                beforeLi.setBackgroundDrawable(null);
            }

            beforeView = view;

            mainImg.setColorFilter(getResources().getColor(android.R.color.holo_orange_dark), PorterDuff.Mode.SRC_IN);
            subImg.setColorFilter(getResources().getColor(android.R.color.holo_orange_dark), PorterDuff.Mode.SRC_IN);
            // color 필터의 색 변경
            li.setBackgroundResource(R.drawable.gridview_background);

            MainActivity.common.setSelectedMainImgId(MainActivity.common.getBookmarkLists().get(position).getMainImg());
            MainActivity.common.setSelectedSubImgId(MainActivity.common.getBookmarkLists().get(position).getSubImg());
            // 메인, 서브 drawable id 저장

            MainActivity.common.setMainText(MainActivity.common.getBookmarkLists().get(position).getMainImgText(MainActivity.common.selectedLocale));
            MainActivity.common.setSubText(MainActivity.common.getBookmarkLists().get(position).getSubImgText(MainActivity.common.selectedLocale));
            // 메인, 서브 icon text 저장

            MainActivity.common.setIconSelectedPos(nowPos);
        }
    } // grid 아이템 클릭 시 main 아이콘 이미지 대입
}
