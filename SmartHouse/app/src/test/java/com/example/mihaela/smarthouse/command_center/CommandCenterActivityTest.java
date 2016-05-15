package com.example.mihaela.smarthouse.command_center;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by silviu on 10-May-16.
 */
public class CommandCenterActivityTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }




    @Test
    public void testSetExpAdapter() throws Exception {
        //Andrei
        System.out.println("setExpAdapter");
        CmdListAdapter newAdapter = null;
        CommandCenterActivity instance = new CommandCenterActivity();
        instance.setExpAdapter(newAdapter);
        CmdListAdapter result;
        result=instance.getExpAdapter();
        if(result==null)
            fail("The null test was not passed.");
    }


    @Test
    public void testGetExpAdapterBeforeSet() throws Exception {
        System.out.println("getExpAdapterBeforeSet");
        CommandCenterActivity instance = new CommandCenterActivity();
        CmdListAdapter result = instance.getExpAdapter();
        // TODO review the generated test code and remove the default call to fail.
        if(result!=null)
            fail("The ExpAdapter should be null");
    }

    @Test
    public void testGetExpAdapterAfterSet() throws Exception {
        System.out.println("getExpAdapterAfterSet");
        CommandCenterActivity instance = new CommandCenterActivity();
        CmdListAdapter expected = new CmdListAdapter(instance, instance.getExpListItems());
        instance.setExpAdapter(expected);
        CmdListAdapter result = instance.getExpAdapter();
        // TODO review the generated test code and remove the default call to fail.
        assertEquals("The result should be equal.",expected, result);
    }

    @Test
    public void testGetExpListItemsBeforeSet() throws Exception {
        System.out.println("getExpListItems");
        CommandCenterActivity instance = new CommandCenterActivity();
        ArrayList<CmdListHeader> expResult = null;
        ArrayList<CmdListHeader> result = instance.getExpListItems();
        assertEquals("Result should be NULL",expResult, result);
    }

    @Test
    public void testGetExpListItemsAfterSet() throws Exception {
        System.out.println("getIndexOfDuplicateItem");
        CmdListHeader item1 = new CmdListHeader();
        CmdListHeader item2 = new CmdListHeader();
        CommandCenterActivity instance = new CommandCenterActivity();
        ArrayList<CmdListItem> list1 = new ArrayList<CmdListItem>();
        ArrayList<CmdListItem> list2 = new ArrayList<CmdListItem>();
        CmdButtonListItem listItem1 = new CmdButtonListItem();
        CmdButtonListItem listItem2 = new CmdButtonListItem();
        ArrayList<CmdListHeader> items = new ArrayList<CmdListHeader>();
        listItem1.setTitle("Test1");
        listItem1.setStatus("Test1");
        listItem2.setTitle("Test2");
        listItem2.setStatus("Test2");
        list1.add(listItem1);
        list2.add(listItem1);
        list2.add(listItem2);
        item1.setName("Item1");
        item1.setItems(list1);
        item2.setName("Item2");
        item2.setItems(list2);
        items.add(item1);
        items.add(item2);
        items.add(item1);
        instance.setExpListItems(items);
        ArrayList<CmdListHeader> result = instance.getExpListItems();
        assertEquals("Index not correct.",items, result);
    }

    @Test
    public void testGetItem() throws Exception {
        System.out.println("getItem");
        ArrayList<CmdListItem> items = new ArrayList<CmdListItem>();
        ArrayList<CmdListHeader> newItems = new ArrayList<CmdListHeader>();
        CmdListHeader expected = new CmdListHeader();
        CmdListHeader result=null;
        CmdButtonListItem item = new CmdButtonListItem();
        item.setTitle("test");
        item.setStatus("test");
        items.add(item);
        expected.setName("expected");
        expected.setItems(items);
        newItems.add(expected);
        int index = 0;
        CommandCenterActivity instance = new CommandCenterActivity();
        instance.setExpListItems(newItems);
        result=instance.getItem(index);
        if(expected!=result)
            fail("Result doesn't match.");
    }

    @Test
    public void testGetItemOutOfBounds() throws Exception {
        System.out.println("getItemOutOfBounds");
        ArrayList<CmdListItem> items = new ArrayList<CmdListItem>();
        ArrayList<CmdListHeader> newItems = new ArrayList<CmdListHeader>();
        CmdListHeader expected = new CmdListHeader();
        CmdListHeader result=null;
        CmdButtonListItem item = new CmdButtonListItem();
        item.setTitle("test");
        item.setStatus("test");
        items.add(item);
        expected.setName("expected");
        expected.setItems(items);
        newItems.add(expected);
        int index = 3;
        CommandCenterActivity instance = new CommandCenterActivity();
        instance.setExpListItems(newItems);
        result=instance.getItem(index);
        if(result!=null)
            fail("Result doesn't match.");
    }

    @Test
    public void testGetItemNegativeIndex() throws Exception {
        System.out.println("getItemNegativeIndex");
        ArrayList<CmdListItem> items = new ArrayList<CmdListItem>();
        ArrayList<CmdListHeader> newItems = new ArrayList<CmdListHeader>();
        CmdListHeader expected = new CmdListHeader();
        CmdListHeader result=null;
        CmdButtonListItem item = new CmdButtonListItem();
        item.setTitle("test");
        item.setStatus("test");
        items.add(item);
        expected.setName("expected");
        expected.setItems(items);
        newItems.add(expected);
        int index = -1;
        CommandCenterActivity instance = new CommandCenterActivity();
        instance.setExpListItems(newItems);
        result=instance.getItem(index);
        if(result!=null)
            fail("Result doesn't match.");
    }

    @Test
    public void testGetItems() throws Exception {
        System.out.println("getItems");
        int begin = 0;
        int end = 2;
        CommandCenterActivity instance = new CommandCenterActivity();
        ArrayList<CmdListItem> items = new ArrayList<CmdListItem>();
        CmdButtonListItem item = new CmdButtonListItem();
        CmdListHeader header = new CmdListHeader();
        ArrayList<CmdListHeader> expResult = new ArrayList<CmdListHeader>();
        item.setTitle("Test1");
        item.setStatus("Test1");
        items.add(item);
        header.setName("Element1");
        header.setItems(items);
        expResult.add(header);
        item.setTitle("Test2");
        item.setStatus("Test2");
        items.add(item);
        header.setName("Element2");
        header.setItems(items);
        expResult.add(header);
        instance.setExpListItems(expResult);
        ArrayList<CmdListHeader> result = instance.getItems(begin, end);
        assertEquals("Lists not equal.",expResult, result);
    }

    @Test
    public void testGetItemsIndexOOB() throws Exception {
        System.out.println("getItemsIndexOOB");
        int begin = 0;
        int end = 5;
        CommandCenterActivity instance = new CommandCenterActivity();
        ArrayList<CmdListItem> items = new ArrayList<CmdListItem>();
        CmdButtonListItem item = new CmdButtonListItem();
        CmdListHeader header = new CmdListHeader();
        ArrayList<CmdListHeader> expResult = new ArrayList<CmdListHeader>();
        item.setTitle("Test1");
        item.setStatus("Test1");
        items.add(item);
        header.setName("Element1");
        header.setItems(items);
        expResult.add(header);
        item.setTitle("Test2");
        item.setStatus("Test2");
        items.add(item);
        header.setName("Element2");
        header.setItems(items);
        expResult.add(header);
        instance.setExpListItems(expResult);
        ArrayList<CmdListHeader> result = instance.getItems(begin, end);
        assertEquals("Lists not equal.",expResult, result);
    }

    @Test
    public void testGetItemsIndexReversed() throws Exception {
        System.out.println("getItemsIndexReversed");
        int begin = 2;
        int end = 0;
        CommandCenterActivity instance = new CommandCenterActivity();
        ArrayList<CmdListItem> items = new ArrayList<CmdListItem>();
        CmdButtonListItem item = new CmdButtonListItem();
        CmdListHeader header = new CmdListHeader();
        ArrayList<CmdListHeader> expResult = new ArrayList<CmdListHeader>();
        item.setTitle("Test1");
        item.setStatus("Test1");
        items.add(item);
        header.setName("Element1");
        header.setItems(items);
        expResult.add(header);
        item.setTitle("Test2");
        item.setStatus("Test2");
        items.add(item);
        header.setName("Element2");
        header.setItems(items);
        expResult.add(header);
        instance.setExpListItems(expResult);
        ArrayList<CmdListHeader> result = instance.getItems(begin, end);
        assertEquals("Lists not equal.",expResult, result);
    }

    @Test
    public void testGetItemsMidStart() throws Exception {
        System.out.println("getItemsMidStart");
        int begin = 1;
        int end = 2;
        CommandCenterActivity instance = new CommandCenterActivity();
        ArrayList<CmdListItem> items = new ArrayList<CmdListItem>();
        CmdButtonListItem item = new CmdButtonListItem();
        CmdListHeader header = new CmdListHeader();
        ArrayList<CmdListHeader> expResult = new ArrayList<CmdListHeader>();
        ArrayList<CmdListHeader> expected = new ArrayList<CmdListHeader>();
        item.setTitle("Test1");
        item.setStatus("Test1");
        items.add(item);
        header.setName("Element1");
        header.setItems(items);
        expResult.add(header);
        item.setTitle("Test2");
        item.setStatus("Test2");
        items.add(item);
        header.setName("Element2");
        header.setItems(items);
        expResult.add(header);
        instance.setExpListItems(expResult);
        expected.add(header);
        ArrayList<CmdListHeader> result = instance.getItems(begin, end);
        assertEquals("Lists not equal.",expected, result);
    }

    @Test
    public void testGetIndexOf() throws Exception {
        System.out.println("getIndexOf");
        CmdListHeader item1 = new CmdListHeader();
        CmdListHeader item2 = new CmdListHeader();
        CommandCenterActivity instance = new CommandCenterActivity();
        ArrayList<CmdListItem> list1 = new ArrayList<CmdListItem>();
        ArrayList<CmdListItem> list2 = new ArrayList<CmdListItem>();
        CmdButtonListItem listItem1 = new CmdButtonListItem();
        CmdButtonListItem listItem2 = new CmdButtonListItem();
        ArrayList<CmdListHeader> items = new ArrayList<CmdListHeader>();
        listItem1.setTitle("Test1");
        listItem1.setStatus("Test1");
        listItem2.setTitle("Test2");
        listItem2.setStatus("Test2");
        list1.add(listItem1);
        list2.add(listItem1);
        list2.add(listItem2);
        item1.setName("Item1");
        item1.setItems(list1);
        item2.setName("Item2");
        item2.setItems(list2);
        items.add(item1);
        items.add(item2);
        instance.setExpListItems(items);
        int expResult = 0;
        int result = instance.getIndexOf(item1);
        assertEquals("Index not correct.",expResult, result);
    }

    @Test
    public void testGetIndexOfItemNonExisting() throws Exception {
        System.out.println("getIndexOfItemNonExisting");
        CmdListHeader item1 = new CmdListHeader();
        CmdListHeader item2 = new CmdListHeader();
        CmdListHeader item3 = new CmdListHeader();
        CommandCenterActivity instance = new CommandCenterActivity();
        ArrayList<CmdListItem> list1 = new ArrayList<CmdListItem>();
        ArrayList<CmdListItem> list2 = new ArrayList<CmdListItem>();
        CmdButtonListItem listItem1 = new CmdButtonListItem();
        CmdButtonListItem listItem2 = new CmdButtonListItem();
        ArrayList<CmdListHeader> items = new ArrayList<CmdListHeader>();
        listItem1.setTitle("Test1");
        listItem1.setStatus("Test1");
        listItem2.setTitle("Test2");
        listItem2.setStatus("Test2");
        list1.add(listItem1);
        list2.add(listItem1);
        list2.add(listItem2);
        item1.setName("Item1");
        item1.setItems(list1);
        item2.setName("Item2");
        item2.setItems(list2);
        item3.setName("Item3");
        item3.setItems(list1);
        items.add(item1);
        items.add(item2);
        instance.setExpListItems(items);
        int expResult = -1;
        int result = instance.getIndexOf(item3);
        assertEquals("Index not correct.",expResult, result);
    }

    @Test
    public void testGetIndexOfDuplicateItem() throws Exception {
        System.out.println("getIndexOfDuplicateItem");
        CmdListHeader item1 = new CmdListHeader();
        CmdListHeader item2 = new CmdListHeader();
        CommandCenterActivity instance = new CommandCenterActivity();
        ArrayList<CmdListItem> list1 = new ArrayList<CmdListItem>();
        ArrayList<CmdListItem> list2 = new ArrayList<CmdListItem>();
        CmdButtonListItem listItem1 = new CmdButtonListItem();
        CmdButtonListItem listItem2 = new CmdButtonListItem();
        ArrayList<CmdListHeader> items = new ArrayList<CmdListHeader>();
        listItem1.setTitle("Test1");
        listItem1.setStatus("Test1");
        listItem2.setTitle("Test2");
        listItem2.setStatus("Test2");
        list1.add(listItem1);
        list2.add(listItem1);
        list2.add(listItem2);
        item1.setName("Item1");
        item1.setItems(list1);
        item2.setName("Item2");
        item2.setItems(list2);
        items.add(item1);
        items.add(item2);
        items.add(item1);
        instance.setExpListItems(items);
        int expResult = 2;
        int result = instance.getIndexOf(item1);
        assertEquals("Index not correct.",expResult, result);
    }
}