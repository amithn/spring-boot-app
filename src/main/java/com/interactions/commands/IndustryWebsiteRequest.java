package com.interactions.commands;

/**
 * Created by voicestreams on 1/3/15.
 */

/*
{
    "targetExpression": "HWVISITS: 'www.facebook.com'",
    "subject": 1,
    "region": "UK",
    "dateFrom": "2014-07-12",
    "dateTo": "2014-07-12",
    "type": "INDUSTRY_WEBSITES"
}
 */
public class IndustryWebsiteRequest implements APIRequest {
    private String targetExpression;
    private int subject;
    private String region;
    private String dateFrom;
    private String dateTo;
    private ReportType type;

    public String getTargetExpression() {
        return targetExpression;
    }

    public void setTargetExpression(String targetExpression) {
        this.targetExpression = targetExpression;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }
}
