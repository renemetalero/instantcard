package com.orchestration.instantcard.models.generals;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class Metadata {

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @JsonProperty("_messageType")
    @SerializedName("_messageType")
    private String messageType;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @JsonProperty("_messageId")
    @SerializedName("_messageId")
    private String messageId;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @JsonProperty("_messageIdOrg")
    @SerializedName("_messageIdOrg")
    private String messageIdOrg;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @JsonProperty("_shortMessageId")
    @SerializedName("_shortMessageId")
    private String shortMessageId;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @JsonProperty("_applicationId")
    @SerializedName("_applicationId")
    private String applicationId;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @JsonProperty("_serviceId")
    @SerializedName("_serviceId")
    private String serviceId;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @JsonProperty("_datetime")
    @SerializedName("_datetime")
    private String datetime;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageIdOrg() {
        return messageIdOrg;
    }

    public void setMessageIdOrg(String messageIdOrg) {
        this.messageIdOrg = messageIdOrg;
    }

    public String getShortMessageId() {
        return shortMessageId;
    }

    public void setShortMessageId(String shortMessageId) {
        this.shortMessageId = shortMessageId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    public Metadata(
            Metadata other) {
        this.messageType= InstantCardConstants.TAG_RESPONSE;
        this.messageId = other.getMessageId();
        this.messageIdOrg = other.getMessageIdOrg();
        this.shortMessageId = other.getShortMessageId();
        this.applicationId = other.getApplicationId();
        this.serviceId = other.getServiceId();
        this.datetime= LocalDateTime.now().withNano(0).toString();

    }
    public Metadata() {
        this.messageType="";
        this.messageId = "";
        this.messageIdOrg ="";
        this.shortMessageId ="";
        this.applicationId = "";
        this.serviceId = "";
        this.datetime="";
    }

    public Metadata cloneInfo() {
        return new Metadata(this);
    }
}