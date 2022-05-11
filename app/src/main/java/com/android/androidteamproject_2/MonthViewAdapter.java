package com.android.androidteamproject_2;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;


public class MonthViewAdapter extends FragmentStateAdapter {
    private static final int NUM_ITEMS = 60;
    // 앱을 실행시킨 순간의 연도, 월, 날짜
    private int year;
    private int month;

    public MonthViewAdapter(Fragment fa) {
        super(fa);
        //java calendar를 통해 년월일 초기화 시킴
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
    }

    @Override
    public MonthFragment createFragment(int position) {
        position =NUM_ITEMS/2;

        return MonthFragment.newInstance(month, year);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}