package com.example.kalkulator

import android.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kalkulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var operand1: Double? = null
    private var operand2: Double? = null
    private var currentOperator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup tombol angka
        binding.btn0.setOnClickListener { appendNumber("0") }
        binding.btn1.setOnClickListener { appendNumber("1") }
        binding.btn2.setOnClickListener { appendNumber("2") }
        binding.btn3.setOnClickListener { appendNumber("3") }
        binding.btn4.setOnClickListener { appendNumber("4") }
        binding.btn5.setOnClickListener { appendNumber("5") }
        binding.btn6.setOnClickListener { appendNumber("6") }
        binding.btn7.setOnClickListener { appendNumber("7") }
        binding.btn8.setOnClickListener { appendNumber("8") }
        binding.btn9.setOnClickListener { appendNumber("9") }

        // Setup tombol operator
        binding.btnPlus.setOnClickListener { setOperator("+") }
        binding.btnMinus.setOnClickListener { setOperator("-") }
        binding.btnMultiply.setOnClickListener { setOperator("*") }
        binding.btnDivide.setOnClickListener { setOperator("/") }

        // Tombol untuk menghapus karakter terakhir
        binding.btnDelete.setOnClickListener {
            val currentText = binding.tvDisplay.text.toString()
            if (currentText.isNotEmpty()) {
                binding.tvDisplay.text = currentText.dropLast(1)
            }
        }

        // Tombol untuk menghitung hasil
        binding.btnEqual.setOnClickListener {
            calculateResult()
        }

        // Tombol untuk clear
        binding.btnClear.setOnClickListener {
            binding.tvDisplay.text = ""
            binding.tvResult.text = ""
            operand1 = null
            operand2 = null
            currentOperator = null
        }
    }

    // Fungsi untuk menambahkan angka ke dalam display
    private fun appendNumber(number: String) {
        binding.tvDisplay.append(number)
    }

    // Fungsi untuk mengatur operator
    private fun setOperator(operator: String) {
        if (operand1 == null) {
            operand1 = binding.tvDisplay.text.toString().toDoubleOrNull()
            binding.tvDisplay.text = ""
        }
        currentOperator = operator
    }

    // Fungsi untuk menghitung hasil
    private fun calculateResult() {
        operand2 = binding.tvDisplay.text.toString().toDoubleOrNull()

        if (operand1 != null && operand2 != null && currentOperator != null) {
            val result = when (currentOperator) {
                "+" -> operand1!! + operand2!!
                "-" -> operand1!! - operand2!!
                "*" -> operand1!! * operand2!!
                "/" -> operand1!! / operand2!!
                else -> 0.0
            }
            binding.tvResult.text = result.toString()

            // Tampilkan hasil menggunakan Toast
            Toast.makeText(this, "Hasil: $result", Toast.LENGTH_SHORT).show()

            // Reset untuk perhitungan selanjutnya
            operand1 = result
            binding.tvDisplay.text = ""
        } else {
            Toast.makeText(this, "Input tidak valid", Toast.LENGTH_SHORT).show()
        }
    }
}

//class MainActivity : AppCompatActivity() {
//    private var tvDisplay: TextView? = null
//    private var tvResult: TextView? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        tvDisplay = findViewById<TextView>(R.id.tv_display)
//        tvResult = findViewById<TextView>(R.id.tv_result)
//        val btnDelete = findViewById<Button>(R.id.btn_delete)
//
//        btnDelete.setOnClickListener { // Menghapus karakter terakhir pada tvDisplay
//            val currentText = tvDisplay.getText().toString()
//            if (currentText.length > 0) {
//                tvDisplay.setText(currentText.substring(0, currentText.length - 1))
//            }
//        }
//    }
//
//    fun onEqualClick(view: View?) {
//        // Kalkulasi hasil perhitungan
//        val expression = tvDisplay!!.text.toString()
//        val result = evaluateExpression(expression) // Metode untuk menghitung ekspresi matematika
//        tvResult!!.text = result.toString()
//
//        // Tampilkan hasil menggunakan Toast
//        Toast.makeText(this, "Hasil: $result", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun evaluateExpression(expression: String): Double {
//        // Implementasikan metode untuk menghitung ekspresi matematika
//        // Ini bisa menggunakan script evaluator atau library lain.
//        return 0 // Placeholder untuk hasil perhitungan
//    }
//}


//class MainActivity : AppCompatActivity() {
//
//    private lateinit var tvDisplay: TextView
//    private lateinit var tvResult: TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(res.layout.activity_main)
//
//        tvDisplay = findViewById(R.id.tv_display)
//        tvResult = findViewById(R.id.tv_result)
//        val btnDelete: Button = findViewById(R.id.btn_delete)
//
//        btnDelete.setOnClickListener {
//            // Menghapus karakter terakhir pada tvDisplay
//            val currentText = tvDisplay.text.toString()
//            if (currentText.isNotEmpty()) {
//                tvDisplay.text = currentText.substring(0, currentText.length - 1)
//            }
//        }
//    }
//
//    fun onEqualClick(view: android.view.View) {
//        // Kalkulasi hasil perhitungan
//        val expression = tvDisplay.text.toString()
//        val result = evaluateExpression(expression)  // Metode untuk menghitung ekspresi matematika
//        tvResult.text = result.toString()
//
//        // Tampilkan hasil menggunakan Toast
//        Toast.makeText(this, "Hasil: $result", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun evaluateExpression(expression: String): Double {
//        // Implementasikan metode untuk menghitung ekspresi matematika
//        // Ini bisa menggunakan script evaluator atau library lain.
//        return 0.0  // Placeholder untuk hasil perhitungan
//    }
//}


//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }
//}