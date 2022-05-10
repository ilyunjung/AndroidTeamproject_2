package com.android.androidteamproject_2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MonthFragment extends Fragment {

    private static final String ARG_PARAM1 = "year";
    private static final String ARG_PARAM2 = "month";
    private int year;
    private int month;
    static GridAdapter adapter;
    private Calendar mCalendar;
    int firstday; // 첫날의 요일
    int lastday; //달의 마지막 날짜
    ArrayList<String> daylist;//날짜 저장 리스트
    int gridviewH;
    public MonthFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            year = getArguments().getInt(ARG_PARAM1);
            month = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_month_view, container, false);
        GridView gridview = (GridView) v.findViewById(R.id.gridview);
        setCalendar(year, month); //daylist를 초기화 히면서 year, month 정보를 넣는다.

        daylist = new ArrayList<String>();
        mCalendar.set(Calendar.YEAR,year);
        mCalendar.set(Calendar.MONTH,month);
        int dayNum = mCalendar.get(Calendar.DAY_OF_WEEK);
        for (int i = 1; i < dayNum; i++) {//매달 1일과 요일을 일치시키기위한 공백 추가
            daylist.add("");
        }
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //position은 내가 누른 위치.
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), (year) + "/" + (month + 1) + "/" + (position - firstday + 1), Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new GridAdapter(getActivity(), daylist);
        gridview.setAdapter(adapter);
        return v;
    }

    private void setCalendar(int year, int month){
        //매달 첫날의 요일, 마지막날이 30일인지 31인지를 판별
        Calendar calendar = Calendar.getInstance(); //calendar 객체
        //입력받은 각각의 연도 달을 설정
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// firstday를 만들기 위해 입력받은 년, 월에 대한 일자를 1로 설정한다.
        firstday = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //첫날을 설정하는데 Calendar.DAY_OF_WEEK는 일요일부터 차례로 1, 2, 3...으로 설정이 된다
        //convertview, daylist는 모두 0부터 시작하니 Calendar.DAY_OF_WEEK과 맞추기 위해 -1을 해준다
        lastday = calendar.getActualMaximum(Calendar.DATE);
        // 마지막날은 해당 달의 최대값
        for (int i=0; i<6*7; i++) { // 7행6열의 리스트를 만든다
            if ( i < firstday || i > (lastday + firstday - 1)) daylist.add("");
                // 첫날보다 작거나, 마지막날보다 크면 공백으로해줌
            else //그렇지 않으면 첫날부터 마지막날까지 써줌
                daylist.add("" + (i - firstday + 1));
        }
    }
    public static MonthFragment newInstance(int year, int month) {
        MonthFragment fragment = new MonthFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        fragment.setArguments(args);
        return fragment;
    }
}