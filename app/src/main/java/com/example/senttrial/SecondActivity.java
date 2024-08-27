package com.example.senttrial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.chaquo.python.Python;
import com.chaquo.python.PyObject;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Retrieve the result ArrayList from MainActivity
        Intent intent = getIntent();
        ArrayList<String> resulting = intent.getStringArrayListExtra("result1");
        Log.d("SecondActivity", "Received result1 size: " + (resulting != null ? resulting.size() : 0));
        if (resulting != null) {
            // Perform sentiment analysis on each tweet
            ArrayList<String> sentiments = new ArrayList<>();
            Python py = Python.getInstance();
            PyObject sentimentModel = py.getModule("SentAnalysisModel");
            for (String tweet : resulting) {
                String sentiment = sentimentModel.callAttr("predict_sentiment", tweet).toString();
                sentiments.add("Tweet: " + tweet + "\nSentiment: " + sentiment);
            }


            // Display the sentiments in a ListView
//            ListView listView = findViewById(R.id.listView);
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sentiments);
//            listView.setAdapter(adapter);

            // Show a dialog for next action
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Click Next for overall sentiment")
                    .setPositiveButton("Next", (dialog, which) -> {
                        if (sentiments != null && !sentiments.isEmpty()) {
                            // Navigate to ThirdActivity
                            Intent nextIntent = new Intent(SecondActivity.this, ThirdActivity.class);
                            intent.putStringArrayListExtra("resultThird", sentiments);
                            startActivity(nextIntent);
                            Log.d("SecondActivity", "2Received result size: " + (sentiments != null ? sentiments.size() : 0));
                        } else {
                            Toast.makeText(SecondActivity.this, "Sentiments list is null or empty", Toast.LENGTH_SHORT).show();
                        }

                    })
                    .setCancelable(false)
                    .create()
                    .show();

        } else {
            // Handle case where result ArrayList is null
            Toast.makeText(this, "1 Result is null", Toast.LENGTH_SHORT).show();
        }
    }
}