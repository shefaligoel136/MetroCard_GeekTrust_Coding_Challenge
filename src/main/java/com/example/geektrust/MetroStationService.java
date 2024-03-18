package com.example.geektrust;

import com.example.geektrust.Constant.CommandType;
import com.example.geektrust.Model.CheckIn;
import com.example.geektrust.Services.CheckInService;
import com.example.geektrust.Services.MetroCardService;
import com.example.geektrust.Services.PrintSummaryService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MetroStationService {

    MetroCardService metroCardService = new MetroCardService();
    CheckInService checkInService = new CheckInService(metroCardService);
    PrintSummaryService printSummaryService = new PrintSummaryService(checkInService);
    public void start(String[] args) throws IOException {
        try{
            if(args.length <= 0){
                throw new IOException("Empty File");
            }
            FileInputStream fs = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fs);

            while(sc.hasNextLine()){
                String[] input = sc.nextLine().split(" ",2);
                CommandType commandType = CommandType.valueOf(input[0]);
                switch (commandType){
                    case BALANCE:
                        String[] cardDetail = input[1].split(" ",2);
                        metroCardService.addCard(cardDetail[0], Integer.parseInt(cardDetail[1]));
                        break;
                    case CHECK_IN:
                        String[] checkInDetail = input[1].split(" ",3);
                        checkInService.checkInPassenger(new CheckIn(checkInDetail[0], checkInDetail[1], checkInDetail[2]));
                        break;
                    case PRINT_SUMMARY:
                        printSummaryService.printSummary();
                        break;
                    default:
                        break;
                }
            }
        }catch(IOException exception){
            throw new IOException("Error while reading the input "+ exception);
        }
    }
}
