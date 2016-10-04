package com.skyversion.communicon;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.View.OnClickListener;
import com.skyversion.communicon.Fragments.BookmarkFragment;
import com.skyversion.communicon.Fragments.SearchListviewFragment;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnQueryTextListener, LanguageCode {

    private String[] navItems = {"언어설정"};
    private ListView lvNavList; // 메뉴바
    private DrawerLayout dlDrawer; // 드로우어 레이아웃
    private ActionBarDrawerToggle dtToggle; // 액션바 토글버튼
    private Intent intent = null;

    private Toolbar myToolbar;
    private SearchView searchView;

    private ImageView mainSrc, subSrc;
    private LinearLayout selectedIconLayout;
    // 선택한 아이콘의 레이아웃
    public static final Common common = new Common();
    private int index = 0;
    private boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        commonInit();

        init();
        start();

        common.addIconLists(new IconList(R.drawable.ic_call_black_24dp, "call", "コール", "такси"));
        common.addIconLists(new IconList(R.drawable.ic_local_taxi_black_24dp, "taxi", "タクシー","такси"));
        common.addIconLists(new IconList(R.drawable.ic_location_city_black_24dp, "school","学校","школа"));
        common.addIconLists(new IconList(R.drawable.ic_flight_takeoff_black_24dp, "airport","空港","аэропорт"));
        common.addIconLists(new IconList(R.drawable.ic_motorcycle_black_24dp, "motorcycle","オートバイ","мотоцикл"));
        common.addIconLists(new IconList(R.drawable.ic_wc_black_24dp, "toilet","トイレ","туалет"));
        common.addIconLists(new IconList(R.drawable.ic_wifi_black_24dp, "wifi","Wi-Fi","вай-фай"));
        common.addIconLists(new IconList(R.drawable.ic_local_pharmacy_black_24dp, "medicine","医学","лекарственное средство"));
        common.addIconLists(new IconList(R.drawable.ic_local_parking_black_24dp, "parking","パーキング","стоянка"));
        common.addIconLists(new IconList(R.drawable.ic_local_gas_station_black_24dp, "gas station","ガソリンスタンド","бензоколонка"));
        common.addIconLists(new IconList(R.drawable.ic_local_hospital_black_24dp, "hospital","病院","больница"));
        common.addIconLists(new IconList(R.drawable.ic_account_balance_black_24dp, "musium","博物館","музей"));

        common.addIconLists(new IconList(R.drawable.bridge, "bridge","ブリッジ","мост"));
        common.addIconLists(new IconList(R.drawable.edit, "pencil","鉛筆","карандаш"));
        common.addIconLists(new IconList(R.drawable.microphone, "mic","マイクロフォン","микрофон"));
        common.addIconLists(new IconList(R.drawable.ticket, "ticket","チケット","билет"));
        common.addIconLists(new IconList(R.drawable.umbrella, "umbrella","傘","зонтик"));


        common.addBookmarkList(new BookmarkList(R.drawable.placeholder, R.drawable.ic_call_black_24dp,
                new String[]{"where","どこ","где"}, new String[]{"call","コール","телефон"}, true));
        common.addBookmarkList(new BookmarkList(R.drawable.ic_access_time_black_24dp, R.drawable.ic_account_balance_black_24dp,
                new String[]{"when","いつ","когда"}, new String[]{"museum","博物館","музей"}, true));
        common.addBookmarkList(new BookmarkList(R.drawable.money, R.drawable.ic_local_pharmacy_black_24dp,
                new String[]{"how much","いくら","сколько"}, new String[]{"medicine","薬局","лекарственное средство"}, true));

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //actionbar toggle을 이용하여 드로어 이용 시 추가

        searchView.setIconified(true);
        // 초기상태

        searchViewVitalization(true);

        fragmentReplace(index);

        searchView.setQueryHint("아이콘을 검색해주세요.");
    }

    private void fragmentReplace(int index){
        Fragment newFragment = null;

        newFragment = getFragment(index);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment, newFragment);

        transaction.commit();
    }

    private Fragment getFragment(int index){
        Fragment newFragment = null;

        if(index == 0)
            newFragment = new BookmarkFragment();
        else{

            newFragment = new SearchListviewFragment();

            if(!isClick){
                isClick = true;

                searchView.setOnQueryTextListener(new OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();
                        newText = newText.toString().toLowerCase(Locale.getDefault());
                        common.getGridSubAdapter().filter(newText);
                        return true;
                    }
                });
            } // 버튼 첫 클릭 시 검색가능하게 끔 listener추가 : gridAdapter이 추가가 되어있지 않으므로 하는 조치
        }


        return newFragment;
    }

    private void commonInit(){
        mainSrc = (ImageView)findViewById(R.id.selectedMainImgView);
        subSrc = (ImageView)findViewById(R.id.selectedSubImgView);
        common.setMainSrc(mainSrc);
        common.setSubSrc(subSrc);
    }

    private void init() {
        lvNavList = (ListView) findViewById(R.id.lv_activity_main_nav_list);
//        flContainer = (FrameLayout) findViewById(R.id.fl_activity_main_container);
        dlDrawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        lvNavList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));

        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        }; // 액션바 토글을 드래그할 떄

        searchView = (SearchView)findViewById(R.id.searchView);

        selectedIconLayout = (LinearLayout)findViewById(R.id.selectedIconLayout);
    }

    private void start(){
        lvNavList.setOnItemClickListener(new DrawerItemClickListener());
        dlDrawer.setDrawerListener(dtToggle);
        // 드래그 할 시 효과를 줌
        dlDrawer.closeDrawer(lvNavList);

        myToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "OnClick", Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(this);
        searchView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                searchViewVitalization(false);
            }
        });

        selectedIconLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();

                if(mainSrc.getDrawable() == null && subSrc.getDrawable() == null){
                    Toast.makeText(getBaseContext(), "아이콘을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(getBaseContext(), "선택완료", Toast.LENGTH_SHORT).show();
                    try{
                        intent = new Intent(MainActivity.this, Confirm.class);
                        intent.putExtra("pos", common.getIconSelectedPos());
                        intent.putExtra("isIconified", searchView.isIconified());
                        startActivity(intent);
                    }catch (ArrayIndexOutOfBoundsException arrIdxEx){
                        Toast.makeText(getBaseContext(), "Array Index Out Of Bounds Exception", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        selectedIconLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                Toast.makeText(MainActivity.this, "Long Click", Toast.LENGTH_SHORT).show();
                if(mainSrc.getDrawable() == null && subSrc.getDrawable() == null){
                    Toast.makeText(getBaseContext(), "아이콘을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    boolean isDuplication = false;
                    for(int i=0;i<common.getBookmarkLists().size();i++){
                        if(common.getBookmarkLists().get(i).getMainImg() == common.getSelectedMainImgId()&&
                                common.getBookmarkLists().get(i).getSubImg() == common.getSelectedSubImgId()){
                            isDuplication = true;
                            Toast.makeText(getBaseContext(), "북마크에 중복 등록은 불가합니다.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if(isDuplication == false){
//                        common.addBookmarkList(new BookmarkList(R.drawable.ic_place_black_24dp, R.drawable.ic_local_taxi_black_24dp,"locate", "taxi", true));
                        String[] value = null;
                        switch (common.selectedLocale){
                            case 0:
                                value = common.where;
                                break;
                            case 1:
                                value = common.when;
                                break;
                            case 2:
                                value = common.how_much;
                                break;
                        }
                        common.addBookmarkList(new BookmarkList(common.getSelectedMainImgId(), common.getSelectedSubImgId(),
                               value, common.getIconLists().get(common.getIconSelectedPos()).getAllText(), true));
                        common.getGridAdapter().updateContent(common.getBookmarkLists());
                        Toast.makeText(getBaseContext(), "북마크에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
    } // add event

    public void searchViewVitalization(boolean isVitalization){
        if(isVitalization){
            searchView.setIconified(true);
            // search 아이콘 비활성화
            index = 0;
        }else{
            searchView.setIconified(false);
            // search 아이콘 활성화
            searchView.onActionViewExpanded();
            // 검색상태

            index = 1;
        } // 검색화면

        fragmentReplace(index);
    } // search view 활성화 설정

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        dtToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(dtToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
            dlDrawer.closeDrawers();

            switch (pos){
                case 0:
                    intent = new Intent(MainActivity.this, Language.class);
                    startActivityForResult(intent, QUESTION);
                    break;
            }
        }
    } // nav listview click event listener

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case -1:
                if(resultCode == KOREAN)
                    Toast.makeText(getBaseContext(), "한국어", Toast.LENGTH_SHORT).show();
                else if(resultCode == JAPANESE)
                    Toast.makeText(getBaseContext(), "일본어", Toast.LENGTH_SHORT).show();
                else if(resultCode == CHINESE)
                    Toast.makeText(getBaseContext(), "중국어", Toast.LENGTH_SHORT).show();
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();
        return false;
    } // text 제출시

    @Override
    public boolean onQueryTextSubmit(String newText) {
        Toast.makeText(getBaseContext(), newText, Toast.LENGTH_SHORT).show();
        return false;
    } // text 입력할 때마다


    @Override
    public void onBackPressed() {

        if(!searchView.isIconified()){
            searchView.setIconified(true);
            searchViewVitalization(true);
            index = 0;
            return;
        }else if(!searchView.isIconified() && !dlDrawer.isDrawerOpen(lvNavList)) {
            System.exit(0);
        }// search 아이콘 상태가 false이거나 drawerlayout이 close상태일 시
        else if(dlDrawer.isDrawerOpen(lvNavList)){
            dlDrawer.closeDrawer(lvNavList);
            return;
        }// drawerlayout이 on상태일 시
        else{
            super.onBackPressed();
        }
    }// 뒤로가기 키를 눌렀을 시 이벤트 발생

}
