package com.interactions.commands;

/**
 * Created by voicestreams on 1/3/15.
 */

/*
{
    "output":"WEBSITE",
    "values": [23],
    "offset": 0,
    "limit": 50
}
 */
public class FetchTopXRequest implements APIRequest {
    private Metric output;
    private int[] values;
    private int offset = 0;
    private int limit;

    public FetchTopXRequest() {}

    public FetchTopXRequest(Metric output, int[] values, int offset, int limit) {
        this.output = output;
        this.values = values;
        this.offset = offset;
        this.limit = limit;
    }

    public Metric getOutput() {
        return output;
    }

    public void setOutput(Metric output) {
        this.output = output;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
