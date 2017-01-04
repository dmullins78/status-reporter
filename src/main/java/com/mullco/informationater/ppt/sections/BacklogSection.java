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
        return 430;
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
        if (workItems != null && workItems.size() >= topThreeCounter) {
            WorkItem workItem = workItems.get(topThreeCounter - 1);
            cell.makeCell(table, topThreeCounter, areaCounter, workItem.getSummary());
        } else {
            cell.makeCell(table, topThreeCounter, areaCounter, "");
        }
    }

}
