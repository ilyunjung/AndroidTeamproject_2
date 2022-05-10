package com.android.androidteamproject_2;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;


public class MonthViewAdapter extends FragmentStateAdapter {
    private static final int NUM_ITEMS = 3;
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

    @NonNull
    @Override
    public MonthFragment createFragment(int position) {
        switch(position)
        {

            case 0:
                month -=1;
                return MonthFragment.newInstance(year, month);
            case 1:
                return MonthFragment.newInstance(year, month);
            case 2:
                month +=1;
                return MonthFragment.newInstance(year, month);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}