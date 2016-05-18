package com.example.mihaela.smarthouse.command_center;

import com.example.mihaela.smarthouse.smart_unit.ASmartUnit;

/**
 * Created by Mihaela on 24.04.2016.
 */
public interface CmdListItem {

    public String getTitle();

    public void setTitle(String newTitle);

    public String getStatus();

    public void setStatus(String newStatus);
<<<<<<< HEAD
    public void setId(String id);
    public String getId();

    public ASmartUnit getSmartUnit();
    public void setSmartUnit(ASmartUnit unit);
=======

    public void setIndex(int index);
>>>>>>> origin/master
}