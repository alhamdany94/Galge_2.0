package com.example.test.galge_20;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class GameOverFragment extends Fragment implements View.OnClickListener {


    private TextView status, trials, aboutTony;
    private ImageView imgOfTony;
    private boolean win;
    private int numOftrials;
    private String word;
    private Button endButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_over, container, false);

        status = view.findViewById(R.id.status_of_game);
        trials = view.findViewById(R.id.trials);
        aboutTony = view.findViewById(R.id.tony_is);
        imgOfTony = view.findViewById(R.id.tony_end);
        endButton = view.findViewById(R.id.end_btn);
        endButton.setOnClickListener(this);

        Bundle arg = getArguments();
        win = arg.getBoolean("win");
        numOftrials = arg.getInt("trials");
        word = arg.getString("word");

        if(win){
            celebrationScreen();

        } else {
            tryAgainScreen();

        }


        return view;
    }



    private void celebrationScreen(){

        this.status.setText("Sejt! Du vandt!");
        this.trials.setText("Din score (forsøg):  "+String.valueOf(numOftrials));
        this.imgOfTony.setImageResource(R.drawable.forkert5);
        this.aboutTony.setText("Tony er i live");
        this.endButton.setText("Gem din score");

    }

    private void tryAgainScreen(){
        this.status.setText("Tony er død!");
        this.trials.setText("Din score (forsøg):  "+String.valueOf(numOftrials));
        this.imgOfTony.setImageResource(R.drawable.dead);
        this.aboutTony.setText("Surt show");
        this.endButton.setText("Prøv igen");

    }

    @Override
    public void onClick(View v) {

        if(win){

        } else {

            getFragmentManager().popBackStack();
            getFragmentManager().beginTransaction()
                    .replace(R.id.frag_container, new GameFragment())
                    .addToBackStack(null)
                    .commit();

        }

    }
}
