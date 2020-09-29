package com.mikhailovskii.lab1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerList {

    @XmlElement(name = "customer")
    @Getter
    @Setter
    private ArrayList<Customer> customers;

}
