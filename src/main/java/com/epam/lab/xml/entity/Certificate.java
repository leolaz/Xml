package com.epam.lab.xml.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
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
}
