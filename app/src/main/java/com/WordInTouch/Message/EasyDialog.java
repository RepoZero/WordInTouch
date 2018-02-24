package com.WordInTouch.Message;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by cloner on 12/5/17.
 */

public class EasyDialog {

    public void oneItem(Context context , String title , String message , int icon , String item , final getClickOneItem callback ){

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             callback.Item();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        if (icon!=0)
            builder.setIcon(icon);

        if(title!=null)
            builder.setTitle(title);

        if(message!=null)
            builder.setMessage(message);

        builder.setNeutralButton(item, dialogClickListener);
        builder.show();

    }


    public void twoItem(Context context , String title , String message , int icon , String one_item , String second_item , final getClickTwoItem callback ){


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        callback.oneItem();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        callback.secondItem();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

     if (icon!=0)
        builder.setIcon(icon);

     if(title!=null)
        builder.setTitle(title);

     if(message!=null)
        builder.setMessage(message);

        builder.setPositiveButton(one_item, dialogClickListener);
        builder.setNegativeButton(second_item, dialogClickListener);
        builder.show();

    }



    public interface getClickOneItem{
        void Item();
    }
    public interface getClickTwoItem{
        void oneItem();
        void secondItem();
    }
}
