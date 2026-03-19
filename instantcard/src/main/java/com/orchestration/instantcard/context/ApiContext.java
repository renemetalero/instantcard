package com.orchestration.instantcard.context;


import com.orchestration.instantcard.models.generals.Metadata;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
@RequestScope
public class ApiContext {

    private Metadata metadata;
    public Metadata getMetadata() {
        return Objects.nonNull(this.metadata) ? this.metadata : createMetadata();
    }

    public void setContextMetadata(Metadata metadata) {
        if(!Objects.isNull(metadata))
            this.metadata = metadata;
        else
            this.metadata = createMetadataNull();
    }
    public Metadata createMetadataNull(){
        Metadata emptyMetadata = new Metadata();
        emptyMetadata.setMessageType(InstantCardConstants.TAG_RESPONSE);
        emptyMetadata.setDatetime(LocalDateTime.now().withNano(0).toString());
        return emptyMetadata;
    }

    public Metadata createMetadata() {
        Metadata metadataTransform = new Metadata();
        metadataTransform.setMessageType(InstantCardConstants.TAG_RESPONSE);
        UUID id = UUID.randomUUID();
        metadataTransform.setMessageId(id.toString());

        metadataTransform.setMessageIdOrg(id.toString());

        metadataTransform.setShortMessageId(id.toString().substring(15));
        metadataTransform.setApplicationId(InstantCardConstants.API_ID);
        metadataTransform.setServiceId(InstantCardConstants.API_ID);
        metadataTransform.setDatetime(LocalDateTime.now().withNano(0).toString());
        return metadataTransform;
    }

}