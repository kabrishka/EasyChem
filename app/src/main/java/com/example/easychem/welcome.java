package com.example.easychem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.easychem.models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class welcome extends AppCompatActivity {

    private ConstraintLayout welcome_page;

    //подключаем БД
    FirebaseAuth auth; //для авторизации
    FirebaseDatabase db; //для подключения к бд
    DatabaseReference users; //для работы с таблицами внутри бд

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().hide();

        welcome_page = findViewById(R.id.welcome_page);
        LinearLayout btnLogIn = findViewById(R.id.btnLogIn);
        LinearLayout btnRegister = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance(); //запуск авторизации в бд
        db =FirebaseDatabase.getInstance(); //поключение к бд
        users = db.getReference("Users");


        btnLogIn.setOnClickListener(view -> showLogInWindow());


        btnRegister.setOnClickListener(view -> showRegisterWindow());


    }

    private void showLogInWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("LogIn");
        dialog.setMessage("Enter all data for sign in");

        LayoutInflater inflater = LayoutInflater.from(this);
        View signInWindow = inflater.inflate(R.layout.login_window, null);
        dialog.setView(signInWindow);

        MaterialEditText password = signInWindow.findViewById(R.id.passwordField);
        MaterialEditText email = signInWindow.findViewById(R.id.emailField);

        //кнопка для отмены всплывающего окна
        dialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
        //кнопка регистрации в бд
        dialog.setPositiveButton("Sign In", (dialogInterface, i) -> {
            if(TextUtils.isEmpty(email.getText().toString())) {
                Snackbar.make(welcome_page, "Enter your email", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(password.getText().toString().length() < 5) {
                Snackbar.make(welcome_page, "Please enter a password longer than 5 characters", Snackbar.LENGTH_SHORT).show();
                return;
            }
            //когда успешно авторизовлаись
            auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnSuccessListener(authResult -> {
                        Intent intent = new Intent(welcome.this, MainActivity.class);
                        intent.putExtra("user_email", email.getText().toString());
                        intent.putExtra("user_pass", password.getText().toString());
                        startActivity(intent);
                        finish();
                    }).addOnFailureListener(e -> Snackbar.make(welcome_page, "Error" + e.getMessage(), Snackbar.LENGTH_SHORT).show());

        });
        dialog.show();
    }

    private void showRegisterWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Registration");
        dialog.setMessage("Enter all data for registration");

        LayoutInflater inflater = LayoutInflater.from(this);
        View registerWindow = inflater.inflate(R.layout.register_window, null);
        dialog.setView(registerWindow);

        MaterialEditText name = registerWindow.findViewById(R.id.nameField);
        MaterialEditText password = registerWindow.findViewById(R.id.passwordField);
        MaterialEditText email = registerWindow.findViewById(R.id.emailField);

        //кнопка для отмены всплывающего окна
        dialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
        //кнопка регистрации в бд
        dialog.setPositiveButton("Register", (dialogInterface, i) -> {
            if(TextUtils.isEmpty(name.getText().toString())) {
                Snackbar.make(welcome_page, "Enter your name", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(email.getText().toString())) {
                Snackbar.make(welcome_page, "Enter your email", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if(password.getText().toString().length() < 5) {
                Snackbar.make(welcome_page, "Please enter a password longer than 5 characters", Snackbar.LENGTH_SHORT).show();
                return;
            }

            //регистрация пользователя
            //вызовется если пользователь был успешно добавлен в БД
            auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnSuccessListener(authResult -> {
                        User user = new User(name.getText().toString(),
                                email.getText().toString(),
                                password.getText().toString());

                        //ключ для пользователя
                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(unused -> {
                            Snackbar.make(welcome_page, "User added!", Snackbar.LENGTH_SHORT).show();
                            Intent intent = new Intent(welcome.this, MainActivity.class);
                            intent.putExtra("user_email", user.getEmail());
                            intent.putExtra("user_pass", user.getPassword());
                            startActivity(intent);
                            finish();
                        });
                    });
        });
        dialog.show();
    }
}