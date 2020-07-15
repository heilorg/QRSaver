package com.example.qrsaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF3B6DFF));

        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        PhotoFragment fragment1 = new PhotoFragment();
        adapter.addItem(fragment1);

        StorageFragment fragment2 = new StorageFragment();
        adapter.addItem(fragment2);

        CreationFragment fragment3 = new CreationFragment();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);

        final BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.tab1:
                    pager.setCurrentItem(0);
                    return true;

                case R.id.tab2:
                    pager.setCurrentItem(1);
                    return true;

                case R.id.tab3:
                    pager.setCurrentItem(2);
                    return true;
            }
            return false;
            }
        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        findViewById(R.id.tab1).callOnClick();
                        Intent intent = new Intent(MainActivity.this, SaveqrActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        findViewById(R.id.tab2).callOnClick();
                        break;
                    case 2:
                        findViewById(R.id.tab3).callOnClick();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Intent intent = getIntent();
        if(intent != null){
            int page = intent.getExtras().getInt("page");
            if(page == 1){
                pager.setCurrentItem(0);
                Intent intent2 = new Intent(MainActivity.this, SaveqrActivity.class);
                startActivity(intent2);
                findViewById(R.id.tab1).callOnClick();
            }else if(page == 2){
                pager.setCurrentItem(1);
                findViewById(R.id.tab2).callOnClick();
            }else if(page == 3){
                pager.setCurrentItem(2);
                findViewById(R.id.tab3).callOnClick();
            }
        }
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MyPagerAdapter(FragmentManager fm){
            super(fm);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }


    // 액션바 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }

    // 액션바 클릭 이벤트
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.loginAction) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
