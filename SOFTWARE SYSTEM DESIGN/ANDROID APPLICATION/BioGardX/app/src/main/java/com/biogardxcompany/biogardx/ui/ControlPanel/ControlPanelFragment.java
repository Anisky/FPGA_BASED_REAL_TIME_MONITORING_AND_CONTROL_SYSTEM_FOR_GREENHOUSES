package com.biogardxcompany.biogardx.ui.ControlPanel;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.biogardxcompany.biogardx.Actuators.Actuators;
import com.biogardxcompany.biogardx.Actuators.AirConditioner;
import com.biogardxcompany.biogardx.Actuators.Curtains;
import com.biogardxcompany.biogardx.Actuators.Irrigation;
import com.biogardxcompany.biogardx.Actuators.Lights;
import com.biogardxcompany.biogardx.Actuators.Roof;
import com.biogardxcompany.biogardx.ConnectionServices.BluetoothConnectionService;
import com.biogardxcompany.biogardx.Constants;
import com.biogardxcompany.biogardx.MainActivity;
import com.biogardxcompany.biogardx.R;
import com.biogardxcompany.biogardx.ConnectionServices.FirebaseService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ControlPanelFragment extends Fragment  implements Switch.OnCheckedChangeListener,
                                                          Button.OnClickListener,
                                                          SeekBar.OnSeekBarChangeListener,
                                                          EditText.OnEditorActionListener
                                                            {
    private MainActivity activity ;
    private ControlPanelViewModel controlPanelViewModel;
    private ArrayList<Object> cmdList = new ArrayList();

    private TextView tvAirValue, tvIrrValue, tvLightsValue , tvIrrCardTitle;
    private TextInputEditText etAir , etLights ,etIrr;
    private TextInputLayout  tilAir , tilIrr, tilLight ;
    private RadioGroup rgControlMode ;
    private RadioButton rbManual, rbAutomatic ;
    private MaterialButton btAirPlus, btAitMinus , btIrrPlus,btIrrMinus,btSetCommands ,btSetAir, btSetLights ,btSetIrr;
    private SwitchMaterial swAirEn , swLightEn, swIrrEn, swRoofEn,swCurtainsEn, swRoofState ;
    private SeekBar sbLights , sbIrr, sbCurtains ;

    private LinearLayout airAuto,lightAuto,irrAuto;
    private ConstraintLayout lightManu,airManu,irrManu ;
    private MaterialCardView cdRoof, cdCurtains;


    // Actuators instances variables
    private Actuators mActuators ;
    private AirConditioner mAirConditioner;
    private Lights lights;
    private Irrigation irrigation;
    private Roof roof;
    private Curtains curtains ;

    // firebase service instantces
     FirebaseService mFirebaseService ;

     private BluetoothConnectionService mbBluetoothConnectionService ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        controlPanelViewModel =
                ViewModelProviders.of(this).get(ControlPanelViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_control_panel, container, false);

        activity = (MainActivity)getActivity();

        mFirebaseService = FirebaseService.getInstance();
        mbBluetoothConnectionService = new BluetoothConnectionService();
        mActuators = Actuators.getInstance();
        lights = new Lights();
        mAirConditioner =  new AirConditioner();
        irrigation = new Irrigation();
        roof = new Roof( );
        curtains = new Curtains();


        // setting references to views
        cdCurtains = root.findViewById(R.id.card_curtains);
        cdRoof     = root.findViewById(R.id.card_roof);

        airAuto = root.findViewById(R.id.automatic_air_layout);
        airManu = root.findViewById(R.id.manual_air_layout);
        irrAuto =  root.findViewById(R.id.automatic_irr_layout);
        irrManu = root.findViewById(R.id.manual_irr_layout);
        lightManu =  root.findViewById(R.id.manual_lights_layout);
        lightAuto = root.findViewById(R.id.automatic_lights_layout);

        swAirEn =  root.findViewById(R.id.sw_air);
        swLightEn = root.findViewById(R.id.sw_light);
        swIrrEn = root.findViewById(R.id.sw_irr);
        swCurtainsEn = root.findViewById(R.id.sw_curtains);
        swRoofEn = root.findViewById(R.id.sw_roof);
        swRoofState =root.findViewById(R.id.sw_roof_co);

        btAirPlus = (MaterialButton)root.findViewById(R.id.bnt_plus_air);
        btAitMinus = (MaterialButton)root.findViewById(R.id.bnt_minus_air);
        btIrrPlus = (MaterialButton)root.findViewById(R.id.btn_set_irr);
        btSetCommands = (MaterialButton)root.findViewById(R.id.bt_set_commands);
        btSetAir = root.findViewById(R.id.btn_set_air);
        btSetLights = root.findViewById(R.id.btn_set_lights);
        btSetIrr =  root.findViewById(R.id.btn_set_irr);


        tvAirValue = (TextView)root.findViewById(R.id.tv_air_value);
        tvLightsValue = (TextView)root.findViewById(R.id.tv_lights_value);
        tvIrrCardTitle= root.findViewById(R.id.card_irr_title);

        etAir = root.findViewById(R.id.et_air);
        etLights= root.findViewById(R.id.ed_setp_lights);
        etIrr= root.findViewById(R.id.ed_setp_irr);

        tilAir = root.findViewById(R.id.et_set_air);
        tilAir.setHint(String.valueOf(mAirConditioner.getSetPoint()));

        tilLight = root.findViewById(R.id.et_set_light);
        tilLight.setHint(String.valueOf(lights.getSetPoint()));

        tilIrr = root.findViewById(R.id.et_set_irr);
        tilIrr.setHint(String.valueOf(irrigation.getSetPoint()));

        sbLights = root.findViewById(R.id.sb_lights);
        sbIrr = root.findViewById(R.id.sb_irr);
        sbCurtains=root.findViewById(R.id.sb_curtains);

        rbAutomatic = root.findViewById(R.id.radio_automatic);
        rbManual    = root.findViewById(R.id.radio_manual);
        etAir.setOnEditorActionListener(this);
        etLights.setOnEditorActionListener(this);

        sbLights.setOnSeekBarChangeListener(this);
        sbIrr.setOnSeekBarChangeListener(this);
        sbCurtains.setOnSeekBarChangeListener(this);

        // set switches onCheckChangeListener
        swAirEn.setOnCheckedChangeListener(this);
        swLightEn.setOnCheckedChangeListener(this);
        swIrrEn.setOnCheckedChangeListener(this);
        swCurtainsEn.setOnCheckedChangeListener(this);
        swRoofEn.setOnCheckedChangeListener(this);
        swRoofState.setOnCheckedChangeListener(this);
        isSwitchChecked();

        // set buttons onClickListener
        btAirPlus.setOnClickListener(this);
        btAitMinus.setOnClickListener(this);
        btSetCommands.setOnClickListener(this);
        btSetLights.setOnClickListener(this);
        btSetAir.setOnClickListener(this);
        btSetIrr.setOnClickListener(this);





        rgControlMode = (RadioGroup)root.findViewById(R.id.control_radio);

        controlPanelViewModel.init();
        controlPanelViewModel.getCommand().observe(getViewLifecycleOwner(), new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Object> cmd) {

                updatiUI(cmd);
            }
        });

        rgControlMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.radio_manual == checkedId){
                    setManualModeUI();
                    mActuators.setMode(false);
                }
                else if(R.id.radio_automatic == checkedId){
                    setAutomaticUI();
                    mActuators.setMode(true);
                }
            }
        });

        return root;
    }

    private void updatiUI(ArrayList<Object> cmd) {

            if (cmd.size() == 5) {
                mAirConditioner = (AirConditioner) cmd.get(0);
                if(cmd.get(1) instanceof Lights)
                     lights = (Lights) cmd.get(1);
                if(cmd.get(2) instanceof Irrigation)
                     irrigation = (Irrigation) cmd.get(2);
                if(cmd.get(3) instanceof Roof)
                   roof = (Roof) cmd.get(3);
                if(cmd.get(4) instanceof Curtains)
                   curtains = (Curtains) cmd.get(4);

                if(!lights.isMode()){
                    setManualModeUI();
                    rbManual.setChecked(true);
                    rbAutomatic.setChecked(false);
                }else{
                    setAutomaticUI();
                    rbManual.setChecked(false);
                    rbAutomatic.setChecked(true);
                }
            }

            swAirEn.setChecked(mAirConditioner.isEnabled());
            tvAirValue.setText(String.valueOf(mAirConditioner.getValue()));

            swLightEn.setChecked(lights.isEnabled());
            tvLightsValue.setText(String.valueOf(lights.getValue()));
            sbLights.setProgress(lights.getValue());

            swIrrEn.setChecked(irrigation.isEnabled());
            sbIrr.setProgress(irrigation.getState());

            swRoofEn.setChecked(roof.isEnabled());
            swRoofState.setChecked(roof.getState());

            swCurtainsEn.setChecked(curtains.isEnabled());
            sbCurtains.setProgress(curtains.getState());


        }


    private void isSwitchChecked() {
         setCardBackground(swAirEn,swAirEn.isChecked());
         setCardBackground(swLightEn,swLightEn.isChecked());
         setCardBackground(swIrrEn, swIrrEn.isChecked());
         setCardBackground(swCurtainsEn,swCurtainsEn.isChecked());
         setCardBackground(swRoofEn, swRoofEn.isChecked());
         //setCardBackground(swAirEn,swRoofState.isChecked());
    }

    private void setAutomaticUI() {


        airAuto.setVisibility(View.VISIBLE);
        airManu.setVisibility(View.GONE);
        irrAuto.setVisibility(View.VISIBLE);
        irrManu.setVisibility(View.GONE);
        lightAuto.setVisibility(View.VISIBLE);
        lightManu.setVisibility(View.GONE);
        cdRoof.setVisibility(View.GONE);
        cdCurtains.setVisibility(View.GONE);
        tvIrrCardTitle.setText(R.string.card_irr_title_moisture);


    }
    public void setManualModeUI (){
        airAuto.setVisibility(View.GONE);
        airManu.setVisibility(View.VISIBLE);
        irrAuto.setVisibility(View.GONE);
        irrManu.setVisibility(View.VISIBLE);
        lightAuto.setVisibility(View.GONE);
        lightManu.setVisibility(View.VISIBLE);
        cdRoof.setVisibility(View.VISIBLE);
        cdCurtains.setVisibility(View.VISIBLE);
        tvIrrCardTitle.setText(R.string.card_irrigation_title);
    }

    private void setCardBackground(View sw , boolean isChecked) {
        ConstraintLayout constraintLayout = (ConstraintLayout)sw.getParent();

        if(isChecked){
             constraintLayout.setBackgroundColor(getResources().getColor(R.color.actuator_enabled_Bg));
            for (int index = 0; index < constraintLayout.getChildCount(); index++) {
                View nextChild =constraintLayout.getChildAt(index);
                nextChild.setEnabled(true);
                nextChild.setBackgroundColor(getResources().getColor(R.color.actuator_enabled_Bg));
                if((nextChild instanceof LinearLayout) || (nextChild instanceof ConstraintLayout)){
                    for (int indx = 0; indx < ((ViewGroup) nextChild).getChildCount(); indx++) {
                        View nexChild = ((ViewGroup)nextChild).getChildAt(indx);
                        nexChild.setEnabled(true);
                        nexChild.setBackgroundColor(getResources().getColor(R.color.actuator_enabled_Bg));
                        if(nexChild instanceof TextInputLayout)
                        nexChild.setBackgroundColor(getResources().getColor(R.color.actuator_enabled_Bg));
                        if(nexChild instanceof MaterialButton)
                            ((MaterialButton) nexChild).setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                      //  nexChild.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));

                    }
                }
            }
        }
        else {
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.actuator_disabled_Bg));
            for (int index = 0; index < constraintLayout.getChildCount(); index++) {
                View nextChild = constraintLayout.getChildAt(index);
                nextChild.setEnabled(false);
                nextChild.setBackgroundColor(getResources().getColor(R.color.actuator_disabled_Bg));
                if((nextChild instanceof LinearLayout) || (nextChild instanceof ConstraintLayout)){
                    for (int indx = 0; indx < ((ViewGroup) nextChild).getChildCount(); indx++) {
                        View nexChild = ((ViewGroup)nextChild).getChildAt(indx);
                        nexChild.setEnabled(false);
                        nexChild.setBackgroundColor(getResources().getColor(R.color.actuator_disabled_Bg));
                        if(nexChild instanceof TextInputLayout)
                            nexChild.setBackgroundColor(getResources().getColor(R.color.actuator_disabled_Bg));
                        if(nexChild instanceof MaterialButton)
                            ((MaterialButton) nexChild).setStrokeColor(ColorStateList.valueOf(getResources().getColor(R.color.actuator_disabled_Bg)));
                      //  nexChild.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.available_data_txt_cl)));
                    }
                }
            }   
        }
        sw.setEnabled(true);
    }

    private void setCommands(){
        mFirebaseService.getDatabaseReference().child("Commands").child(mFirebaseService.getUserId()).setValue(mActuators);
        mFirebaseService.getDatabaseReference().child("Commands").child(mFirebaseService.getUserId()).child("AirConditioner").setValue(mAirConditioner);
        mFirebaseService.getDatabaseReference().child("Commands").child(mFirebaseService.getUserId()).child("Lights").setValue(lights);
        mFirebaseService.getDatabaseReference().child("Commands").child(mFirebaseService.getUserId()).child("Irrigation").setValue(irrigation);
        mFirebaseService.getDatabaseReference().child("Commands").child(mFirebaseService.getUserId()).child("Roof").setValue(roof);
        mFirebaseService.getDatabaseReference().child("Commands").child(mFirebaseService.getUserId()).child("Curtains").setValue(curtains);


        activity.sendMessage(getCommandAsBytes().getBytes());

    }

    private String getCommandAsBytes() {
     char [] b = new char[16];
        // Starting string char
        b[0] = 'C';
        // setting control mode cmd
        if(mActuators.isMode()) b[1] ='A'; else  b[1] = 'M';

        // setting air cmd
        if(mAirConditioner.isEnabled()) b[2] ='E'; else b[2] ='D';
        b[3]= (char)(mAirConditioner.getSetPoint()+25);
        b[4]= (char)(mAirConditioner.getValue()+25);

        // setting lights
        if(lights.isEnabled()) b[5] ='E';else b[5] ='D';
        b[6]= (char)( lights.getSetPoint()+25);
        b[7]= (char) (lights.getValue()+25);
        // setting irrigation
        if(irrigation.isEnabled()) b[8] ='E';else b[8] ='D';
        if(irrigation.getState()== Constants.LIGHT_IRR) b[9] ='L';
        else if(irrigation.getState()== Constants.MEDIUM_IRR)  b[9] ='M';
        else if(irrigation.getState()== Constants.INTENSIVE_IRR)  b[9] ='I';
        b[10]= (char)(irrigation.getSetPoint()+25);
        // setting roof
        if(roof.isEnabled()) b[11] ='E';else b[11] ='D';
        if(roof.getState()) b[12] ='C';else b[12] ='O';
        // setting curtains
        if(curtains.isEnabled()) b[13] ='E';else b[13] ='D';
        if(curtains.getState()== Constants.CURTAINS_OPEN) b[14] ='O';
        else if(curtains.getState()== Constants.CURTAINS_H_OPEN)  b[14] ='H';
        else if(curtains.getState()== Constants.CURTAINS_CLOSED)  b[14] ='C';

        b[15] = 'X';
    return new String (b) ;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
      switch(buttonView.getId()){
          case R.id.sw_air :
              setCardBackground(swAirEn ,isChecked);
              mAirConditioner.setIsEnabled(isChecked);
              break;
          case R.id.sw_light :
              setCardBackground(swLightEn ,isChecked);
              lights.setIsEnabled(isChecked);
              break;
          case R.id.sw_irr :
              setCardBackground(swIrrEn ,isChecked);
              irrigation.setIsEnabled(isChecked);
              break;
          case R.id.sw_roof :
              setCardBackground(swRoofEn ,isChecked);
              roof.setIsEnabled(isChecked);
              break;
          case R.id.sw_roof_co :
              roof.setState(isChecked);
              break;
          case R.id.sw_curtains :
              setCardBackground(swCurtainsEn ,isChecked);
              curtains.setIsEnabled(isChecked);
              break; 
      }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bnt_plus_air :
                if (mAirConditioner.getValue() < 50) {
                mAirConditioner.setValue(mAirConditioner.getValue()+1);
                tvAirValue.setText(String.valueOf(mAirConditioner.getValue()));
                }
                break;
            case R.id.bnt_minus_air :
                if (mAirConditioner.getValue() >0) {
                    mAirConditioner.setValue(mAirConditioner.getValue() - 1);
                    tvAirValue.setText(String.valueOf(mAirConditioner.getValue()));
                }
            case R.id.bt_set_commands :
                setCommands();
                break;
            case R.id.btn_set_air :
                String  eString = etAir.getText().toString();
                //Log.e("Cmd leljhhjkl", "s" );
                if(!eString.isEmpty()) {
                    mAirConditioner.setSetPoint(Integer.valueOf(eString));
                    tilAir.setHint(eString);
                }
                break;
            case R.id.btn_set_lights :
                String  fString = etLights.getText().toString();

                if(!fString.isEmpty()) {
                    lights.setSetPoint(Integer.valueOf(fString));
                    tilLight.setHint(fString);
                    Log.e("Cmd leljhhjkl", "s");
                }
                break;
            case R.id.btn_set_irr :
                String  gString = etIrr.getText().toString();
                if(!gString.isEmpty()) {
                    irrigation.setSetPoint(Integer.valueOf(gString));
                    tilIrr.setHint(gString);
                    Log.e("Cmd leljhhjkl", "s");
                }
                break;

        }

    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.sb_lights :
                 lights.setValue(progress);
                 tvLightsValue.setText(String.valueOf(progress));
                 break;
            case R.id.sb_curtains :
                curtains.setState(progress);
                break;
            case R.id.sb_irr :
                irrigation.setState(progress);
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP) {
            String message = v.getText().toString();

        }
        return true;

     }

 }