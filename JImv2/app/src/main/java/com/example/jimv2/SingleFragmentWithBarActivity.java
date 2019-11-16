package com.example.jimv2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public abstract class SingleFragmentWithBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }




    }
    /*
    if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.EXERCISE_ACTIVITY, fragment).commit();
    } else if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id., fragment).commit();
    } else if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.CALANDER_FRAGMENT, fragment).commit();
    }

     */
}
