package com.example.finalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecommendedCharacter extends AppCompatActivity {
Bundle charClass;
String character, race;
TextView charTitle, recClass;
ImageView charImg;
Button nxtBut;
Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_character);
        charClass = getIntent().getExtras();
        character = charClass.getString("Class");
        race = charClass.getString("Race");
        charTitle = findViewById(R.id.txtClassTitle);
        recClass = findViewById(R.id.recommendedRace);
        charTitle.setText(character);
        charImg = findViewById(R.id.charImgView);
        nxtBut = findViewById(R.id.charNameBut);
        myIntent = new Intent(this, NoviceRaceChoice.class);

        if (character.equals("Barbarian")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.barbarian));
            recClass.setText(race);
        }else if (character.equals("Fighter")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.fighter));
            recClass.setText(race);
        }else if (character.equals("Monk")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.monk));
            recClass.setText(race);
        }else if (character.equals("Rogue")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.rogue));
            recClass.setText(race);
        }else if (character.equals("Paladin")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.paladin));
            recClass.setText(race);
        }else if (character.equals("Ranger")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.ranger));
            recClass.setText(race);
        }else if (character.equals("Warlock")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.warlock));
            recClass.setText(race);
        }else if (character.equals("Sorcerer")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.sorcerer));
            recClass.setText(race);
        }else if (character.equals("Wizard")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.wizard));
            recClass.setText(race);
        }else if (character.equals("Bard")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.bard));
            recClass.setText(race);
        }else if (character.equals("Cleric")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.cleric));
            recClass.setText(race);
        }else if (character.equals("Druid")){
            charImg.setImageDrawable(getResources().getDrawable(R.drawable.druid));
            recClass.setText(race);
        }

        nxtBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myIntent.putExtra("Class", character);
                startActivity(myIntent);
            }
        });

    }
}