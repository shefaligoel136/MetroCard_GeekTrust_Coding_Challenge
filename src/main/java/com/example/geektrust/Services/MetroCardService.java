package com.example.geektrust.Services;

import com.example.geektrust.Interface.IMetroCard;
import com.example.geektrust.Model.MetroCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroCardService implements IMetroCard {

    private final Map<String, MetroCard> metroCardMap = new HashMap<>();
    @Override
    public void addCard(String cardId, int balance) {
        MetroCard metroCard = new MetroCard(cardId, balance);
        metroCardMap.put(cardId, metroCard);
    }

    @Override
    public List<MetroCard> getCards() {
        List<MetroCard> metroCardList = new ArrayList<>();
        for(Map.Entry<String, MetroCard> entry: metroCardMap.entrySet()){
            metroCardList.add(entry.getValue());
        }
        return metroCardList;
    }

    @Override
    public int transaction(String cardId, int amount) {
        MetroCard metroCard = metroCardMap.get(cardId);
        int availableBalance = metroCard.getBalance();

        int diff = availableBalance - amount;

        if(diff<0){
            metroCard.setBalance(0);
            metroCardMap.put(cardId, metroCard);
            return Math.abs(availableBalance-amount);
        }

        metroCard.setBalance(diff);
        metroCardMap.put(cardId, metroCard);
        return 0;
    }
}
