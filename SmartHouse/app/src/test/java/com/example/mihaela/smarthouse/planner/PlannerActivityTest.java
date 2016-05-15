package com.example.mihaela.smarthouse.planner;

import android.widget.ListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by silviu on 10-May-16.
 */
public class PlannerActivityTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testGetPlannerItemsList() throws Exception {

        PlannerActivity instance = new PlannerActivity();
        List<PlannerListItem> expResult = new ArrayList<>();
        PlannerListItem plan1 = new PlannerListItem("plan1",true);
        PlannerListItem plan2 = new PlannerListItem("plan2",true);
        PlannerListItem plan3 = new PlannerListItem("plan3",false);
        PlannerListItem plan4 = new PlannerListItem("plan4",true);
        expResult.add(plan1);
        expResult.add(plan2);
        expResult.add(plan3);
        expResult.add(plan4);

        instance.setPlannerItemsList(expResult);
        assertEquals(instance.getPlannerItemsList(), expResult);
    }

    @Test
    public void testSetPlannerItemsList() throws Exception {


        PlannerActivity instance = new PlannerActivity();
        List<PlannerListItem> expResult = new ArrayList<>();
        PlannerListItem plan1 = new PlannerListItem("plan1",true);
        PlannerListItem plan2 = new PlannerListItem("plan2",true);
        PlannerListItem plan3 = new PlannerListItem("plan3",false);
        PlannerListItem plan4 = new PlannerListItem("plan4",true);
        expResult.add(plan1);
        expResult.add(plan2);
        expResult.add(plan3);
        expResult.add(plan4);

        instance.setPlannerItemsList(expResult);
        assertEquals(instance.getPlannerItemsList(), expResult);
    }

    @Test
    public void testGetListView() throws Exception {
        PlannerActivity instance=new PlannerActivity();
        ListView expResult;
        expResult = new ListView(null);

        instance.setListView(expResult);
        assertEquals(expResult,instance.getListView());
    }

    @Test
    public void testSetListView() throws Exception {
        PlannerActivity instance=new PlannerActivity();
        ListView expResult;
        expResult = new ListView(null);
        instance.setListView(expResult);
        assertEquals(expResult,instance.getListView());
    }

    @Test
    public void testAddPlanerItem() throws Exception {
        PlannerActivity instance = new PlannerActivity();
        PlannerListItem plan1 = new PlannerListItem("plan1",true);
        instance.addPlanerItem(plan1);
        assertEquals(instance.getPlannerItemsList().get(0),plan1);
    }
}