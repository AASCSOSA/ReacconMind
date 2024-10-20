package com.reacconmind.reacconmind.model;

import com.reacconmind.reacconmind.enums.ModerationTypeEnum;

public class ModerationResult {
    private ModerationTypeEnum decision;
    private String details;

    public ModerationTypeEnum getDecision() {
        return decision;
    }

    public void setDecision(ModerationTypeEnum decision) {
        this.decision = decision;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}