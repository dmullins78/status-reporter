package com.mullco.informationater.emails;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.mullco.informationater.models.MonthlyStats;
import com.mullco.informationater.jira.WorkItem;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mullco.informationater.work.WorkItemSorter.groupWorkByProduct;

public class EmailTemplateGenerator {

    private final MustacheFactory mf;

    public EmailTemplateGenerator() {
        mf = new DefaultMustacheFactory();
    }

    public String getRichTemplate(MonthlyStats stats) {
        Mustache mustache = mf.compile("emails/weekly-mfs.mustache");

        return generateMFS(stats, mustache);
    }

    private String generateMFS(MonthlyStats stats, Mustache mustache) {
        try {
            Map<String, List<WorkItem>> areaBacklog = groupWorkByProduct("MFS", stats.getBacklog());

            Map<String, Object> params = new HashMap<>();
            params.put("inprogress", stats.getInProgress());
            params.put("backlog", areaBacklog.entrySet());

            StringWriter stringWriter = new StringWriter();
            mustache.execute(stringWriter, params).flush();

            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private String generate(MonthlyStats stats, Mustache mustache) {
//        try {
//            List<WorkItem> areaBacklog = getWorkByArea("MFS", stats.getBacklog());
//
//            Map<String, Object> params = new HashMap<>();
//            params.put("inprogress", stats.getInProgress());
//            params.put("backlog", areaBacklog);
//
//            StringWriter stringWriter = new StringWriter();
//            mustache.execute(stringWriter, params).flush();
//
//            return stringWriter.toString();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
