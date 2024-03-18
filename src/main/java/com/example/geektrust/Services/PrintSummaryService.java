package com.example.geektrust.Services;

import com.example.geektrust.Constant.PassengerType;
import com.example.geektrust.Constant.Station;
import com.example.geektrust.Interface.IPrintSummary;
import com.example.geektrust.Model.PassengerCount;

import java.util.Map;
import java.util.PriorityQueue;

public class PrintSummaryService implements IPrintSummary {

    CheckInService checkInService;

    public PrintSummaryService(CheckInService checkInService){
        this.checkInService = checkInService;
    }
    @Override
    public void printSummary() {
        Map<String, Integer> stationAmountSummary = checkInService.getStationAmountSummary();
        Map<String, Integer> stationDiscountSummary = checkInService.getStationDiscountSummary();
        Map<Station, Map<PassengerType,Integer>> passengerCountPerStationType = checkInService.getPassengerCountPerStationType();

        for(Station station: Station.values()){
            System.out.println("TOTAL COLLECTION FOR STATION: " + station.name() + " " + stationAmountSummary.get(station.name()) + " " + stationDiscountSummary.get(station.name()));
            System.out.println("PASSENGER TYPE SUMMARY");
            PriorityQueue<PassengerCount> sortedCount = convertToQueue(passengerCountPerStationType.get(station));
            while (!sortedCount.isEmpty()) {
                PassengerCount passengerCount = sortedCount.poll();
                System.out.println(passengerCount.getPassengerType().name() + " " + passengerCount.getCount());
            }
        }
    }

    private PriorityQueue<PassengerCount> convertToQueue(Map<PassengerType, Integer> map) {
        PriorityQueue<PassengerCount> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<PassengerType, Integer> entry : map.entrySet()) {
            priorityQueue.add(new PassengerCount(entry.getKey(), entry.getValue()));
        }
        return priorityQueue;
    }
}
