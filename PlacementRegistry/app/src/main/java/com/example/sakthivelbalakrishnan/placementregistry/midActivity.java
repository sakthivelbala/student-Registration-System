package com.example.sakthivelbalakrishnan.placementregistry;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class midActivity extends AppCompatActivity {

    private RelativeLayout midscreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button fillForm=new Button(this);
        fillForm.setText("FILL FORM");
        RelativeLayout.LayoutParams ffpos=new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        ffpos.addRule(RelativeLayout.CENTER_HORIZONTAL);
        ffpos.addRule(RelativeLayout.CENTER_VERTICAL);
        fillForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),program.class));
            }
        });
        midscreen = new RelativeLayout(this);
        midscreen.addView(fillForm,ffpos);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.bg);
        midscreen.setBackground(drawable);
        setContentView(midscreen);
    }
}
