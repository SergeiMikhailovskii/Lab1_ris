package com.mikhailovskii.lab1.entity;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item implements Serializable {

    @XmlElement(name = "itemId")
    @Getter
    @Setter
    private int itemId;

    @XmlElement(name = "itemPrice")
    @Getter
    @Setter
    private int itemPrice;

    @XmlElement(name = "itemName")
    @Getter
    @Setter
    private String itemName;

}
