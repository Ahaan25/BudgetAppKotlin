package com.example.budgetappkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switch:Switch=findViewById(R.id.switch1)
        val budgetButton=findViewById<Button>(R.id.button1)
        val transactionButton=findViewById<Button>(R.id.button2)

        budgetButton.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java)
            startActivity(intent)
        }

        transactionButton.setOnClickListener {
            val intent = Intent(this, TransactionsActivity::class.java)
            startActivity(intent)
        }


        val sharedPreferences=getSharedPreferences("theme", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        val nightMode=sharedPreferences.getBoolean("night", false)

        if(nightMode){
            switch.isChecked=true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        switch.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                editor.putBoolean("night", true)
                editor.apply()
                Toast.makeText(this@MainActivity, "Dark theme", Toast.LENGTH_SHORT).show()
            }
            else{
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                editor.putBoolean("night", false)
                editor.apply()
                Toast.makeText(this@MainActivity, "Light theme", Toast.LENGTH_SHORT).show()
            }
        }
    }
}