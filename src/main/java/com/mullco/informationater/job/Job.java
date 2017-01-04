package com.mullco.informationater.job;

import com.mullco.informationater.jira.WorkItem;

import java.util.List;

public interface Job {

    String getName();
    int execute(List<WorkItem> workItems);

}
