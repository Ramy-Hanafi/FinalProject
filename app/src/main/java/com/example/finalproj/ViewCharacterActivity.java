package com.example.finalproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class ViewCharacterActivity extends AppCompatActivity {

    private List<character> characterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private characterAdapter adapter;
    private Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_character);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.characterSearchRecycler);
        adapter = new characterAdapter(characterList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        searchButton = findViewById(R.id.searchButton);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getEmail();


                db.collection("character")
                        .whereEqualTo("Creator", uid)
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







        CreatePDF();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(getApplicationContext(), SearchCharacterActivity.class);
                startActivity(p);
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
        paragraph.add(new Paragraph("Name: user name   "));
        paragraph.add(new Paragraph("Race: User Race   "));
        paragraph.add(new Paragraph("Class: User Class   "));
        paragraph.add(new Paragraph("Background: User Background   \n"));
        paragraph.add(new Paragraph("Health Points: HP     Armour Class: AC     Hit Dice: HD    Speed: 30\n"));
        paragraph.add(new Paragraph("Strength: stat   Modifier: mod\n"));
        paragraph.add(new Paragraph("Dexterity: stat   Modifier: mod\n"));
        paragraph.add(new Paragraph("Constitution: stat   Modifier: mod\n"));
        paragraph.add(new Paragraph("Intelligence: stat   Modifier: mod\n"));
        paragraph.add(new Paragraph("Wisdom: stat   Modifier: mod\n"));
        paragraph.add(new Paragraph("Charisma: stat   Modifier: mod\n"));
        paragraph.add(new Paragraph("Strength: weapon   Modifier: mod    Damage: 1D6\n"));
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

        Toast.makeText(ViewCharacterActivity.this, "Created pdf", Toast.LENGTH_LONG).show();
    }


    public void viewPDF(View view) {
        Intent i = new Intent(getApplicationContext(), ViewCharacterPdf.class);
        startActivity(i);
    }
}