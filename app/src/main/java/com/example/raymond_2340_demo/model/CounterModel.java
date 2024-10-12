package com.example.raymond_2340_demo.model;

public class CounterModel {
    private  int counter;

    // add a constructor and initalize this variable to 0
    public CounterModel(){
        this.counter = 0;
    }

    //returns the correct value of counter
    public int getCounter() {
        return counter;
    }

    // setter
    public void setCounter(int counter) {
        this.counter = counter;
    }

    //increments the value of counter by 1
    public void incrementCounter(){
        counter++;
    }
}
