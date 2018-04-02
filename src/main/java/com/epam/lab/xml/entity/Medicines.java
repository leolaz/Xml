package com.epam.lab.xml.entity;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "medicine")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Medicines {

    @XmlElement(name = "medication")
    List<Medicine> medicines = new ArrayList<>();
}
