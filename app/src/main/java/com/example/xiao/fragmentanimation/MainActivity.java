package com.example.xiao.fragmentanimation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Fragment1 fragment1;

    private Fragment2 fragment2;

    private Fragment3 fragment3;

    private FragmentManager manager;

    int lastFragment = 0;

    private static final int TAG1 = 1;

    private static final int TAG2 = 2;

    private static final int TAG3 = 3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastFragment != TAG1) {
                        changeFragment(fragment1);
                        lastFragment = TAG1;
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (lastFragment != TAG2) {
                        changeFragment(fragment2);
                        lastFragment = TAG2;
                    }
                    return true;
                case R.id.navigation_notifications:
                    if (lastFragment != TAG3) {
                        changeFragment(fragment3);
                        lastFragment = TAG3;
                    }
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    private void changeFragment(Fragment fragment) {
        manager.beginTransaction()
                .setCustomAnimations(R.anim.fragment_slide_in_from_bottom, R.anim.fragment_slide_out_from_top)
                .replace(R.id.Container, fragment).addToBackStack(null).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void initFragment() {
        fragment1 = Fragment1.newInstance("Fragment1");
        fragment2 = Fragment2.newInstance("Fragment2");
        fragment3 = Fragment3.newInstance("Fragment3");
        manager = getSupportFragmentManager();
        lastFragment = TAG1;
        manager.beginTransaction().add(R.id.Container, fragment1).commit();
    }
}
