package com.biogardxcompany.biogardx.ui.ControlPanel;

import com.biogardxcompany.biogardx.ConnectionServices.FirebaseService;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ControlPanelViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Object>> mActuators;
    private FirebaseService repo;

    public void init() {
        repo = FirebaseService.getInstance();
        mActuators = repo.getLiveCmd();

    }
    public LiveData<ArrayList<Object>> getCommand() {
        return mActuators;
    }
}