package com.mullco.informationater.ppt;

import org.apache.poi.hslf.usermodel.HSLFTable;
import org.apache.poi.hslf.usermodel.HSLFTableCell;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.sl.usermodel.VerticalAlignment;

import java.awt.*;

public class PowerpointTableCell {

    public void makeCell(HSLFTable table, int rowIndex, int columnIndex, String textValue) {
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
}
