package com.hh.calendartest;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by haohe on 2017/8/9 0009.
 */

public class DateAdapter extends BaseAdapter {

    private Context context;
    private int emptyTop, totalCount, totalDaysOfMonth;
    private int currentYear, currentMonth; // 当前翻转到的年月
    private int choosedYear, choosedMonth, choosedPosition; // 选择的年月日

    public DateAdapter(Context context, int emptyTop, int totalDaysOfMonth, int totalCount) {

        this.context =context;
        this.emptyTop = emptyTop;
        this.totalCount = totalCount;
        this.totalDaysOfMonth = totalDaysOfMonth;

    }

    public void setChoosedDate(int choosedYear, int choosedMonth, int choosedPosition, int currentYear, int currentMonth){

        this.choosedYear = choosedYear;
        this.choosedMonth = choosedMonth;
        this.choosedPosition = choosedPosition;
        this.currentYear = currentYear;
        this.currentMonth = currentMonth;
    }

    public void setChoosedDate(int choosedYear, int choosedMonth, int choosedPosition){

        this.choosedYear = choosedYear;
        this.choosedMonth = choosedMonth;
        this.choosedPosition = choosedPosition;
    }

    public void refresh( int emptyTop, int totalCount, int totalDaysOfMonth){

        this.emptyTop = emptyTop;
        this.totalCount = totalCount;
        this.totalDaysOfMonth = totalDaysOfMonth;

        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return (totalCount+7);
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null){
            view = View.inflate(context, R.layout.item_date, null);
        }

        TextView txt = view.findViewById(R.id.date_txt);
        TextView date_info = view.findViewById(R.id.date_info);
        RelativeLayout date_rl = view.findViewById(R.id.date_rl);

        if(position <7){
            switch (position){
                case 0:
                    txt.setText("日");
                    break;
                case 1:
                    txt.setText("一");
                    break;
                case 2:
                    txt.setText("二");
                    break;
                case 3:
                    txt.setText("三");
                    break;
                case 4:
                    txt.setText("四");
                    break;
                case 5:
                    txt.setText("五");
                    break;
                case 6:
                    txt.setText("六");
                    break;
            }
        }
        else if(position < emptyTop + 7)
            txt.setText("");
        else if(position >= emptyTop + totalDaysOfMonth + 7)
            txt.setText("");
        else
            txt.setText(position - 7 - emptyTop + 1+"");

        if(position == choosedPosition &&  currentYear == choosedYear && currentMonth == choosedMonth){

//            date_rl.setSelected(true);
            txt.setTextColor(Color.parseColor("#FFFFFF"));
            date_rl.setBackgroundColor(Color.parseColor("#ff6d00"));
        }

        else{

//            date_rl.setSelected(false);
            txt.setTextColor(Color.parseColor("#777777"));
            date_rl.setBackground(null);
        }

        return view;
    }
}
