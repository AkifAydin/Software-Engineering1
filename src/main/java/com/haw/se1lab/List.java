package com.haw.se1lab;

import java.util.Date;

public class List {
    //Attribute
    private final String listTitle;
    private final Date validFrom;
    private final Date validTo;
    private final boolean visibleForOthers;

    //Konstruktor
    public List(String listTitle, Date validFrom, Date validTo, boolean visibleForOthers){
        this.listTitle = listTitle;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.visibleForOthers = visibleForOthers;
    }

    //Getters
    public String getListTitle() {
        return listTitle;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public boolean isVisibleForOthers() {
        return visibleForOthers;
    }
}
