package com.WordInTouch;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.WordInTouch.SQLiteDB.Connection;
import com.WordInTouch.Volley.LruBitmapCache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;

/**
 * Created by cloner on 10/9/17.
 */

public class Application extends android.app.Application {

    public static final String TAG = Application.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
//    private static Application mInstance;

    public static String SERVER_ADDRESS = "http://wordintouch.com/api/";


    static public SQLiteDatabase db;


   public static ArrayList<Character> type_parametrs =new ArrayList<Character>();
  public static ArrayList<String> texts =new ArrayList<String>();





    @Override
    public void onCreate() {
        super.onCreate();


        Connection connection = new Connection(this);
        db = connection.getWritableDatabase();



//        mInstance = this;

    }

//
//    public static synchronized Application getInstance() {
//        return mInstance;
//    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }


    }
}
