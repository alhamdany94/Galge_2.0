package com.example.test.galge_20;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import model.Player;


/**
 * A simple {@link Fragment} subclass.
 */
public class HighscoreFragment extends Fragment {


    ArrayList<Player> players = new ArrayList<>();


    public HighscoreFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_highscore, container, false);
        ListView listView = (ListView)view.findViewById(R.id.highscoreList);

        return view;
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return players.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

}
