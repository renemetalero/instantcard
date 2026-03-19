package com.orchestration.instantcard.models.generals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Header {

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @Size(max = 3, message = "El campo branch debe ser de {max} caracteres")
    @Pattern(regexp = "[0-9]{1,3}", message = "El campo branch solo permite numeros")
    private String branch;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @Size(max = 4, message = "El campo supervisor debe ser de {max} caracteres")
    @Pattern(regexp = "[0-9]{1,4}", message = "El campo supervisor solo permite numeros")
    @JsonInclude(Include.NON_NULL)
    private String supervisor;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @Size(max = 4, message = "El campo batch debe ser de {max} caracteres")
    @Pattern(regexp = "[0-9]{1,4}", message = "El campo batch solo permite numeros")
    private String batch;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @Size(max = 8, message = "El campo date debe ser de {max} caracteres")
    @Pattern(regexp = "[0-9]{8}", message = "El campo date solo permite numeros")
    private String date;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @Size(max = 6, message = "El campo hour debe ser de {max} caracteres")
    @Pattern(regexp = "[0-9]{6}", message = "El campo hour solo permite numeros")
    private String hour;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @Size(max = 10, message = "El campo system debe ser de {max} caracteres")
    private String system;

    @NotNull(message = "El campo user es requerido")
    @NotBlank(message = "El campo user no puede ir vacio")
    @Size(max = 20, message = "El campo user debe ser de {max} caracteres") //modificar campo user longitud de campo a 20 caracteres
    @JsonInclude(Include.NON_NULL)
    private String user;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @Size(max = 16, message = "El campo locationUser debe ser de {max} caracteres")
    @Pattern(regexp = InstantCardConstants.IPV4_REGEXP, message = "El campo locationUser lleva el formato 0.0.0.0")
    @JsonInclude(Include.NON_NULL)
    private String locationUser;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @Size(max = 9, message = "El campo channelId debe ser de {max} caracteres")
    @Pattern(regexp = "[0-9]{1,9}", message = "El campo channelId solo permite numeros")
    private String channelId;

    @NotNull(message = "0505")
    @NotBlank(message = "0506")
    @Size(max = 2, message = "El campo instanceId debe ser de {max} caracteres")
    @Pattern(regexp = "[0-9]*", message = "El campo instanceId solo permite numeros")
    private String instanceId;

    public Header(){ }

    public Header(Header header){
        this.setBranch(header.getBranch());
        this.setBatch(header.getBatch());
        this.setDate(header.getDate());
        this.setHour(header.getHour());
        this.setSystem(header.getSystem());
        this.setChannelId(header.getChannelId());
        this.setInstanceId(header.getInstanceId());
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLocationUser() {
        return locationUser;
    }

    public void setLocationUser(String locationUser) {
        this.locationUser = locationUser;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}
