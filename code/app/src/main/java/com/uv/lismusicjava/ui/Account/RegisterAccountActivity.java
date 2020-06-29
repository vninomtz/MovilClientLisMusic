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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.uv.lismusicjava.HomeActivity;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.jsonmanagement.SingletonRequestQueue;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterAccountActivity extends AppCompatActivity {
    EditText email, username, password, firstName, lastName, birthdate;
    Button buttonRegister;
    CheckBox checkBoxTermsAndConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        email = findViewById(R.id.textEditEmail);
        firstName = findViewById(R.id.textEditFirstName);
        lastName = findViewById(R.id.textEditLastName);
        password = findViewById(R.id.textEditPassword);
        username = findViewById(R.id.textEditUsername);
        birthdate = findViewById(R.id.textDate);

        showTermsAndConditions();

    }

    public void registerAccount(View view) {
//        if(validateNotEmptyFields()){
//            Toast.makeText(this, "Register...",Toast.LENGTH_SHORT).show();
//            jsonParse();
//        }
          createAccountPOST();
    }

    public void onRadioButtonClick(View view) {
        RadioGroup radioGroupGender = findViewById(R.id.radioGroupGenders);
        RadioButton radioButtonSelected = findViewById(radioGroupGender.getCheckedRadioButtonId());
    }

    public void showDatePickerDialog(View view) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = year + "-" + (month + 1) + "-" + day;
                TextView textDate = findViewById(R.id.textDate);
                textDate.setText(selectedDate);
            }
        });
        newFragment.show(this.getSupportFragmentManager(), "datePicker");
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

    private void goHomeScreen() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void createAccountPOST() {
        Map<String,String> paramsAccountMapJoined = getValuesForForm();
        JSONObject paramsAccount = new JSONObject(paramsAccountMapJoined);
        String ip = getString(R.string.ip);
        final String url = "http://" + ip + ":5000/account";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, paramsAccount, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Mensaje de exito", "Respuesta en JSON: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Login", "Error Respuesta en JSON: " + error.getMessage());
            }
        });
        SingletonRequestQueue.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private Map<String, String> getValuesForForm() {

        String emailJoined = email.getText().toString();
        String firstNameJoined = firstName.getText().toString();
        String lastNameJoined = lastName.getText().toString();
        String passwordJoined = password.getText().toString();
        String userNameJoined = username.getText().toString();
        String birthDateJoined = birthdate.getText().toString();


        Map<String, String> paramsAccountMap = new HashMap();
        paramsAccountMap.put("firstName", firstNameJoined);
        paramsAccountMap.put("lastName", lastNameJoined);
        paramsAccountMap.put("email", emailJoined);
        paramsAccountMap.put("password", passwordJoined);
        paramsAccountMap.put("userName", userNameJoined);
        paramsAccountMap.put("gender", "Female");
        paramsAccountMap.put("birthday", birthDateJoined);
        paramsAccountMap.put("cover", "mifotoperfil.jpg");
        paramsAccountMap.put("typeRegister", "System");

        return paramsAccountMap;
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

        if (emailJoined.isEmpty()) {
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (usernameJoined.isEmpty()) {
            Toast.makeText(this, "Please enter an username", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (passwordJoined.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (firstNameJoined.isEmpty()) {
            Toast.makeText(this, "Please enter a first name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lastNameJoined.isEmpty()) {
            Toast.makeText(this, "Please enter a last name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!checkBoxTermsAndConditions.isChecked()) {
            Toast.makeText(this, "You should accept Terms and Conditions", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
