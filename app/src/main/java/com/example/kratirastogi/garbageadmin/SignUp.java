package com.example.kratirastogi.garbageadmin;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
EditText email,password;
Button signup;
String em,p;
ImageView imageView;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        imageView=findViewById(R.id.imageView);
        Glide.with(this).asGif().load(R.mipmap.garbi).into(imageView);
        ImageView t=findViewById(R.id.imageView9);
        t.setBackgroundColor(Color.argb(50,255,140,0));
        email=findViewById(R.id.email);

        password=findViewById(R.id.password);
        signup=findViewById(R.id.signup);
  auth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 em=email.getText().toString();
                 p=password.getText().toString();
                if (TextUtils.isEmpty(em))
                {
                    email.setError("enter email");
                }
               else if (TextUtils.isEmpty(p))
                {
                    password.setError("enter password");
                }

  else {
                    //create user
                    auth.createUserWithEmailAndPassword(em, p)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(SignUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {

                                        startActivity(new Intent(SignUp.this, Registration.class));
                                        finish();
                                    }

                                }
                            });
                }


            }
        });
    }
}
