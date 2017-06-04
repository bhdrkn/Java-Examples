package com.bahadirakin.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Ip {

    @JsonProperty("origin")
    private final String origin;

    @JsonCreator
    public Ip(@JsonProperty("origin") String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ip ip = (Ip) o;
        return Objects.equals(origin, ip.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(origin);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("origin", origin)
                .toString();
    }
}
