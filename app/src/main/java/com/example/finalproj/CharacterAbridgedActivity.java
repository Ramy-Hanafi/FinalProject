package com.example.finalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CharacterAbridgedActivity extends AppCompatActivity {
    RadioGroup grp1;
    RadioButton btn1, btn2, btn3 ,btn4, btn5;
    TextView question;
    Button confirmBut;
    Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_abridged);
        myIntent = new Intent(this, RecommendedCharacter.class);

        btn1 = findViewById(R.id.choice1);
        btn2 = findViewById(R.id.choice2);
        btn3 = findViewById(R.id.choice3);
        btn4 = findViewById(R.id.choice4);
        btn5 = findViewById(R.id.choice5);
        question = findViewById(R.id.questionText);
        confirmBut = findViewById(R.id.confirmButton);
        grp1 = findViewById(R.id.choiceGrp);

        confirmBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn1.isChecked()) {
                    grp1.clearCheck();
                    meleePath();
                } else if (btn2.isChecked()) {
                    grp1.clearCheck();
                    rangedPath();
                } else if (btn3.isChecked()) {
                    grp1.clearCheck();
                    supportPath();
                } else {
                    Toast.makeText(CharacterAbridgedActivity.this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void meleePath() {
        question.setText("How would you like to fight as a melee?");
        btn1.setText("I want to be very aggressive!");
        btn2.setText("Balanced like a healthy diet!");
        btn3.setText("Strike my foes as fast as I can!");
        btn4.setVisibility(View.VISIBLE);
        btn4.setHighlightColor(Color.BLACK);
        btn4.setClickable(true);
        btn4.setText("Stealth is answer!");
        btn5.setVisibility(View.VISIBLE);
        btn5.setHighlightColor(Color.BLACK);
        btn5.setClickable(true);
        btn5.setText("A strong wall to aid my allies!");
        confirmBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn1.isChecked()) {
                    myIntent.putExtra("Class","Barbarian");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Dragonborn, Dwarf");
                    grp1.clearCheck();
                    startActivity(myIntent);
                } else if (btn2.isChecked()) {
                    myIntent.putExtra("Class","Fighter");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Dragonborn, Dwarf");
                    grp1.clearCheck();
                    startActivity(myIntent);
                } else if (btn3.isChecked()) {
                    myIntent.putExtra("Class","Monk");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman");
                    grp1.clearCheck();
                    startActivity(myIntent);
                } else if (btn4.isChecked()) {
                    myIntent.putExtra("Class","Rogue");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Elf");
                    grp1.clearCheck();
                    startActivity(myIntent);
                }else if (btn5.isChecked()) {
                    myIntent.putExtra("Class","Paladin");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Dragonborn, Dwarf");
                    grp1.clearCheck();
                    startActivity(myIntent);
                }
                else {
                    Toast.makeText(CharacterAbridgedActivity.this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void rangedPath(){
        question.setText("How would you like to shoot down your enemies?");
        btn1.setText("A single arrow for a single target!");
        btn2.setText("The elements are what brings armies down.");
        btn3.setVisibility(View.INVISIBLE);
        confirmBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn1.isChecked()) {
                    myIntent.putExtra("Class","Ranger");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Elf");
                    grp1.clearCheck();
                    startActivity(myIntent);
                } else if (btn2.isChecked()) {
                    grp1.clearCheck();
                    magicPath();
                } else {
                    Toast.makeText(CharacterAbridgedActivity.this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void magicPath() {
        question.setText("Magic comes from many places, choose one!");
        btn1.setText("I want my power to come from a contract with a higher being!");
        btn2.setText("I want my power to be passed to my blood!");
        btn3.setVisibility(View.VISIBLE);
        btn3.setText("I want to learn, create, and control my own magic!");
        confirmBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn1.isChecked()) {
                    myIntent.putExtra("Class","Warlock");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Dragonborn, Tiefling");
                    grp1.clearCheck();
                    startActivity(myIntent);
                } else if (btn2.isChecked()) {
                    myIntent.putExtra("Class","Sorcerer");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Dragonborn, Tiefling");
                    grp1.clearCheck();
                    startActivity(myIntent);
                } else if (btn3.isChecked()) {
                    myIntent.putExtra("Class","Wizard");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Dragonborn, Tiefling, Gnome");
                    grp1.clearCheck();
                    startActivity(myIntent);
                } else {
                    Toast.makeText(CharacterAbridgedActivity.this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void supportPath(){
        question.setText("How would you aid your allies?");
        btn1.setText("I want to help with music");
        btn2.setText("Let the lord guide my allies");
        btn3.setText("Nature is my specialty");
        confirmBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn1.isChecked()) {
                    myIntent.putExtra("Class","Bard");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Gnome, Tiefling");
                    grp1.clearCheck();
                    startActivity(myIntent);
                } else if (btn2.isChecked()) {
                    myIntent.putExtra("Class","Cleric");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Gnome, Tiefling");
                    grp1.clearCheck();
                    startActivity(myIntent);
                } else if (btn3.isChecked()) {
                    myIntent.putExtra("Class","Druid");
                    myIntent.putExtra("Race", "Recommended Class:\nHuman, Gnome, Tiefling, Elf");
                    grp1.clearCheck();
                    startActivity(myIntent);
                } else {
                    Toast.makeText(CharacterAbridgedActivity.this, "Please select an answer!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}