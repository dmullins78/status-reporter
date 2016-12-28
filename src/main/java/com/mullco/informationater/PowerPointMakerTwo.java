package com.mullco.informationater;

import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.sl.draw.DrawTableShape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.VerticalAlignment;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class PowerPointMakerTwo {

    public void makeStuff(java.util.List<WorkItem> workItems) throws IOException {
        //test data for the first taable
        String[][] txt1 = {
                {"INPUT FILE", "NUMBER OF RECORDS"},
                {"Item File", "11,559"},
                {"Vendor File", "502"},
                {"Purchase History File - # of PO\u2019s\r(12/01/04 - 05/31/06)", "12,852"},
                {"Purchase History File - # of PO Lines\r(12/01/04 - 05/31/06)", "53,523" },
                {"Total PO History Spend", "$10,172,038"}
        };

        HSLFSlideShow ppt = new HSLFSlideShow();

        HSLFSlide slide = ppt.createSlide();

        //six rows, two columns
        HSLFTable table1 = slide.createTable(6, 2);
        for (int i = 0; i < txt1.length; i++) {
            for (int j = 0; j < txt1[i].length; j++) {
                HSLFTableCell cell = table1.getCell(i, j);

                HSLFTextRun rt = cell.getTextParagraphs().get(0).getTextRuns().get(0);
                rt.setFontFamily("Arial");
                rt.setFontSize(10d);
                if(i == 0){
                    cell.getFill().setForegroundColor(new Color(227, 227, 227));
                } else {
                    rt.setBold(true);
                }
                cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
                cell.setHorizontalCentered(true);
                cell.setText(txt1[i][j]);
            }
        }

        DrawTableShape dts1 = new DrawTableShape(table1);
        dts1.setAllBorders(1.0, Color.black);

        table1.setColumnWidth(0, 300);
        table1.setColumnWidth(1, 150);

        int pgWidth = ppt.getPageSize().width;
        table1.moveTo((pgWidth - table1.getAnchor().getWidth())/2., 100.);

        //test data for the second taable
        String[][] txt2 = {
                {"Data Source"},
                {"CAS Internal Metrics - Item Master Summary\r" +
                        "CAS Internal Metrics - Vendor Summary\r" +
                        "CAS Internal Metrics - PO History Summary"}
        };

        //two rows, one column
        HSLFTable table2 = slide.createTable(2, 1);
        for (int i = 0; i < txt2.length; i++) {
            for (int j = 0; j < txt2[i].length; j++) {
                HSLFTableCell cell = table2.getCell(i, j);
                HSLFTextRun rt = cell.getTextParagraphs().get(0).getTextRuns().get(0);
                rt.setFontSize(10d);
                rt.setFontFamily("Arial");
                if(i == 0){
                    cell.getFill().setForegroundColor(new Color(0, 51, 102));
                    rt.setFontColor(Color.white);
                    rt.setBold(true);
                    rt.setFontSize(14d);
                    cell.setHorizontalCentered(true);
                } else {
                    rt.getTextParagraph().setBullet(true);
                    rt.setFontSize(12d);
                    rt.getTextParagraph().setTextAlign(TextParagraph.TextAlign.LEFT);
                    cell.setHorizontalCentered(false);
                }
                cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
                cell.setText(txt2[i][j]);
            }
        }
        table2.setColumnWidth(0, 300);
        table2.setRowHeight(0, 30);
        table2.setRowHeight(1, 70);

        DrawTableShape dts2 = new DrawTableShape(table2);
        dts2.setOutsideBorders(Color.black, 1.0);

        table2.moveTo(200, 400);

        FileOutputStream out = new FileOutputStream("hslf-table-2.ppt");
        ppt.write(out);
        out.close();

        ppt.close();
    }
}
