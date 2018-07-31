package com.example.sakthivelbalakrishnan.placementregistry;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class program extends AppCompatActivity {

    private EditText named,surnamed,aged,interestedsportd,acheivementsd,emaild;
    private Button addData;
    private DatabaseReference datab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Resources res=getResources();
        Drawable drawable=res.getDrawable(R.drawable.bg);
        GradientDrawable gd=new GradientDrawable();
        gd.setStroke(3, Color.LTGRAY);
        gd.setCornerRadius(30f);
        super.onCreate(savedInstanceState);
        datab=FirebaseDatabase.getInstance().getReference();
        RelativeLayout mainscreen=new RelativeLayout(this);
        named=new EditText(this);
        named.setBackground(gd);
        named.setHint("First Name");
        RelativeLayout.LayoutParams namepos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        mainscreen.addView(named,namepos);
        named.setId('1');
        surnamed=new EditText(this);
        surnamed.setBackground(gd);
        surnamed.setHint("Last Name");
        RelativeLayout.LayoutParams surnamepos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        surnamepos.addRule(RelativeLayout.BELOW,named.getId());
        mainscreen.addView(surnamed,surnamepos);
        surnamed.setId('2');
        aged=new EditText(this);
        aged.setBackground(gd);
        aged.setHint("Age");
        RelativeLayout.LayoutParams agepos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        agepos.addRule(RelativeLayout.BELOW,surnamed.getId());
        mainscreen.addView(aged,agepos);
        aged.setId('3');
        emaild=new EditText(this);
        emaild.setBackground(gd);
        emaild.setHint("Register Number");
        RelativeLayout.LayoutParams emailpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        emailpos.addRule(RelativeLayout.BELOW,aged.getId());
        mainscreen.addView(emaild,emailpos);
        emaild.setId('9');
        interestedsportd =new EditText(this);
        interestedsportd.setBackground(gd);
        interestedsportd.setHint("Interested Sport");
        RelativeLayout.LayoutParams inteerstedspodrtpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        interestedsportd.setLines(5);
        inteerstedspodrtpos.addRule(RelativeLayout.BELOW,emaild.getId());
        mainscreen.addView(interestedsportd,inteerstedspodrtpos);
        interestedsportd.setId('4');
        acheivementsd=new EditText(this);
        acheivementsd.setBackground(gd);
        acheivementsd.setHint("Achivements");
        RelativeLayout.LayoutParams acheivementspos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        acheivementsd.setLines(5);
        acheivementspos.addRule(RelativeLayout.BELOW,interestedsportd.getId());
        mainscreen.addView(acheivementsd,acheivementspos);
        acheivementsd.setId('5');
        addData=new Button(this);
        addData.setText("REGISTER");
        RelativeLayout.LayoutParams addDataPos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        addDataPos.addRule(RelativeLayout.BELOW,acheivementsd.getId());
        mainscreen.addView(addData,addDataPos);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname=named.getText().toString();
                String lname=surnamed.getText().toString();
                String age=aged.getText().toString();
                String interest=interestedsportd.getText().toString();
                String acheivements=acheivementsd.getText().toString();
                String email=emaild.getText().toString().trim();
                if((!TextUtils.isEmpty(fname))&&(!TextUtils.isEmpty(lname))&&(!TextUtils.isEmpty(interest))&&(!TextUtils.isEmpty(acheivements))&&(!TextUtils.isEmpty(email))){
                    manageData passDb= new manageData(fname,lname,interest,acheivements,age);
                    datab.child(email.toString()).setValue(passDb);
                    Toast.makeText(program.this,"Success",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),Data.class));

                }
                else{
                    Toast.makeText(program.this,"Please Fill ALL THE DETAILS",Toast.LENGTH_SHORT).show();
                }

            }
        });

        mainscreen.setBackground(drawable);
        setContentView(mainscreen);
    }
}

