package com.WordInTouch.UI;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.WordInTouch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Verify extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.verify_Edt_Code) EditText edt_code ;
    @BindView(R.id.verify_Btn_Next) Button btn_next;
    @BindView(R.id.verify_Txt_Timer) TextView txt_timer;

     int counter=120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);

        timer();

        btn_next.setOnClickListener(this);
    }

    public void timer(){
        new CountDownTimer(120000, 1000){
            public void onTick(long millisUntilFinished){
                int minutes = counter / 60 ;
                int second = counter % 60 ;

                txt_timer.setText(minutes+":"+second);
                if(second==0)
                  txt_timer.setText(minutes+":"+second+"0");


                counter--;
            }
            public  void onFinish(){
                finish();
            }
        }.start();
    }



    @Override
    public void onClick(View v) {

        if(v==btn_next){

            startActivity(new Intent(Verify.this,Main.class));

        }

    }
}
