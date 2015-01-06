package com.interactions.commands;

public class IndustryWebsiteRequestBuilder {

    IndustryWebsiteRequest industryWebsiteRequest = new IndustryWebsiteRequest();

    public IndustryWebsiteRequestBuilder withExpression(String expression) {
        industryWebsiteRequest.setTargetExpression(expression);
        return this;
    }

    public IndustryWebsiteRequestBuilder withSubject(int subject) {
        industryWebsiteRequest.setSubject(subject);
        return this;
    }

    public IndustryWebsiteRequestBuilder withRegion(String region) {
        industryWebsiteRequest.setRegion(region);
        return this;
    }

    public IndustryWebsiteRequestBuilder withDateFrom(String dateFrom) {
        industryWebsiteRequest.setDateFrom(dateFrom);
        return this;
    }

    public IndustryWebsiteRequestBuilder withDateTo(String dateTo) {
        industryWebsiteRequest.setDateTo(dateTo);
        return this;
    }

    public IndustryWebsiteRequestBuilder withType(ReportType type) {
        industryWebsiteRequest.setType(type);
        return this;
    }

    public IndustryWebsiteRequest build() {
        return industryWebsiteRequest;
    }
}
