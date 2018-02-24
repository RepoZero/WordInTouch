package com.WordInTouch.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.WordInTouch.Message.EasyDialog;
import com.WordInTouch.R;

import java.util.ArrayList;

/**
 * Created by cloner on 1/24/18.
 */

public class MenuFragment extends Fragment {

    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<Integer>  drawable = new ArrayList<Integer>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        ListView lst = (ListView) view.findViewById(R.id.fragment_menu_lst);

        if(name.size()==0) {

            name.add(getResources().getString(R.string.Account_setting));
            name.add(getResources().getString(R.string.Sync));
            name.add(getResources().getString(R.string.Info));
            name.add(getResources().getString(R.string.Exit));

            drawable.add(R.drawable.account);
            drawable.add(R.drawable.sync);
            drawable.add(R.drawable.info);
            drawable.add(R.drawable.exit);

        }

        MenuAdapter adapter =  new MenuAdapter (getContext(),name,drawable); ;



        lst.setAdapter(adapter);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){

                }else if(position==1){

                }else if(position==2){

                }else {



                    EasyDialog easyDialog = new EasyDialog();

                    easyDialog.twoItem(getContext(), getResources().getString(R.string.skip_dialog_title),
                            null,
                            R.drawable.warning,
                            getResources().getString(R.string.yes),
                            getResources().getString(R.string.no),
                            new EasyDialog.getClickTwoItem(){
                                @Override
                                public void oneItem() {
                                    getActivity().finish();
                                }

                                @Override
                                public void secondItem() {


                                }
                            });
                }
            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class MenuAdapter extends BaseAdapter{

        private Context context;
        private ArrayList<String> name_item = new ArrayList<String>();
        private ArrayList<Integer>  drawable_item = new ArrayList<Integer>();

       public MenuAdapter( Context context , ArrayList<String> name_item , ArrayList<Integer>  drawable_item ){
            this.context=context;
            this.name_item=name_item;
            this.drawable_item=drawable_item;
       }

        @Override
        public int getCount() {
            return name_item.size();
        }

        @Override
        public Object getItem(int position) {
            return name_item.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_menu,parent,false);
            }

            TextView txt = (TextView) convertView.findViewById(R.id.item_menu_txt);
            ImageView img = (ImageView) convertView.findViewById(R.id.item_menu_img);

            txt.setText(name_item.get(position));
            img.setImageResource(drawable_item.get(position));

            return convertView;
        }
    }
}
