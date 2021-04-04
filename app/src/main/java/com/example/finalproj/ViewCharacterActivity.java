package com.example.finalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

public class ViewCharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_character);
        CreatePDF();
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