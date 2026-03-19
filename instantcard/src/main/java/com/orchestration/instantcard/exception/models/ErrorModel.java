package com.orchestration.instantcard.exception.models;


import com.orchestration.instantcard.exception.enums.InstantCardEnumError;

public class ErrorModel {
    private String code;
    private String title;
    private String detail;

    public ErrorModel() {}

    public ErrorModel(String code, String title, String detail) {
        this.code = code;
        this.title = title;
        this.detail = detail;
    }

    public ErrorModel(InstantCardEnumError enume, String detail){
        this.code = enume.getCode();
        this.title = enume.getTitle();
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ErrorModel{code='" + code + "', title='" + title + "', detail='" + detail + "'}";
    }
}
