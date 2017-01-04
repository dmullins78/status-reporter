package com.mullco.informationater.ppt;

import org.apache.poi.hslf.usermodel.HSLFTable;
import org.apache.poi.hslf.usermodel.HSLFTableCell;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.sl.usermodel.VerticalAlignment;

import java.awt.*;

import static com.mullco.informationater.ppt.Colors.*;

public class PowerpointTableCell {
    public static final String FONT_FAMILY = "Calibri";
    public static final double FONT_SIZE = 18.;

    public void makeBoldCell(HSLFTable table, int rowIndex, int columnIndex, String textValue) {
        HSLFTableCell cell = makeCell(table, rowIndex, columnIndex, textValue);

        getFont(cell).setBold(true);
    }

    public void makeHeaderCell(HSLFTable table, int rowIndex, int columnIndex, String textValue) {
        HSLFTableCell cell = makeCell(table, rowIndex, columnIndex, textValue);
        cell.setHorizontalCentered(true);
        cell.getFill().setForegroundColor(REALLYDARKGREEN());

        getFont(cell).setBold(true);
        getFont(cell).setFontColor(Color.white);
    }

    public HSLFTableCell makeCell(HSLFTable table, int rowIndex, int columnIndex, String textValue) {
        HSLFTableCell cell = table.getCell(rowIndex, columnIndex);
        cell.setText(textValue);
        cell.setVerticalAlignment(VerticalAlignment.TOP);
        cell.setFillColor(getRowColor(rowIndex));
        setFontStuff(cell);

        return cell;
    }

    private Color getRowColor(int rowIndex) {
        return rowIndex % 2 == 0 ? DARKGREEN() : LIGHTGREEN();
    }

    private HSLFTextRun getFont(HSLFTableCell cell) {
        return cell.getTextParagraphs().get(0).getTextRuns().get(0);
    }

    private void setFontStuff(HSLFTableCell cell) {
        java.util.List<HSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (HSLFTextParagraph textParagraph : textParagraphs) {
            for (HSLFTextRun hslfTextRun : textParagraph.getTextRuns()) {
                hslfTextRun.setFontFamily(FONT_FAMILY);
                hslfTextRun.setFontSize(FONT_SIZE);
            }
        }
    }
}
