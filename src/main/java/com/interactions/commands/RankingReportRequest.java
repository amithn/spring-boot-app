package com.interactions.commands;

import java.util.ArrayList;
import java.util.List;

public class RankingReportRequest implements SymphonyRequest {
    private List<ReportType> reports = new ArrayList<>();
    private SymphonyRequest request;

    public RankingReportRequest() {
    }

    public List<ReportType> getReports() {
        return reports;
    }

    public void addReportType(ReportType reportType) {
        this.reports.add(reportType);
    }

    public SymphonyRequest getRequest() {
        return request;
    }

    public void setRequest(SymphonyRequest request) {
        this.request = request;
    }
}
