package com.epam.lab.xml.entity;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "medication")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Medicine {
    @XmlElement
    private String name;
    @XmlElement
    private String pharm;
    @XmlElement
    private String group;
    @XmlElementWrapper(name = "analogs")
    @XmlElement(name = "analog")
    private List<String> analogs = new ArrayList<>();
    @XmlElementWrapper(name = "versions")
    @XmlElement(name = "form")
    private List<Form> forms = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(List<String> analogs) {
        this.analogs = analogs;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public void addForm(Form form){
        forms.add(form);
    }

    public void addAnalog(String analog){
        analogs.add(analog);
    }


}
