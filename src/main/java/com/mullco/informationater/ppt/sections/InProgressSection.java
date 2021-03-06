package com.mullco.informationater.ppt.sections;

import com.mullco.informationater.jira.WorkItem;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFTable;

import java.util.List;

import static com.mullco.informationater.work.WorkItemFilter.getInProgressEnhancements;
import static com.mullco.informationater.work.WorkItemFilter.getInProgressSignificantEfforts;

public class InProgressSection extends PowerPointSection {

    Integer getLeftStartPosition() {
        return 410;
    }

    Integer getHeightStartPosition() {
        return 0;
    }

    String getSectionHeaderName() {
        return null;
    }

    HSLFTable populateTable(List<WorkItem> data, HSLFSlide slide) {
        HSLFTable table = slide.createTable(data.size() + 2, 3);
        table.setColumnWidth(0, 100);
        table.setColumnWidth(1, 130);
        table.setColumnWidth(2, 370);

        cell.makeHeaderCell(table, 0, 0, "Area");
        cell.makeHeaderCell(table, 0, 1, "People");
        cell.makeHeaderCell(table, 0, 2, "In-Progress");

        List<WorkItem> sigEfforts = getInProgressSignificantEfforts(data);
        addSignificantEfforts(sigEfforts, table);

        List<WorkItem> enhancements = getInProgressEnhancements(data);
        addEnhancements(enhancements, table, sigEfforts.size());

        return table;
    }

    private void addSignificantEfforts(List<WorkItem> data, HSLFTable table) {
        for (int i = 0; i < data.size(); i++) {
            WorkItem workItem = data.get(i);
            cell.makeBoldCell(table, i + 1, 0, workItem.getSummary());
            cell.makeCell(table, i + 1, 1, workItem.getPeople());
            cell.makeCell(table, i + 1, 2, workItem.getDescription());
        }
    }

    private void addEnhancements(List<WorkItem> data, HSLFTable table, int offset) {
        for (int i = 0; i < data.size(); i++) {
            WorkItem workItem = data.get(i);
            cell.makeBoldCell(table, offset + i + 1, 0, "Enh");
            cell.makeCell(table, offset + i + 1, 1, "All");
            cell.makeCell(table, offset + i + 1, 2, workItem.getSummary());
        }
    }

}
