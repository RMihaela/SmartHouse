package com.example.mihaela.smarthouse.stats;

import android.content.Context;
import android.widget.ExpandableListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by silviu on 10-May-16.
 */
public class StatsActivityTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /*@Test
    public void testSetStandardGroups() throws Exception {
        StatsActivity instance = new StatsActivity();
        ArrayList<StatsListHeader> expResult = null;
        ArrayList<StatsListHeader> result = instance.SetStandardGroups();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    @Test
    public void testSetExpAdapter() throws Exception {
        StatsActivity instance = new StatsActivity();
        Context context = null;
        ArrayList<StatsListHeader>  ExpListItems = StatsActivity.SetStandardGroups();

        StatsListAdapter newAdapter = new StatsListAdapter(context, ExpListItems);

        instance.setExpAdapter(newAdapter);

        assertEquals(instance.getExpAdapter(), newAdapter);
    }

    @Test
    public void testSetExpListItems() throws Exception {
        StatsActivity instance = new StatsActivity();
        ArrayList<StatsListHeader> list = new ArrayList<StatsListHeader>();
        ArrayList<StatsListItem> ch_list = new ArrayList<StatsListItem>();
        StatsListHeader gru = new StatsListHeader();
        gru.setName("Group1");
        StatsListItem ch = new StatsListItem();
        ch.setTitle("title");
        ch.setStatus("status");
        ch_list.add(ch);
        gru.setItems(ch_list);
        list.add(gru);

        instance.setExpListItems(list);
        assertEquals(instance.getExpListItems(), list);
    }

    @Test
    public void testAddItem() throws Exception {
        StatsActivity instance = new StatsActivity();

        StatsListHeader gru = new StatsListHeader();
        ArrayList<StatsListItem> ch_list = new ArrayList<>();
        gru.setName("Group1");
        StatsListItem ch = new StatsListItem();
        ch.setTitle("title");
        ch.setStatus("status");
        ch_list.add(ch);
        gru.setItems(ch_list);

        instance.addItem(gru);
        assertEquals(instance.getItem(0), gru);
    }

    @Test
    public void testAddItemsList() throws Exception {
        StatsActivity instance = new StatsActivity();

        ArrayList<StatsListHeader> newListItems = new ArrayList<>();
        newListItems = instance.SetStandardGroups();

        instance.addItemsList(newListItems);

        assertEquals(instance.getExpListItems(), newListItems);
    }

    @Test
    public void testGetItem() throws Exception {
        StatsActivity instance = new StatsActivity();

        StatsListHeader gru = new StatsListHeader();
        ArrayList<StatsListItem> ch_list = new ArrayList<>();
        gru.setName("Group1");
        StatsListItem ch = new StatsListItem();
        ch.setTitle("title");
        ch.setStatus("status");
        ch_list.add(ch);
        gru.setItems(ch_list);

        instance.addItem(gru);
        assertEquals(instance.getItem(0), gru);
    }

    @Test
    public void testGetItemsList() throws Exception {
        StatsActivity instance = new StatsActivity();

        ArrayList<StatsListHeader> list = new ArrayList<StatsListHeader>();
        ArrayList<StatsListItem> ch_list = new ArrayList<StatsListItem>();
        StatsListHeader gru = new StatsListHeader();
        gru.setName("Group1");
        StatsListItem ch = new StatsListItem();
        ch.setTitle("title");
        ch.setStatus("status");
        ch_list.add(ch);
        gru.setItems(ch_list);
        list.add(gru);

        instance.setExpListItems(list);
        assertEquals(instance.getItemsList(0,1), list);
    }

    @Test
    public void testGetExpAdapter() throws Exception {
        StatsActivity instance = new StatsActivity();
        Context context = null;
        ArrayList<StatsListHeader>  ExpListItems = StatsActivity.SetStandardGroups();

        StatsListAdapter newAdapter = new StatsListAdapter(context, ExpListItems);

        instance.setExpAdapter(newAdapter);

        assertEquals(instance.getExpAdapter(), newAdapter);
    }

    @Test
    public void testGetExpListItems() throws Exception {
        StatsActivity instance = new StatsActivity();
        ArrayList<StatsListHeader> list = new ArrayList<StatsListHeader>();
        ArrayList<StatsListItem> ch_list = new ArrayList<StatsListItem>();
        StatsListHeader gru = new StatsListHeader();
        gru.setName("Group1");
        StatsListItem ch = new StatsListItem();
        ch.setTitle("title");
        ch.setStatus("status");
        ch_list.add(ch);
        gru.setItems(ch_list);
        list.add(gru);

        instance.setExpListItems(list);
        assertEquals(instance.getExpListItems(), list);
    }
}