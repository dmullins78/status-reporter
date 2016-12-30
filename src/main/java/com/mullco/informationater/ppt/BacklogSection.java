package com.mullco.informationater.ppt;

import com.mullco.informationater.WorkItem;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFTable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BacklogSection extends PowerPointSection {

    public static final String[] MEMBERS_AREAS = new String[]{"CID", "Marketing", "MFS", "MPG", "Solutions"};

    Integer getLeftStartPosition() {
        return 10;
    }

    Integer getHeightStartPosition() {
        return 400;
    }

    String getSectionHeaderName() {
        return "Backlog";
    }

    HSLFTable populateTable(List<WorkItem> data, HSLFSlide slide) {
        Map<String, List<WorkItem>> itemsByArea = groupWorkByArea(data);

        HSLFTable table = slide.createTable(4, 5);
        for (int i = 0; i < MEMBERS_AREAS.length; i++) {
            cell.makeHeaderCell(table, 0, i, MEMBERS_AREAS[i]);
            table.setColumnWidth(i, 200);
        }

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

    private Map<String, List<WorkItem>> groupWorkByArea(List<WorkItem> data) {
        return data.stream().collect(Collectors.groupingBy(WorkItem::getDepValue));
    }
}
