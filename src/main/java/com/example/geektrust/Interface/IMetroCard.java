package com.example.geektrust.Interface;

import com.example.geektrust.Model.MetroCard;

import java.util.List;

public interface IMetroCard {
    void addCard(String cardId, int balance);
    List<MetroCard> getCards();

    int transaction(String cardId, int amount);
}
