package com.epam.lab.xml.entity;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public void addForm(Form form){
        forms.add(form);
    }

    public void addAnalog(String analog){
        analogs.add(analog);
    }


}
