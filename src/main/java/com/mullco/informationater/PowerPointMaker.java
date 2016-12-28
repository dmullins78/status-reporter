package com.mullco.informationater;

import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.sl.draw.DrawTableShape;
import org.apache.poi.sl.usermodel.VerticalAlignment;

import java.awt.*;
import java.io.FileOutputStream;
import java.util.List;

public class PowerPointMaker {
    public void makeStuff(List<WorkItem> workItems) {
        String[][] data = {
                {"INPUT FILE", "NUMBER OF RECORDS"},
                {"Item File", "11,559"},
                {"Vendor File", "300"},
                {"Purchase History File", "10,000"},
                {"Total # of requisitions", "10,200,038"}
        };

        HSLFSlideShow ppt = new HSLFSlideShow();

        HSLFSlide slide = ppt.createSlide();

        HSLFTable table = makeTable(data, slide, "Alpha", false);
        slide.addShape(table);
        table.moveTo(10, 10);

        HSLFTable table2 = makeTable(data, slide, "Beta", false);
        slide.addShape(table2);
        table2.moveTo(190, 10);

        HSLFTable table3 = makeTable(data, slide, "Charlie", true);
        slide.addShape(table3);
        table3.moveTo(10, 150);

        int pgWidth = ppt.getPageSize().width;
//        table2.moveTo(pgWidth - 20, 10.);


        writeToFile(ppt);
    }

    private void writeToFile(HSLFSlideShow ppt) {
        try {
            FileOutputStream out = new FileOutputStream("hslf-table.ppt");
            ppt.write(out);
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private HSLFTable makeTable(String[][] data, HSLFSlide slide, String beta, boolean superBig) {
        HSLFTable table = slide.createTable(5, 2);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                HSLFTableCell cell = table.getCell(i, j);
                cell.setText(data[i][j] + " " + beta);

                HSLFTextRun rt = cell.getTextParagraphs().get(0).getTextRuns().get(0);
                rt.setFontFamily("Arial");
                rt.setFontSize(10.);

                cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
                cell.setHorizontalCentered(true);
            }
        }

        DrawTableShape dts1 = new DrawTableShape(table);
        dts1.setAllBorders(1.0, Color.black);

        //set width of the 1st column
        table.setColumnWidth(0, 100);
        //set width of the 2nd column

        int width = 225;
        if (superBig) {
            width = 590;
        }

        table.setColumnWidth(1, width);

        return table;
    }
}
