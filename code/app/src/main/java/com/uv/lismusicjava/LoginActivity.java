package com.uv.lismusicjava;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.uv.lismusicjava.domain.Account;
import com.uv.lismusicjava.jsonmanagement.SingletonRequestQueue;
import com.uv.lismusicjava.ui.Account.RegisterAccountActivity;
import com.uv.lismusicjava.utils.SingletonAccount;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    protected CallbackManager callbackManager;
    private TextView newAccountTextView;
    private TextView resetPasswordTextView;
    private Button buttonLoginSystem;
    private EditText editTextUser;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLoginSystem = findViewById(R.id.btn_login);
        buttonLoginSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithSystem();
            }
        });

        editTextUser = findViewById(R.id.textEmail);
        editTextPassword = findViewById(R.id.textEditPassword);

        LoginButton loginButton;
        ImageView principalLogo;

        principalLogo = findViewById(R.id.principalLogo);
        principalLogo.setImageResource(R.drawable.lismusic3);

        newAccountTextView = findViewById(R.id.newAccount);
        newAccountTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        resetPasswordTextView = findViewById(R.id.resetPassword);
        resetPasswordTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);

        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goHomeScreen();
            }
            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Operación cancelada", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Ocurrió un error al conectarse con facebook", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goHomeScreen() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void goRegisterAccountScreen(View view){
        Intent intent = new Intent(this, RegisterAccountActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goResetPasswordScreen(View view){
      //  Intent intent = new Intent(this, ResetPasswordActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void loginWithSystem() {
        if(validateFields()){
            createLoginRequest();
        }
    }

    private boolean validateFields(){
        boolean isValid = true;
        if(editTextUser.getText().toString().isEmpty()){
            isValid = false;
            Toast.makeText(this, "Please enter an username or email", Toast.LENGTH_SHORT).show();
        } else if(editTextPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return  isValid;
    }

    private void createLoginRequest() {
        Map<String,String> requestParams = getValuesForLoginRequest();
        JSONObject paramsAccount = new JSONObject(requestParams);
        String ip = getString(R.string.ip);
        final String url = "http://" + ip + ":5000/login";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, paramsAccount, handleResponseSuccess(),handleResponseError());
        SingletonRequestQueue.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private Map<String, String> getValuesForLoginRequest() {
        Map<String, String> paramsAccountMap = new HashMap();
        paramsAccountMap.put("user", editTextUser.getText().toString());
        paramsAccountMap.put("password", editTextPassword.getText().toString());
        return paramsAccountMap;
    }

    private Response.Listener<JSONObject> handleResponseSuccess(){
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Gson gson = new Gson();
                    Account account = gson.fromJson(response.getJSONObject("account").toString(), Account.class);
                    account.setAccesToken(response.getString("access_token"));
                    SingletonAccount.setSingletonAccount(account);
                    Toast.makeText(LoginActivity.this, "Welcome: " + SingletonAccount.getSingletonAccount().getFirstName(), Toast.LENGTH_SHORT).show();
                    goHomeScreen();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener handleResponseError() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String body = null;
                try {
                    body = new String(error.networkResponse.data, "UTF-8");
                    JSONObject errorObject = new JSONObject(body);

                    Toast.makeText(LoginActivity.this, errorObject.get("error").toString(), Toast.LENGTH_SHORT).show();
                } catch (UnsupportedEncodingException | JSONException e) {
                    e.printStackTrace();
                }

            }
        };
    }



}
