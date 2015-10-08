package com.actislink.model;

import java.time.Duration;

public class GroupCreation {
    private final String name;
    private final Duration maxFreq;

    public GroupCreation(String name, Duration maxFreq) {
        this.name = name;
        this.maxFreq = maxFreq;
    }

    public String getName() {
        return name;
    }

    public Duration getDuration() {
        return maxFreq;
    }
}
