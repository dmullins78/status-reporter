package com.mullco.informationater;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class PowerPointMakerTest {

    @Test
    public void shouldMakeCompletedSection() throws Exception {
        List<WorkItem> items = new ArrayList<WorkItem>();
        items.add(new WorkItem("1", "Completed 1 and a bunch of other really long text that does not need to be this long", "CID", "Other", asList("Dan", "Bob")));
        items.add(new WorkItem("2", "Completed 2 \n Still Completing", "MFS", "Deposits", asList("Dan", "Bob")));

        MonthlyStats monthlyStats = new MonthlyStats(items, items, null);

        PowerPointMaker powerPointMaker = new PowerPointMaker();
        powerPointMaker.makeStuff(monthlyStats, "hslf-table.ppt");
    }

}
