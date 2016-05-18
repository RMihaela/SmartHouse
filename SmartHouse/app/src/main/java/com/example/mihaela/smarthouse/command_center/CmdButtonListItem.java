package com.example.mihaela.smarthouse.command_center;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Mihaela on 24.04.2016.
 */
public class CmdButtonListItem implements CmdListItem{
    private String title;
    private String status;
    private int index;

    public String getTitle() {
        return title == null? " " : title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public void setIndex(int index){
        this.index=index;
    }

}