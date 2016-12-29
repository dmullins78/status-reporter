package com.mullco.informationater;

import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.sl.draw.DrawTableShape;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class PowerPointMaker {
    public void makeStuff(MonthlyStats stats, String outputFile) {
        debug();

        HSLFSlideShow ppt = new HSLFSlideShow();
        ppt.setPageSize(new Dimension(1024, 768));

        HSLFSlide slide = ppt.createSlide();

        HSLFTextBox t1 = makeSectionHeader(slide, "Completed");
        t1.moveTo(10., 0);

        HSLFTable table = makeCompletedTable(stats.getCompleted(), slide);
        table.moveTo(10., 35.);

        double x = 400;

        HSLFTextBox t2 = makeSectionHeader(slide, "In-Progress");
        t2.moveTo(x, 0);

        HSLFTable inProgress = makeInProgressTable(stats.getInProgress(), slide);
        inProgress.moveTo(x, 35.);

        HSLFTextBox t3 = makeSectionHeader(slide, "Backlog");
        t3.moveTo(10., 400);

        HSLFTable backlogTable = makeBacklogTable(stats.getCompleted(), slide);
        backlogTable.moveTo(10., 435);

        writeToFile(ppt, outputFile);
    }

    private HSLFTextBox makeSectionHeader(HSLFSlide slide, String completed) {
        HSLFTextBox t1 = slide.createTextBox();
        t1.setText(completed);
        t1.setHorizontalCentered(false);
        t1.getTextParagraphs().get(0).getTextRuns().get(0).setFontSize(26.);
        t1.setAnchor(new Rectangle(200, 26));
        return t1;
    }

    private HSLFTable makeCompletedTable(java.util.List<WorkItem> data, HSLFSlide slide) {
        HSLFTable table = slide.createTable(data.size() + 1, 2);
        makeCell(table, 0, 0, "Area");
        makeCell(table, 0, 1, "");

        for (int i = 0; i < data.size(); i++) {
            WorkItem workItem = data.get(i);
            makeCell(table, i + 1, 0, workItem.getDepValue());
            makeCell(table, i + 1, 1, workItem.getSummary());
        }

        DrawTableShape dts1 = new DrawTableShape(table);
        dts1.setAllBorders(1.0, Color.white);
        table.setColumnWidth(0, 50);
        table.setColumnWidth(1, 325);

        return table;
    }

    private HSLFTable makeBacklogTable(java.util.List<WorkItem> data, HSLFSlide slide) {
        HSLFTable table = slide.createTable(4, 5);
        String[] areas = new String[]{"CID", "Marketing", "MFS", "MPG", "Solutions"};

        for (int i = 0; i < areas.length; i++) {
            makeCell(table, 0, i, areas[i]);
        }

        Map<String, java.util.List<WorkItem>> itemsByArea = data.stream().collect(Collectors.groupingBy(WorkItem::getDepValue));
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < areas.length; j++) {

                List<WorkItem> workItems = itemsByArea.get(areas[j]);
                if (workItems != null && workItems.size() >= i) {
                    WorkItem workItem = workItems.get(i - 1);
                    makeCell(table, i, j, workItem.getSummary());
                } else {
                    makeCell(table, i, j, "");
                }
            }
        }

        DrawTableShape dts1 = new DrawTableShape(table);
        dts1.setAllBorders(1.0, Color.white);
        for (int i = 0; i < areas.length; i++) {
            table.setColumnWidth(i, 200);
        }

        return table;
    }

    private HSLFTable makeInProgressTable(java.util.List<WorkItem> data, HSLFSlide slide) {
        HSLFTable table = slide.createTable(data.size() + 1, 3);
        makeCell(table, 0, 0, "Area");
        makeCell(table, 0, 1, "People");
        makeCell(table, 0, 2, "");

        for (int i = 0; i < data.size(); i++) {
            WorkItem workItem = data.get(i);
            java.util.List<String> people = workItem.getPeople();

            makeCell(table, i + 1, 0, workItem.getDepValue());
            makeCell(table, i + 1, 1, String.join(",", people));
            makeCell(table, i + 1, 2, workItem.getSummary());
        }

        DrawTableShape dts1 = new DrawTableShape(table);
        dts1.setAllBorders(1.0, Color.white);
        table.setColumnWidth(0, 50);
        table.setColumnWidth(1, 125);
        table.setColumnWidth(2, 435);

        return table;
    }

    private void makeCell(HSLFTable table, int rowIndex, int columnIndex, String textValue) {
        HSLFTableCell cell = table.getCell(rowIndex, columnIndex);
        cell.setText(textValue);

        if (rowIndex % 2 == 0) {
            cell.setFillColor(new Color(202, 211, 205));
        } else if (rowIndex % 2 != 0) {
            cell.setFillColor(new Color(230, 234, 231));
        }

        HSLFTextRun rt = cell.getTextParagraphs().get(0).getTextRuns().get(0);
        rt.setFontFamily("Calibri");
        rt.setFontSize(18.);

        if (columnIndex == 0) {
            rt.setBold(true);
        }

        if (rowIndex == 0) {
            cell.getFill().setForegroundColor(new Color(0, 105, 60));
            rt.setFontColor(Color.white);
            rt.setBold(true);
            cell.setHorizontalCentered(true);
        }

        cell.setVerticalAlignment(VerticalAlignment.TOP);
    }

    private void writeToFile(HSLFSlideShow ppt, String outputFile) {
        try {
            FileOutputStream out = new FileOutputStream(outputFile);
            ppt.write(out);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void debug() {
        try {
            XMLSlideShow ppt = new XMLSlideShow(new FileInputStream("/Users/dan/Desktop/members-reporting-2.pptx"));
            Dimension pageSize = ppt.getPageSize();
            System.out.println("pageSize = " + pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
