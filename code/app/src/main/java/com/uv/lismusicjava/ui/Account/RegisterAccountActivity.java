package com.uv.lismusicjava.ui.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.media.UnsupportedSchemeException;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.uv.lismusicjava.HomeActivity;
import com.uv.lismusicjava.LoginActivity;
import com.uv.lismusicjava.R;
import com.uv.lismusicjava.domain.Account;
import com.uv.lismusicjava.jsonmanagement.SingletonRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterAccountActivity extends AppCompatActivity {
    EditText email, username, password, firstName, lastName, birthdate;
    Button buttonRegister;
    CheckBox checkBoxTermsAndConditions;
    RadioGroup radioGroupGender;
    RadioButton radioButtonMale, radioButtonFemale;

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
        radioGroupGender = findViewById(R.id.radioGroupGenders);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        showTermsAndConditions();

    }

    public void registerAccount(View view) {
        if(validateNotEmptyFields()){
            createAccountPOST();
        }

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
        Map<String,String> paramsAccountMapJoined = putValuesForPost();
        JSONObject paramsAccount = new JSONObject(paramsAccountMapJoined);
        String ip = getString(R.string.ip);
        final String url = "http://" + ip + ":5000/account";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, paramsAccount, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Mensaje de exito", "Respuesta en JSON: " + response);
                cleanFields();
                goLoginScreen();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body;
               // String statusCode = String.valueOf(error.networkResponse.statusCode);
                if(error.networkResponse.data!=null){
                    try {
                        body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                        JSONObject jsonObjectError = new JSONObject(body);
                        String jsonErrorString = jsonObjectError.getString("error");
                        Log.i("Register", "Error in JSON: " + body);
                        Toast.makeText(getApplicationContext(),jsonErrorString,Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        SingletonRequestQueue.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private Map<String, String> putValuesForPost() {
        Account account = getValuesAccountRegistered();
        Map<String, String> paramsAccountMap = new HashMap();
        paramsAccountMap.put("firstName", account.getFirstName());
        paramsAccountMap.put("lastName", account.getLastName());
        paramsAccountMap.put("email", account.getEmail());
        paramsAccountMap.put("password", account.getPassword());
        paramsAccountMap.put("userName", account.getUsername());
        paramsAccountMap.put("gender", account.getGender());
        paramsAccountMap.put("birthday", account.getBirthDate());
        paramsAccountMap.put("cover", account.getCover());
        paramsAccountMap.put("typeRegister", account.getSocialMedia());
        return paramsAccountMap;
    }
    private Account getValuesAccountRegistered(){
        String emailJoined = email.getText().toString();
        String firstNameJoined = firstName.getText().toString();
        String lastNameJoined = lastName.getText().toString();
        String passwordJoined = password.getText().toString();
        String userNameJoined = username.getText().toString();
        String birthDateJoined = birthdate.getText().toString();
        String genderJoined = getGender();

        Account account = new Account(firstNameJoined,lastNameJoined,emailJoined,userNameJoined,
                passwordJoined,genderJoined,null,birthDateJoined, "System");
        return account;
    }
    private String getGender(){
        String genderResult;
        if(radioButtonMale.isChecked()){
            genderResult = "Male";
            return genderResult;
        }
        if(radioButtonFemale.isChecked()){
            genderResult = "Female";
            return genderResult;
        }
        return genderResult ="";
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
        }
        if(!validateEmail(emailJoined)){
            email.setError("Invalid email");
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
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
    private void cleanFields(){
        email.setText("");
        username.setText("");
        password.setText("");
        firstName.setText("");
        lastName.setText("");
        birthdate.setText("");
        radioGroupGender.clearCheck();
    }
    private boolean validateEmail(String emailForValidate) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(emailForValidate).matches();
    }
    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
