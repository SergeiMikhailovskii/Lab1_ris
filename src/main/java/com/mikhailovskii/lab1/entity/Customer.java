package com.mikhailovskii.lab1.entity;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @XmlElement(name = "customerId")
    @Getter
    @Setter
    private int customerId;

    @XmlElement(name = "cardId")
    @Getter
    @Setter
    private int cardId;

    @XmlElement(name = "bonusesAmount")
    @Getter
    @Setter
    private int bonusesAmount;

    @XmlElement(name = "customerName")
    @Getter
    @Setter
    private String customerName;

}
