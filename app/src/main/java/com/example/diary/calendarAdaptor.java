package com.example.diary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class calendarAdaptor extends RecyclerView.Adapter<calendarHolder>{

    private final ArrayList<String> daysMonth;
    private final OneItemListener oneItemListener;

    public calendarAdaptor(ArrayList<String> daysMonth, OneItemListener oneItemListener) {
        this.daysMonth = daysMonth;
        this.oneItemListener = oneItemListener;
    }

    @NonNull
    @Override
    public calendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.1666666);
        return new calendarHolder(view, oneItemListener);

    }

    @Override
    public void onBindViewHolder(@NonNull calendarHolder holder, int position) {

        holder.dayMonth.setText(daysMonth.get(position));

    }

    @Override
    public int getItemCount() {
        return daysMonth.size();
    }

    public interface OneItemListener{
        void onItemClick(int position, String dayText);
    }
}
