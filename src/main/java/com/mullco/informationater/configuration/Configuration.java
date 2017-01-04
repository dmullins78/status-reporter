package com.mullco.informationater.configuration;

public class Configuration {

    String jiraUrl;
    String jiraUid;
    String jiraPwd;
    String emailHost;
    String emailUid;
    String emailPwd;

    public Configuration(String jiraUrl, String jiraUid, String jiraPwd, String emailHost, String emailUid, String emailPwd) {
        this.jiraUrl = jiraUrl;
        this.jiraUid = jiraUid;
        this.jiraPwd = jiraPwd;
        this.emailHost = emailHost;
        this.emailUid = emailUid;
        this.emailPwd = emailPwd;
    }

    public String getJiraUrl() {
        return jiraUrl;
    }

    public String getJiraUid() {
        return jiraUid;
    }

    public String getJiraPwd() {
        return jiraPwd;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public String getEmailUid() {
        return emailUid;
    }

    public String getEmailPwd() {
        return emailPwd;
    }
}
