package com.example.geektrust.Services;

import com.example.geektrust.Constant.PassengerType;
import com.example.geektrust.Constant.ServiceCharges;
import com.example.geektrust.Constant.Station;
import com.example.geektrust.Interface.ICheckIn;
import com.example.geektrust.Model.CheckIn;

import java.util.HashMap;
import java.util.Map;

public class CheckInService implements ICheckIn {
    MetroCardService metroCardService;
    Map<String, Integer> getStationAmount;
    Map<String, Integer> getStationDiscount;

    Map<Station, Map<PassengerType, Integer>> getPassengerCountPerStationType;

    Map<String, Station> passengerTravelMap;



    public CheckInService(MetroCardService metroCardService){
        this.metroCardService = metroCardService;
        getStationAmount = new HashMap<>();
        getStationDiscount = new HashMap<>();
        getPassengerCountPerStationType = new HashMap<>();

        for(Station station: Station.values()){
            getStationAmount.put(station.name(),0);
            getStationDiscount.put(station.name(),0);
            getPassengerCountPerStationType.put(station,new HashMap<>());
        }
        passengerTravelMap = new HashMap<>();
    }

    @Override
    public void checkInPassenger(CheckIn checkIn) {
        String cardId = checkIn.getCardId();

        if(passengerTravelMap.containsKey(cardId)){
            int charges = checkIn.getPassengerType().getValue()/2;
            getStationDiscount.put(checkIn.getStation().name(), getStationDiscount.getOrDefault(checkIn.getStation().name(),0)+charges);

            int transaction = metroCardService.transaction(cardId,charges);

            if(charges != 0){
                charges += (int) (transaction * ServiceCharges.SERVICE_CHARGE_PERCENTAGE);
            }

            getStationAmount.put(checkIn.getStation().name(), getStationAmount.getOrDefault(checkIn.getStation().name(),0)+charges);
            passengerTravelMap.remove(cardId);

        }else{
            int charges = checkIn.getPassengerType().getValue();

            int transaction = metroCardService.transaction(cardId,charges);

            if(charges != 0){
                charges += (int) (transaction * ServiceCharges.SERVICE_CHARGE_PERCENTAGE);
            }

            getStationAmount.put(checkIn.getStation().name(), getStationAmount.getOrDefault(checkIn.getStation().name(),0)+charges);
            passengerTravelMap.put(cardId, checkIn.getStation());
        }

        updatePassengerCount(getPassengerCountPerStationType.get(checkIn.getStation()), checkIn.getPassengerType(), checkIn.getStation());
    }

    @Override
    public Map<Station, Map<PassengerType, Integer>> getPassengerCountPerStationType() {
        return this.getPassengerCountPerStationType;
    }

    private void updatePassengerCount(Map<PassengerType, Integer> map, PassengerType passengerType, Station station){

        map.put(passengerType, map.getOrDefault(passengerType,0)+1);

        getPassengerCountPerStationType.put(station, map);
    }

    @Override
    public Map<String, Integer> getStationAmountSummary() {
        return this.getStationAmount;
    }

    @Override
    public Map<String, Integer> getStationDiscountSummary() {
        return this.getStationDiscount;
    }
}
