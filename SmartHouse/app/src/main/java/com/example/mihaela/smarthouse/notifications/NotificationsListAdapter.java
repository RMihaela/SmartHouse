package com.example.mihaela.smarthouse.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mihaela.smarthouse.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silviu on 27-Apr-16.
 */
public class NotificationsListAdapter extends BaseAdapter {

    private Context context;
    private List<NotificationsListItem> notificationsItemsList=new ArrayList<>();
    private static LayoutInflater inflater=null;

    public NotificationsListAdapter(Context context, List<NotificationsListItem> notificationsList ){

        this.setContext(context);
        this.getNotificationsItemsList().addAll(notificationsList);
        this.setInflater((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));


    }

    public static LayoutInflater getInflater() {
        return inflater;
    }

    public static void setInflater(LayoutInflater inflater) {
        NotificationsListAdapter.inflater = inflater;
    }

    @Override
    public int getCount() {
       return this.getNotificationsItemsList().size();
    }

    @Override
    public Object getItem(int position) {
       return this.getNotificationsItemsList().get(position);

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view= this.getInflater().inflate(R.layout.notification_list_item,null);
        }
        NotificationsListItem notifcationItem=notificationsItemsList.get(position);

        TextView  type= (TextView)view.findViewById(R.id.type);
        type.setText(notifcationItem.getType());

        TextView  message= (TextView)view.findViewById(R.id.message);
        message.setText(notifcationItem.getType());

        return view;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<NotificationsListItem> getNotificationsItemsList() {
        return notificationsItemsList;
    }

    public void setNotificationsItemsList(List<NotificationsListItem> notificationsItemsList) {
        this.notificationsItemsList = notificationsItemsList;
    }
}
