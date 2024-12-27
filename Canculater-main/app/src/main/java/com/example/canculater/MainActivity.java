package com.example.canculater;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String operator = ""; // Переменная для хранения текущего оператора
    String oldNumber; // Переменная для хранения старого числа
    Boolean isNew = true; // Флаг, указывающий, является ли ввод новым
    EditText editText; // Поле для отображения и ввода чисел

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText); // Инициализация поля ввода
    }

    // Метод для обработки нажатия на цифровые кнопки
    public void clickNumber(View view) {
        if (isNew)
            editText.setText(""); // Очистка поля, если ввод новый
        isNew = false;

        String number = editText.getText().toString(); // Получение текущего числа
        // Добавление цифры в зависимости от нажатой кнопки
        if (view.getId() == R.id.bu7) {
            number += "7";
        } else if (view.getId() == R.id.bu8) {
            number += "8";
        } else if (view.getId() == R.id.bu9) {
            number += "9";
        }
        if (view.getId() == R.id.bu0) {
            number += "0";
        } else if (view.getId() == R.id.bu1) {
            number += "1";
        } else if (view.getId() == R.id.bu2) {
            number += "2";
        } else if (view.getId() == R.id.bu3) {
            number += "3";
        } else if (view.getId() == R.id.bu4) {
            number += "4";
        } else if (view.getId() == R.id.bu5) {
            number += "5";
        } else if (view.getId() == R.id.bu6) {
            number += "6";
        }
        // Обработка точки (десятичного разделителя)
        if (view.getId() == R.id.buPoint) {
            if (PointIsTrue(number)) {
            } else {
                number += ".";
            }
        }
        // Обработка смены знака числа
        if (view.getId() == R.id.buPlusMin) {
            if (numberisZero(number)) {
            } else {
                if (PlMinIsTrue(number)) {
                    number = number.substring(1);
                } else {
                    number = "-" + number;
                }
            }
        }
        editText.setText(number); // Обновление текста в поле ввода
    }

    // Метод для обработки нажатия на операторы
    public void operation(View view) {
        isNew = true;
        oldNumber = editText.getText().toString(); // Сохранение старого числа
        // Определение оператора в зависимости от нажатой кнопки
        if (view.getId() == R.id.buDel) {
            operator = "/";
        } else if (view.getId() == R.id.bupMin) {
            operator = "-";
        } else if (view.getId() == R.id.buPlus) {
            operator = "+";
        } else if (view.getId() == R.id.buYmn) {
            operator = "*";
        }
    }

    // Метод для выполнения вычислений
    public void clickEquval(View view) {
        String newNumber = editText.getText().toString(); // Получение нового числа
        Double result = 0.0;
        // Выполнение операции в зависимости от оператора
        switch (operator) {
            case "-":
                result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                break;
            case "+":
                result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                break;
            case "*":
                result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                break;
            case "/":
                result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                break;
        }
        editText.setText(result + ""); // Отображение результата
    }

    // Метод для очистки поля ввода
    public void acClick(View view) {
        editText.setText("0");
        isNew = true;
    }

    // Метод для проверки наличия точки в числе
    public boolean PointIsTrue(String number) {
        if (number.indexOf(".") == -1) {
            return false;
        } else {
            return true;
        }
    }

    // Метод для проверки, является ли число отрицательным
    public boolean PlMinIsTrue(String number) {
        if (number.charAt(0) == '-') {
            return true;
        } else {
            return false;
        }
    }

    // Метод для вычисления процента
    public void ClickPercent(View view) {
        if (operator == "") {
            String number = editText.getText().toString();
            double temp = Double.parseDouble(number) / 100;
            number = temp + "";
            editText.setText(number);
        } else {
            oldNumber = editText.getText().toString();
            String newNumber = editText.getText().toString();
            Double result = 0.0;
            // Выполнение операции с учетом процента
            switch (operator) {
                case "-":
                    result = Double.parseDouble(oldNumber) - Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                    break;
                case "+":
                    result = Double.parseDouble(oldNumber) + Double.parseDouble(oldNumber) * Double.parseDouble(newNumber) / 100;
                    break;
                case "*":
                    result = Double.parseDouble(oldNumber) * Double.parseDouble(oldNumber) / 100;
                    break;
                case "/":
                    result = Double.parseDouble(oldNumber) / Double.parseDouble(oldNumber) * 100;
                    break;
            }
            editText.setText(result + "");
            operator = "";
        }
    }

    // Метод для проверки, является ли число нулем
    public boolean numberisZero(String number) {
        if (number.equals("0")) {
            return true;
        } else {
            return false;
        }
    }
}