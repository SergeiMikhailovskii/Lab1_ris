package com.mikhailovskii.lab1.logic;

import com.mikhailovskii.lab1.entity.Item;
import com.mikhailovskii.lab1.entity.Purchase;
import com.mikhailovskii.lab1.exception.BaseException;
import com.mikhailovskii.lab1.exception.CardNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.BindException;
import java.util.List;

@Controller
public class ShopController {

    private final ShopRepository shopRepository;

    public ShopController(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @GetMapping("/")
    public String createForm() {
        return "/index.html";
    }

    @GetMapping("/items")
    public ModelAndView getItems() {
        List<Item> items = shopRepository.getItems();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/items.html");
        modelAndView.addObject("items", items);

        return modelAndView;
    }

    @GetMapping("/items/show/{id}")
    public ModelAndView showExtendedInfo(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/extendedInfo.html");
        modelAndView.addObject("itemId", id);

        return modelAndView;
    }

    @PostMapping("/items/buy")
    public String buyItem(
            @RequestParam int itemId,
            @RequestParam(required = false) String cardId,
            @RequestParam(required = false) String bonusesAmount) {

        int numCardId;
        int numBonusesAmount;

        try {
            numCardId = Integer.parseInt(cardId);
        } catch (NumberFormatException e) {
            numCardId = 0;
        }

        try {
            numBonusesAmount = Integer.parseInt(bonusesAmount);
        } catch (NumberFormatException e) {
            numBonusesAmount = 0;
        }

        shopRepository.buyItem(itemId, numCardId, numBonusesAmount);
        return "/index.html";
    }

    @GetMapping("/cardInfo")
    public ModelAndView getCardInfo(@RequestParam String cardNumber) {
        try {
            List<Purchase> purchases = shopRepository.getCardInfo(Integer.parseInt(cardNumber));

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/cardInfo.html");
            modelAndView.addObject("bonusesLeft", shopRepository.getBonusesAmount(Integer.parseInt(cardNumber)));
            modelAndView.addObject("purchases", purchases);

            return modelAndView;
        } catch (CardNotFoundException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/error.html");
            return modelAndView;
        }
    }

    @ExceptionHandler(BaseException.class)
    public ModelAndView handleCustomExceptions(BaseException exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index.html");
        modelAndView.addObject("message", exception.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(BindException.class)
    public ModelAndView numberFormatError() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index.html");
        modelAndView.addObject("message", "Error with parsing number attribute");

        return modelAndView;
    }

}
