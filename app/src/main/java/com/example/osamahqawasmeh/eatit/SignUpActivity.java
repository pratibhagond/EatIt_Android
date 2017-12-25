package com.example.osamahqawasmeh.eatit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osamahqawasmeh.eatit.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    private EditText etMobile, etName, etPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        assignControls();
    }

    private void assignControls(){
        this.etMobile = (EditText)findViewById(R.id.et_MobileNum);
        this.etName = (EditText)findViewById(R.id.et_Name);
        this.etPassword = (EditText)findViewById(R.id.et_Password);
        this.btnSignUp = (Button)findViewById(R.id.btn_SignUp);

        final FirebaseDatabase fbDB = FirebaseDatabase.getInstance();
        final DatabaseReference userTbl = fbDB.getReference("User");

        this.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                mDialog.setMessage("Please Wait!");
                mDialog.show();

                userTbl.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Check if the mpbile number already registered ?
                        if(dataSnapshot.child(etMobile.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "User Already exist!!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            mDialog.dismiss();
                            User user = new User(etName.getText().toString(), etPassword.getText().toString());
                            userTbl.child(etMobile.getText().toString()).setValue(user);
                            Toast.makeText(SignUpActivity.this, "User registered successfully!!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
