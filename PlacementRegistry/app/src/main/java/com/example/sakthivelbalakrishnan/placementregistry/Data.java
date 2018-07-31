package com.example.sakthivelbalakrishnan.placementregistry;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Data extends AppCompatActivity {

    private FirebaseAuth dbobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbobj=FirebaseAuth.getInstance();
        if (dbobj==null){
            finish();
            startActivity(new Intent(this,Login.class));
        }
        FirebaseUser user=dbobj.getCurrentUser();
        manageData obj=new manageData();
        GradientDrawable gd=new GradientDrawable();
        gd.setStroke(3, Color.LTGRAY);
        gd.setCornerRadius(30f);
        super.onCreate(savedInstanceState);
        RelativeLayout lastScreen=new RelativeLayout(this);
        TextView fname=new TextView(this);
        fname.setGravity(Gravity.CENTER);
        fname.setBackground(gd);
        fname.setTextColor(Color.LTGRAY);
        RelativeLayout.LayoutParams fnamepos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        fname.setText("FIRST NAME: "+obj.getFname());
        lastScreen.addView(fname,fnamepos);
        fname.setId('1');
        TextView lname=new TextView(this);
        lname.setTextColor(Color.LTGRAY);
        lname.setGravity(Gravity.CENTER);
        lname.setBackground(gd);
        lname.setText("LAST NAME: "+obj.getLname());
        RelativeLayout.LayoutParams lnamepos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        lnamepos.addRule(RelativeLayout.BELOW,fname.getId());
        lastScreen.addView(lname,lnamepos);
        lname.setId('2');
        TextView age=new TextView(this);
        age.setText("AGE: "+obj.getAge());
        age.setTextColor(Color.LTGRAY);
        age.setGravity(Gravity.CENTER);
        age.setBackground(gd);
        RelativeLayout.LayoutParams agepos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        agepos.addRule(RelativeLayout.BELOW,lname.getId());
        lastScreen.addView(age,agepos);
        age.setId('3');
        TextView interest=new TextView(this);
        interest.setTextColor(Color.LTGRAY);
        interest.setGravity(Gravity.CENTER);
        interest.setBackground(gd);
        interest.setText("INTERESTS: "+obj.getIntrest());
        RelativeLayout.LayoutParams interestpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        interestpos.addRule(RelativeLayout.BELOW,age.getId());
        lastScreen.addView(interest,interestpos);
        interest.setId('4');
        TextView accomplishments=new TextView(this);
        accomplishments.setTextColor(Color.LTGRAY);
        accomplishments.setGravity(Gravity.CENTER);
        accomplishments.setBackground(gd);
        accomplishments.setText("ACHEIVEMENTS: "+obj.getAcheivments());
        RelativeLayout.LayoutParams accompos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        accompos.addRule(RelativeLayout.BELOW,interest.getId());
        lastScreen.addView(accomplishments,accompos);
        accomplishments.setId('5');
        Button update=new Button(this);
        update.setText("update");
        RelativeLayout.LayoutParams updagteposd=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        updagteposd.addRule(RelativeLayout.BELOW,accomplishments.getId());
        lastScreen.addView(update,updagteposd);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),program.class));
            }
        });
        Button logout=new Button(this);
        logout.setText("LOG OUT "+user.getEmail());
        RelativeLayout.LayoutParams logoutbpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        logoutbpos.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lastScreen.addView(logout,logoutbpos);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbobj.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        Resources res=getResources();
        Drawable drawable=res.getDrawable(R.drawable.bg);
        lastScreen.setBackground(drawable);
        setContentView(lastScreen);
    }
}
