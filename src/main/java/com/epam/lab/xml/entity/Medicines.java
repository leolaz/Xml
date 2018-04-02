package com.epam.lab.xml.entity;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@ToString
@XmlRootElement(name = "medicine")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Medicines {

    @XmlElement(name = "medication")
    List<Medicine> medicines = new ArrayList<>();

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
