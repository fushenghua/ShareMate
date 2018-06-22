package com.mate.dialog;

import android.graphics.drawable.Drawable;

/**
 * create by fushenghua
 */
public class MenuItem {

    private int id;

    private String title;

    private Drawable icon;

    public MenuItem() {
    }

    public MenuItem(int id, String title, Drawable icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
