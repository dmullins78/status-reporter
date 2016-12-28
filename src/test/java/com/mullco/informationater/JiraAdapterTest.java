package com.mullco.informationater;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class JiraAdapterTest {

    @Test
    public void testSomeStuff() {
        JiraAdapter jiraAdapter = new JiraAdapter();

        List<WorkItem> workItems = jiraAdapter.getStuff();
        System.out.println("workItems.size() = " + workItems.size());

        for (WorkItem workItem : workItems) {
            System.out.println("workItem = " + workItem);
        }
    }
}
