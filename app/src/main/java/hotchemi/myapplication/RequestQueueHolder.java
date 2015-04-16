package hotchemi.myapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * @author Shintaro Katafuchi
 */
public final class RequestQueueHolder {

    private static RequestQueueHolder instance;

    private RequestQueue requestQueue;

    private static Context context;

    private RequestQueueHolder(Context context) {
        RequestQueueHolder.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueHolder getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQueueHolder(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

}
