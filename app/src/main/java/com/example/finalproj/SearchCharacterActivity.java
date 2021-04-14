package com.example.finalproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchCharacterActivity extends AppCompatActivity {

    private List<character> characterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private characterAdapter adapter;
    private Button searchButton;
    private Spinner raceSpinner, classSpinner, backgroundSpinner;
    private TextView racetext, classtext, backgroundtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_character2);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.characterSearchRecycler);
        adapter = new characterAdapter(characterList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        searchButton = findViewById(R.id.searchButton);
        raceSpinner = (Spinner) findViewById(R.id.RaceSpinner);
        classSpinner = (Spinner) findViewById(R.id.ClassSpinner);
        backgroundSpinner = (Spinner) findViewById(R.id.BackgroundSpinner);
        racetext = findViewById(R.id.textView4);
        classtext = findViewById(R.id.textView6);
        backgroundtext = findViewById(R.id.textView7);

        ArrayAdapter<String> myRaceAdapter = new ArrayAdapter<String>(SearchCharacterActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.RaceArray));
        myRaceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> myClassAdapter = new ArrayAdapter<String>(SearchCharacterActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ClassArray));
        myClassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> myBackgroundAdapter = new ArrayAdapter<String>(SearchCharacterActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.BackgroundArray));
        myBackgroundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(myRaceAdapter);
        classSpinner.setAdapter(myClassAdapter);
        backgroundSpinner.setAdapter(myBackgroundAdapter);

        db.collection("character")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();
                            Toast.makeText(getApplicationContext(), "Query Made", Toast.LENGTH_LONG).show();
                            for (int i = 0; i < myListOfDocuments.size(); i++) {
                                String charID = myListOfDocuments.get(i).getId();
                                String charName = myListOfDocuments.get(i).get("Name").toString();
                                characterList.add(new character(charID, charName));
                                adapter.notifyDataSetChanged();
                            }


                        }
                    }
                });
    }

    public void FilterResults(View view) {
        characterList.clear();
        adapter.notifyDataSetChanged();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String filterRace = raceSpinner.getSelectedItem().toString();
        String filterClass = classSpinner.getSelectedItem().toString();
        String filterBackground = backgroundSpinner.getSelectedItem().toString();


        //bool for ignore filter?
        db.collection("character")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();
                            for (int i=0; i<myListOfDocuments.size(); i++) {
                                if(filterRace.equals(myListOfDocuments.get(i).get("Race").toString()) && filterClass.equals(myListOfDocuments.get(i).get("Class").toString()) && filterBackground.equals(myListOfDocuments.get(i).get("Background").toString())) {
                                    String charID = myListOfDocuments.get(i).getId();
                                    String charName = myListOfDocuments.get(i).get("Name").toString();
                                    characterList.add(new character(charID, charName));
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(getApplicationContext(),"Query Made",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });
        }

    public void ClearFilters(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        characterList.clear();
        adapter.notifyDataSetChanged();
        db.collection("character")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();
                            Toast.makeText(getApplicationContext(), "Query Made", Toast.LENGTH_LONG).show();
                            for (int i = 0; i < myListOfDocuments.size(); i++) {
                                String charID = myListOfDocuments.get(i).getId();
                                String charName = myListOfDocuments.get(i).get("Name").toString();
                                characterList.add(new character(charID, charName));
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }
}

