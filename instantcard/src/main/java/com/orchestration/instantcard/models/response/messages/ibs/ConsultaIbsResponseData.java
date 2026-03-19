package com.orchestration.instantcard.models.response.messages.ibs;

import com.orchestration.instantcard.models.generals.Header;

public class ConsultaIbsResponseData {
    private Header header;
    private ConsultaIbsResponseBody body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ConsultaIbsResponseBody getBody() {
        return body;
    }

    public void setBody(ConsultaIbsResponseBody body) {
        this.body = body;
    }
}
