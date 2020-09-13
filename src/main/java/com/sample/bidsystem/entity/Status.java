package com.sample.bidsystem.entity;

public enum Status {
    RUNNING("RUNNING"), OVER("OVER");

    private String value;

    Status(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
