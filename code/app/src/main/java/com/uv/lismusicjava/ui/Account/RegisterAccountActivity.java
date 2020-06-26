package com.uv.lismusicjava.ui.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.uv.lismusicjava.HomeActivity;
import com.uv.lismusicjava.R;

import java.text.DateFormat;
import java.util.Calendar;

public class RegisterAccountActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
     EditText email, username, password, firstName,lastName;
     Button buttonRegister;
     CheckBox checkBoxTermsAndConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        chooseDateOfBirthday();
        showTermsAndConditions();



    }
    public void registerAccount(View view){
        if(validateNotEmptyFields()){
            Toast.makeText(this, "Register...",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean validateNotEmptyFields() {
        email = findViewById(R.id.textEditEmail);
        username = findViewById(R.id.textEditUsername);
        password = findViewById(R.id.textEditPassword);
        firstName = findViewById(R.id.textEditFirstName);
        lastName = findViewById(R.id.textEditLastName);
        checkBoxTermsAndConditions = findViewById(R.id.checkBoxTermsAndConditions);

        String emailJoined = email.getText().toString();
        String usernameJoined = username.getText().toString();
        String passwordJoined = password.getText().toString();
        String firstNameJoined = firstName.getText().toString();
        String lastNameJoined = lastName.getText().toString();

        if(emailJoined.isEmpty()){
            Toast.makeText(this, "Please enter an email",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(usernameJoined.isEmpty()){
            Toast.makeText(this, "Please enter an username",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(passwordJoined.isEmpty()){
            Toast.makeText(this, "Please enter a password",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(firstNameJoined.isEmpty()){
            Toast.makeText(this, "Please enter a first name",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lastNameJoined.isEmpty()) {
            Toast.makeText(this, "Please enter a last name",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!checkBoxTermsAndConditions.isChecked()){
            Toast.makeText(this, "You should accept Terms and Conditions",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(calendar.getTime());

        TextView textDate = findViewById(R.id.textDate);
        textDate.setText(currentDateString);
    }

    public void onRadioButtonClick(View view) {
        RadioGroup radioGroupGender = findViewById(R.id.radioGroupGenders);
        RadioButton radioButtonSelected = findViewById(radioGroupGender.getCheckedRadioButtonId());
        Toast.makeText(this, radioButtonSelected.getText() + " is selected", Toast.LENGTH_SHORT).show();
    }



    private void chooseDateOfBirthday(){
        Button buttonDate = findViewById(R.id.buttonDate);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date picker");
            }
        });
    }
    private void showTermsAndConditions() {
        TextView termsAndConditions = findViewById(R.id.textViewTermsAndConditions);
        termsAndConditions.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterAccountActivity.this, PopUpTermsAndConditions.class));
            }
        });
    }

    private void goHomeScreen(){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
