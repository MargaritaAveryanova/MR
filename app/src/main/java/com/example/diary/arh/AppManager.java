package com.example.diary.arh;

import android.content.Context;

import com.example.diary.adapter.ListItem;
import com.example.diary.db.MyDbManager;

import java.util.List;

public class AppManager {
    private MyDbManager dbManager;
    private List<ListItem> currentItems;

    public AppManager(Context context) {
        dbManager = new MyDbManager(context);
        dbManager.openDb();
    }

    // Все методы работы с данными
    public void addItem(String title, String description, String uri, String quote) {
        dbManager.insertToDb(title, description, uri, quote);
        currentItems = dbManager.getFromDb(); // Обновляем кэш
    }

    public void deleteItem(int id) {
        dbManager.delete(id);
        currentItems = dbManager.getFromDb();
    }

    public List<ListItem> getItems() {
        if (currentItems == null) {
            currentItems = dbManager.getFromDb();
        }
        return currentItems;
    }
}
