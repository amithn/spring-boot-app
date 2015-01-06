package com.interactions.commands;

public class RankingReportRequestBuilder {
    RankingReportRequest rankingReportRequest;

    public RankingReportRequestBuilder newRankingReportRequest() {
        rankingReportRequest = new RankingReportRequest();
        return this;
    }

    public RankingReportRequestBuilder addReportType(ReportType reportType) {
        rankingReportRequest.addReportType(ReportType.INDUSTRY_WEBSITES);
        return this;
    }

    public RankingReportRequestBuilder withRequest(APIRequest request) {
        rankingReportRequest.setRequest(request);
        return this;
    }

    public RankingReportRequest build() {
        return rankingReportRequest;
    }
}
