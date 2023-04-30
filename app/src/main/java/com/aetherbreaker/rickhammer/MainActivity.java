package com.aetherbreaker.rickhammer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private EditText inputOrderNum;
    private EditText inputCustomerName;

    private EditText inputDeskLengthInches;
    private EditText inputDeskWidthInches;
    private TextView previewAreaPrice;

    private EditText inputDeskDrawerCount;
    private TextView previewDrawerPrice;

    private RadioGroup radioMaterialSelect;

    private TextView viewTotalCost;

    private final Map<String, String> dataMap = new HashMap<String, String>() {{
        put("orderNum", "0");
        put("customerName", "Customer Name");
        put("deskLengthInches", "0");
        put("deskWidthInches", "0");
        put("deskDrawerCount", "0");
        put("deskMaterial", "Pine");
    }};

    DelayedTextWatcher.Callback orderNumCallback = (final Editable s) -> {
        dataMap.put("orderNum", s.toString());
        updateMainPreview();
    };

    DelayedTextWatcher.Callback customerNameCallback = (final Editable s) -> {
        dataMap.put("customerName", s.toString());
        updateMainPreview();
    };

    DelayedTextWatcher.Callback deskLengthInchesCallback = (final Editable s) -> {
        dataMap.put("deskLengthInches", s.toString());
        updateMainPreview();
        updateSubPreview(
                previewAreaPrice,
                calculateAreaPrice());
    };

    DelayedTextWatcher.Callback deskWidthInchesCallback = (final Editable s) -> {
        dataMap.put("deskWidthInches", s.toString());
        updateMainPreview();
        updateSubPreview(
                previewAreaPrice,
                calculateAreaPrice());
    };

    DelayedTextWatcher.Callback deskDrawerCountCallback = (final Editable s) -> {
        dataMap.put("deskDrawerCount", s.toString());
        updateMainPreview();
        updateSubPreview(
                previewDrawerPrice,
                Integer.parseInt(s.toString()) * 30);
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


        previewAreaPrice = findViewById(R.id.previewAreaPrice);
        previewDrawerPrice = findViewById(R.id.previewDrawerPrice);
        viewTotalCost = findViewById(R.id.viewTotalCost);

        radioMaterialSelect = findViewById(R.id.radioMaterialSelect);


        // Set up the listeners
        inputOrderNum.addTextChangedListener(new DelayedTextWatcher(orderNumCallback));
        inputCustomerName.addTextChangedListener(new DelayedTextWatcher(customerNameCallback));
        inputDeskLengthInches.addTextChangedListener(new DelayedTextWatcher(deskLengthInchesCallback));
        inputDeskWidthInches.addTextChangedListener(new DelayedTextWatcher(deskWidthInchesCallback));
        inputDeskDrawerCount.addTextChangedListener(new DelayedTextWatcher(deskDrawerCountCallback));
        radioMaterialSelect.setOnCheckedChangeListener(
            new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                    boolean isChecked = checkedRadioButton.isChecked();
                    if (isChecked) {
                        dataMap.put("deskMaterial", checkedRadioButton.getText().toString());
                        updateMainPreview();
                    }}});
    }

    private int calculateAreaPrice() {
        return (Integer.parseInt(dataMap.get("deskLengthInches")) * Integer.parseInt(dataMap.get("deskWidthInches")) >= 750) ? 50 : 0;
    }

    private int calculateTotalPrice() {
        int materialPrice = 0;
        switch (dataMap.get("deskMaterial")) {
            case "Mahogany":
                materialPrice = 150;
            case "Oak":
                materialPrice = 125;
        }
        return 200 + calculateAreaPrice() + (Integer.parseInt(dataMap.get("deskDrawerCount")) * 30) + materialPrice;
    }

    @SuppressLint("DefaultLocale")
    private void updateSubPreview(TextView view, int price) {
        view.setText(
                String.format(
                        "$%d",
                        price
                )
        );
    }

    @SuppressLint("DefaultLocale")
    private void updateMainPreview() {
        viewTotalCost.setText(
                String.format(
                        "Order Number: %d\n" +
                        "Customer Name: %s\n" +
                        "Desk Length: %f\n" +
                        "Desk Width: %f\n" +
                        "Desk Drawers: %d\n" +
                        "Desk Material: %s\n" +
                        "Total Cost: $%d",
                        Integer.parseInt(dataMap.get("orderNum")),
                        dataMap.get("customerName"),
                        Float.parseFloat(dataMap.get("deskLengthInches")),
                        Float.parseFloat(dataMap.get("deskWidthInches")),
                        Integer.parseInt(dataMap.get("deskDrawerCount")),
                        dataMap.get("deskMaterial"),
                        calculateTotalPrice()
                )
        );
    }
}