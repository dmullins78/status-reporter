package com.mullco.informationater.ppt.sections;

import com.mullco.informationater.jira.WorkItem;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFTable;

import java.util.List;
import java.util.Map;

import static com.mullco.informationater.work.WorkItemFilter.groupWorkByArea;

public class BacklogSection extends PowerPointSection {
    public static final String[] MEMBERS_AREAS = new String[]{"CID", "Marketing", "MFS", "MPG", "Solutions"};
    public static final int COLUMN_WIDTH = 200;

    Integer getLeftStartPosition() {
        return 10;
    }

    Integer getHeightStartPosition() {
        return 445;
    }

    String getSectionHeaderName() {
        return "Backlog";
    }

    HSLFTable populateTable(List<WorkItem> data, HSLFSlide slide) {
        HSLFTable table = slide.createTable(4, 5);
        for (int i = 0; i < MEMBERS_AREAS.length; i++) {
            cell.makeHeaderCell(table, 0, i, MEMBERS_AREAS[i]);
            table.setColumnWidth(i, COLUMN_WIDTH);
        }

        Map<String, List<WorkItem>> itemsByArea = groupWorkByArea(data);
        for (int topThreeCounter = 1; topThreeCounter <= 3; topThreeCounter++) {
            for (int areaCounter = 0; areaCounter < MEMBERS_AREAS.length; areaCounter++) {
                List<WorkItem> workItems = itemsByArea.get(MEMBERS_AREAS[areaCounter]);
                makeCell(table, topThreeCounter, areaCounter, workItems);
            }
        }

        return table;
    }

    private void makeCell(HSLFTable table, int topThreeCounter, int areaCounter, List<WorkItem> workItems) {
        String summary = "";

        if (areaHasEnoughWork(topThreeCounter, workItems)) {
            WorkItem workItem = workItems.get(topThreeCounter - 1);
            summary = getSummaryText(workItem);
        }

        cell.makeCell(table, topThreeCounter, areaCounter, summary);
    }

    private boolean areaHasEnoughWork(int topThreeCounter, List<WorkItem> workItems) {
        return workItems != null && workItems.size() >= topThreeCounter;
    }

    private String getSummaryText(WorkItem workItem) {
        String summary = workItem.getSummary();

        if (isAlreadyPrioritized(workItem)) {
            summary = summary + "*";
        }

        return summary;
    }

    private boolean isAlreadyPrioritized(WorkItem workItem) {
        return workItem.getDepartmentPriority() > 0;
    }

}
