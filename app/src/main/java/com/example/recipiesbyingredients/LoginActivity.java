package com.example.recipiesbyingredients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.recipiesbyingredients.fragments.CallbackFragment;
import com.example.recipiesbyingredients.fragments.LoginFragment;
import com.example.recipiesbyingredients.fragments.RegisterFragment;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements CallbackFragment {

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
//    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        mAuth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addFragment();
    }

//    public void onButtonClicked(View view){
//
//        Button accountButton = (Button) findViewById(R.id.bAccount);
//        Button loginButton = (Button) findViewById(R.id.bLogin);
//
//        if(accountButton.isPressed()){
//            Toast.makeText(LoginActivity.this, "New account is stored locally. Created a new account and user can login", Toast.LENGTH_LONG).show();
//            addFragment();
//
//        } else if(loginButton.isPressed()) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
//    }

    public void addFragment(){
        LoginFragment fragment = new LoginFragment();
        fragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,fragment);
        fragmentTransaction.commit();
    }

    public void replaceFragment(){
        fragment = new RegisterFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void changeFragment() {
        replaceFragment();
    }
}
