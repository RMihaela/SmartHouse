package com.example.mihaela.smarthouse.command_center;

import java.util.ArrayList;

/**
 * Created by Mihaela on 24.04.2016.
 */
public class CmdListHeader {
    private String Name;
    private ArrayList<CmdButtonListItem> Items;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<CmdButtonListItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<CmdButtonListItem> Items) {
        this.Items = Items;
    }

}
