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

    protected HSLFTextBox makeSectionHeader(HSLFSlide slide, String completed) {
        HSLFTextBox sectionLabel = slide.createTextBox();
        sectionLabel.setText(completed);
        sectionLabel.setHorizontalCentered(false);
        sectionLabel.getTextParagraphs().get(0).getTextRuns().get(0).setFontSize(26.);
        sectionLabel.setAnchor(new Rectangle(200, 26));
        return sectionLabel;
    }

    public void makeSection(List<WorkItem> data, HSLFSlide slide) {
        HSLFTextBox sectionHeader = makeSectionHeader(slide, getSectionHeaderName());
        sectionHeader.moveTo(getLeftStartPosition(), getHeightStartPosition());

        HSLFTable table = populateTable(data, slide);
        table.moveTo(getLeftStartPosition(), getHeightStartPosition() + 35);

        DrawTableShape dts1 = new DrawTableShape(table);
        dts1.setAllBorders(1.0, Color.white);
    }

}
