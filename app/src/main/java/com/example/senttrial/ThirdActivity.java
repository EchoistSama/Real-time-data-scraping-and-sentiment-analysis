//package com.example.senttrial;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.util.Log;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//import com.chaquo.python.Python;
//import com.chaquo.python.PyObject;
//import java.util.ArrayList;
//import java.util.Iterator;
//
//public class ThirdActivity extends AppCompatActivity {
//
//    private LinearLayout pieChartContainer;
//    private TextView explanationText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_third);
//
//        // Retrieve the result ArrayList from MainActivity
//        ArrayList<String> resulting2 = MainActivity.getResult();
//        if (resulting2 != null && !resulting2.isEmpty()) {
//            // Convert Java ArrayList to Python list
//            Python py = Python.getInstance();
//            PyObject builtins = py.getModule("builtins");
//            PyObject pyList = builtins.callAttr("list", (Object) resulting2.toArray(new String[0]));
//            PyObject sentimentModel = py.getModule("SentAnalysisModel");
////            Convert ArrayList<String> to Python list of strings
//            // Call count_sentiments function
//            PyObject countResult = sentimentModel.callAttr("count_sentiments", pyList);
//
//            // Extract Java int values from Python result
//            int positiveCount = countResult.asList().get(0).toJava(int.class);
//            int neutralCount = countResult.asList().get(1).toJava(int.class);
//            int negativeCount = countResult.asList().get(2).toJava(int.class);
//            Log.d("ThirdActivity", "Positive Count: " + positiveCount);
//            Log.d("ThirdActivity", "Neutral Count: " + neutralCount);
//            Log.d("ThirdActivity", "Negative Count: " + negativeCount);
////
//            // Calculate percentages
//            float total = positiveCount + neutralCount + negativeCount;
//            float[] data = {100f * positiveCount / total, 100f * neutralCount / total, 100f * negativeCount / total};
//            String[] labels = {"Positive", "Neutral", "Negative"};
//            int[] colors = {Color.BLUE, Color.GREEN, Color.RED};
//
//            // Draw pie chart and set explanation text
//            drawPieChart(data, labels, colors);
//            setExplanationText();
//        } else {
//            // Handle case where result ArrayList is null or empty
//            // You may want to show a message or handle this scenario
//        }
//    }
//
//    private void drawPieChart(float[] data, String[] labels, int[] colors) {
//        LinearLayout pieChartContainer = findViewById(R.id.pieChartContainer);
//        pieChartContainer.removeAllViews(); // Clear previous views
//
//        float total = 0;
//        for (float value : data) {
//            total += value;
//        }
//        float startAngle = 0;
////        for (int i = 0; i < 3; i++) {
////            float angle = 360 * (data[i] / total);
////            PieChartSlice pieChartSlice = new PieChartSlice(this, 0, angle, colors[i], labels[i], data[i]);
////            pieChartContainer.addView(pieChartSlice);
////        }
////    }
//        for (int i = 0; i < data.length; i++) {
//            float sweepAngle = 360 * (data[i] / 100);
//            PieChartSlice pieChartSlice = new PieChartSlice(this, startAngle, sweepAngle, colors[i], labels[i], data[i]);
//            pieChartContainer.addView(pieChartSlice);
//            startAngle += sweepAngle;
//        }
//    }
//
//    private void setExplanationText() {
//        TextView explanationText = findViewById(R.id.explanationText);
//        explanationText.setText("This pie chart shows the overall sentiment distribution of the analyzed tweets.");
//    }
//}

//package com.example.senttrial;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//import com.chaquo.python.Python;
//import com.chaquo.python.PyObject;
//import java.util.ArrayList;
//
//public class ThirdActivity extends AppCompatActivity {
//
//    private LinearLayout pieChartContainer;
//    private TextView explanationText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_third);
//
//        // Retrieve the result ArrayList from MainActivity
//        ArrayList<String> resulting2 = MainActivity.getResult();
//        if (resulting2 != null && !resulting2.isEmpty()) {
//            // Convert Java ArrayList to Python list
//            Python py = Python.getInstance();
//            PyObject builtins = py.getModule("builtins");
//            PyObject pyList = builtins.callAttr("list", (Object) resulting2.toArray(new String[0]));
//            PyObject sentimentModel = py.getModule("SentAnalysisModel");
//
//            // Call count_sentiments function
//            PyObject countResult = sentimentModel.callAttr("count_sentiments", pyList);
//
//            // Extract Java int values from Python result
//            int positiveCount = countResult.asList().get(0).toJava(int.class);
//            int neutralCount = countResult.asList().get(1).toJava(int.class);
//            int negativeCount = countResult.asList().get(2).toJava(int.class);
//            Log.d("ThirdActivity", "Positive Count: " + positiveCount);
//            Log.d("ThirdActivity", "Neutral Count: " + neutralCount);
//            Log.d("ThirdActivity", "Negative Count: " + negativeCount);
//
//            // Calculate percentages
//            float total = positiveCount + neutralCount + negativeCount;
//            float[] data = {100f * positiveCount / total, 100f * neutralCount / total, 100f * negativeCount / total};
//            String[] labels = {"Positive", "Neutral", "Negative"};
//            int[] colors = {Color.BLUE, Color.GREEN, Color.RED};
//
//            // Log the calculated data
//            for (int i = 0; i < data.length; i++) {
//                Log.d("ThirdActivity", labels[i] + " percentage: " + data[i] + "%");
//            }
//
//            // Draw pie chart and set explanation text
//            drawPieChart(data, labels, colors);
//            setExplanationText();
//        } else {
//            // Handle case where result ArrayList is null or empty
//            Log.d("ThirdActivity", "resulting2 is null or empty");
//        }
//    }
//
//
//    private void drawPieChart(float[] data, String[] labels, int[] colors) {
//        LinearLayout pieChartContainer = findViewById(R.id.pieChartContainer);
//        pieChartContainer.removeAllViews(); // Clear previous views
//
//        float total = 0;
//        for (float value : data) {
//            total += value;
//        }
//
//        float startAngle = 0;
//        for (int i = 0; i < data.length; i++) {
//            float sweepAngle = 360 * (data[i] / 100);
//            Log.d("ThirdActivity", "Drawing " + labels[i] + " slice: startAngle=" + startAngle + ", sweepAngle=" + sweepAngle);
//            PieChartSlice pieChartSlice = new PieChartSlice(this, startAngle, sweepAngle, colors[i], labels[i], data[i]);
//            pieChartContainer.addView(pieChartSlice);
//            startAngle += sweepAngle + 1;
//        }
//    }
//
//    private void setExplanationText() {
//        TextView explanationText = findViewById(R.id.explanationText);
//        explanationText.setText("This pie chart shows the overall sentiment distribution of the analyzed tweets.");
//    }
//}

//package com.example.senttrial;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//import com.chaquo.python.Python;
//import com.chaquo.python.PyObject;
//import java.util.ArrayList;
//
//public class ThirdActivity extends AppCompatActivity {
//
//    private LinearLayout pieChartContainer;
//    private TextView explanationText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_third);
//
//        // Retrieve the result ArrayList from MainActivity
//        ArrayList<String> resulting2 = MainActivity.getResult();
//        if (resulting2 != null && !resulting2.isEmpty()) {
//            // Convert Java ArrayList to Python list
//            Python py = Python.getInstance();
//            PyObject builtins = py.getModule("builtins");
//            PyObject pyList = builtins.callAttr("list", (Object) resulting2.toArray(new String[0]));
//            PyObject sentimentModel = py.getModule("SentAnalysisModel");
//
//            // Call count_sentiments function
//            PyObject countResult = sentimentModel.callAttr("count_sentiments", pyList);
//
//            // Extract Java int values from Python result
//            int positiveCount = countResult.asList().get(0).toJava(int.class);
//            int neutralCount = countResult.asList().get(1).toJava(int.class);
//            int negativeCount = countResult.asList().get(2).toJava(int.class);
//            Log.d("ThirdActivity", "Positive Count: " + positiveCount);
//            Log.d("ThirdActivity", "Neutral Count: " + neutralCount);
//            Log.d("ThirdActivity", "Negative Count: " + negativeCount);
//
//            // Calculate percentages
//            float total = positiveCount + neutralCount + negativeCount;
//            float[] data = {100f * positiveCount / total, 100f * neutralCount / total, 100f * negativeCount / total};
//            String[] labels = {"Positive", "Neutral", "Negative"};
//            int[] colors = {Color.BLUE, Color.GREEN, Color.RED};
//
//            // Log the calculated data
//            for (int i = 0; i < data.length; i++) {
//                Log.d("ThirdActivity", labels[i] + " percentage: " + data[i] + "%");
//            }
//
//            // Draw pie chart and set explanation text
//            drawPieChart(data, labels, colors);
//            setExplanationText();
//        } else {
//            // Handle case where result ArrayList is null or empty
//            Log.d("ThirdActivity", "resulting2 is null or empty");
//        }
//    }
//
//    private void drawPieChart(float[] data, String[] labels, int[] colors) {
//        LinearLayout pieChartContainer = findViewById(R.id.pieChartContainer);
//        pieChartContainer.removeAllViews(); // Clear previous views
//
//        float startAngle = 0;
//        for (int i = 0; i < data.length; i++) {
//            float sweepAngle = 360 * (data[i] / 100);
//            Log.d("ThirdActivity", "Drawing " + labels[i] + " slice: startAngle=" + startAngle + ", sweepAngle=" + sweepAngle);
//            PieChartSlice pieChartSlice = new PieChartSlice(this, startAngle, sweepAngle, colors[i], labels[i], data[i]);
//            pieChartContainer.addView(pieChartSlice);
//            startAngle += sweepAngle + 1;  // Adding 1 degree gap for visibility
//        }
//    }
//
//    private void setExplanationText() {
//        TextView explanationText = findViewById(R.id.explanationText);
//        explanationText.setText("This pie chart shows the overall sentiment distribution of the analyzed tweets.");
//    }
//}

//package com.example.senttrial;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//import com.chaquo.python.Python;
//import com.chaquo.python.PyObject;
//import java.util.ArrayList;
//
//public class ThirdActivity extends AppCompatActivity {
//
//    private LinearLayout pieChartContainer;
//    private TextView explanationText;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_third);
//
//        // Retrieve the result ArrayList from the previous activity
//        ArrayList<String> resulting2 = MainActivity.getResult();
//        if (resulting2 != null && !resulting2.isEmpty()) {
//            // Convert Java ArrayList to Python list
//            Python py = Python.getInstance();
//            PyObject builtins = py.getModule("builtins");
//            PyObject pyList = builtins.callAttr("list", (Object) resulting2.toArray(new String[0]));
//            PyObject sentimentModel = py.getModule("SentAnalysisModel");
//
//            // Call count_sentiments function
//            PyObject countResult = sentimentModel.callAttr("count_sentiments", pyList);
//
//            // Extract Java int values from Python result
//            int positiveCount = countResult.asList().get(0).toJava(int.class);
//            int neutralCount = countResult.asList().get(1).toJava(int.class);
//            int negativeCount = countResult.asList().get(2).toJava(int.class);
//            Log.d("ThirdActivity", "Positive Count: " + positiveCount);
//            Log.d("ThirdActivity", "Neutral Count: " + neutralCount);
//            Log.d("ThirdActivity", "Negative Count: " + negativeCount);
//
//            // Calculate percentages
//            float total = positiveCount + neutralCount + negativeCount;
//            float[] data = {100f * positiveCount / total, 100f * neutralCount / total, 100f * negativeCount / total};
//            String[] labels = {"Positive", "Neutral", "Negative"};
//            int[] colors = {Color.BLUE, Color.GREEN, Color.RED};
//
//            // Log the calculated data
//            for (int i = 0; i < data.length; i++) {
//                Log.d("ThirdActivity", labels[i] + " percentage: " + data[i] + "%");
//            }
//
//            // Draw pie chart and set explanation text
//            drawPieChart(data, labels, colors);
//            setExplanationText();
//        } else {
//            // Handle case where result ArrayList is null or empty
//            Log.d("ThirdActivity", "resulting2 is null or empty");
//        }
//    }
//
//    private void drawPieChart(float[] data, String[] labels, int[] colors) {
//        pieChartContainer = findViewById(R.id.pieChartContainer);
//        pieChartContainer.removeAllViews(); // Clear previous views
//
//        float startAngle = 0;
//        for (int i = 0; i < data.length; i++) {
//            float sweepAngle = 360 * (data[i] / 100);
//            Log.d("ThirdActivity", "Drawing " + labels[i] + " slice: startAngle=" + startAngle + ", sweepAngle=" + sweepAngle);
//            PieChartSlice pieChartSlice = new PieChartSlice(this, startAngle, sweepAngle, colors[i], labels[i], data[i]);
//            pieChartContainer.addView(pieChartSlice);
//            startAngle += sweepAngle + 1;  // Adding 1 degree gap for visibility
//        }
//    }
//
//    private void setExplanationText() {
//        explanationText = findViewById(R.id.explanationText);
//        explanationText.setText("This pie chart shows the overall sentiment distribution of the analyzed tweets.");
//    }
//}

package com.example.senttrial;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.chaquo.python.Python;
import com.chaquo.python.PyObject;
import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    private PieChartView pieChartView;
    private TextView explanationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Retrieve the result ArrayList from the previous activity
        ArrayList<String> resulting2 = MainActivity.getResult();
        if (resulting2 != null && !resulting2.isEmpty()) {
            // Convert Java ArrayList to Python list
            Python py = Python.getInstance();
            PyObject builtins = py.getModule("builtins");
            PyObject pyList = builtins.callAttr("list", (Object) resulting2.toArray(new String[0]));
            PyObject sentimentModel = py.getModule("SentAnalysisModel");

            // Call count_sentiments function
            PyObject countResult = sentimentModel.callAttr("count_sentiments", pyList);

            // Extract Java int values from Python result
            int positiveCount = countResult.asList().get(0).toJava(int.class);
            int neutralCount = countResult.asList().get(1).toJava(int.class);
            int negativeCount = countResult.asList().get(2).toJava(int.class);
            Log.d("ThirdActivity", "Positive Count: " + positiveCount);
            Log.d("ThirdActivity", "Neutral Count: " + neutralCount);
            Log.d("ThirdActivity", "Negative Count: " + negativeCount);

            // Calculate percentages
            float total = positiveCount + neutralCount + negativeCount;
            float[] data = {100f * positiveCount / total, 100f * neutralCount / total, 100f * negativeCount / total};
            String[] labels = {"Positive", "Neutral", "Negative"};
            int[] colors = {Color.BLUE, Color.GREEN, Color.RED};

            // Log the calculated data
            for (int i = 0; i < data.length; i++) {
                Log.d("ThirdActivity", labels[i] + " percentage: " + data[i] + "%");
            }

            // Draw pie chart and set explanation text
            pieChartView = findViewById(R.id.pieChartView);
            pieChartView.setData(data, labels, colors);
            setExplanationText();
        } else {
            // Handle case where result ArrayList is null or empty
            Log.d("ThirdActivity", "resulting2 is null or empty");
        }
    }

    private void setExplanationText() {
        explanationText = findViewById(R.id.explanationText);
        explanationText.setText("This pie chart shows the overall sentiment distribution of the analyzed tweets.");
    }
}
