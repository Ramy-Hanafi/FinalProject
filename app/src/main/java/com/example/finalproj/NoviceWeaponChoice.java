package com.example.finalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NoviceWeaponChoice extends AppCompatActivity {

    RadioGroup weaponRG;
    RadioButton wep1, wep2, wep3, wep4, wep5;
    Button nxtBut;
    Bundle charBun;
    Intent myIntent;
    String charClass, charRace, charBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novice_weapon_choice);
        myIntent = new Intent(this, StatCharCreationActivity.class);

        charBun = getIntent().getExtras();
        charClass = charBun.getString("Class");
        charRace = charBun.getString("Race");
        charBackground = charBun.getString("Background");
        weaponRG = findViewById(R.id.weaponRadioGroup);
        wep1 = findViewById(R.id.weaponRad1);
        wep2 = findViewById(R.id.weaponRad2);
        wep3 = findViewById(R.id.weaponRad3);
        wep4 = findViewById(R.id.weaponRad4);
        wep5 = findViewById(R.id.weaponRad5);
        wep5.setVisibility(View.INVISIBLE);
        nxtBut = findViewById(R.id.nxtButton);
        myIntent.putExtra("Novice", 1);

        if(charClass.equals("Barbarian")){
            wep1.setText("Greataxe");
            wep2.setText("Greatsword");
            wep3.setText("Maul");
            wep4.setText("War Hammer");
        }else if(charClass.equals("Fighter")){
            wep1.setText("Longsword + Shield");
            wep2.setText("Two Longswords");
            wep3.setText("War Hammer");
            wep4.setText("Lance");
            wep5.setVisibility(View.VISIBLE);
            wep5.setText("Greataxe");
        }else if(charClass.equals("Monk")){
            wep1.setText("Shortsword");
            wep2.setText("Spear");
            wep3.setText("Quarterstaff");
            wep4.setVisibility(View.INVISIBLE);
        }else if(charClass.equals("Rogue")){
            wep1.setText("Rapier");
            wep2.setText("Shortsword");
            wep3.setVisibility(View.INVISIBLE);
            wep4.setVisibility(View.INVISIBLE);
        }else if(charClass.equals("Paladin")){
            wep1.setText("Longsword + Shield");
            wep2.setText("Greatsword");
            wep3.setText("War Hammer + Shield");
            wep4.setVisibility(View.INVISIBLE);
        }else if(charClass.equals("Ranger")){
            wep1.setText("Two Shortswords");
            wep2.setText("Two Daggers");
            wep3.setText("Two Darts");
            wep4.setText("Short Bow");
            wep5.setVisibility(View.VISIBLE);
            wep5.setText("Light Crowssbow");
        }else if(charClass.equals("Warlock")){
            wep1.setText("Light Crossbow");
            wep2.setText("Quarterstaff");
            wep3.setText("Handaxe");
            wep4.setText("Spear");
        }else if(charClass.equals("Wizard")){
            wep1.setText("Quarterstaff");
            wep2.setText("Dagger");
            wep3.setVisibility(View.INVISIBLE);
            wep4.setVisibility(View.INVISIBLE);
        }else if(charClass.equals("Sorcerer")){
            wep1.setText("Light Crossbow");
            wep2.setText("Quarterstaff");
            wep3.setText("Handaxe");
            wep4.setText("Spear");
        }else if(charClass.equals("Bard")) {
            wep1.setText("Rapier");
            wep2.setText("Light Crossbow");
            wep3.setText("Short Bow");
            wep4.setVisibility(View.INVISIBLE);
        } else if(charClass.equals("Cleric")){
            wep1.setText("Mace");
            wep2.setText("Light Crowssbow");
            wep3.setText("Short Bow");
            wep4.setText("Javelin");
        }else if(charClass.equals("Druid")){
            wep1.setText("Quarterstaff");
            wep2.setText("Club");
            wep3.setText("Dagger");
            wep4.setText("Light Crossbow");
            wep5.setVisibility(View.VISIBLE);
            wep5.setText("Short Bow");
        }

        nxtBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wep1.isChecked()) {
                    myIntent.putExtra("Weapon", wep1.getText().toString());
                    myIntent.putExtra("Class", charClass);
                    myIntent.putExtra("Background", charBackground);
                    myIntent.putExtra("Race", charRace);
                    startActivity(myIntent);
                }
                else if (wep2.isChecked()) {
                    myIntent.putExtra("Weapon", wep2.getText().toString());
                    myIntent.putExtra("Class", charClass);
                    myIntent.putExtra("Background", charBackground);
                    myIntent.putExtra("Race", charRace);
                    startActivity(myIntent);
                }
                else if (wep3.isChecked()) {
                    myIntent.putExtra("Weapon", wep3.getText().toString());
                    myIntent.putExtra("Class", charClass);
                    myIntent.putExtra("Background", charBackground);
                    myIntent.putExtra("Race", charRace);
                    startActivity(myIntent);
                }
                else if (wep4.isChecked()) {
                    myIntent.putExtra("Weapon", wep4.getText().toString());
                    myIntent.putExtra("Class", charClass);
                    myIntent.putExtra("Background", charBackground);
                    myIntent.putExtra("Race", charRace);
                    startActivity(myIntent);
                }
                else if (wep5.isChecked()) {
                    myIntent.putExtra("Weapon", wep5.getText().toString());
                    myIntent.putExtra("Class", charClass);
                    myIntent.putExtra("Background", charBackground);
                    myIntent.putExtra("Race", charRace);
                    startActivity(myIntent);
                }else{
                    Toast.makeText(NoviceWeaponChoice.this, "Please Select a Weapon",
                            Toast.LENGTH_SHORT).show();
                }
            }
            });


        }
}