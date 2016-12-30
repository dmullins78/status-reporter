package com.mullco.informationater;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.junit.Test;

import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class PowerPointMakerTest {

    @Test
    public void shouldMakeCompletedSection() throws Exception {
        List<WorkItem> items = new ArrayList<>();
        items.add(new WorkItem("1", "Completed 1 and a bunch of other really long text that does not need to be this long", "CID", "Other", asList("Dan", "Bob")));
        items.add(new WorkItem("2", "Completed 2 \n Still Completing", "MFS", "Deposits", asList("Dan", "Bob")));

        MonthlyStats monthlyStats = new MonthlyStats(items, items, items);

        PowerPointMaker powerPointMaker = new PowerPointMaker();
        powerPointMaker.makeStuff(monthlyStats, "hslf-table.ppt");
    }


    private void debug() {
        try {
            XMLSlideShow ppt = new XMLSlideShow(new FileInputStream("/Users/dan/Desktop/members-reporting-2.pptx"));
            Dimension pageSize = ppt.getPageSize();
            System.out.println("pageSize = " + pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
