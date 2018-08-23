package com.example.test.galge_20;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import logic.Galgelogik;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements View.OnClickListener {

    private TextView word;
    private ImageView hangmanImage;
    private Button[] letters;
    private Galgelogik logic;


    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        this.logic = new Galgelogik();
        this.word = view.findViewById(R.id.the_word);
        this.hangmanImage = view.findViewById(R.id.hangman);
        initLetters(view);
        initWord();

        return view;
    }

    private void initLetters(View view) {
        letters = new Button[29];
        char id = 'A';
        int i;
        for (i = 0; i < letters.length; i++) {
            int resource = getResources().getIdentifier(String.valueOf(id), "id", getActivity().getPackageName());
            letters[i] = view.findViewById(resource);

            System.out.print(letters[i]);

            letters[i].setOnClickListener(this);
            id++;
            if (i == 25) break;
        }

        letters[i] = view.findViewById(R.id.Æ);
        letters[i].setOnClickListener(this);
        i++;
        letters[i] = view.findViewById(R.id.Ø);
        letters[i].setOnClickListener(this);
        i++;
        letters[i] = view.findViewById(R.id.Å);
        letters[i].setOnClickListener(this);
        i++;
        this.hint = view.findViewById(R.id.HINT);
        this.hint.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        Button b = (Button) v;
        String letter = b.getText().toString().toLowerCase();
        guess(letter);
        v.setEnabled(false);

    }


    public void guess(String letter) {


        logic.gætBogstav(letter);
        updateHangmanImage();

// Hvis spillet er vundet
        if (logic.getSynligtOrd().equals(logic.getOrdet())) {
            endOfGame();

        }


    }

    public void endOfGame() {

        boolean win = false;
        int trials = logic.getAntalForkerteBogstaver();
        if (logic.erSpilletVundet()) win = true;

        Fragment gameOverFragment = new GameOverFragment();

        Bundle arg = new Bundle();
        arg.putBoolean("win", win);
        arg.putInt("trials", trials);
        arg.putString("word", logic.getOrdet());
        gameOverFragment.setArguments(arg);

        getFragmentManager().popBackStack();
        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frag_container, gameOverFragment)
                .commit();

        logic.logStatus();

    }


    public void updateHangmanImage() {


        word.setText(logic.getSynligtOrd());

        switch (logic.getAntalForkerteBogstaver()) {
            case 1:
                hangmanImage.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                hangmanImage.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                hangmanImage.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                hangmanImage.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                hangmanImage.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                hangmanImage.setImageResource(R.drawable.dead);
                endOfGame();
                break;
            default:

        }


    }

    public void initWord() {


        new AsyncTask() {


            boolean forbindelse = false;

            @Override
            protected Object doInBackground(Object... arg0) {


                try {
                    logic.hentOrdFraDr();
                    forbindelse = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.interrupted();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return Log.d("AsyncTask", "doInBackGround afsluttet");  // <5>
            }

            @Override
            protected void onPostExecute(Object result) {


                if (forbindelse && getActivity() != null) {

                    Toast.makeText(getActivity(), "Bruger ord fra www.indkast.dk", Toast.LENGTH_LONG).show();
                    word.setText(logic.getSynligtOrd());

                } else if (!forbindelse && getActivity() != null) {
                    Toast.makeText(getActivity(), "Error. Bruger standard ord (hardcoded). Tjek evt. internetforbindelse", Toast.LENGTH_LONG).show();

                    word.setText(logic.getSynligtOrd());


                }


                System.out.println("**************************** ORDET -->  " + logic.getOrdet() + " <-- ORDET ****************************");


            }
        }.execute();

    }

}