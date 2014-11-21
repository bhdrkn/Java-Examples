package com.bahadirakin.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bhdrkn on 14/11/14.
 */
@Entity
@Table(name="ShortMessage")
public class ShortMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    private String sourceAddress;
    private Byte sourceTon;
    private Byte sourceNpi;

    private String destinationAddress;
    private Byte destinationTon;
    private Byte destinationNpi;

    private String message;

    private Byte priorityFlag;

    public ShortMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Byte getSourceTon() {
        return sourceTon;
    }

    public void setSourceTon(Byte sourceTon) {
        this.sourceTon = sourceTon;
    }

    public Byte getSourceNpi() {
        return sourceNpi;
    }

    public void setSourceNpi(Byte sourceNpi) {
        this.sourceNpi = sourceNpi;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Byte getDestinationTon() {
        return destinationTon;
    }

    public void setDestinationTon(Byte destinationTon) {
        this.destinationTon = destinationTon;
    }

    public Byte getDestinationNpi() {
        return destinationNpi;
    }

    public void setDestinationNpi(Byte destinationNpi) {
        this.destinationNpi = destinationNpi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Byte getPriorityFlag() {
        return priorityFlag;
    }

    public void setPriorityFlag(Byte priorityFlag) {
        this.priorityFlag = priorityFlag;
    }

    @Override
    public String toString() {
        return message;
    }
}
