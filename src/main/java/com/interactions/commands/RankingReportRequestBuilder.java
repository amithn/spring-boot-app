package com.interactions.commands;

public class RankingReportRequestBuilder {
    RankingReportRequest rankingReportRequest;

    public RankingReportRequestBuilder newRankingReportRequest() {
        rankingReportRequest = new RankingReportRequest();
        return this;
    }

    public RankingReportRequestBuilder addReportType(ReportType reportType) {
        rankingReportRequest.addReportType(ReportType.INDUSTRT_WEBSITE);
        return this;
    }

    public RankingReportRequestBuilder withRequest(SymphonyRequest request) {
        rankingReportRequest.setRequest(request);
        return this;
    }

    public RankingReportRequest build() {
        return rankingReportRequest;
    }
}
