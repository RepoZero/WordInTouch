package com.WordInTouch.UI;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.internal.NavigationMenu;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.WordInTouch.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.yavski.fabspeeddial.FabSpeedDial;

import static com.WordInTouch.Application.db;
import static com.WordInTouch.Application.texts;
import static com.WordInTouch.Application.type_parametrs;

public class AddTerm extends AppCompatActivity {


    @BindView(R.id.add_term_term) EditText term;
    @BindView(R.id.add_term_typespn) Spinner spn_type;
    @BindView(R.id.add_term_url) EditText url;
    @BindView(R.id.add_term_fab) FabSpeedDial fab;
    @BindView(R.id.add_term_lst) ListView lst;

    ArrayList<String> type = new ArrayList<String>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        ButterKnife.bind(this);
        this.setTitle("Add Term");

        getTypeSpn();


        lst.setDivider(null);
        lstAdapter lstAdapter = new lstAdapter(this, type_parametrs,texts);
        lst.setAdapter(lstAdapter);

        fab.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.action_Category){

                    Intent i = new Intent(getApplicationContext(), DialogSpinner.class);

                    i.putExtra("op_code",0);
                    i.putExtra("op_code",0);
                    i.putExtra("op_code",0);
                    startActivity(i);

                }else if (menuItem.getItemId()==R.id.action_Translation){

                    Intent i = new Intent(getApplicationContext(), DialogTextView.class);

                    i.putExtra("char_type",'t');
                    i.putExtra("op_code",0);
                    startActivity(i);

                }else if (menuItem.getItemId()==R.id.action_Definition){

                    Intent i = new Intent(getApplicationContext(), DialogTextView.class);

                    i.putExtra("char_type",'d');
                    i.putExtra("op_code",0);
                    startActivity(i);

                }else if (menuItem.getItemId()==R.id.action_Synonym){

                    Intent i = new Intent(getApplicationContext(), DialogTextView.class);

                    i.putExtra("char_type",'s');
                    i.putExtra("op_code",0);
                    startActivity(i);

                }else if (menuItem.getItemId()==R.id.action_Antonym){

                    Intent i = new Intent(getApplicationContext(), DialogTextView.class);

                    i.putExtra("char_type",'a');
                    i.putExtra("op_code",0);
                    startActivity(i);

                }else if (menuItem.getItemId()==R.id.action_Example){

                    Intent i = new Intent(getApplicationContext(), DialogTextView.class);

                    i.putExtra("char_type",'e');
                    i.putExtra("op_code",0);
                    startActivity(i);

                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        lstAdapter lstAdapter = new lstAdapter(this, type_parametrs,texts);
        lst.setAdapter(lstAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.add_term, menu);
        return super.onCreateOptionsMenu(menu);
    }








    private class spnAdapter extends BaseAdapter{

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

    private class lstAdapter extends BaseAdapter{

        private Context context ;
        private ArrayList<Character> type_parametr =new ArrayList<Character>();
        private ArrayList<String> text =new ArrayList<String>();

        public lstAdapter(Context context , ArrayList<Character> type_parametr,ArrayList<String> text ){
            this.context=context;
            this.type_parametr = type_parametr;
            this.text=text;
        }

        @Override
        public int getCount() {
            return type_parametr.size() ;
        }

        @Override
        public Object getItem(int position) {
            return text.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=LayoutInflater.from(context).inflate(R.layout.item_lst,parent,false);
            }

            ImageView img = (ImageView) convertView.findViewById(R.id.item_lst_img);
            LinearLayout lin = (LinearLayout) convertView.findViewById(R.id.item_lst_linear);
            TextView txt = (TextView) convertView.findViewById(R.id.item_lst_txt);
            TextView txt_type = (TextView) convertView.findViewById(R.id.item_lst_txt_type);




            if(type_parametr.get(position)=='c'){
                img.setColorFilter(ContextCompat.getColor(context, R.color.Category));
                lin.setBackground(convertView.getResources().getDrawable(R.drawable.lst_category));

                txt_type.setText(context.getResources().getText(R.string.Category));
                txt_type.setTextColor(context.getResources().getColor(R.color.Category));

            }else if(type_parametr.get(position)=='t'){
                img.setColorFilter(ContextCompat.getColor(context, R.color.Translation));
                lin.setBackground(convertView.getResources().getDrawable(R.drawable.lst_translation));

                txt_type.setText(context.getResources().getText(R.string.Translation));
                txt_type.setTextColor(context.getResources().getColor(R.color.Translation));

            }else if(type_parametr.get(position)=='d'){
                img.setColorFilter(ContextCompat.getColor(context, R.color.Definition));
                lin.setBackground(convertView.getResources().getDrawable(R.drawable.lst_definition));

                txt_type.setText(context.getResources().getText(R.string.Definition));
                txt_type.setTextColor(context.getResources().getColor(R.color.Definition));

            }else if(type_parametr.get(position)=='s'){
                img.setColorFilter(ContextCompat.getColor(context, R.color.Synonym));
                lin.setBackground(convertView.getResources().getDrawable(R.drawable.lst_synonym));

                txt_type.setText(context.getResources().getText(R.string.Synonym));
                txt_type.setTextColor(context.getResources().getColor(R.color.Synonym));

            }else if(type_parametr.get(position)=='a'){
                img.setColorFilter(ContextCompat.getColor(context, R.color.Antonym));
                lin.setBackground(convertView.getResources().getDrawable(R.drawable.lst_antonym));

                txt_type.setText(context.getResources().getText(R.string.Antonym));
                txt_type.setTextColor(context.getResources().getColor(R.color.Antonym));


            }else if(type_parametr.get(position)=='e'){

                img.setColorFilter(ContextCompat.getColor(context, R.color.Example));
                lin.setBackground(convertView.getResources().getDrawable(R.drawable.lst_example));

                txt_type.setText(context.getResources().getText(R.string.Example));
                txt_type.setTextColor(context.getResources().getColor(R.color.Example));
            }


            if(type_parametr.get(position)=='c')
                txt.setText(getCategoryName(Integer.parseInt(text.get(position))));
            else
            txt.setText(text.get(position));

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    deleteItem(position);

                }
            });

            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (type_parametr.get(position)=='c'){

                    }else {

                        Intent i = new Intent(getApplicationContext(), DialogTextView.class);

                        i.putExtra("char_type",type_parametr.get(position));
                        i.putExtra("op_code",1);
                        i.putExtra("index_edit",position);
                        i.putExtra("text",text.get(position));
                        startActivity(i);

                    }
                }
            });



            return convertView ;
        }


        public String getCategoryName(int id){

            String text = null;

            String select = "SELECT text FROM category WHERE id="+id;
            Cursor cursor = db.rawQuery(select, null);
            if (cursor != null && cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {

                    do {

                        text =cursor.getString(cursor.getColumnIndex("text"));

                    } while (cursor.moveToNext());
                }
                cursor.close();
            }

            return text;
        }
    }

    private void deleteItem(int id){

        type_parametrs.remove(id);
        texts.remove(id);

        lstAdapter lstAdapter = new lstAdapter(this, type_parametrs,texts);
        lst.setAdapter(lstAdapter);


    }


    private void addTerm() {

        if(TextUtils.isEmpty(term.getText().toString())){
            term.setError("Insert term");
            return;
        }

        if(uniqTerm(term.getText().toString())){
            term.setError("Term is available");
            return;
        }



        String term_text ="'"+term.getText().toString()+"',";
        int type_id= +getIdType(spn_type.getSelectedItem().toString());
        String term_url = ",'"+url.getText().toString()+"'";

        String insertTerm = "INSERT INTO term (text,type_id,external_url) VALUES ("+term_text+type_id+term_url+")";
       Cursor cursor = db.rawQuery(insertTerm,null);
        cursor.moveToFirst();



        int term_id = getTermId(term.getText().toString());



        insertTerm = "INSERT INTO log (term_id , internal_id , text , role) VALUES ("+term_id +","+ term_id+","+term_text+"1"+") ";
        cursor = db.rawQuery(insertTerm,null);
        cursor.moveToFirst();



        for (int i=0;i<type_parametrs.size();i++){

            char c = type_parametrs.get(i);
            String table = null;
            switch (c){

                case 'c':
                    table = "category_term";
                case 't':
                    table = "translation";
                    break;

                case 'd':
                    table = "definition";
                    break;

                case 's':
                    table = "synonym";
                    break;

                case 'a':
                    table = "antonym";
                    break;

                case 'e':
                    table= "example";
                    break;

            }



            if(c=='c') {

                String text_parametr =  texts.get(i);
                String Insert_parametrs_table = "INSERT INTO category_term (term_id,text) VALUES (" + term_id +","+ text_parametr + ")";
                cursor = db.rawQuery(Insert_parametrs_table, null);
                cursor.moveToFirst();
                continue;

            }

                String text_parametr = ",'" + texts.get(i) + "'";
                String Insert_parametrs_table = "INSERT INTO " + table + " (term_id,text) VALUES (" + term_id + text_parametr + ")";
                Log.e("A",Insert_parametrs_table);
                cursor = db.rawQuery(Insert_parametrs_table, null);
                cursor.moveToFirst();



            if(c!='c') {

                int id = getIdFromParametr(table,texts.get(i));
                int role =getRoleId(table);
                Insert_parametrs_table = "INSERT INTO log (term_id , internal_id , text , role) VALUES ("+term_id+","+id+text_parametr+","+role+")";
                Cursor cursors = db.rawQuery(Insert_parametrs_table, null);
                cursors.moveToFirst();
                cursors.close();
            }



        }

        cursor.close();

        finish();


    }

    public boolean uniqTerm(String text){
        String query_uniqe = "SELECT * From term";
        boolean notUnique = false;

        Cursor cursor_type = db.rawQuery(query_uniqe, null);

        if (cursor_type != null && cursor_type.getCount() > 0) {

            if (cursor_type.moveToFirst()) {

                do {
                    if(text.equals(cursor_type.getString(cursor_type.getColumnIndex("text")))){
                        notUnique = true;
                    }

                } while (cursor_type.moveToNext());
            }
            cursor_type.close();
        }

        return notUnique;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                break;
            case R.id.action_apply:
                addTerm();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getTypeSpn(){
        String select_query_type = "SELECT * FROM type";
        Cursor cursor_type = db.rawQuery(select_query_type, null);

        if (cursor_type != null && cursor_type.getCount() > 0) {

            if (cursor_type.moveToFirst()) {

                do {
                    type.add(cursor_type.getString(cursor_type.getColumnIndex("text")));

                } while (cursor_type.moveToNext());
            }
            cursor_type.close();
        }
        spnAdapter spnAdapterType = new spnAdapter(this,type);
        spn_type.setAdapter(spnAdapterType);

    }

    public int getIdType(String TypeText){

        String select_query_type = "SELECT * FROM type WHERE text='"+TypeText+"'";
        Cursor cursor_type = db.rawQuery(select_query_type, null);

        int id =-1 ;

        if (cursor_type != null && cursor_type.getCount() > 0) {

            if (cursor_type.moveToFirst()) {

                do {
                    id=cursor_type.getInt(cursor_type.getColumnIndex("id"));
                } while (cursor_type.moveToNext());
            }
            cursor_type.close();
        }

        return id;
    }

    public int getTermId(String term_text){


        String select_query_type = "SELECT * FROM term WHERE text='"+term_text+"'";
        Cursor cursor_type = db.rawQuery(select_query_type, null);

        int id =-1 ;

        if (cursor_type != null && cursor_type.getCount() > 0) {

            if (cursor_type.moveToFirst()) {

                do {

                    id=cursor_type.getInt(cursor_type.getColumnIndex("id"));

                } while (cursor_type.moveToNext());
            }
            cursor_type.close();
        }

        return id;
    }

    public int getIdFromParametr(String table , String parametr){

        String Select_p = "SELECT * FROM "+table+" WHERE text='"+parametr+"'" ;
        Cursor cursor_type = db.rawQuery(Select_p, null);

        int id =-1 ;

        if (cursor_type != null && cursor_type.getCount() > 0) {

            if (cursor_type.moveToFirst()) {

                do {
                    id=cursor_type.getInt(cursor_type.getColumnIndex("id"));
                } while (cursor_type.moveToNext());
            }
            cursor_type.close();
        }

        return id;


    }

    public int getRoleId(String text){


        String select_query = "SELECT * FROM parameter WHERE text='"+text+"'";
        Cursor cursor_type = db.rawQuery(select_query, null);

        int id =-1 ;

        if (cursor_type != null && cursor_type.getCount() > 0) {

            if (cursor_type.moveToFirst()) {

                do {

                    id=cursor_type.getInt(cursor_type.getColumnIndex("id"));

                } while (cursor_type.moveToNext());
            }
            cursor_type.close();
        }

        return id;

    }


}
