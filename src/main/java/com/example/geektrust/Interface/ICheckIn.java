package com.example.geektrust.Interface;

import com.example.geektrust.Constant.PassengerType;
import com.example.geektrust.Constant.Station;
import com.example.geektrust.Model.CheckIn;

import java.util.Map;

public interface ICheckIn {
    void checkInPassenger(CheckIn checkIn);

    Map<Station, Map<PassengerType, Integer>>  getPassengerCountPerStationType();
    Map<String, Integer> getStationAmountSummary();
    Map<String, Integer> getStationDiscountSummary();
}
