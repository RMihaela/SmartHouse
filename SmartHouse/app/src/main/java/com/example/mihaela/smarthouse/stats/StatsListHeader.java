package com.example.mihaela.smarthouse.stats;

import java.util.ArrayList;

/**
 * Created by Mihaela on 17.04.2016.
 */
public class StatsListHeader {
    private String Name;
    private ArrayList<StatsListItem> Items;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<StatsListItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<StatsListItem> Items) {
        this.Items = Items;
    }

}
