package com.aetherbreaker.rickhammer

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aetherbreaker.rickhammer.DeskMaterial
import kotlin.collections.mapOf;

class MainActivity : AppCompatActivity() {
    private var inputOrderNum: EditText? = null
    private var inputCustomerName: EditText? = null
    private var inputDeskLengthInches: EditText? = null
    private var previewLengthPrice: TextView? = null
    private var inputDeskWidthInches: EditText? = null
    private var previewWidthPrice: TextView? = null
    private var inputDeskDrawerCount: EditText? = null
    private var previewDrawerPrice: TextView? = null
    private var radioMaterialSelect: RadioGroup? = null

    private var dataMap = mapOf(
        "orderNum" to 0,
        "customerName" to "",
        "deskLengthInches" to 0.0,
        "deskWidthInches" to 0.0,
        "deskDrawerCount" to 0,
        "materialSelect" to DeskMaterial.OAK
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize all the views
        inputOrderNum = findViewById(R.id.inputOrderNum)
        inputCustomerName = findViewById(R.id.inputCustomerName)
        inputDeskLengthInches = findViewById(R.id.inputDeskLengthInches)
        previewLengthPrice = findViewById(R.id.previewLengthPrice)
        inputDeskWidthInches = findViewById(R.id.inputDeskWidthInches)
        previewWidthPrice = findViewById(R.id.previewWidthPrice)
        inputDeskDrawerCount = findViewById(R.id.inputDeskDrawerCount)
        previewDrawerPrice = findViewById(R.id.previewDrawerPrice)
        radioMaterialSelect = findViewById(R.id.radioMaterialSelect)


    }
}