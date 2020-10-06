package com.biogardxcompany.biogardx.ui.analytics;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.biogardxcompany.biogardx.MainActivity;
import com.biogardxcompany.biogardx.Measurment.Measurments;
import com.biogardxcompany.biogardx.R;
import com.biogardxcompany.biogardx.ConnectionServices.FirebaseService;
import com.google.android.material.card.MaterialCardView;

public class AnalyticsFragment extends Fragment {
    private AnalyticsViewModel mAnalyticsViewModel;
    private FirebaseService mFirebaseService = FirebaseService.getInstance();
    private TextView temInTv;
    private TextView humInTv;
    private TextView soilMoisTv;
    private TextView co2Tv;
    private TextView tempOutTv;
    private TextView humOutTv;
    private TextView rainDroTv;
    private TextView solarRadiTv;
    private TextView waterLvlTv;
    private TextView PHLvlTv;

    private MutableLiveData<Measurments> liveData = new MutableLiveData<>();

    private Measurments measurments = new Measurments() ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAnalyticsViewModel =
                ViewModelProviders.of(this).get(AnalyticsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_analytics, container, false);
        MainActivity activity = (MainActivity) getActivity();

//        CardView cardView = (CardView)root.findViewById(R.id.card_temp_in);
//        Paris.style(cardView).apply(R.style.card_style_unava_data);

        temInTv = root.findViewById(R.id.tv_temp_in_value);
        humInTv = root.findViewById(R.id.tv_hum_in_value);
        soilMoisTv = root.findViewById(R.id.tv_soil_moist_value);
        co2Tv = root.findViewById(R.id.tv_co2_conc_value);
        tempOutTv = root.findViewById(R.id.tv_temp_out_value);
        humOutTv = root.findViewById(R.id.tv_hum_out_value);
        rainDroTv = root.findViewById(R.id.tv_rain_drops_value);
        solarRadiTv = root.findViewById(R.id.tv_solar_radia_value);
        waterLvlTv = root.findViewById(R.id.tv_water_reser_value);
        PHLvlTv = root.findViewById(R.id.tv_ph_value);






        mAnalyticsViewModel.init();
        mAnalyticsViewModel.setData(activity.getData());
        mAnalyticsViewModel.getData().observe(getViewLifecycleOwner(), new Observer<Measurments>() {
            @Override
            public void onChanged(@Nullable Measurments s) {
               updateUI(s);
//                Log.e("LIve " ,s.getValueCo2Con()+ "   :   " + s.getValueHumIn());
            }
        });

        return root;
    }

    private void updateUI(Measurments measurments) {
        if(measurments!= null) {
            if (measurments.getValueTempIn() != null) {
                temInTv.setText(measurments.getValueTempIn() + " C°");
                setNormalBackground(temInTv, R.color.temp_ic_tint);


            }else{
                setErrorBackground(temInTv,R.color.unavailable_data_txt_cl);
                temInTv.setText("N/A");
            }

            if (measurments.getValueHumIn() != null) {
                humInTv.setText(measurments.getValueHumIn() + " %");
                setNormalBackground(humInTv, R.color.hum_ic_tint);
            } else {
                setErrorBackground(humInTv, R.color.unavailable_data_txt_cl);
                humInTv.setText("N/A");
            }

            if (measurments.getValueSoilMoisture() != null) {
                soilMoisTv.setText(measurments.getValueSoilMoisture() + " %");
                setNormalBackground(soilMoisTv, R.color.soil_ic_tint);
            } else {
                setErrorBackground(soilMoisTv, R.color.unavailable_data_txt_cl);
                soilMoisTv.setText("N/A");
            }

            if (measurments.getValueCo2Con() != null){
                co2Tv.setText(measurments.getValueCo2Con() + " %");
                setNormalBackground(co2Tv, R.color.co2_ic_tint);
            } else {
                setErrorBackground(co2Tv, R.color.unavailable_data_txt_cl);
                co2Tv.setText("N/A");
            }

            if (measurments.getValueTempOut() != null){
                tempOutTv.setText(measurments.getValueTempOut() + " C°");
                setNormalBackground(tempOutTv, R.color.temp_ic_tint);
            } else {
                setErrorBackground(tempOutTv, R.color.unavailable_data_txt_cl);
                tempOutTv.setText("N/A");
            }

            if (measurments.getValueHumOut() != null){
                humOutTv.setText(measurments.getValueHumOut() + " %");
                setNormalBackground(humOutTv, R.color.hum_ic_tint);
            } else {
                setErrorBackground(humOutTv, R.color.unavailable_data_txt_cl);
                humOutTv.setText("N/A");
            }

            if (measurments.getValueRainDrops() != null){
                rainDroTv.setText(measurments.getValueRainDrops()+ " mm");
                setNormalBackground(rainDroTv, R.color.rain_ic_tint );
            } else {
                setErrorBackground(rainDroTv, R.color.unavailable_data_txt_cl);
                rainDroTv.setText("N/A");
            }

            if (measurments.getValueSolarRadi() != null){
                solarRadiTv.setText(measurments.getValueSolarRadi() + " lx");
                setNormalBackground(solarRadiTv, R.color.sun_ic_tint);
            } else {
                setErrorBackground(solarRadiTv, R.color.unavailable_data_txt_cl);
                solarRadiTv.setText("N/A");
            }

            if (measurments.getValueWaterLvl() != null){
                waterLvlTv.setText(measurments.getValueWaterLvl() + " %");
                setNormalBackground(waterLvlTv, R.color.water_ic_tint);
            } else {
                setErrorBackground(waterLvlTv, R.color.unavailable_data_txt_cl);
                waterLvlTv.setText("N/A");
            }

            if (measurments.getValuePhLevel() != null){
                PHLvlTv.setText(measurments.getValuePhLevel());
                setNormalBackground(PHLvlTv, R.color.ph_ic_tint);
            } else {
                setErrorBackground(PHLvlTv, R.color.unavailable_data_txt_cl);
                PHLvlTv.setText("N/A");
            }
        }
    }
    private void setErrorBackground (TextView tv, int colorId){
        LinearLayout linearLayout1 = (LinearLayout) tv.getParent();
        LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getParent();
        MaterialCardView cardView = (MaterialCardView) linearLayout2.getParent();
        cardView.setStrokeColor(getResources().getColor(R.color.colorAccent));
        cardView.setStrokeWidth(2);
        cardView.setClickable(false);
        linearLayout1.setBackgroundColor(getResources().getColor(R.color.unavailable_data_bg));
        linearLayout2.setBackgroundColor(getResources().getColor(R.color.unavailable_data_bg));

        for (int index = 0; index < (linearLayout2).getChildCount(); index++) {
            View nextChild =  linearLayout2.getChildAt(index);
            nextChild.setBackgroundColor(getResources().getColor(R.color.unavailable_data_bg));
            if(nextChild instanceof ImageView){
                ((ImageView) nextChild).setColorFilter(ContextCompat.getColor(getContext(), colorId));
            }

        }
        for (int index = 0; index < linearLayout1.getChildCount(); index++) {
            TextView nextChild = (TextView) (linearLayout1).getChildAt(index);
            nextChild.setBackgroundColor(getResources().getColor(R.color.unavailable_data_bg));
            nextChild.setTextColor(getResources().getColor(R.color.unavailable_data_txt_cl));

        }

    }
    private void setNormalBackground(TextView tv,int colorId){

        LinearLayout linearLayout1 = (LinearLayout) tv.getParent();
        LinearLayout linearLayout2 = (LinearLayout) linearLayout1.getParent();
        MaterialCardView cardView = (MaterialCardView) linearLayout2.getParent();
        cardView.setStrokeColor(getResources().getColor(R.color.colorAccent));
        cardView.setStrokeWidth(2);
        cardView.setClickable(true);
        linearLayout1.setBackgroundColor(getResources().getColor(R.color.available_data_bg));
        linearLayout2.setBackgroundColor(getResources().getColor(R.color.available_data_bg));

        for (int index = 0; index < ((LinearLayout) linearLayout2).getChildCount(); index++) {
            View nextChild = (View) ((LinearLayout) linearLayout2).getChildAt(index);
            nextChild.setBackgroundColor(getResources().getColor(R.color.available_data_bg));
            if(nextChild instanceof ImageView) {
                ((ImageView) nextChild).setColorFilter(ContextCompat.getColor(getContext(), colorId));
            }
        }

        for (int index = 0; index < ((LinearLayout) linearLayout1).getChildCount(); index++) {
            TextView nextChild = (TextView) ((LinearLayout) linearLayout1).getChildAt(index);
            nextChild.setBackgroundColor(getResources().getColor(R.color.available_data_bg));
            nextChild.setTextColor(getResources().getColor(R.color.available_data_txt_cl));
        }


    }





}