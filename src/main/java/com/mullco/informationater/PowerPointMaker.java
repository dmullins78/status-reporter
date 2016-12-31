package com.mullco.informationater;

import com.mullco.informationater.ppt.BacklogSection;
import com.mullco.informationater.ppt.CompletedSection;
import com.mullco.informationater.ppt.InProgressSection;
import org.apache.poi.hslf.model.PPFont;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;

import java.awt.*;
import java.io.FileOutputStream;

public class PowerPointMaker {

    CompletedSection completedSection;
    InProgressSection inProgressSection;
    BacklogSection backlogSection;

    public PowerPointMaker() {
        completedSection = new CompletedSection();
        inProgressSection = new InProgressSection();
        backlogSection = new BacklogSection();
    }

    public void makeStuff(MonthlyStats stats, String outputFile) {
        HSLFSlideShow ppt = new HSLFSlideShow();
        ppt.setPageSize(new Dimension(1024, 768));

        HSLFSlide slide = ppt.createSlide();
        completedSection.makeSection(stats.getCompleted(), slide);
        inProgressSection.makeSection(stats.getInProgress(), slide);
        backlogSection.makeSection(stats.getBacklog(), slide);

        writeToFile(ppt, outputFile);
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
