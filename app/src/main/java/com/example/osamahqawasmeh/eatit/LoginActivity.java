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

public class LoginActivity extends AppCompatActivity {

    private EditText etMobileNum, etPassword;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        assignConstols();
    }

    private void assignConstols(){
        this.etMobileNum = (EditText)findViewById(R.id.et_MobileNum);
        this.etPassword  = (EditText)findViewById(R.id.et_Password);
        this.btnSignIn = (Button)findViewById(R.id.btn_SignIn);

        final FirebaseDatabase fbDB = FirebaseDatabase.getInstance();
        final DatabaseReference userTbl = fbDB.getReference("User");

        this.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Please Wait!");
                mDialog.show();

                userTbl.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(etMobileNum.getText().toString()).exists()){
                            mDialog.dismiss();
                            //Get User Info.
                            User user = dataSnapshot.child(etMobileNum.getText().toString()).getValue(User.class);
                            if (user.getPassowrd().equals(etPassword.getText().toString())) {
                                Toast.makeText(LoginActivity.this, "Login success!!!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Login failed!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Mobile Number doens't exist!!!", Toast.LENGTH_SHORT).show();
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
