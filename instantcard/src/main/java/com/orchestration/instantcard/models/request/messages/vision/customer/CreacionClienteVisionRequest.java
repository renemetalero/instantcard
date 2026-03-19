package com.orchestration.instantcard.models.request.messages.vision.customer;

import com.orchestration.instantcard.models.generals.Metadata;

public class CreacionClienteVisionRequest {

    private Metadata metadata;

    private CreacionClienteVisionRequestData data;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public CreacionClienteVisionRequestData getData() {
        return data;
    }

    public void setData(CreacionClienteVisionRequestData data) {
        this.data = data;
    }
}
