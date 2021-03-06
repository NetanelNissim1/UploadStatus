package com.momagic.uploadstatus.model;

import java.sql.Timestamp;

public class ModelMoMagic implements Comparable {

    private String activationDate;
    private String appName;
    private String IMEI;
    private String androidId;
    private String androidVersion;
    private String model;
    private String status;
    private Timestamp lastUpdate;
    private String didCalls;
    private String numberOfContacts;
    private String madeACall;
    private String uninstalled;

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String iMEI) {
        IMEI = iMEI;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int compareTo(Object o) {
        ModelMoMagic moMagic = (ModelMoMagic) o;
        return this.lastUpdate.compareTo(moMagic.lastUpdate);
    }

    public String getDidCalls() {
        return didCalls;
    }

    public void setDidCalls(String didCalls) {
        this.didCalls = didCalls;
    }

    public String getNumberOfContacts() {
        return numberOfContacts;
    }

    public void setNumberOfContacts(String numberOfContacts) {
        this.numberOfContacts = numberOfContacts;
    }

    public String getMadeACall() {
        return madeACall;
    }

    public void setMadeACall(String madeACall) {
        this.madeACall = madeACall;
    }

    public String getUninstalled() {
        return uninstalled;
    }

    public void setUninstalled(String uninstalled) {
        this.uninstalled = uninstalled;
    }

}