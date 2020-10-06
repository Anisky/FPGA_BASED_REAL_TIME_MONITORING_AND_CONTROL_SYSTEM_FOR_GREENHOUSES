package com.biogardxcompany.biogardx.ui.analytics;

import com.biogardxcompany.biogardx.MainActivity;
import com.biogardxcompany.biogardx.Measurment.Measurments;
import com.biogardxcompany.biogardx.ConnectionServices.FirebaseService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnalyticsViewModel extends ViewModel {

    public static MutableLiveData<Measurments> measurmentsMLD;
    private FirebaseService repo;
    private MainActivity ripo ;

    public void init() {
        repo = FirebaseService.getInstance();
        measurmentsMLD = repo.getData();

    }
//    public AnalyticsViewModel (){
//        measurmentsMLD = new MutableLiveData<>();
//
//    }
    public void setData (Measurments data){
       measurmentsMLD.setValue(data);
    }
    public LiveData<Measurments> getData() {
        return measurmentsMLD;
    }
}