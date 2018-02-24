package com.WordInTouch.UI;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.WordInTouch.Message.EasyDialog;
import com.WordInTouch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.login_Edt_PhoneNumber) EditText edt_phone_number ;
    @BindView(R.id.login_Btn_Next) Button btn_next;
    @BindView(R.id.login_Txt_Skip) TextView txt_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        prepaireView();


        txt_skip.setOnClickListener(this);
        btn_next.setOnClickListener(this);


    }

    private void prepaireView(){
        txt_skip.setPaintFlags(txt_skip.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onClick(View v) {
        if(v==txt_skip){

            EasyDialog easyDialog = new EasyDialog();

            easyDialog.twoItem(Login.this, this.getString(R.string.skip_dialog_title),
                    this.getString(R.string.skip_dialog_text),
                    R.drawable.warning,
                    this.getString(R.string.yes),
                    this.getString(R.string.no),
                    new EasyDialog.getClickTwoItem(){
                        @Override
                        public void oneItem() {
                            Toast.makeText(Login.this, "Yes", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void secondItem() {

                            Toast.makeText(Login.this, "No", Toast.LENGTH_SHORT).show();

                        }
                    });


        }
        if (v==btn_next){
            startActivity(new Intent(Login.this,Verify.class));
        }
    }

}
