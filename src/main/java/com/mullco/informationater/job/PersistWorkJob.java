package com.mullco.informationater.job;

import com.mullco.informationater.jira.WorkItem;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

import static java.sql.Date.valueOf;

public class PersistWorkJob implements Job {

    private Sql2o sql2o;

    public PersistWorkJob(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public String getName() {
        return "persist-work";
    }

    public int execute(List<WorkItem> workItems) {
        String sql =
                "INSERT INTO WORK_ITEM " +
                "(JIRA_ID, DESCRIPTION, SUMMARY, PRODUCT, COMPLETION_DATE, IN_PROGRESS, IS_DONE, WORK_TYPE, PEOPLE, PRIORITY, WORK_KIND) " +
                "VALUES (:id, :description, :summary, :product, :completion_date, :in_progress, :is_done, :work_type, :people, :priority, :work_kind)";


        try (Connection con = sql2o.open()) {
            Query query = con.createQuery(sql);

            for (WorkItem workItem : workItems) {
                query.addParameter("id", workItem.getId())
                        .addParameter("summary", workItem.getSummary())
                        .addParameter("description", workItem.getDescription())
                        .addParameter("product", workItem.getProductValue())
                        .addParameter("completion_date", valueOf(workItem.getCompletionDate()))
                        .addParameter("in_progress", workItem.isInProgress())
                        .addParameter("is_done", workItem.isDone())
                        .addParameter("work_type", workItem.getType())
                        .addParameter("people", workItem.getPeople())
                        .addParameter("priority", workItem.getPriority())
                        .addParameter("work_kind", workItem.getScope())
                        .addToBatch();
            }

            query.executeBatch();
            con.commit();
        }

        return 0;
    }
}
