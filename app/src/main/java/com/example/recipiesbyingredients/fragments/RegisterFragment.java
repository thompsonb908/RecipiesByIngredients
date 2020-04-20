package com.example.recipiesbyingredients.fragments;

import android.app.Activity;
import android.os.Bundle;
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
import androidx.fragment.app.FragmentTransaction;

import com.example.recipiesbyingredients.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.opencensus.tags.Tag;

public class RegisterFragment extends Fragment {

    Button btnRegister;
    EditText etUsername, etPassword;

    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);

        mAuth = FirebaseAuth.getInstance();

        etUsername = view.findViewById(R.id.reg_username);
        etPassword = view.findViewById(R.id.reg_password);

        btnRegister = view.findViewById(R.id.reg_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(etUsername.getText().toString(),etPassword.getText().toString())
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("register","createUserWithEmail:success");
                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    Fragment fragment = new LoginFragment();
                                    ft.replace(R.id.fragmentContainer,fragment);
                                    ft.commit();
                                } else {
                                    Log.w("register", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getContext(),"Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        return view;
    }
}
