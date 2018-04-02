package com.epam.lab.xml.entity;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "form")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Form {
    @XmlAttribute
    private FormEnum name;
    @XmlElement(name = "version")
    private List<Version> versions = new ArrayList<>();

    public void addVersion(Version version) {
        this.versions.add(version);
    }
}
