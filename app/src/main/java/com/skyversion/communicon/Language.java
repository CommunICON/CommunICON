package com.skyversion.communicon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by ss on 2016-08-14.
 */
public class Language extends Activity{
    private ListView lvLanguage;
    private final String[] localeLanguage = new String[]{"English", "日本語", "русский"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_activity);
        lvLanguage = (ListView)findViewById(R.id.lvLanguage);


        lvLanguage.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, localeLanguage));
        lvLanguage.setOnItemClickListener(new onItemClickListener());
    }

    private class onItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            try{
                Toast.makeText(Language.this, localeLanguage[position].toString(), Toast.LENGTH_SHORT).show();
                MainActivity.common.selectedLocale = position;
            }catch (NullPointerException e){
                Toast.makeText(Language.this, "NullPointException", Toast.LENGTH_SHORT).show();
            }
            setResult(position);
            finish();
        }
    } // 언어 추가
}