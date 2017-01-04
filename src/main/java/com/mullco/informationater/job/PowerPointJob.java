package com.mullco.informationater.job;

import com.mullco.informationater.jira.WorkItem;
import com.mullco.informationater.ppt.PowerPointMaker;

import java.util.List;

public class PowerPointJob implements Job {

    private final PowerPointMaker powerPointMaker;

    public PowerPointJob(PowerPointMaker powerPointMaker) {
        this.powerPointMaker = powerPointMaker;
    }

    public String getName() {
        return "powerpoints";
    }

    public int execute(List<WorkItem> workItems) {
        powerPointMaker.makeStuff(workItems, "hslf-table-2.ppt");

        return 0;
    }

}
