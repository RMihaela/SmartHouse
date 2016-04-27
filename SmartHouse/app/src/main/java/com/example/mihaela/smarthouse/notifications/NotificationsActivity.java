package com.example.mihaela.smarthouse.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mihaela.smarthouse.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    private List<NotificationsListItem> notificationsItemsList=new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        getNotificationsItemsList().add(new NotificationsListItem("Information","Garage doors will open at 7:00 A.M"));
        getNotificationsItemsList().add(new NotificationsListItem("Alert","You left the doors open. We took care of that for you"));

        setListView((ListView) findViewById(R.id.listView));
        getListView().setAdapter(new NotificationsListAdapter(this, this.getNotificationsItemsList()));
    }

    public List<NotificationsListItem> getNotificationsItemsList() {
        return notificationsItemsList;
    }

    public void setNotificationsItemsList(List<NotificationsListItem> notificationsItemsList) {
        this.notificationsItemsList = notificationsItemsList;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public void addNotification (NotificationsListItem notificationsListItem){
        this.notificationsItemsList.add(notificationsListItem);
    }

    public void removeSeenNotifications(){
        List<NotificationsListItem> toRemove=new ArrayList<>();
        for(NotificationsListItem notification:notificationsItemsList){
            if(notification.isSeen()) {
                toRemove.add(notification);
            }
        }

        for(NotificationsListItem notificationToRemove:toRemove){
            this.notificationsItemsList.remove(notificationToRemove);
        }
    }
}
