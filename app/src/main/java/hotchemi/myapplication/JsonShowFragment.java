package hotchemi.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonShowFragment extends Fragment {

    private static final String BUNDLE_KEY_URL = "url";

    private ListView listView;

    public static JsonShowFragment newInstance(String url) {
        JsonShowFragment fragment = new JsonShowFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    public JsonShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_json_show, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_view);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String url = getArguments().getString(BUNDLE_KEY_URL);
        RequestQueueHolder.getInstance(getActivity().getApplicationContext())
                .addToRequestQueue(new StringRequest(
                        Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jArray = new JSONArray(response);
                            ArrayList<String> items = new ArrayList<>();
                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject jsonObject = jArray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                items.add(name);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, items);
                            listView.setAdapter(arrayAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("tag", error.toString());
                    }
                }));
    }

}