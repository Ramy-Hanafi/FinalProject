package com.example.finalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NoviceRaceChoice extends AppCompatActivity {
    private Button nextButton;
    private RadioButton elfRadio;
    private RadioButton dragonRadio;
    private RadioButton humanRadio;
    private RadioButton gnomeRadio;
    private RadioButton tieflingRadio;
    private RadioButton dwarfRadio;
    private RadioGroup rg1;
    private RadioGroup rg2;
    private Intent myRaceIntent;
    private Bundle charBundle;
    private String charClass;

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                rg2.clearCheck(); // clear the second RadioGroup!
                rg2.setOnCheckedChangeListener(listener2); //reset the listener
                Log.e("XXX2", "do the work");
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(listener1);
                Log.e("XXX2", "do the work");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novice_race_choice);
        myRaceIntent = new Intent(this, NoviceBackground.class);

        charBundle = getIntent().getExtras();
        charClass = charBundle.getString("Class");
        nextButton = findViewById(R.id.nextRaceButton);
        rg1 = findViewById(R.id.raceRG);
        elfRadio = findViewById(R.id.lighthammerRadio2);
        dragonRadio = findViewById(R.id.greatclubRadio2);
        humanRadio = findViewById(R.id.handaxeRadio2);
        gnomeRadio = findViewById(R.id.gnomeRadio2);
        dwarfRadio = findViewById(R.id.clubRadio2);
        tieflingRadio = findViewById(R.id.tieflingRadio2);
        rg2 = findViewById(R.id.raceRG2);
        rg1.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
        rg2.clearCheck();
        rg1.setOnCheckedChangeListener(listener1);
        rg2.setOnCheckedChangeListener(listener2);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean selected = true;
                if(elfRadio.isChecked()){
                    myRaceIntent.putExtra("Race", "Elf");
                }
                else if(dragonRadio.isChecked()){
                    myRaceIntent.putExtra("Race", "DragonBorne");
                }
                else if(humanRadio.isChecked()){
                    myRaceIntent.putExtra("Race", "Human");
                }
                else if(gnomeRadio.isChecked()){
                    myRaceIntent.putExtra("Race", "Gnome");
                }
                else if(dwarfRadio.isChecked()){
                    myRaceIntent.putExtra("Race", "Dwarf");
                }
                else if(tieflingRadio.isChecked()){
                    myRaceIntent.putExtra("Race", "Tiefling");
                }
                else{
                    Toast.makeText(NoviceRaceChoice.this, "Please Select a race",
                            Toast.LENGTH_SHORT).show();
                    selected = false;
                }

                if(selected) {
                    myRaceIntent.putExtra("Class", charClass);
                    startActivity(myRaceIntent);
                }

            }
        });
    }
}