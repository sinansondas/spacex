
package com.app.spacex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Core implements Serializable {

    @SerializedName("core_serial")
    @Expose
    private String coreSerial;
    @SerializedName("reused")
    @Expose
    private Boolean reused;
    @SerializedName("land_success")
    @Expose
    private Boolean landSuccess;
    @SerializedName("landing_type")
    @Expose
    private Object landingType;
    @SerializedName("landing_vehicle")
    @Expose
    private Object landingVehicle;

    public String getCoreSerial() {
        return coreSerial;
    }

    public void setCoreSerial(String coreSerial) {
        this.coreSerial = coreSerial;
    }

    public Boolean getReused() {
        return reused;
    }

    public void setReused(Boolean reused) {
        this.reused = reused;
    }

    public Boolean getLandSuccess() {
        return landSuccess;
    }

    public void setLandSuccess(Boolean landSuccess) {
        this.landSuccess = landSuccess;
    }

    public Object getLandingType() {
        return landingType;
    }

    public void setLandingType(Object landingType) {
        this.landingType = landingType;
    }

    public Object getLandingVehicle() {
        return landingVehicle;
    }

    public void setLandingVehicle(Object landingVehicle) {
        this.landingVehicle = landingVehicle;
    }

}
