
package com.example.khalilurrehman.testexpandalblecode.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FrameworkDataLs {

    @SerializedName("FRAMEWORK_ID")
    @Expose
    private String fRAMEWORKID;
    @SerializedName("FRAMEWORK_TITLE")
    @Expose
    private String fRAMEWORKTITLE;
    @SerializedName("CATEGORY_ID")
    @Expose
    private String cATEGORYID;
    @SerializedName("FrameSub")
    @Expose
    private List<FrameSub> frameSub = null;

    private Boolean isSelected;

    public FrameworkDataLs(String frameworktitle, String categoryid, String frameworkid,boolean isSelected) {
        this.fRAMEWORKID = frameworkid;
        this.cATEGORYID = categoryid;
        this.fRAMEWORKTITLE = frameworktitle;
        this.isSelected = isSelected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getFRAMEWORKID() {
        return fRAMEWORKID;
    }

    public void setFRAMEWORKID(String fRAMEWORKID) {
        this.fRAMEWORKID = fRAMEWORKID;
    }

    public String getFRAMEWORKTITLE() {
        return fRAMEWORKTITLE;
    }

    public void setFRAMEWORKTITLE(String fRAMEWORKTITLE) {
        this.fRAMEWORKTITLE = fRAMEWORKTITLE;
    }

    public String getCATEGORYID() {
        return cATEGORYID;
    }

    public void setCATEGORYID(String cATEGORYID) {
        this.cATEGORYID = cATEGORYID;
    }

    public List<FrameSub> getFrameSub() {
        return frameSub;
    }

    public void setFrameSub(List<FrameSub> frameSub) {
        this.frameSub = frameSub;
    }

}
