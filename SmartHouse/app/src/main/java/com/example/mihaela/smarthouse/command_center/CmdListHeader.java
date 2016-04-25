package com.example.mihaela.smarthouse.command_center;

import java.util.ArrayList;

/**
 * Created by Mihaela on 24.04.2016.
 */
public class CmdListHeader {
    private String Name;
    private ArrayList<CmdListItem> Items=new ArrayList<>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<CmdListItem> getItems() {
        return Items;
    }

    public void setItems(ArrayList<CmdListItem> Items) {
        this.Items = Items;
    }

}
