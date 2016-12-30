package com.mullco.informationater;

import java.util.List;

public class MagicMaker {

    JiraAdapter adapter;
    WorkItemSorter workItemSorter;
    PowerPointMaker powerPointMaker;

    public MagicMaker() {
        adapter = new JiraAdapter();
        workItemSorter = new WorkItemSorter();
        powerPointMaker = new PowerPointMaker();
    }

    public void doIt() {
        List<WorkItem> stuff = adapter.getStuff();

        MonthlyStats monthlyStats = workItemSorter.sortWorkItems(stuff);

        powerPointMaker.makeStuff(monthlyStats, "hslf-table-2.ppt");
    }
}
