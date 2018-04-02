package com.epam.lab.xml.entity;

import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@XmlRootElement(name = "form")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Form {
    @XmlAttribute
    private FormEnum name;
    @XmlElement(name = "version")
    private List<Version> versions = new ArrayList<>();

    public FormEnum getName() {
        return name;
    }

    public void setName(FormEnum name) {
        this.name = name;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> version) {
        this.versions = version;
    }

    public void addVersion(Version version) {
        this.versions.add(version);
    }
}
