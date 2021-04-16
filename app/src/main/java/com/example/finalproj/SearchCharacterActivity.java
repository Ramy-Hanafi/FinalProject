package com.example.finalproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchCharacterActivity extends AppCompatActivity {

    private List<character> characterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private characterAdapter adapter;
    private Button searchButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference charRef = db.collection("character").document("AmnSxEnApyLtnDkQRwf5");
    private String TAG = "SearchCharacterActivity";

    private String KEY_RACE = "Race";
    private String KEY_CLASS = "Class";
    private String KEY_BACKGROUND = "Background";
    private String KEY_WEAPON = "Weapon";
    private String KEY_STRENGTH = "STR";
    private String KEY_DEXTERITY = "DEX";
    private String KEY_CON = "CON";
    private String KEY_INTELLIGENCE = "INT";
    private String KEY_WISDOM = "WIS";
    private String KEY_CHARISMA = "CHR";
    private String KEY_NAME = "Name";

    private String race, charClass, backg, weap, name, charDocName;
    private int str, dex, con, intel, wis, chr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_character2);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.characterSearchRecycler);
        adapter = new characterAdapter(characterList, new characterAdapter.ClickListener() {
            @Override
            public void onViewClicked(int position) {
                loadChar(position);

            }

            @Override
            public void onEditClicked(int position) {

            }
        });
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        searchButton = findViewById(R.id.searchButton);

        db.collection("character")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();
                            Toast.makeText(getApplicationContext(),"Query Made",Toast.LENGTH_LONG).show();
                            for (int i=0; i<myListOfDocuments.size(); i++) {
                                String charID = myListOfDocuments.get(i).getId();
                                String charName = myListOfDocuments.get(i).get("Name").toString();
                                characterList.add(new character(charID, charName));
                                adapter.notifyDataSetChanged();
                            }



                        }
                    }
                });

    }
    public void CreatePDF() {
        String path = getExternalFilesDir(null).toString()+"/user.pdf";
        File file = new File(path);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Document document = new com.itextpdf.text.Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();

        Font myfont = new Font(Font.FontFamily.HELVETICA, 24);

        Paragraph paragraph = new Paragraph();
        paragraph.add(new Paragraph("Name: " + name));
        paragraph.add(new Paragraph("Race: " + race));
        paragraph.add(new Paragraph("Class: " + charClass));
        paragraph.add(new Paragraph("Background: " + backg +  "\n"));
        paragraph.add(new Paragraph("Health Points: HP     Armour Class: AC     Hit Dice: HD    Speed: 30\n"));
        paragraph.add(new Paragraph("Strength: " + String.valueOf(str) + " Modifier: mod\n"));
        paragraph.add(new Paragraph("Dexterity: " + String.valueOf(dex) + " Modifier: mod\n"));
        paragraph.add(new Paragraph("Constitution: " + String.valueOf(con) + " Modifier: mod\n"));
        paragraph.add(new Paragraph("Intelligence: " + String.valueOf(intel) + " Modifier: mod\n"));
        paragraph.add(new Paragraph("Wisdom: " + String.valueOf(wis) + " Modifier: mod\n"));
        paragraph.add(new Paragraph("Charisma: " + String.valueOf(chr) + " Modifier: mod\n"));
        paragraph.add(new Paragraph("Strength: " + String.valueOf(str) +   " Modifier: mod\n"));
        paragraph.add(new Paragraph("Weapon: " + weap + " Damage: 1D6\n"));
        paragraph.add(new Paragraph("Features ________________________________________________________________________________________________" +
                "________________________________________________________________________________________________" +
                "________________________________________________________________________________________________" +
                "________________________________________________________________________________________________" +
                "________________________________________________________________________________________________" +
                "________________________________________________________________________________________________" +
                "________________________________________________________________________________________________"));
        paragraph.add(new Paragraph("Notes ___________________________________________________________________________________________________"+
                "___________________________________________________________________________________________________" +
                "___________________________________________________________________________________________________" +
                "___________________________________________________________________________________________________" +
                "___________________________________________________________________________________________________" +
                "___________________________________________________________________________________________________" +
                "___________________________________________________________________________________________________" +
                "___________________________________________________________________________________________________"));




        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();

        Toast.makeText(SearchCharacterActivity.this, "Created pdf", Toast.LENGTH_LONG).show();
    }


    public void viewPDF() {
        Intent i = new Intent(getApplicationContext(), ViewCharacterPdf.class);
        startActivity(i);
    }

    public void loadChar(int position){
        character character = adapter.characterList.get(position);
        charDocName = character.getCharacterID();
        charRef = db.collection("character").document(charDocName);
        charRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            if (documentSnapshot.exists()) {
                                race = documentSnapshot.getString(KEY_RACE);
                                charClass = documentSnapshot.getString(KEY_CLASS);
                                backg = documentSnapshot.getString(KEY_BACKGROUND);
                                weap = documentSnapshot.getString(KEY_WEAPON);
                                name = documentSnapshot.getString(KEY_NAME);
                                str = documentSnapshot.getLong(KEY_STRENGTH).intValue();
                                con = documentSnapshot.getLong(KEY_CON).intValue();
                                intel = documentSnapshot.getLong(KEY_INTELLIGENCE).intValue();
                                chr = documentSnapshot.getLong(KEY_CHARISMA).intValue();
                                dex = documentSnapshot.getLong(KEY_DEXTERITY).intValue();
                                wis = documentSnapshot.getLong(KEY_WISDOM).intValue();

                            } else {
                                Toast.makeText(SearchCharacterActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                            }
                            CreatePDF();
                            viewPDF();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SearchCharacterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
}