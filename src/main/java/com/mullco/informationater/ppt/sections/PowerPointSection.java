package com.mullco.informationater.ppt.sections;

import com.mullco.informationater.jira.WorkItem;
import com.mullco.informationater.ppt.PowerpointTableCell;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFTable;
import org.apache.poi.hslf.usermodel.HSLFTextBox;
import org.apache.poi.sl.draw.DrawTableShape;

import java.awt.*;
import java.util.List;

public abstract class PowerPointSection {
    PowerpointTableCell cell;

    public PowerPointSection() {
        cell = new PowerpointTableCell();
    }

    abstract Integer getLeftStartPosition();

    abstract Integer getHeightStartPosition();

    abstract String getSectionHeaderName();

    abstract HSLFTable populateTable(List<WorkItem> data, HSLFSlide slide);

    protected HSLFTextBox makeSectionHeader(HSLFSlide slide, String labelText) {
        HSLFTextBox sectionLabel = slide.createTextBox();
        sectionLabel.setText(labelText);
        sectionLabel.setHorizontalCentered(false);
        sectionLabel.getTextParagraphs().get(0).getTextRuns().get(0).setFontSize(26.);
        sectionLabel.setAnchor(new Rectangle(200, 26));
        return sectionLabel;
    }

    public void makeSectionWithHeader(List<WorkItem> data, HSLFSlide slide) {
        HSLFTextBox sectionHeader = makeSectionHeader(slide, getSectionHeaderName());
        sectionHeader.moveTo(getLeftStartPosition(), getHeightStartPosition());

        makeTableWithHeightOffset(data, slide, 35);
    }

    public void makeSection(List<WorkItem> data, HSLFSlide slide) {
        makeTableWithHeightOffset(data, slide, 0);
    }

    private void makeTableWithHeightOffset(List<WorkItem> data, HSLFSlide slide, int offset) {
        HSLFTable table = populateTable(data, slide);
        table.moveTo(getLeftStartPosition(), getHeightStartPosition() + offset);

        DrawTableShape tableShape = new DrawTableShape(table);
        tableShape.setAllBorders(1.0, Color.white);
    }

}
