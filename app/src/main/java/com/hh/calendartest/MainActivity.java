package com.hh.calendartest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.right)
    TextView right;
    @BindView(R.id.gridview)
    CoustomerGridView gridview;

    private Unbinder unbinder;
    private DateAdapter adapter;

    private int currentYear, currentMonth, currentDay, dayOfMonth, firstDayOfWeek, totalDaysOfMonth;
    private int emptyTop, emptyBottom, totalCount;

    private int currentPositionChoosed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        init();
        setListener();
    }

    private void setListener() {

        left.setOnClickListener(this);
        right.setOnClickListener(this);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if(position<emptyTop+7){
                    return;
                }

                if(position >= emptyTop+totalDaysOfMonth+7){
                    return;
                }


                Log.i("day_click","点击："+currentYear+"年"+(currentMonth+1)+"月"+(currentPositionChoosed-7-emptyTop+1)+"日" +
                        "currentPositionChoosed :"+ currentPositionChoosed + " position :" +position);

                adapterView.getChildAt(currentPositionChoosed).setSelected(false);
                adapterView.getChildAt(position).setSelected(true);
                currentPositionChoosed = position;

                adapter.setChoosedDate(currentYear, currentMonth, currentPositionChoosed, currentYear, currentMonth);

            }
        });

    }


    private void init() {

        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        date.setText(currentYear+"年"+(currentMonth+1)+ "月");

        setDateInfo();

        gridview.setAdapter(adapter = new DateAdapter(this, emptyTop, totalDaysOfMonth, totalCount));
        adapter.setChoosedDate(currentYear, currentMonth, (currentDay+7+emptyTop-1), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        currentPositionChoosed = emptyTop+ 7 +currentDay -1;
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left:
                if(currentMonth == 0){
                    currentMonth = 11;
                    currentYear--;
                }else
                    currentMonth--;

                setDateInfo();

                adapter.refresh(emptyTop, totalCount, totalDaysOfMonth);
                adapter.setChoosedDate(currentYear, currentMonth, currentPositionChoosed);
                break;
            case R.id.right:

                if(currentMonth == 11){
                    currentMonth = 0;
                    currentYear ++;
                }else
                    currentMonth++;

                setDateInfo();

                adapter.refresh(emptyTop, totalCount, totalDaysOfMonth);
                adapter.setChoosedDate(currentYear, currentMonth, currentPositionChoosed);

                break;
        }
    }

    private void setDateInfo(){

        date.setText(currentYear+"年"+(currentMonth+1)+ "月");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.MONTH, currentMonth);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        totalDaysOfMonth = DateUtil.getDaysOfMonth(currentYear, currentMonth);

        emptyTop = firstDayOfWeek -1;
        emptyBottom =  ((int)(Math.ceil((emptyTop+totalDaysOfMonth)/7.0)) * 7 - (emptyTop+totalDaysOfMonth));
        totalCount = emptyTop + totalDaysOfMonth + emptyBottom;

        Log.i("day" ,currentYear+"年" + (currentMonth+1) + "月" + dayOfMonth +"日 1号是星期"+(firstDayOfWeek-1)+"， 共"+ totalDaysOfMonth + "天");

    }
}
