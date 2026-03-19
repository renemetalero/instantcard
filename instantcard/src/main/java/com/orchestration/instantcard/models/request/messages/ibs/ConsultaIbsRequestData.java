package com.orchestration.instantcard.models.request.messages.ibs;

import com.orchestration.instantcard.models.generals.Header;

public class ConsultaIbsRequestData {
    private Header header;
    private ConsultaIbsRequestBody body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ConsultaIbsRequestBody getBody() {
        return body;
    }

    public void setBody(ConsultaIbsRequestBody body) {
        this.body = body;
    }
}
