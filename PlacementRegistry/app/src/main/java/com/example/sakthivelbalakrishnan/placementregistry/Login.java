package com.example.sakthivelbalakrishnan.placementregistry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout signin;
    private EditText username,password;
    private Button signinbutton;
    private TextView signupoption;
    private ProgressDialog progress;
    private FirebaseAuth dbobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Resources res=getResources();
        Drawable drawable=res.getDrawable(R.drawable.bg);

        GradientDrawable gd=new GradientDrawable();
        gd.setStroke(3,Color.LTGRAY);
        gd.setCornerRadius(30f);

        super.onCreate(savedInstanceState);
        progress=new ProgressDialog(this);
        dbobj=FirebaseAuth.getInstance();

        if(dbobj.getCurrentUser()!=null){
            FirebaseUser user=dbobj.getCurrentUser();
            if(user.isEmailVerified()){
                finish();
                startActivity(new Intent(getApplicationContext(),midActivity.class));
            }else{
            finish();
            startActivity(new Intent(getApplicationContext(),activation.class));}

        }
        signin=new RelativeLayout(this);
        username=new EditText(this);
        username.setGravity(Gravity.CENTER);
        username.setBackground(gd);
        username.setHint("Email");
        username.setMaxLines(1);
        username.setHorizontallyScrolling(true);
        password=new EditText(this);
        password.setGravity(Gravity.CENTER);
        password.setBackground(gd);
        password.setHint("Password");
        password.setMaxLines(1);
        password.setHorizontallyScrolling(true);
        password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        signinbutton=new Button(this);
        signinbutton.setText("SIGN IN");
        signupoption=new TextView(this);
        signupoption.setText("Don't have an account sign up!");
        signupoption.setGravity(Gravity.CENTER);
        username.setId('1');
        password.setId('2');
        signinbutton.setId('4');
        RelativeLayout.LayoutParams usernamepos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        usernamepos.addRule(RelativeLayout.CENTER_IN_PARENT);
        RelativeLayout.LayoutParams signinpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams passwordpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        passwordpos.addRule(RelativeLayout.BELOW,username.getId());
        RelativeLayout.LayoutParams buttonpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        buttonpos.addRule(RelativeLayout.BELOW,password.getId());
        RelativeLayout.LayoutParams textpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textpos.addRule(RelativeLayout.BELOW,signinbutton.getId());
        username.setLayoutParams(usernamepos);
        password.setLayoutParams(passwordpos);
        signinbutton.setLayoutParams(buttonpos);
        signin.setLayoutParams(signinpos);
        signupoption.setLayoutParams(textpos);
        signin.addView(username);
        signin.addView(password);
        signin.addView(signinbutton);
        signin.addView(signupoption);

        signinbutton.setOnClickListener(this);
        signupoption.setOnClickListener(this);

        signin.setBackground(drawable);
        setContentView(signin);
    }
    private void userlogin(){
        String email=username.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter a valid email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter a valid password",Toast.LENGTH_SHORT).show();
            return;
        }
        progress.setMessage("Loging in!");
        progress.show();

        dbobj.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.dismiss();
                        if(task.isSuccessful()){
                            FirebaseUser user=dbobj.getCurrentUser();
                            if(user.isEmailVerified()){
                                finish();
                                startActivity(new Intent(getApplicationContext(),midActivity.class));
                            }else{
                            finish();
                            startActivity(new Intent(getApplicationContext(),activation.class));}
                        }
                        else{
                            Toast.makeText(Login.this,"Login failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if(view==signinbutton){
            userlogin();
        }
        if(view==signupoption){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

    }
}
