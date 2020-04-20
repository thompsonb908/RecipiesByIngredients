package com.example.recipiesbyingredients.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.recipiesbyingredients.MainActivity;
import com.example.recipiesbyingredients.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    Button btnLogin,btnRegister;
    EditText etUsername, etPassword;
    CallbackFragment callbackFragment;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);

        mAuth = FirebaseAuth.getInstance();

        etUsername = view.findViewById(R.id.log_username);
        etPassword = view.findViewById(R.id.log_password);

        btnLogin = view.findViewById(R.id.log_login);
        btnRegister = view.findViewById(R.id.log_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((etUsername.getText().toString() == "") || (etPassword.getText().toString()=="")){
                    Toast.makeText((Activity) getContext(), "Input username and password",Toast.LENGTH_SHORT);
                } else {
                    mAuth.signInWithEmailAndPassword(etUsername.getText().toString(),etPassword.getText().toString())
                            .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("login", "signInWithEmail:success");
//                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent = new Intent(getContext(), MainActivity.class);
//                                        intent.putExtra("user", user);
                                        startActivity(intent);
                                    } else {
                                        Log.w("login", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(getContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callbackFragment!=null){
                    callbackFragment.changeFragment();
                }

            }
        });

        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment){
        this.callbackFragment = callbackFragment;
    }
}
