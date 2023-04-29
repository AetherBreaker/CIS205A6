package com.aetherbreaker.rickhammer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private EditText inputOrderNum;
    private EditText inputCustomerName;

    private EditText inputDeskLengthInches;
    private TextView previewLengthPrice;

    private EditText inputDeskWidthInches;
    private TextView previewWidthPrice;

    private EditText inputDeskDrawerCount;
    private TextView previewDrawerPrice;

    private RadioGroup radioMaterialSelect;

    private TextView viewTotalCost;

    private Map<String, String> dataMap = new HashMap<String, String>() {{
        put("orderNum", "0");
        put("customerName", "Customer Name");
        put("deskLengthInches", "0");
        put("deskWidthInches", "0");
        put("deskDrawerCount", "0");
        put("deskMaterial", "Pine");
    }};

    DelayedTextWatcher.callbackFunction orderNumCallback = (final Editable s) -> {
        dataMap.put("orderNum", s.toString());
        updatePreview();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all the views
        inputOrderNum = findViewById(R.id.inputOrderNum);
      inputCustomerName = findViewById(R.id.inputCustomerName);
        inputDeskLengthInches = findViewById(R.id.inputDeskLengthInches);
        inputDeskWidthInches = findViewById(R.id.inputDeskWidthInches);
        inputDeskDrawerCount = findViewById(R.id.inputDeskDrawerCount);


        previewLengthPrice = findViewById(R.id.previewLengthPrice);
        previewWidthPrice = findViewById(R.id.previewWidthPrice);
        previewDrawerPrice = findViewById(R.id.previewDrawerPrice);
        viewTotalCost = findViewById(R.id.viewTotalCost);

        radioMaterialSelect = findViewById(R.id.radioMaterialSelect);

        // Set up the listeners
        inputOrderNum.addTextChangedListener(new DelayedTextWatcher(orderNumCallback));

    }



    private void updatePreview() {
        viewTotalCost.setText(
                String.format(
                        "Order Number: %d\n" +
                        "Customer Name: %s\n" +
                        "Desk Length: %f\n" +
                        "Desk Width: %f\n" +
                        "Desk Drawers: %d\n" +
                        "Desk Material: %s\n",
                        Integer.parseInt(dataMap.get("orderNum")),
                        dataMap.get("customerName"),
                        Float.parseFloat(dataMap.get("deskLengthInches")),
                        Float.parseFloat(dataMap.get("deskWidthInches")),
                        Integer.parseInt(dataMap.get("deskDrawerCount")),
                        dataMap.get("deskMaterial")
                )
        );
    }
}