package com.example.finalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CharacterReview extends AppCompatActivity {

    private Bundle charBun;
    private String charRaceString, charClassString, charBackgroundString, charWeaponString;
    private int str, dex, con, intel, wis, chr;
    private TextView userRace, userClass, userBackgroud, userWeapon, userStr, userDex, userCon, userInt, userWis, userChr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_review);
        charBun = getIntent().getExtras();
        charRaceString = charBun.getString("Race");
        charClassString = charBun.getString("Class");
        charBackgroundString = charBun.getString("Background");
        charWeaponString = charBun.getString("Weapon");
        str = charBun.getInt("Str");
        dex = charBun.getInt("Dex");
        con = charBun.getInt("Con");
        intel = charBun.getInt("Int");
        wis = charBun.getInt("Wis");
        chr = charBun.getInt("Chr");
        
        userRace = findViewById(R.id.textViewUserRace);
        userClass = findViewById(R.id.textViewUserClass);
        userBackgroud = findViewById(R.id.textViewUserBackground);
        userWeapon = findViewById(R.id.textViewUserWeapon);
        userStr = findViewById(R.id.textViewUserStr);
        userDex = findViewById(R.id.textViewUserDex);
        userCon = findViewById(R.id.textViewUserCon);
        userInt = findViewById(R.id.textViewUserInt);
        userWis = findViewById(R.id.textViewUserWis);
        userChr = findViewById(R.id.textViewUserChr);

        userRace.setText(charRaceString);
        userClass.setText(charClassString);
        userBackgroud.setText(charBackgroundString);
        userWeapon.setText(charWeaponString);
        userStr.setText(String.valueOf(str));
        userDex.setText(String.valueOf(dex));
        userCon.setText(String.valueOf(con));
        userInt.setText(String.valueOf(intel));
        userWis.setText(String.valueOf(wis));
        userChr.setText(String.valueOf(chr));
    }
}