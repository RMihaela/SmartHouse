package com.example.mihaela.smarthouse.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.mihaela.smarthouse.R;
import com.example.mihaela.smarthouse.managers.AlertsManager;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    public static List<NotificationsListItem> newNotifications;

    public static void initNewNotifList(){
        if (newNotifications == null) {
            newNotifications = new ArrayList<>();
        }
    }

    private List<NotificationsListItem> notificationsItemsList=new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertsManager.setContext(this);
        setContentView(R.layout.activity_notifications);

        notificationsItemsList.addAll(newNotifications);
        newNotifications.clear();

        setListView((ListView) findViewById(R.id.listView2));
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
