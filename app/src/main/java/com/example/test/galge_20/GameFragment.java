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

    private Button A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R_,S,T,U,V,W,X,Y,Z,AE,OE,AA, hint;
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
        for (i = 0; i < letters.length ; i++) {
            int resource = getResources().getIdentifier(String.valueOf(id), "id", getActivity().getPackageName());
            letters[i] = view.findViewById(resource);

            System.out.print(letters[i]);

           letters[i].setOnClickListener(this);
            id++;
            if(i == 25) break;
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


        Button b = (Button)v;
        String letter = b.getText().toString().toLowerCase();
        guess(letter);
        v.setEnabled(false);

    }


    public void guess(String letter) {

        logic.gætBogstav(letter);
        updateHangmanImage();


        if(logic.erSpilletSlut()) {
            endOfGame();

        }

    }

    public void endOfGame() {

        boolean win = false;
        int trials = logic.getAntalForkerteBogstaver();
        if(logic.erSpilletVundet()) win = true;

        Fragment gameOverFragment = new GameOverFragment();

        Bundle arg = new Bundle();
        arg.putBoolean("win", win);
        arg.putInt("trials", trials);
        arg.putString("word",logic.getOrdet());
        gameOverFragment.setArguments(arg);

        getFragmentManager().popBackStack();
        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frag_container, gameOverFragment)
                .commit();

        logic.logStatus();

    }


    private void disableButton(String letter){
        for (int i = 0; i <letters.length ; i++) {
            if(letters[i].getText().toString() == letter) {
                letters[i].setEnabled(false);
            }

        }
    }


    public void updateHangmanImage(){


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
                break;
            default:

        }

    }

    public void initWord(){


        new AsyncTask() {


            boolean forbindelse = false;

            @Override
            protected Object doInBackground(Object... arg0) {


                try {
                    logic.hentOrdFraDr();
                    forbindelse = true;
                }

                catch(InterruptedException e){
                    e.printStackTrace();
                    Thread.interrupted();
                }

                catch (Exception e) {
                    e.printStackTrace();
                }

                return Log.d("AsyncTask", "doInBackGround afsluttet");  // <5>
            }

            @Override
            protected void onPostExecute(Object result) {


                if (forbindelse && getActivity() != null) {

                      Toast.makeText(getActivity(), "Ord hentet fra www.dr.dk", Toast.LENGTH_LONG).show();
                    word.setText(logic.getSynligtOrd());

                } else if(!forbindelse && getActivity() !=null){
                        Toast.makeText(getActivity(), "Fejl! tjek evt. internet forbindelse", Toast.LENGTH_LONG).show();

                    word.setText(logic.getSynligtOrd());


                }


                System.out.println("ORDET---------------------------------------------------------------------------- ORDET >>>>>: " + logic.getOrdet() + " :<<<<< ORDET ------------------------------------------------------------------------------------------");


            }
        }.execute();

    }

//
//        new AsyncTask() {
//
//            boolean recived = false;
//
//            @Override
//            protected Object doInBackground(Object[] objects) {
//
//                try {
//                    logic.hentOrdFraDr();
//                    recived = true;
//
//                }
//
//                catch(InterruptedException e){
//                    e.printStackTrace();
//                    Thread.interrupted();
//                }
//
//                catch (Exception e) {
//                    e.printStackTrace();
//                    return e;
//                }
//
//                return Log.d("AsyncTask", "doInBackGround finished");
//            }
//
//            @Override
//            protected void onPostExecute(Object o) {
//
//                if (recived){
//                    Log.d("SE HER ________-----------------", logic.getSynligtOrd());
//                    word.setText(logic.getSynligtOrd());
//                    Toast.makeText(getActivity(), "Ord hentet fra www.dr.dk", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(), "Fejl - ord ikke hentet", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        }.execute();
//
//
//    }
}


//this.A = view.findViewById(R.id.A);
//        this.B = view.findViewById(R.id.B);
//        this.C = view.findViewById(R.id.C);
//        this.D = view.findViewById(R.id.D);
//        this.E = view.findViewById(R.id.E);
//        this.F = view.findViewById(R.id.F);
//        this.G = view.findViewById(R.id.G);
//        this.H = view.findViewById(R.id.H);
//        this.I = view.findViewById(R.id.I);
//        this.J = view.findViewById(R.id.J);
//        this.K = view.findViewById(R.id.K);
//        this.L = view.findViewById(R.id.L);
//        this.M = view.findViewById(R.id.M);
//        this.N = view.findViewById(R.id.N);
//        this.O = view.findViewById(R.id.O);
//        this.P = view.findViewById(R.id.P);
//        this.Q = view.findViewById(R.id.Q);
//        this.R_ = view.findViewById(R.id.R);
//        this.S = view.findViewById(R.id.S);
//        this.T = view.findViewById(R.id.T);
//        this.U = view.findViewById(R.id.U);
//        this.V = view.findViewById(R.id.V);
//        this.W = view.findViewById(R.id.W);
//        this.X = view.findViewById(R.id.X);
//        this.Y = view.findViewById(R.id.Y);
//        this.Z = view.findViewById(R.id.Z);
//        this.AE = view.findViewById(R.id.Æ);
//        this.OE = view.findViewById(R.id.Ø);
//        this.AA = view.findViewById(R.id.Å);
//        this.hint = view.findViewById(R.id.HINT);
//        this.hangmanImage = view.findViewById(R.id.)