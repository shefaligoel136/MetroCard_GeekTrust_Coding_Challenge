package com.example.geektrust.Constant;

public enum PassengerType {
    ADULT(200),
    SENIOR_CITIZEN(100),
    KIDS(50);

    private int value;
    PassengerType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
