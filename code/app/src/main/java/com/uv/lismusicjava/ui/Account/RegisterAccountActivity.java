package com.uv.lismusicjava.ui.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.uv.lismusicjava.Account.Account;
import com.uv.lismusicjava.ui.login.LoginActivity;
import com.uv.lismusicjava.R;
import java.util.regex.Pattern;

public class RegisterAccountActivity extends AppCompatActivity {
    EditText email, username, password, firstName, lastName, birthdate;
    CheckBox checkBoxTermsAndConditions;
    RadioGroup radioGroupGender;
    RadioButton radioButtonMale, radioButtonFemale;
    AccountViewModel accountViewModel;
    private static final String coverDefault = "defaultAccountCover.png";
    private static final String systemRegister = "System";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        configElementsActivity();
        setUpViewModel();

    }


    private void configElementsActivity(){
        email = findViewById(R.id.textEditEmail);
        firstName = findViewById(R.id.textEditFirstName);
        lastName = findViewById(R.id.textEditLastName);
        password = findViewById(R.id.textEditPassword);
        username = findViewById(R.id.textEditUsername);
        birthdate = findViewById(R.id.textDate);
        radioGroupGender = findViewById(R.id.radioGroupGenders);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        checkBoxTermsAndConditions = findViewById(R.id.checkBoxTermsAndConditions);
    }
    public  void setUpViewModel(){
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        accountViewModel.init();
        accountViewModel.getToastObserver().observe(this, response ->{
            Toast.makeText(this,response, Toast.LENGTH_SHORT).show();
        });
    }

    public void registerAccount(View view) {
        Account account= getValuesAccountRegistered();
        if(validateEmail(account.getEmail())){
            if(validateSelectedTermsAndConditions()){
                if(accountViewModel.validateFieldsRegister(account)){
                    accountViewModel.getRegisterAccountResponse().observe(this, response ->{
                        if(response!=null){
                            goLoginScreen();
                            cleanFields();
                        }
                    });
                }else{
                    accountViewModel.getRegisterAccountError().observe(this,messageError ->{
                        Toast.makeText(this, messageError,Toast.LENGTH_SHORT).show();
                    });
                }
            }else{
                Toast.makeText(this,"Please accept terms and conditions",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"Please enter a valid email",Toast.LENGTH_SHORT).show();
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


    public void showTermsAndConditions(View view) {
        TextView termsAndConditions = findViewById(R.id.textViewTermsAndConditions);
        termsAndConditions.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterAccountActivity.this, PopUpTermsAndConditions.class));
            }
        });
    }

    private Account getValuesAccountRegistered(){
        String emailJoined = email.getText().toString();
        String firstNameJoined = firstName.getText().toString();
        String lastNameJoined = lastName.getText().toString();
        String passwordJoined = password.getText().toString();
        String userNameJoined = username.getText().toString();
        String birthDateJoined = birthdate.getText().toString();
        String genderJoined = getGender();

        return new Account(firstNameJoined,lastNameJoined,emailJoined,userNameJoined,
                passwordJoined,genderJoined,coverDefault,birthDateJoined, systemRegister);
    }
    private boolean validateSelectedTermsAndConditions(){
        return checkBoxTermsAndConditions.isChecked();
    }
    private String getGender(){
        String genderResult = "";
        if(radioButtonMale.isChecked()){
            genderResult = "Male";
        }else if (radioButtonFemale.isChecked()){
            genderResult = "Female";
        }
        return genderResult;
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
