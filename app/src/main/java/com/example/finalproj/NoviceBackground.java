package com.example.finalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NoviceBackground extends AppCompatActivity {
    RadioGroup backRg;
    Button nxtBut;
    RadioButton acolyteRad, criminalRad, folkRad, nobleRad, sageRad, soldierRad;
    private Bundle charBun;
    private String charRaceString, charClassString;
    Intent myBackgroundIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novice_background);

        charBun = getIntent().getExtras();
        charRaceString = charBun.getString("Race");
        charClassString = charBun.getString("Class");
        backRg = findViewById(R.id.backgroundRadioGroup);
        acolyteRad = findViewById(R.id.acolyteRadio);
        criminalRad = findViewById(R.id.criminalRadio);
        folkRad = findViewById(R.id.folkRadio);
        nobleRad = findViewById(R.id.nobleRadio);
        sageRad = findViewById(R.id.sageRadio);
        soldierRad = findViewById(R.id.soldierRadio);
        nxtBut = findViewById(R.id.nxtButton);
        myBackgroundIntent = new Intent(this, NoviceWeaponChoice.class);

        nxtBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean selected = true;
                if(acolyteRad.isChecked()){
                    myBackgroundIntent.putExtra("Background", "Acolyte");
                }
                else if(criminalRad.isChecked()){
                    myBackgroundIntent.putExtra("Background", "Criminal");
                }
                else if(folkRad.isChecked()){
                    myBackgroundIntent.putExtra("Background", "Folk");
                }
                else if(nobleRad.isChecked()){
                    myBackgroundIntent.putExtra("Background", "Noble");
                }
                else if(sageRad.isChecked()){
                    myBackgroundIntent.putExtra("Background", "Sage");
                }
                else if(soldierRad.isChecked()){
                    myBackgroundIntent.putExtra("Background", "Soldier");
                }
                else{
                    Toast.makeText(NoviceBackground.this, "Please Select a Background",
                            Toast.LENGTH_SHORT).show();
                    selected = false;
                }

                if(selected) {
                    myBackgroundIntent.putExtra("Race", charRaceString);
                    myBackgroundIntent.putExtra("Class", charClassString);
                    startActivity(myBackgroundIntent);
                }

            }
        });
    }


}