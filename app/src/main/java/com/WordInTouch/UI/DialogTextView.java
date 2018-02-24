package com.WordInTouch.UI;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.WordInTouch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.WordInTouch.Application.texts;
import static com.WordInTouch.Application.type_parametrs;


public class DialogTextView extends AppCompatActivity {

    private char type;
    private int op;
    private int index=-1;
    private String text=null;
    @BindView(R.id.dialog_textview_Edt) EditText edt;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_textview);
        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.dialog_textview_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle the menu item


                if(item.getItemId()==R.id.action_apply){

                    if(TextUtils.isEmpty(edt.getText().toString())){

                        Toast.makeText(DialogTextView.this, ""+DialogTextView.this.getResources().getString(R.string.enter_text), Toast.LENGTH_SHORT).show();
                    }else {

                        if(op==0) {
                            type_parametrs.add(type);
                            texts.add(edt.getText().toString());
                            finish();
                        }else if(op==1){
                            texts.set(index,edt.getText().toString());
                            finish();
                        }

                    }

                }
                return true;
            }
        });

        toolbar.inflateMenu(R.menu.apply_title_menu);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getChar("char_type");
            op = extras.getInt("op_code");

            if(op==1){

                index = extras.getInt("index_edit");

                text = extras.getString("text");
                edt.setText(text);
            }
        }


        if(type=='c'){

            toolbar.setTitle(this.getResources().getString(R.string.Category));

        }else if(type=='t'){

            toolbar.setTitle(this.getResources().getString(R.string.Translation));

        }else if(type=='d'){

            toolbar.setTitle(this.getResources().getString(R.string.Definition));

        }else if(type=='s'){

            toolbar.setTitle(this.getResources().getString(R.string.Synonym));

        }else if(type=='a'){

            toolbar.setTitle(this.getResources().getString(R.string.Antonym));

        }else if(type=='e'){

            toolbar.setTitle(this.getResources().getString(R.string.Example));

        }









    }


}

