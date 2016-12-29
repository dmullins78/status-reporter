package com.mullco.informationater;

import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.sl.draw.DrawTableShape;
import org.apache.poi.sl.usermodel.VerticalAlignment;

import java.awt.*;
import java.io.FileOutputStream;
import java.util.*;

/*
Row:
R 202
G 211
B 205

*/

public class PowerPointMaker {
    public void makeStuff(MonthlyStats stats, String outputFile) {
//        String[][] data = {
//                {"INPUT FILE", "NUMBER OF RECORDS"},
//                {"Item File", "11,559"},
//                {"Vendor File", "300"},
//                {"Purchase History File", "10,000"},
//                {"Total # of requisitions", "10,200,038"}
//        };

        HSLFSlideShow ppt = new HSLFSlideShow();

        HSLFSlide slide = ppt.createSlide();

        HSLFTextBox t1 = slide.createTextBox();
        t1.setText("Completed");
        t1.setHorizontalCentered(false);
        t1.getTextParagraphs().get(0).getTextRuns().get(0).setFontSize(20.);
        t1.setAnchor(new Rectangle(200, 20));
        t1.moveTo(10., 0);

        HSLFTable table = makeCompletedTable(stats.getCompleted(), slide);
        table.moveTo(10., 28.);

        int pgWidth = ppt.getPageSize().width;

        double x = pgWidth * .42;

        HSLFTextBox t2 = slide.createTextBox();
        t2.setText("In-Progress");
        t2.setHorizontalCentered(false);
        t2.getTextParagraphs().get(0).getTextRuns().get(0).setFontSize(20.);
        t2.setAnchor(new Rectangle(200, 20));
        t2.moveTo(x, 0);

        HSLFTable inProgress = makeInProgressTable(stats.getInProgress(), slide);
        inProgress.moveTo(x, 28.);

        int pgHeight = ppt.getPageSize().height;
//
//        double y = pgHeight / 2.;
//
//        HSLFTextBox t3 = slide.createTextBox();
//        t3.setText("BRA Bravos 4 Life");
//        t3.setHorizontalCentered(false);
//        t3.getTextParagraphs().get(0).getTextRuns().get(0).setFontSize(20.);
//        t3.setAnchor(new Rectangle(200, 20));
//        t3.moveTo(10., y);
//
//        HSLFTable table3 = makeCompletedTable(data, slide, "Charlie", true);
//        table3.moveTo(10., y + 25);

        writeToFile(ppt, outputFile);
    }

    private HSLFTable makeCompletedTable(java.util.List<WorkItem> data, HSLFSlide slide) {
        HSLFTable table = slide.createTable(data.size(), 2);

        for (int i = 0; i < data.size(); i++) {
            WorkItem workItem = data.get(i);
            makeCell(table, i, 0, workItem.getDepValue());
            makeCell(table, i, 1, workItem.getSummary());
        }

        DrawTableShape dts1 = new DrawTableShape(table);
        dts1.setAllBorders(1.0, Color.white);
        table.setColumnWidth(0, 50);
        table.setColumnWidth(1, 225);

        return table;
    }

    private HSLFTable makeInProgressTable(java.util.List<WorkItem> data, HSLFSlide slide) {
        HSLFTable table = slide.createTable(data.size(), 3);

        for (int i = 0; i < data.size(); i++) {
            WorkItem workItem = data.get(i);
            java.util.List<String> people = workItem.getPeople();

            makeCell(table, i, 0, workItem.getDepValue());
            makeCell(table, i, 1, String.join(",", people));
            makeCell(table, i, 2, workItem.getSummary());
        }

        DrawTableShape dts1 = new DrawTableShape(table);
        dts1.setAllBorders(1.0, Color.white);
        table.setColumnWidth(0, 50);
        table.setColumnWidth(1, 75);
        table.setColumnWidth(2, 275);

        return table;
    }

    private void makeCell(HSLFTable table, int i, int columnIndex, String textValue) {
        HSLFTableCell cell = table.getCell(i, columnIndex);
        cell.setText(textValue);

//                HSLFTableCell cell2 = table.getCell(i, 1);
//                cell2.setText(workItem.getSummary());

//                if (j == 0) {
//                    cell.setText("CID");
//                } else {
//                    cell.setText(data[i][j]);
//                }

        if (i % 2 == 0) {
            cell.setFillColor(new Color(202, 211, 205));
        } else if (i % 2 != 0) {
            cell.setFillColor(new Color(230, 234, 231));
        }
//
        HSLFTextRun rt = cell.getTextParagraphs().get(0).getTextRuns().get(0);
        rt.setFontFamily("Calibri");
        rt.setFontSize(18.);

        if (columnIndex == 0) {
            rt.setBold(true);
        }
//
//                if(i == 0){
//                    cell.getFill().setForegroundColor(new Color(0, 105, 60));
//                    rt.setFontColor(Color.white);
//                    rt.setBold(true);
//                    rt.setFontSize(14d);
//                }


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
}
