package com.example.geektrust.Model;

import com.example.geektrust.Constant.PassengerType;
import com.example.geektrust.Constant.Station;

public class CheckIn {
    private final String cardId;
    private final Station station;
    private final PassengerType passengerType;


    public CheckIn(String cardId, String station, String passengerType) {
        this.cardId = cardId;
        this.station = Station.valueOf(station);
        this.passengerType = PassengerType.valueOf(passengerType);
    }

    public String getCardId() {
        return cardId;
    }

    public Station getStation() {
        return station;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }
}
