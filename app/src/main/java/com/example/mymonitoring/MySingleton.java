package com.example.mymonitoring;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    //contrusctor
    private MySingleton(Context context)
    {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context)
    {
       if(mInstance==null)
       {
           mInstance = new MySingleton(context);
       }
       return mInstance;
    }


    // method request que

    public RequestQueue getRequestQueue()
    {
            if(requestQueue==null)

            {
                requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            }
            return requestQueue;
    }

    public <T>void  addTorequestQue(Request<T> request)
    {
            requestQueue.add(request);
    }
}
