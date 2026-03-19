package com.orchestration.instantcard.models.response;

import com.orchestration.instantcard.models.generals.Header;

public class InstantCardResponseData {

    private Header header;
    private InstantCardResponseBody body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public InstantCardResponseBody getBody() {
        return body;
    }

    public void setBody(InstantCardResponseBody body) {
        this.body = body;
    }
}
