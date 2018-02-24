package com.WordInTouch.UI.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.WordInTouch.R;
import com.WordInTouch.SQLiteDB.Query;
import com.WordInTouch.UI.AddTerm;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cloner on 1/24/18.
 */

public class LocalFragment extends Fragment {

    @BindView(R.id.fragment_local_fab) FloatingActionButton fab;
    @BindView(R.id.fragment_local_lst) ListView lst;
    @BindView(R.id.fragment_local_edt) EditText edt;


     ArrayList<Integer> id = new ArrayList<Integer>();
     ArrayList<String> text = new ArrayList<String>();
     ArrayList<Integer> parameterId = new ArrayList<Integer>();

    double displaySizeInch;



    



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local, container, false);

        ButterKnife.bind(this, view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddTerm.class));
            }
        });






        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);

         displaySizeInch = Math.sqrt(x+y) + 0.3;


        id.add(1);
        id.add(2);
        id.add(3);
        id.add(4);

        id.add(1);
        id.add(2);
        id.add(3);
        id.add(4);


        text.add(getResources().getString(R.string.testText));
        text.add(getResources().getString(R.string.lorem));
        text.add("Term 1165");
        text.add("fake Term");

        text.add(getResources().getString(R.string.testText));
        text.add(getResources().getString(R.string.lorem));
        text.add("Term 1165");
        text.add("fake Term");


        parameterId.add(1);
        parameterId.add(9);
        parameterId.add(5);
        parameterId.add(8);

        parameterId.add(1);
        parameterId.add(9);
        parameterId.add(5);
        parameterId.add(8);


        localAdapter localAdapter = new localAdapter(getContext(), id, text, parameterId);

        lst.setAdapter(localAdapter);

        return view;
    }


    public class localAdapter extends BaseAdapter {

        private Context context ;
        private ArrayList<Integer> id = new ArrayList<Integer>();
        private ArrayList<String> text = new ArrayList<String>();
        private ArrayList<Integer> parameterId = new ArrayList<Integer>();
        private WindowManager windowManager;

        localAdapter(Context context, ArrayList<Integer> id, ArrayList<String> text , ArrayList<Integer> parameterId ){
            this.context=context;
            this.id=id;
            this.text=text;
            this.parameterId=parameterId;
        }


        @Override
        public int getCount() {
            return text.size();
        }

        @Override
        public Object getItem(int position) {
            return text.get(position);
        }

        @Override
        public long getItemId(int position) {
            return id.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(context).inflate(R.layout.local_lst, parent, false);

            TextView txt = convertView.findViewById(R.id.local_lst_txt);
            TextView txt_type = convertView.findViewById(R.id.local_lst_txt_type);




            Log.e("AAA", displaySizeInch + "");

            int charShow = 30;

            if(displaySizeInch > 6)
                charShow=40;

            if(displaySizeInch > 7)
                charShow= 60;

            if (text.get(position).length() > charShow) {
                StringBuilder someText = new StringBuilder();

                for (int i = 0; i < charShow; i++)
                    someText.append(text.get(position).charAt(i));
                someText.append(" ...");
                txt.setText(someText.toString());


            } else
                txt.setText(text.get(position));

            String parameter = Query.getStringFromInt("parameter", "id", parameterId.get(position), "text");

            txt_type.setText(parameter);

            switch (parameterId.get(position)) {
                case 1:
                    txt_type.setTextColor(getResources().getColor(R.color.black));
                    break;
                case 4:
                    txt_type.setTextColor(getResources().getColor(R.color.Category));
                    break;
                case 5:
                    txt_type.setTextColor(getResources().getColor(R.color.Translation));
                    break;
                case 6:
                    txt_type.setTextColor(getResources().getColor(R.color.Definition));
                    break;
                case 7:
                    txt_type.setTextColor(getResources().getColor(R.color.Synonym));
                    break;
                case 8:
                    txt_type.setTextColor(getResources().getColor(R.color.Antonym));
                    break;
                case 9:
                    txt_type.setTextColor(getResources().getColor(R.color.Example));
                    break;
            }

            return convertView;
        }


    }
}
