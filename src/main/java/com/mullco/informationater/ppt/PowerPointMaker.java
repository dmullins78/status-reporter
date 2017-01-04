package com.mullco.informationater.ppt;

import com.mullco.informationater.jira.WorkItem;
import com.mullco.informationater.ppt.sections.BacklogSection;
import com.mullco.informationater.ppt.sections.CompletedSection;
import com.mullco.informationater.ppt.sections.InProgressSection;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;

import java.awt.*;
import java.io.FileOutputStream;
import java.util.List;

import static com.mullco.informationater.work.WorkItemFilter.getBacklog;
import static com.mullco.informationater.work.WorkItemFilter.getCompleted;
import static com.mullco.informationater.work.WorkItemFilter.getInProgress;

public class PowerPointMaker {

    CompletedSection completedSection;
    InProgressSection inProgressSection;
    BacklogSection backlogSection;

    public PowerPointMaker() {
        completedSection = new CompletedSection();
        inProgressSection = new InProgressSection();
        backlogSection = new BacklogSection();
    }

    public void makeStuff(List<WorkItem> stats, String outputFile) {
        HSLFSlideShow ppt = new HSLFSlideShow();
        ppt.setPageSize(new Dimension(1024, 768));

        HSLFSlide slide = ppt.createSlide();
        completedSection.makeSection(getCompleted(stats), slide);
        inProgressSection.makeSection(getInProgress(stats), slide);
        backlogSection.makeSection(getBacklog(stats), slide);

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
