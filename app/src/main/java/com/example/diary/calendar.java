package com.example.diary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class calendar extends AppCompatActivity implements calendarAdaptor.OneItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        calendarAdaptor calendarAdaptor = new calendarAdaptor(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdaptor);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth(); // Количество дней в месяце
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1); // Первый день месяца
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue(); // День недели для первого дня месяца

        // Корректировка для начала недели с понедельника
        if (dayOfWeek == 7) { // Если первый день месяца — воскресенье
            dayOfWeek = 0; // Сдвигаем на 0, чтобы начать с понедельника
        }

        // Заполняем пустые ячейки для дней предыдущего месяца
        for (int i = 1; i < dayOfWeek; i++) {
            daysInMonthArray.add("");
        }

        // Заполняем дни текущего месяца
        for (int i = 1; i <= daysInMonth; i++) {
            daysInMonthArray.add(String.valueOf(i));
        }

        // Заполняем пустые ячейки для дней следующего месяца
        int totalCells = 42; // 6 строк по 7 дней
        while (daysInMonthArray.size() < totalCells) {
            daysInMonthArray.add("");
        }

        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date) {
        // Создаем DateTimeFormatter с русской локалью
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("ru"));
        return date.format(formatter);
    }

    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.month);
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.equals("")) {
            String message = "Selected Date: " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    public void toStr4(View v){
        Intent intent = new Intent(this, Str4.class);
        startActivity(intent);
    }

    public void toNote(View v){
        Intent intent = new Intent(this, Note.class);
        startActivity(intent);
    }
}