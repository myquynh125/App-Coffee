package com.example.doanmon.Code.oder;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {
private TextView textView;
    private MutableLiveData<String> Text;

    public OrderViewModel() {
        Text = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return Text;
    }
}