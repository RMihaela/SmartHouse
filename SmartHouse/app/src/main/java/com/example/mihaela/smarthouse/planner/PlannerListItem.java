package com.example.mihaela.smarthouse.planner;

/**
 * Created by Mihaela on 17.04.2016.
 */
public class PlannerListItem {
    private String title;
//    private String switchButtonText;
    private boolean started;

    public PlannerListItem(String title, boolean started) {
        this.title = title;
        this.started = started;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public String getSwitchButtonText() {
//        return switchButtonText;
//    }
//
//    public void setSwitchButtonText(String switchButtonText) {
//        this.switchButtonText = switchButtonText;
//    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
