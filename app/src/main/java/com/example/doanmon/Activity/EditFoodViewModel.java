package com.example.doanmon.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditFoodViewModel extends ViewModel {
    private MutableLiveData<String> Text;
    public EditFoodViewModel(){
        Text=new MutableLiveData<>();
        Text.setValue("This is update fragment");
    }
    public LiveData<String> getText() {
        return Text;
    }
}
