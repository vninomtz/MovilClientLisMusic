package com.uv.lismusicjava;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.uv.lismusicjava.ui.Account.RegisterAccountActivity;


public class LoginActivity extends AppCompatActivity {

    protected CallbackManager callbackManager;
    private TextView newAccountTextView;
    private TextView resetPasswordTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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



    public void principalLogin(View view) {

    }
}
