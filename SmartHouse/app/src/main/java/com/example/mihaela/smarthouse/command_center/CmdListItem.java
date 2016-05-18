package com.example.mihaela.smarthouse.command_center;

/**
 * Created by Mihaela on 24.04.2016.
 */
public interface CmdListItem {

    public String getTitle();

    public void setTitle(String newTitle);

    public String getStatus();

    public void setStatus(String newStatus);

    public void setIndex(int index);
}