package com.epam.lab.xml.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "certificate")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Certificate {
    @XmlAttribute
    private String number;
    @XmlElement
    private String dateOfIssue;
    @XmlElement
    private String expDate;
    @XmlElement
    private String organisation;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }
}
