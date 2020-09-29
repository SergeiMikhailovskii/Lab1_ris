package com.mikhailovskii.lab1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

@XmlRootElement(name = "purchases")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseList implements Serializable {

    @XmlElement(name = "purchase")
    @Getter
    @Setter
    private ArrayList<Purchase> purchases;


}
