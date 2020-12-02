package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hendrianto.uts_petshop.mail.JavaMailAPI;

public class TestActivity extends AppCompatActivity {
    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        test = findViewById(R.id.button);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    public void sendMail(){
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,"hendrianto8901234@gmail.com"
        ,"Test Doang","isinya ini https://abcdefg.com");
        javaMailAPI.execute();
    }
}