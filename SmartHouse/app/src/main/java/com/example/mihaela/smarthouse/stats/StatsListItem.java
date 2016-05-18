package com.example.mihaela.smarthouse.stats;

/**
 * Created by Mihaela on 17.04.2016.
 */
public class StatsListItem {
    private String title;
    private String status;
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
