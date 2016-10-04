package com.skyversion.communicon.Fragments;

import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.skyversion.communicon.subGridAdapter;

/**
 * Created by ss on 2016-08-15.
 */
public class SearchListviewFragment extends Fragment {
    private GridView gridView;
    private Integer beforePos, nowPos; // 이전, 현재위치
    private View beforeView; // 이전 뷰
    ImageView locateImgView, clockImgView, moneyImgView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_fragment_searchlist, container, false);

        gridView = (GridView)view.findViewById(R.id.subIconGridView);
        locateImgView = (ImageView)view.findViewById(R.id.locateImgView);
        clockImgView = (ImageView)view.findViewById(R.id.clockImgView);
        moneyImgView = (ImageView)view.findViewById(R.id.moneyImgView);

        gridView.setAdapter(MainActivity.common.getGridSubAdapter());

        MainActivity.common.getMainSrc().setAdjustViewBounds(true);

        MainActivity.common.setSubGridAdapter(new subGridAdapter(getActivity(), R.layout.row_sub, MainActivity.common.getIconLists()));
        gridView.setAdapter(MainActivity.common.getGridSubAdapter());
        gridView.setOnItemClickListener(new GridItemClickListener());
        // 그리드 뷰의 선택 아이템을 눌렀을 시

        locateImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.common.getMainSrc().setImageResource(R.drawable.placeholder);

                MainActivity.common.setSelectedMainImgId(R.drawable.placeholder);
                // 메인 이미지 아이디 저장
                MainActivity.common.setMainText(MainActivity.common.where[MainActivity.common.selectedLocale]);
                locateImgView.setColorFilter(getResources().getColor(android.R.color.  holo_orange_dark), PorterDuff.Mode.SRC_IN);
                clockImgView.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
                moneyImgView.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
            }
        });
        clockImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.common.getMainSrc().setImageResource(R.drawable.ic_access_time_black_24dp);

                MainActivity.common.setSelectedMainImgId(R.drawable.ic_access_time_black_24dp);

                MainActivity.common.setMainText(MainActivity.common.when[MainActivity.common.selectedLocale]);
                locateImgView.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
                clockImgView.setColorFilter(getResources().getColor(android.R.color.holo_orange_dark), PorterDuff.Mode.SRC_IN);
                moneyImgView.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
            }
        });
        moneyImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.common.getMainSrc().setImageResource(R.drawable.money);

                MainActivity.common.setSelectedMainImgId(R.drawable.money);
                MainActivity.common.setMainText(MainActivity.common.how_much[MainActivity.common.selectedLocale]);
                locateImgView.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
                clockImgView.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
                moneyImgView.setColorFilter(getResources().getColor(android.R.color.holo_orange_dark), PorterDuff.Mode.SRC_IN);
            }
        });
        // 그리드 뷰의 선택 아이템을 눌렀을 시
        return view;
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MainActivity.common.isBookmark = true;
            MainActivity.common.getSubSrc().setAdjustViewBounds(true);

            MainActivity.common.getSubSrc().setImageResource(MainActivity.common.getIconLists().get(position).getImg());
            // 서브아이콘 변경

            ImageView subImg = (ImageView)view.findViewById(R.id.searchIcon);
            // gridview 내에 있는 imageview 불러오기
            LinearLayout li = (LinearLayout)view.findViewById(R.id.gridSubViewLinear);

            if(beforePos == null)
                beforePos = nowPos = position;

            else{
                beforePos = nowPos;
                nowPos = position;
                ImageView beforeSubImg = (ImageView)beforeView.findViewById(R.id.searchIcon);
                // 이전 view의 imageview 불러오기
                LinearLayout beforeLi = (LinearLayout)beforeView.findViewById(R.id.gridSubViewLinear);

                beforeSubImg.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
                // 이전 imageview의 color 필터의 색 변경
                beforeLi.setBackgroundDrawable(null);
            }

            beforeView = view;

            subImg.setColorFilter(getResources().getColor(android.R.color.holo_orange_dark), PorterDuff.Mode.SRC_IN);
            // color 필터의 색 변경
//            li.setBackgroundResource(R.drawable.gridview_background);
            // 테두리

            MainActivity.common.setSelectedSubImgId(MainActivity.common.getIconLists().get(position).getImg());
            // 서브 drawable id 저장

            MainActivity.common.setSubText(MainActivity.common.getIconLists().get(position).getText(MainActivity.common.selectedLocale));
            // 서브 icon text 저장

            MainActivity.common.setIconSelectedPos(nowPos);
        }
    } // grid 아이템 클릭 시 main 아이콘 이미지 대입
}
