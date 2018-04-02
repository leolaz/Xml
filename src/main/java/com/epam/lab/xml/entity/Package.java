package com.epam.lab.xml.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Package {
    @XmlElement
    private String type;
    @XmlElement
    private int quantity;
    @XmlElement
    private double price;
}
