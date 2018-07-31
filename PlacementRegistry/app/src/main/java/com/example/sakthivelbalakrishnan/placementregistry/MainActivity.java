package com.example.sakthivelbalakrishnan.placementregistry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout signup;
    private EditText username,password,cpassword;
    private Button signupbutton;
    private TextView signinoption;
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
                startActivity(new Intent(this,midActivity.class));
            }else{
            finish();
            startActivity(new Intent(getApplicationContext(),activation.class));}
        }
        signup=new RelativeLayout(this);
        username=new EditText(this);
        username.setGravity(Gravity.CENTER);
        username.setBackground(gd);
        username.setHint("Email");
        password=new EditText(this);
        password.setGravity(Gravity.CENTER);
        password.setBackground(gd);
        password.setHint("Password");
        password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        cpassword=new EditText(this);
        cpassword.setGravity(Gravity.CENTER);
        cpassword.setBackground(gd);
        cpassword.setHint("Confirm Password");
        cpassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        cpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        signupbutton=new Button(this);
        signupbutton.setText("SIGN UP");
        signinoption=new TextView(this);
        signinoption.setText("Already a user! SIGN IN");
        signinoption.setGravity(Gravity.CENTER);
        username.setId('1');
        username.setMaxLines(1);
        username.setHorizontallyScrolling(true);
        password.setId('2');
        password.setMaxLines(1);
        password.setHorizontallyScrolling(true);
        cpassword.setId('3');
        cpassword.setMaxLines(1);
        cpassword.setHorizontallyScrolling(true);
        signupbutton.setId('4');
        RelativeLayout.LayoutParams usernamepos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT
                );
        usernamepos.addRule(RelativeLayout.CENTER_IN_PARENT);
        RelativeLayout.LayoutParams signuppos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams passwordpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        passwordpos.addRule(RelativeLayout.BELOW,username.getId());
        RelativeLayout.LayoutParams cpasswordpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        cpasswordpos.addRule(RelativeLayout.BELOW,password.getId());
        passwordpos.addRule(RelativeLayout.BELOW,username.getId());
        RelativeLayout.LayoutParams buttonpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        buttonpos.addRule(RelativeLayout.BELOW,cpassword.getId());
        RelativeLayout.LayoutParams textpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textpos.addRule(RelativeLayout.BELOW,signupbutton.getId());
        username.setLayoutParams(usernamepos);
        password.setLayoutParams(passwordpos);
        cpassword.setLayoutParams(cpasswordpos);
        signupbutton.setLayoutParams(buttonpos);
        signup.setLayoutParams(signuppos);
        signinoption.setLayoutParams(textpos);
        signup.addView(username);
        signup.addView(password);
        signup.addView(cpassword);
        signup.addView(signupbutton);
        signup.addView(signinoption);

        signupbutton.setOnClickListener(this);
        signinoption.setOnClickListener(this);

        signup.setBackground(drawable);
        setContentView(signup);
    }

    private boolean isValid(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    private void registeruser(){
        String email=username.getText().toString().trim();
        String pass=password.getText().toString().trim();
        String cpass=cpassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter a valid email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isValid(email)){
            Toast.makeText(this,"Please enter a valid email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter a valid password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(cpass)){
            Toast.makeText(this,"Please enter a valid confirm password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(pass.equals(cpass))){
            Toast.makeText(this,"Passwords are not matching",Toast.LENGTH_SHORT).show();
            return;
        }
        progress.setMessage("Registering!");
        progress.show();

        dbobj.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.dismiss();
                       if(task.isSuccessful()){
                           Toast.makeText(MainActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                           finish();
                           startActivity(new Intent(getApplicationContext(),activation.class));
                       }
                       else {
                           Toast.makeText(MainActivity.this,"Registeration failed",Toast.LENGTH_SHORT).show();

                       }
                    }
                });

    }
    @Override
    public void onClick(View view) {
        if(view==signupbutton){
            registeruser();
        }
        if(view==signinoption){
            finish();
            startActivity(new Intent(this,Login.class));
        }
    }
}
