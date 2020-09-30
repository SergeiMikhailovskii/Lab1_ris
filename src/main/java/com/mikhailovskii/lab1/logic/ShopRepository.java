package com.mikhailovskii.lab1.logic;

import com.mikhailovskii.lab1.entity.*;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ShopRepository {
    private static final String CUSTOMERS_PATH = "customers.xml";
    private static final String ITEMS_PATH = "items.xml";
    private static final String PURCHASES_PATH = "purchases.xml";

    void buyItem(int itemId, int cardId, int bonusesAmount) throws NoSuchElementException {
        List<Item> items = getItems();
        List<Customer> customers = getCustomers();

        /*
           Bonuses = bonuses - existing bonuses + 10% from item price
         */

        customers = customers.stream().map(it -> {
            if (it.getCardId() == cardId) {
                return new Customer(it.getCustomerId(),
                        it.getCardId(),
                        it.getBonusesAmount() - bonusesAmount +
                                items.stream().filter(item -> item.getItemId() == itemId).findFirst().orElseThrow().getItemPrice() / 10,
                        it.getCustomerName());
            } else {
                return it;
            }
        }).collect(Collectors.toList());

        writeCustomers(customers);

        System.out.println();
    }

    List<Item> getItems() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ItemList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ItemList itemList = (ItemList) unmarshaller.unmarshal(new File(ITEMS_PATH));

            return itemList.getItems();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Customer> getCustomers() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            CustomerList customerList = (CustomerList) unmarshaller.unmarshal(new File(CUSTOMERS_PATH));

            return customerList.getCustomers();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeCustomers(List<Customer> customers) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerList.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            CustomerList customerList = new CustomerList();
            customerList.setCustomers((ArrayList<Customer>) customers);

            marshaller.marshal(customerList, new File(CUSTOMERS_PATH));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    List<Purchase> getCardInfo(int cardNumber) {
        List<Purchase> purchases = getUserPurchases(cardNumber);

        // null check as user can have no purchases
        if (purchases == null) {
            return new ArrayList<>();
        }
        return purchases;
    }

    private List<Purchase> getUserPurchases(int cardNumber) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PurchaseList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            PurchaseList purchaseList = (PurchaseList) unmarshaller.unmarshal(new File(PURCHASES_PATH));
            List<Item> items = getItems();

            return purchaseList.getPurchases().stream()
                    .filter(it -> it.getCardId() == cardNumber)
                    .peek(it -> it.setItemName(
                            items.stream().filter(item -> item.getItemId() == it.getItemId()).findFirst().get().getItemName()))
                    .collect(Collectors.toList());

        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
