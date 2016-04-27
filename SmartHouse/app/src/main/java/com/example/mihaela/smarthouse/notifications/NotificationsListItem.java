package com.example.mihaela.smarthouse.notifications;

/**
 * Created by silviu on 27-Apr-16.
 */
public class NotificationsListItem {
    private String type;
    private String message;
    private boolean seen;

    public NotificationsListItem(String type,String message) {
        this.setType(type);
        this.setMessage(message);
        this.setSeen(false);

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
