package com.mullco.informationater;

import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.sl.draw.DrawTableShape;
import org.apache.poi.sl.usermodel.VerticalAlignment;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.util.List;

/*
Row:
R 202
G 211
B 205

*/

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

        HSLFTextBox t1 = slide.createTextBox();
        t1.setText("TACO Bravos 4 Life");
        t1.setHorizontalCentered(false);
        t1.getTextParagraphs().get(0).getTextRuns().get(0).setFontSize(20.);
        t1.setAnchor(new Rectangle(200, 20));
        t1.moveTo(10., 0);

        HSLFTable table = makeTable(data, slide, "Alpha", false);
//        slide.addShape(table);
//        table.moveTo(10, 10);

        int pgWidth = ppt.getPageSize().width;
        int pgHeight = ppt.getPageSize().height;
        table.moveTo(10., 25.);

        double x = pgWidth / 2.;

        HSLFTextBox t2 = slide.createTextBox();
        t2.setText("More Bravos 4 Life");
        t2.setHorizontalCentered(false);
        t2.getTextParagraphs().get(0).getTextRuns().get(0).setFontSize(20.);
        t2.setAnchor(new Rectangle(200, 20));
        t2.moveTo(x, 0);

        HSLFTable table2 = makeTable(data, slide, "Beta", false);
//        slide.addShape(table2);
        table2.moveTo(x, 25.);

        double y = pgHeight / 2.;

        HSLFTextBox t3 = slide.createTextBox();
        t3.setText("BRA Bravos 4 Life");
        t3.setHorizontalCentered(false);
        t3.getTextParagraphs().get(0).getTextRuns().get(0).setFontSize(20.);
        t3.setAnchor(new Rectangle(200, 20));
        t3.moveTo(10., y);

        HSLFTable table3 = makeTable(data, slide, "Charlie", true);
//        slide.addShape(table3);
        table3.moveTo(10., y + 25);

//        int pgWidth = ppt.getPageSize().width;
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



                if (j == 0) {
                    cell.setText("CID");
                } else {
                    cell.setText(data[i][j] + " " + beta);
                }

                if (i > 0 && i % 2 == 0) {
                    cell.setFillColor(new Color(202, 211, 205));
                } else if (i > 0 && i % 2 != 0) {
                    cell.setFillColor(new Color(230, 234, 231));
                }

                HSLFTextRun rt = cell.getTextParagraphs().get(0).getTextRuns().get(0);
                rt.setFontFamily("Calibri");
                rt.setFontSize(18.);
                if (j == 0) {
                    rt.setBold(true);
                }

                if(i == 0){
                    cell.getFill().setForegroundColor(new Color(0, 105, 60));
                    rt.setFontColor(Color.white);
                    rt.setBold(true);
                    rt.setFontSize(14d);
//                    cell.setHorizontalCentered(true);
                }


                cell.setVerticalAlignment(VerticalAlignment.TOP);
//                cell.setHorizontalCentered(true);
            }
        }

        DrawTableShape dts1 = new DrawTableShape(table);
        dts1.setAllBorders(1.0, Color.white);

        //set width of the 1st column
        table.setColumnWidth(0, 50);
        //set width of the 2nd column

        int width = 275;
        if (superBig) {
            width = 640;
        }

        table.setColumnWidth(1, width);

        return table;
    }
}
