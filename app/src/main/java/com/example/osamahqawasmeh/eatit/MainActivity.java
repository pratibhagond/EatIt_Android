package com.example.osamahqawasmeh.eatit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSignUp, btnSingIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignControls();

    }

    private void assignControls(){
        this.btnSignUp = (Button)findViewById(R.layout.btnSignUp);
        this.btnSingIn = (Button)findViewById(R.layout.btnSingIn);

    }

}
