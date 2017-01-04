package com.mullco.informationater.ppt.sections;

import com.mullco.informationater.jira.WorkItem;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFTable;

import java.util.List;

public class InProgressSection extends PowerPointSection {

    Integer getLeftStartPosition() {
        return 400;
    }

    Integer getHeightStartPosition() {
        return 0;
    }

    String getSectionHeaderName() {
        return "In-Progress";
    }

    HSLFTable populateTable(List<WorkItem> data, HSLFSlide slide) {
        HSLFTable table = slide.createTable(data.size() + 1, 3);
        table.setColumnWidth(0, 100);
        table.setColumnWidth(1, 130);
        table.setColumnWidth(2, 380);

        cell.makeHeaderCell(table, 0, 0, "Area");
        cell.makeHeaderCell(table, 0, 1, "People");
        cell.makeHeaderCell(table, 0, 2, "");

        for (int i = 0; i < data.size(); i++) {
            WorkItem workItem = data.get(i);
            cell.makeBoldCell(table, i + 1, 0, workItem.getSummary());
            cell.makeCell(table, i + 1, 1, workItem.getPeople());
            cell.makeCell(table, i + 1, 2, workItem.getDescription());
        }

        return table;
    }

}
