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

import static com.mullco.informationater.work.WorkItemFilter.*;
import static java.lang.String.format;

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
        completedSection.makeSection(getCompletedData(stats), slide);
        inProgressSection.makeSection(getInProgress(stats), slide);
        backlogSection.makeSectionWithHeader(getBacklog(stats), slide);

        writeToFile(ppt, outputFile);
    }

    private List<WorkItem> getCompletedData(List<WorkItem> stats) {
        long maintenanceItemCount = getCompletedMaintenanceItemCountInLastDays(stats, 30);
        WorkItem maintenanceItem = new WorkItem("MTC", format("Completed %s maintenance items", maintenanceItemCount));

        List<WorkItem> completedWorkItems = getCompletedNoMaintenanceInLastDays(stats, 30);
        completedWorkItems.add(maintenanceItem);

        return completedWorkItems;
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
