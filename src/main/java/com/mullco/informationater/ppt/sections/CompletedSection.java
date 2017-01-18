package com.mullco.informationater.ppt.sections;

import com.mullco.informationater.jira.WorkItem;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFTable;

import java.util.List;

public class CompletedSection extends PowerPointSection {

    Integer getLeftStartPosition() {
        return 10;
    }

    Integer getHeightStartPosition() {
        return 0;
    }

    String getSectionHeaderName() {
        return null;
    }

    HSLFTable populateTable(List<WorkItem> data, HSLFSlide slide) {
        HSLFTable table = slide.createTable(data.size() + 1, 2);
        table.setColumnWidth(0, 50);
        table.setColumnWidth(1, 325);

        cell.makeHeaderCell(table, 0, 0, "Area");
        cell.makeHeaderCell(table, 0, 1, "Completed");

        for (int i = 0; i < data.size(); i++) {
            WorkItem workItem = data.get(i);
            cell.makeBoldCell(table, i + 1, 0, workItem.getDepValue());
            cell.makeCell(table, i + 1, 1, workItem.getSummary());
        }

        return table;
    }

}
