package com.orchestration.instantcard.models.request.messages.ibs;

import com.orchestration.instantcard.models.generals.Metadata;

public class ConsultaIbsRequest {
    private Metadata metadata;
    private ConsultaIbsRequestData data;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public ConsultaIbsRequestData getData() {
        return data;
    }

    public void setData(ConsultaIbsRequestData data) {
        this.data = data;
    }
}
