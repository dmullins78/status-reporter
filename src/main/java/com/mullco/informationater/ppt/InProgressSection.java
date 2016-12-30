package com.mullco.informationater.ppt;

import com.mullco.informationater.WorkItem;
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
        table.setColumnWidth(0, 50);
        table.setColumnWidth(1, 125);
        table.setColumnWidth(2, 435);

        cell.makeCell(table, 0, 0, "Area");
        cell.makeCell(table, 0, 1, "People");
        cell.makeCell(table, 0, 2, "");

        for (int i = 0; i < data.size(); i++) {
            WorkItem workItem = data.get(i);
            java.util.List<String> people = workItem.getPeople();

            cell.makeCell(table, i + 1, 0, workItem.getDepValue());
            cell.makeCell(table, i + 1, 1, String.join(",", people));
            cell.makeCell(table, i + 1, 2, workItem.getSummary());
        }

        return table;
    }

}
