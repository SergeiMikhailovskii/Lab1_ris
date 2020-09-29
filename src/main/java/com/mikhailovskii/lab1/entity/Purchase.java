package com.mikhailovskii.lab1.entity;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "purchase")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Purchase implements Serializable {

    @XmlElement(name = "purchaseId")
    @Getter
    @Setter
    private int purchaseId;

    @XmlElement(name = "cardId")
    @Getter
    @Setter
    private int cardId;

    @XmlElement(name = "itemId")
    @Getter
    @Setter
    private int itemId;

    @XmlElement(name = "bonusesAmount")
    @Getter
    @Setter
    private int bonusesAmount;

}
