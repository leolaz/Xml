package com.epam.lab.xml.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "version")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Version {
    @XmlElement
    private Certificate certificate;
    @XmlElement(name = "package")
    private Package pack;
    @XmlElement
    private String dosage;
}