package com.example.mihaela.smarthouse.command_center;

/**
 * Created by Mihaela on 24.04.2016.
 */
public class CmdSwitchListItem implements CmdListItem{
    private String title;
    private String status = new String("");

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }
}