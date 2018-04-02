package com.epam.lab.xml.entity;

import lombok.ToString;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import java.util.Arrays;

@ToString
@XmlEnum
public enum FormEnum {
    @XmlEnumValue("Таблетки")
    TAB("Таблетки"),
    @XmlEnumValue("Капсули")
    KAP("Капсули"),
    @XmlEnumValue("Порошок")
    POR("Порошок"),
    @XmlEnumValue("Мазь")
    MAZ("Мазь"),
    @XmlEnumValue("Паста")
    PAS("Паста"),
    @XmlEnumValue("Розчин")
    ROZ("Розчин"),
    @XmlEnumValue("Екстракт")
    EKS("Екстракт"),
    @XmlEnumValue("Настойка")
    NAS("Настойка"),
    @XmlEnumValue("Невідомо")
    NAF("Невідомо");

    String value;

    FormEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FormEnum byValue(String value){
        return Arrays.stream(FormEnum.values()).filter(s-> s.value.equals(value)).findAny().orElse(FormEnum.NAF);
    }
}
