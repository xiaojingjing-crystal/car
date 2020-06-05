package com.test.entity;

public class Command {

    public Command(Boolean clockWise, Integer step) {
        this.clockWise = clockWise;
        this.step = step;
    }

    private Boolean clockWise;
    private Integer step;

    public Boolean getClockWise() {
        return clockWise;
    }

    public Integer getStep() {
        return step;
    }
}
