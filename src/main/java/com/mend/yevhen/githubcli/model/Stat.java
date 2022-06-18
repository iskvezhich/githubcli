package com.mend.yevhen.githubcli.model;

public class Stat {
    private String stat;
    private String value;

    public Stat() {
    }

    public Stat(String stat, String value) {
        this.stat = stat;
        this.value = value;
    }

    public String getStat() {
        return stat;
    }

    public Stat setStat(String stat) {
        this.stat = stat;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Stat setValue(String value) {
        this.value = value;
        return this;
    }
}
