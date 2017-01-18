package com.mullco.informationater.job;

import com.mullco.informationater.jira.WorkItem;

import java.util.List;

import static com.mullco.informationater.work.WorkItemFilter.asSignificantEfforts;
import static com.mullco.informationater.work.WorkItemFilter.getInProgressEnhancementsAndMaintenance;
import static com.mullco.informationater.work.WorkItemFilter.getInProgressSignificantEfforts;

public class EmailDigest {

    List<WorkItem> getInProgressItems(List<WorkItem> workItems) {
        List<WorkItem> ipSigEfforts = getInProgressSignificantEfforts(workItems);

        List<WorkItem> ipEnhancements = asSignificantEfforts(getInProgressEnhancementsAndMaintenance(workItems));
        ipSigEfforts.addAll(ipEnhancements);

        return ipSigEfforts;
    }

}
