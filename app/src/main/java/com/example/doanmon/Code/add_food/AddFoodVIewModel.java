package com.example.doanmon.Code.add_food;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddFoodVIewModel extends ViewModel {
    private MutableLiveData<String> Text;

    public AddFoodVIewModel() {
        Text = new MutableLiveData<>();
        Text.setValue("This is add fodd frament");
    }

    public LiveData<String> getText() {
        return Text;
    }
}
