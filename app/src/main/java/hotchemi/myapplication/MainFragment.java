package hotchemi.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainFragment extends Fragment implements View.OnClickListener {

    private Button button;

    private EditText editText;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(this);
        editText = (EditText) rootView.findViewById(R.id.edittext);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        String url = editText.getText().toString();
        getFragmentManager().beginTransaction().replace(R.id.container, JsonShowFragment.newInstance(url)).commit();
    }

}
