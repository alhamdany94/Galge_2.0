package com.example.test.galge_20;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_game:
                    fragment = new MenuFragment();
                    break;
                case R.id.navigation_highscore:
                    fragment = new HighscoreFragment();
                    break;
                case R.id.navigation_rules:
                    fragment = new RulesFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_container,fragment)
                    .commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Fragment menu = new MenuFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container,menu)
                .commit();

        getSupportActionBar().hide();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }

}
