package com.example.mihaela.smarthouse.notifications;

import android.widget.ListView;
import com.example.mihaela.smarthouse.R;
import android.support.v7.app.AppCompatActivity;
import com.example.mihaela.smarthouse.R;
import android.os.Bundle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by silviu on 10-May-16.
 */
public class NotificationsActivityTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testGetNotificationsItemsList() throws Exception {

        NotificationsActivity instance=new NotificationsActivity();
        List<NotificationsListItem> list=new ArrayList<NotificationsListItem>();
        NotificationsListItem item1=new NotificationsListItem("foo","bar");
        NotificationsListItem item2=new NotificationsListItem("foo","baz");
        list.add(item1);
        list.add(item2);
        instance.setNotificationsItemsList(list);
        assertEquals("Notification List not returned properly",instance.getNotificationsItemsList(),list);

    }



    @Test
    public void testGetListView() throws Exception {
     NotificationsActivity instance=new NotificationsActivity();
        ListView listView;
        listView = new ListView(null);
        instance.setListView(listView);
        assertEquals("View List not returned properly",listView,instance.getListView());

    }



    @Test
    public void testAddNotification() throws Exception {
        NotificationsActivity instance=new NotificationsActivity();
        NotificationsListItem item1=new NotificationsListItem("foo","bar");
        instance.addNotification(item1);
        List<NotificationsListItem> list=new ArrayList<NotificationsListItem>();
        list=instance.getNotificationsItemsList();
        assertEquals(list.get(0),item1);
    }

    @Test
    public void testAddNullNotification() throws Exception {
        NotificationsActivity instance=new NotificationsActivity();
        NotificationsListItem item1=null;
        instance.addNotification(item1);
        List<NotificationsListItem> list=new ArrayList<NotificationsListItem>();
        list=instance.getNotificationsItemsList();
        if(list.get(0)==null){
            fail("Failed to treat null Notification");
        }

    }


    @Test
    public void testRemoveSeenNotifications() throws Exception {
        NotificationsActivity instance=new NotificationsActivity();
        NotificationsListItem item1=new NotificationsListItem("foo","bar");
        NotificationsListItem item2=new NotificationsListItem("foo","baz");
        instance.addNotification(item1);
        instance.addNotification(item2);
        instance.getNotificationsItemsList().get(0).setSeen(true);
        instance.removeSeenNotifications();
        assertTrue(instance.getNotificationsItemsList().size()==1);

    }


    @Test
    public void testSetNotificationsItemsList() throws Exception {

        NotificationsActivity instance=new NotificationsActivity();
        List<NotificationsListItem> list=new ArrayList<NotificationsListItem>();
        NotificationsListItem item1=new NotificationsListItem("foo","bar");
        NotificationsListItem item2=new NotificationsListItem("foo","baz");
        list.add(item1);
        list.add(item2);
        instance.setNotificationsItemsList(list);
        assertEquals(instance.getNotificationsItemsList(),list);

    }

    @Test
    public void testSetListView() throws Exception {
        NotificationsActivity instance=new NotificationsActivity();
        ListView listView;
        listView = new ListView(null);
        instance.setListView(listView);
        assertEquals(listView,instance.getListView());

    }
}