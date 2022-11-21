package com.theanhdev.multisocialprooject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.theanhdev.multisocialprooject.fragments.HomeFragment;
import com.theanhdev.multisocialprooject.fragments.RecentFragment;
import com.theanhdev.multisocialprooject.fragments.SettingFragment;

public class MainActivity extends AppCompatActivity {
    @SuppressLint({"NonConstantResourceId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeFragment());
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    loadFragment(new HomeFragment());
                    break;
                case R.id.recent:
                    loadFragment(new RecentFragment());
                    break;
                case R.id.setting:
                    loadFragment(new SettingFragment());
                    break;
            }
            return true;
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_wrapper, fragment);
        transaction.addToBackStack("replacement");
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }

}