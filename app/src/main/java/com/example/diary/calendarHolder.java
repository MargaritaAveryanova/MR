package com.example.diary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class calendarHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayMonth;
    private final calendarAdaptor.OneItemListener oneItemListener;
    public calendarHolder(@NonNull View itemView, calendarAdaptor.OneItemListener oneItemListener) {
        super(itemView);
        dayMonth = itemView.findViewById(R.id.cellDayText);
        this.oneItemListener = oneItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        oneItemListener.onItemClick(getAdapterPosition(), (String) dayMonth.getText());
    }
}
