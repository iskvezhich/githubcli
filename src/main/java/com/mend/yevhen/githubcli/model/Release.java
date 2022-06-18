package com.mend.yevhen.githubcli.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Release {

    private String name;
    private String distribution;
    private int downloadCount;

    public Release() {
    }

    public Release(String name, String distribution, int downloadCount) {
        this.name = name;
        this.distribution = distribution;
        this.downloadCount = downloadCount;
    }

    public String getName() {
        return name;
    }

    public Release setName(String name) {
        this.name = name;
        return this;
    }

    public String getDistribution() {
        return distribution;
    }

    public Release setDistribution(String distribution) {
        this.distribution = distribution;
        return this;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public Release setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
        return this;
    }
}
