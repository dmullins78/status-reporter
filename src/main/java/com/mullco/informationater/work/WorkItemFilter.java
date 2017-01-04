package com.mullco.informationater.work;

import com.mullco.informationater.jira.WorkItem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

public class WorkItemFilter {

    public static List<WorkItem> getCompleted(List<WorkItem> workItems) {
        return workItems.stream()
                    .filter(t -> t.getCompletionDate().isBefore(now()))
                    .sorted((o1, o2) -> o1.getDepValue().compareTo(o2.getDepValue())).collect(toList());
    }

    public static List<WorkItem> getBacklog(List<WorkItem> workItems) {
        return workItems.stream()
                    .filter(t -> !t.isInProgress() && t.getCompletionDate().isAfter(now()))
                    .filter(t -> !t.isEpic())
                    .collect(toList());
    }

    public static List<WorkItem> getInProgress(List<WorkItem> workItems) {
        return workItems.stream().filter(WorkItem::isEpic).collect(toList());
    }

    public static Map<String, List<WorkItem>> groupWorkByArea(List<WorkItem> data) {
        Map<String, List<WorkItem>> workByArea = data.stream().collect(Collectors.groupingBy(WorkItem::getDepValue));

        for (List<WorkItem> workItems : workByArea.values()) {
            workItems.sort((o1, o2) -> o1.getPriority().compareTo(o2.getPriority()));
        }

        return workByArea;
    }

    public static Map<String, List<WorkItem>> groupWorkByProduct(String area, List<WorkItem> data) {
        Map<String, List<WorkItem>> result = new LinkedHashMap<>();

        List<WorkItem> workByArea = getWorkByArea(area, data);

        workByArea.stream()
                .collect(Collectors.groupingBy(WorkItem::getProductValue))
                .entrySet().stream()
                .sorted((o1, o2) -> o1.getKey().toLowerCase().compareTo(o2.getKey().toLowerCase()))
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;
    }

    public static List<WorkItem> getWorkByArea(String area, List<WorkItem> data) {
        Map<String, List<WorkItem>> workByArea = data.stream().collect(Collectors.groupingBy(WorkItem::getDepValue));

        List<WorkItem> workItems = workByArea.get(area);
        workItems.sort((o1, o2) -> o1.getSummary().compareTo(o2.getSummary()));

        return workItems;
    }

}