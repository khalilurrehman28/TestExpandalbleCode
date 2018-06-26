
package com.example.khalilurrehman.testexpandalblecode.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FrameSub {

    @SerializedName("FRAMEWORK_ID")
    @Expose
    private String fRAMEWORKID;
    @SerializedName("FRAMEWORK_TITLE")
    @Expose
    private String fRAMEWORKTITLE;
    @SerializedName("FRAMEWORK_SUB")
    @Expose
    private String fRAMEWORKSUB;
    @SerializedName("SCORE")
    @Expose
    private String sCORE;
    @SerializedName("REMARK")
    @Expose
    private String rEMARK;
    @SerializedName("CLASS_ID")
    @Expose
    private String cLASSID;
    @SerializedName("FRAMEWORK_DATETIME")
    @Expose
    private String fRAMEWORKDATETIME;
    @SerializedName("FRAMEWORK_MODIFY_DATETIME")
    @Expose
    private String fRAMEWORKMODIFYDATETIME;
    @SerializedName("FRAMEWORK_STATUS")
    @Expose
    private String fRAMEWORKSTATUS;
    @SerializedName("CATEGORY_ID")
    @Expose
    private Object cATEGORYID;
    @SerializedName("SUSPEND")
    @Expose
    private String sUSPEND;

    private Boolean isSelected;
    private Integer marks;

    public FrameSub(String frameworksub, String frameworkid, String frameworktitle, Object categoryid, Integer marks, String score, String remark, Boolean selected) {
        this.fRAMEWORKSUB =frameworksub;
        this.fRAMEWORKID = frameworkid;
        this.fRAMEWORKTITLE = frameworktitle;
        this.cATEGORYID = categoryid;
        this.marks = marks;
        this.sCORE= score;
        this.rEMARK = remark;
        this.isSelected = selected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
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

    public String getFRAMEWORKSUB() {
        return fRAMEWORKSUB;
    }

    public void setFRAMEWORKSUB(String fRAMEWORKSUB) {
        this.fRAMEWORKSUB = fRAMEWORKSUB;
    }

    public String getSCORE() {
        return sCORE;
    }

    public void setSCORE(String sCORE) {
        this.sCORE = sCORE;
    }

    public String getREMARK() {
        return rEMARK;
    }

    public void setREMARK(String rEMARK) {
        this.rEMARK = rEMARK;
    }

    public String getCLASSID() {
        return cLASSID;
    }

    public void setCLASSID(String cLASSID) {
        this.cLASSID = cLASSID;
    }

    public String getFRAMEWORKDATETIME() {
        return fRAMEWORKDATETIME;
    }

    public void setFRAMEWORKDATETIME(String fRAMEWORKDATETIME) {
        this.fRAMEWORKDATETIME = fRAMEWORKDATETIME;
    }

    public String getFRAMEWORKMODIFYDATETIME() {
        return fRAMEWORKMODIFYDATETIME;
    }

    public void setFRAMEWORKMODIFYDATETIME(String fRAMEWORKMODIFYDATETIME) {
        this.fRAMEWORKMODIFYDATETIME = fRAMEWORKMODIFYDATETIME;
    }

    public String getFRAMEWORKSTATUS() {
        return fRAMEWORKSTATUS;
    }

    public void setFRAMEWORKSTATUS(String fRAMEWORKSTATUS) {
        this.fRAMEWORKSTATUS = fRAMEWORKSTATUS;
    }

    public Object getCATEGORYID() {
        return cATEGORYID;
    }

    public void setCATEGORYID(Object cATEGORYID) {
        this.cATEGORYID = cATEGORYID;
    }

    public String getSUSPEND() {
        return sUSPEND;
    }

    public void setSUSPEND(String sUSPEND) {
        this.sUSPEND = sUSPEND;
    }

}
