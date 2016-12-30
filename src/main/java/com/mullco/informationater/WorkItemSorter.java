package com.mullco.informationater;

import java.util.List;
import java.util.stream.Stream;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

public class WorkItemSorter {

    public MonthlyStats sortWorkItems(List<WorkItem> workItems) {
        Stream<WorkItem> stream = workItems.stream();

        List<WorkItem> completed = stream.filter(t -> t.getCompletionDate().isBefore(now())).collect(toList());
        List<WorkItem> inProgress = stream.filter(WorkItem::isInProgress).collect(toList());
        List<WorkItem> backlog = stream.filter(t -> !t.isInProgress() && t.getCompletionDate().isAfter(now())).collect(toList());

        return new MonthlyStats(completed, inProgress, backlog);
    }

}
