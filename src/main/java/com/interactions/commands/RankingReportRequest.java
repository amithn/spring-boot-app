package com.interactions.commands;

import java.util.ArrayList;
import java.util.List;

public class RankingReportRequest implements APIRequest {
    private List<ReportType> reports = new ArrayList<>();
    private APIRequest request;

    public RankingReportRequest() {
    }

    public List<ReportType> getReports() {
        return reports;
    }

    public void addReportType(ReportType reportType) {
        this.reports.add(reportType);
    }

    public APIRequest getRequest() {
        return request;
    }

    public void setRequest(APIRequest request) {
        this.request = request;
    }
}
