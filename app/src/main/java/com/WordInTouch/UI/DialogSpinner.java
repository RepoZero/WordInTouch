package com.WordInTouch.UI;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.WordInTouch.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.WordInTouch.Application.db;
import static com.WordInTouch.Application.texts;
import static com.WordInTouch.Application.type_parametrs;

public class DialogSpinner extends AppCompatActivity {

    ArrayList<Integer> category_id = new ArrayList<Integer>();
    ArrayList<String> category = new ArrayList<String>();
    ArrayList<Integer> sub_category_id = new ArrayList<Integer>();
    ArrayList<String> sub_category = new ArrayList<String>();

    @BindView(R.id.dialog_spinner_spn1) Spinner spn1;
    @BindView(R.id.dialog_spinner_spn2) Spinner spn2;

    private int op;
    private int index=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_spinner);
        ButterKnife.bind(this);


        getCategorySpn();

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getSubCategorySpn(category_id.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            op = extras.getInt("op_code");

            if(op==1){
            }
                index = extras.getInt("index_edit");
            }



        Toolbar toolbar = (Toolbar) findViewById(R.id.dialog_spinner_toolbar);
        toolbar.setTitle(this.getResources().getString(R.string.Category));
        toolbar.inflateMenu(R.menu.apply_title_menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item


                if(item.getItemId()==R.id.action_apply){

                        int selected_item = spn2.getSelectedItemPosition();

                        if(op==0) {
                            type_parametrs.add('c');
                            texts.add(sub_category_id.get(selected_item).toString());
                            finish();
                        }else if(op==1){
                            texts.set(index,sub_category_id.get(selected_item).toString());
                            finish();


                    }

                }
                return true;
            }
        });




    }



        public void getCategorySpn(){
        String select_query_category = "SELECT * FROM category WHERE parent_id=0 ";
        Cursor cursor_category = db.rawQuery(select_query_category, null);

        if (cursor_category != null && cursor_category.getCount() > 0) {

            if (cursor_category.moveToFirst()) {

                do {
                    category_id.add(cursor_category.getInt(cursor_category.getColumnIndex("id")));
                    category.add(cursor_category.getString(cursor_category.getColumnIndex("text")));

                } while (cursor_category.moveToNext());
            }
            cursor_category.close();
        }
        spnAdapter spnAdapterType = new spnAdapter(this,category);
        spn1.setAdapter(spnAdapterType);
    }

    public void getSubCategorySpn(int id){

        sub_category_id.clear();
        sub_category.clear();

        String select_query_sub_category = "SELECT * FROM category WHERE parent_id="+id;
        Cursor cursor_sub_category = db.rawQuery(select_query_sub_category, null);

        if (cursor_sub_category != null && cursor_sub_category.getCount() > 0) {

            if (cursor_sub_category.moveToFirst()) {

                do {
                    sub_category_id.add(cursor_sub_category.getInt(cursor_sub_category.getColumnIndex("id")));
                    sub_category.add(cursor_sub_category.getString(cursor_sub_category.getColumnIndex("text")));

                } while (cursor_sub_category.moveToNext());
            }
            cursor_sub_category.close();
        }
        spnAdapter spnAdapter = new spnAdapter(this,sub_category);
        spn2.setAdapter(spnAdapter);

    }

    private class spnAdapter extends BaseAdapter {

        Context context;
        private ArrayList<String> type = new ArrayList<String>();

        public spnAdapter(Context context , ArrayList<String> type){
            this.context=context;
            this.type=type;
        }

        @Override
        public int getCount() {
            return type.size();
        }

        @Override
        public Object getItem(int position) {
            return type.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_spn,parent,false);
            }

            TextView txt = (TextView) convertView.findViewById(R.id.item_txt_spn);

            txt.setText(type.get(position));
            return convertView;
        }
    }


}
