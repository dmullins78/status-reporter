package com.mullco.informationater;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.junit.Test;

import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class PowerPointMakerTest {

    @Test
    public void shouldMakeCompletedSection() throws Exception {
        List<WorkItem> items = new ArrayList<>();
        items.add(new WorkItem("1", "Completed 1 and a bunch of other really long text that does not need to be this long", "CID", "Other", null, false, "story", null, null, 1.0));
        items.add(new WorkItem("2", "Completed 2 \n Still Completing", "MFS", "Deposits", null, false, "story", null, null, 1.0));

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
