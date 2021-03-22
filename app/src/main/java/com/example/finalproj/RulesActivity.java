package com.example.finalproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RulesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        ArrayList<RuleOutline> ruleList = new ArrayList<>();
        ruleList.add(new RuleOutline("\u2022 Rule 1: Every character belongs to a race, one of the many intelligent humanoid species in the D&D world."));
        ruleList.add(new RuleOutline("\u2022 Rule 2: The race you choose contributes to your character’s\n" +
                "identity in an important way, by establishing a general\n" +
                "appearance and the natural talents gained from culture\n" +
                "and ancestry"));
        ruleList.add(new RuleOutline("\u2022 Rule 2: The race you choose contributes to your character’s\n" +
                "identity in an important way, by establishing a general\n" +
                "appearance and the natural talents gained from culture\n" +
                "and ancestry"));
        ruleList.add(new RuleOutline("\u2022 Rule 2: The race you choose contributes to your character’s\n" +
                "identity in an important way, by establishing a general\n" +
                "appearance and the natural talents gained from culture\n" +
                "and ancestry"));
        ruleList.add(new RuleOutline("\u2022 Rule 2: The race you choose contributes to your character’s\n" +
                "identity in an important way, by establishing a general\n" +
                "appearance and the natural talents gained from culture\n" +
                "and ancestry"));

        mRecyclerView = findViewById(R.id.theTavernRules);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RuleAdapter(ruleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}