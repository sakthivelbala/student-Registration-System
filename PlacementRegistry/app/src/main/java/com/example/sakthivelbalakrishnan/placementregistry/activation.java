package com.example.sakthivelbalakrishnan.placementregistry;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activation extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout activationscreen;
    private TextView notact;
    private FirebaseAuth dbobj;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Resources res=getResources();
        Drawable drawable=res.getDrawable(R.drawable.bg);
        super.onCreate(savedInstanceState);
        dbobj=FirebaseAuth.getInstance();

        if (dbobj==null){
            finish();
            startActivity(new Intent(this,Login.class));
        }
        FirebaseUser user=dbobj.getCurrentUser();
        if(user.isEmailVerified()){
            finish();
            startActivity(new Intent(this,midActivity.class));
        }else{
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(activation.this,"verification email sent",Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        activationscreen=new RelativeLayout(this);
        notact=new TextView(this);
        notact.setText("YOUR ACCOUNT IS NOT ACTIVATED PLEASE CHECK THE REGISTERED EMAIL FOR ACTIVATION");
        notact.setGravity(Gravity.CENTER);
        notact.setId('1');
        logout=new Button(this);
        logout.setText("LOG OUT"+user.getEmail());
        logout.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams textpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        textpos.addRule(RelativeLayout.CENTER_IN_PARENT);
        RelativeLayout.LayoutParams logoutpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        logoutpos.addRule(RelativeLayout.BELOW,notact.getId());
        notact.setLayoutParams(textpos);
        logout.setLayoutParams(logoutpos);
        activationscreen.addView(notact);
        activationscreen.addView(logout);
        logout.setOnClickListener(this);

        activationscreen.setBackground(drawable);
        setContentView(activationscreen);
    }

    @Override
    public void onClick(View view) {
        if(view==logout){
            dbobj.signOut();
            finish();
            startActivity(new Intent(this,Login.class));
        }
    }
}
