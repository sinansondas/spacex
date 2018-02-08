
package com.app.spacex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Reuse implements Serializable {

    @SerializedName("core")
    @Expose
    private Boolean core;
    @SerializedName("side_core1")
    @Expose
    private Boolean sideCore1;
    @SerializedName("side_core2")
    @Expose
    private Boolean sideCore2;
    @SerializedName("fairings")
    @Expose
    private Boolean fairings;
    @SerializedName("capsule")
    @Expose
    private Boolean capsule;

    public Boolean getCore() {
        return core;
    }

    public void setCore(Boolean core) {
        this.core = core;
    }

    public Boolean getSideCore1() {
        return sideCore1;
    }

    public void setSideCore1(Boolean sideCore1) {
        this.sideCore1 = sideCore1;
    }

    public Boolean getSideCore2() {
        return sideCore2;
    }

    public void setSideCore2(Boolean sideCore2) {
        this.sideCore2 = sideCore2;
    }

    public Boolean getFairings() {
        return fairings;
    }

    public void setFairings(Boolean fairings) {
        this.fairings = fairings;
    }

    public Boolean getCapsule() {
        return capsule;
    }

    public void setCapsule(Boolean capsule) {
        this.capsule = capsule;
    }

}
