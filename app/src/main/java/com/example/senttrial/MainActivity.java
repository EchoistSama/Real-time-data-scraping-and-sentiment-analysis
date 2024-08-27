package com.example.senttrial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button buttonSubmit;
    private Spinner spinner;
    private String userInput;
    private String selectedSpinnerItem; // Variable to store selected spinner item
    private boolean isSpinnerUpdated = false; // Flag to check if the spinner has been updated
    private static ArrayList<String> result; // Static variable to store the result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        editTextInput = findViewById(R.id.editTextInput);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        spinner = findViewById(R.id.spinner);

        // Initialize AssetReader and read the asset file
        AssetReader assetReader = new AssetReader(this);
        String vaderLexiconContent = assetReader.readAsset("vader_lexicon.txt");

        // Pass the file content to the Python script
        Python py = Python.getInstance();
        py.getModule("SentAnalysisModel").callAttr("load_vader_lexicon_from_string", vaderLexiconContent);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSpinnerUpdated) {
                    userInput = editTextInput.getText().toString();
                    // Show a toast message with the input value (optional)
                    Toast.makeText(MainActivity.this, "Input: " + userInput, Toast.LENGTH_SHORT).show();

                    // Run the Python script to set the input and get DataFrame items
                    Python py = Python.getInstance();
                    PyObject pyObj = py.getModule("script").callAttr("set_input", userInput); //Add Try & Catch to handle 'Organic_result' key not found due to reaching API limit
                    List<PyObject> pyList = pyObj.asList();

                    // Convert the list of PyObjects to a list of strings
                    String[] items = new String[pyList.size()];
                    for (int i = 0; i < pyList.size(); i++) {
                        items[i] = pyList.get(i).toString();
                    }

                    // Update the Spinner with new items
                    ArrayAdapter<String> newAdapter = new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_spinner_item, items);
                    newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(newAdapter);

                    isSpinnerUpdated = true; // Set the flag to true
                    buttonSubmit.setText("Submit Selected Item"); // Change button text (optional)
                } else {
                    selectedSpinnerItem = spinner.getSelectedItem().toString(); // Get selected item

                    // Show toast message with the selected item (optional)
                    Toast.makeText(MainActivity.this, "Selected Item: " + selectedSpinnerItem, Toast.LENGTH_SHORT).show();

                    // Run the Python script to process the selected item
                    Python py = Python.getInstance();
                    PyObject pyObj = py.getModule("script").callAttr("process_data", selectedSpinnerItem);
                    List<PyObject> resultList = pyObj.asList();
                    result = fetchResultFromScript(selectedSpinnerItem);
                    // Convert the result to a single string
                    StringBuilder resultBuilder = new StringBuilder();
                    for (PyObject resultItem : resultList) {
                        resultBuilder.append(resultItem.toString()).append("\n");
                    }
                    String result = resultBuilder.toString();

                    // Split the result string into an ArrayList<String> based on new line characters
                    ArrayList<String> resultList2 = new ArrayList<>(Arrays.asList(result.split("\n")));
                    Log.d("MainActivity", "Collectedd resultList2 size: " + (resultList2 != null ? resultList2.size() : 0));


                    // Show result from Python script (optional)
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();

                    // Move to the new activity and pass the result
                    if (result != null && !result.isEmpty()) {
                        // Move to the new activity and pass the result
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putStringArrayListExtra("result1", resultList2);
                        startActivity(intent);;
                        Log.d("MainActivity", "Sending result1 size: " + (resultList2 != null ? resultList2.size() : 0));
                    } else {
                        Toast.makeText(MainActivity.this, "3 Result is null or empty", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    // Method to fetch result from Python script
    private ArrayList<String> fetchResultFromScript(String selectedSpinnerItem) {
        Python py = Python.getInstance();
        PyObject pyObj = py.getModule("script").callAttr("process_data", selectedSpinnerItem);
        ArrayList<String> resultListforThird = new ArrayList<>();
        for (PyObject obj : pyObj.asList()) {
            resultListforThird.add(obj.toString());
        }
        return resultListforThird;
    }

    // Method to get the user input if needed elsewhere in the code
    public String getUserInput() {
        return userInput;
    }

    // Method to get the selected spinner item if needed elsewhere in the code
    public String getSelectedSpinnerItem() {
        return selectedSpinnerItem;
    }
    public static ArrayList<String> getResult() {
        return result;
    }
}

