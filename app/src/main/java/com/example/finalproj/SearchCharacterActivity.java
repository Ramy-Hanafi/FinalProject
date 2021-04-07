package com.example.finalproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
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
}