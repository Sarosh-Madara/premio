package com.saroshmadara.root.premiotravelsandtours.model;

import java.io.Serializable;

/**
 * Created by root on 8/18/16.
 */
public class CountryVisa implements Serializable{
    String name;
    int flagId;
    String description;
    String embassyRequirements;
    String durationOfStay;
    String visaFee;
    int[] placesToVisit;
    String[] placesToVisitName;


    public CountryVisa(String name, int flagId, String description, String embassyRequirements, String durationOfStay, String visaFee, int[] placesToVisit, String[] placesToVisitName) {
        this.name = name;
        this.flagId = flagId;
        this.description = description;
        this.embassyRequirements = embassyRequirements;
        this.durationOfStay = durationOfStay;
        this.visaFee = visaFee;
        this.placesToVisit = placesToVisit;
        this.placesToVisitName = placesToVisitName;
    }

    public String[] getPlacesToVisitName() {
        return placesToVisitName;
    }

    public void setPlacesToVisitName(String[] placesToVisitName) {
        this.placesToVisitName = placesToVisitName;
    }

    public int[] getPlacesToVisit() {
        return placesToVisit;
    }

    public void setPlacesToVisit(int[] placesToVisit) {
        this.placesToVisit = placesToVisit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlagId() {
        return flagId;
    }

    public void setFlagId(int flagId) {
        this.flagId = flagId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmbassyRequirements() {
        return embassyRequirements;
    }

    public void setEmbassyRequirements(String embassyRequirements) {
        this.embassyRequirements = embassyRequirements;
    }

    public String getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(String durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    public String getVisaFee() {
        return visaFee;
    }

    public void setVisaFee(String visaFee) {
        this.visaFee = visaFee;
    }}
