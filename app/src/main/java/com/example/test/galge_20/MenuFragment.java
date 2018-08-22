package com.example.test.galge_20;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    private Button play;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);


        this.play = view.findViewById(R.id.play_btn);
        this.play.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == this.play) {



            getFragmentManager().beginTransaction()
                    .replace(R.id.frag_container, new GameFragment())
                    .addToBackStack(null)
                    .commit();

        }
    }
}
