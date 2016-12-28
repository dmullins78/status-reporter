package com.mullco.informationater;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PowerPointMakerTest {

    @Test
    public void shouldMakeStuff() throws Exception {
        List<WorkItem> workItems = new ArrayList<WorkItem>();
        workItems.add(new WorkItem("1", "Summ", "CID", "Other"));

        PowerPointMaker powerPointMaker = new PowerPointMaker();
//        PowerPointMakerTwo powerPointMaker = new PowerPointMakerTwo();
        powerPointMaker.makeStuff(workItems);

    }
}
