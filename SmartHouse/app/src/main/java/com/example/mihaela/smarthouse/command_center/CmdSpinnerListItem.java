package com.example.mihaela.smarthouse.command_center;

/**
 * Created by Mihaela on 24.04.2016.
 */
public class CmdSpinnerListItem {
    private String title;
    private int status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int newStatus) {
        this.status = newStatus;
    }
}