package com.example.raymond_2340_demo.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.raymond_2340_demo.model.CounterModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainViewModel extends ViewModel {
    // use mutableLiveData to hold hte data that can be observed by the view
    //greetingmessage will hold the greeting text and counter will hold the counter value
    private MutableLiveData<String> greetingMessage;
    private MutableLiveData<Integer> counter;

    // instance of counterModel
    private CounterModel counterModel;

    //firebase database reference
    private DatabaseReference databaseReference;

    public MainViewModel(){
        greetingMessage = new MutableLiveData<>();
        counter = new MutableLiveData<>();
        counterModel = new CounterModel();
        greetingMessage.setValue("Hello World from viewModel");
        counter.setValue(counterModel.getCounter());

        // initialize firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // read data from firebase
        databaseReference.child("counter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    //counter exist, retrieve value
                    Integer firebaseCounter = dataSnapshot.getValue(Integer.class);

                    if(firebaseCounter != null) {
                        counter.setValue(firebaseCounter);
                        counterModel.setCounter(firebaseCounter);
                    }
                } else {
                    // counter does not exist, set initial value
                    counterModel.setCounter(0);
                    counter.setValue(0);
                    databaseReference.child("counter").setValue(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // handles errors
            }
        });
    }


    public LiveData<String> getGreetingMessage(){
        return greetingMessage;
    }

    public LiveData<Integer> getCounter(){
        return counter;
    }

    public void updateMessage(){
        greetingMessage.setValue("Hello from MainViewModel!");
    }

    public void incrementCounter(){
        counterModel.incrementCounter();
        counter.setValue(counterModel.getCounter());

        //write the update counter value to firebase
        databaseReference.child("counter").setValue(counterModel.getCounter());
    }
}
