package com.example.infi_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegActivityTwo extends AppCompatActivity {

    public EditText username, password, email,mobileNo,iitbRollNo,dob;
    public CheckBox tnc;
    public Button submitBtn;
    public Button tempBtn;
    public TextView logIn, contactUs,tncStatement;

    public String usernameText, passwordText, emailText, mobileNoText, iitbRollNoText, dobText;

    FirebaseDatabase rootNode;
    DatabaseReference reference, reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_two);

        username=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.email);
        mobileNo=(EditText)findViewById(R.id.mobileNo);
        iitbRollNo=(EditText)findViewById(R.id.iitbroll);
        dob=(EditText)findViewById(R.id.dob);

        tnc=(CheckBox)findViewById(R.id.checkBoxTnC);

        logIn=(TextView)findViewById(R.id.logInPage);
        contactUs=(TextView)findViewById(R.id.contactReg);
        tncStatement=findViewById(R.id.tnc);

        submitBtn=findViewById(R.id.signupBtn);
        tempBtn=findViewById(R.id.temporary_button2);


        usernameText=username.getText().toString();
        passwordText=password.getText().toString();
        emailText=email.getText().toString();
        mobileNoText=mobileNo.getText().toString();
        iitbRollNoText=iitbRollNo.getText().toString();
        dobText=dob.getText().toString();




        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("userDetails");
                reference2=rootNode.getReference("mobileNumber");


                usernameText=username.getText().toString();
                passwordText=password.getText().toString();
                emailText=email.getText().toString();
                mobileNoText=mobileNo.getText().toString();
                iitbRollNoText=iitbRollNo.getText().toString();
                dobText=dob.getText().toString();

                if (usernameText.isEmpty() || passwordText.isEmpty() || emailText.isEmpty() || dobText.isEmpty() || mobileNoText.isEmpty() || iitbRollNoText.isEmpty()) {
                    Toast.makeText(RegActivityTwo.this, "Please Enter All the Details",Toast.LENGTH_LONG).show();
                    return;
                }
                else {

                    Users userDetail = new Users(usernameText, mobileNoText, emailText, dobText, iitbRollNoText, passwordText);
                    reference2.setValue(mobileNoText);
                    reference.child(mobileNoText).setValue(userDetail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(RegActivityTwo.this, "Registration Successful", Toast.LENGTH_LONG).show();

                        }
                    });

                    startActivity(new Intent(RegActivityTwo.this, Interest_Part.class));

                }



            }
        });



    }

    private boolean validate(){
        if (usernameText.isEmpty() || passwordText.isEmpty() || emailText.isEmpty() || dobText.isEmpty() || mobileNoText.isEmpty() || iitbRollNoText.isEmpty()) {
            Toast.makeText(RegActivityTwo.this, "Please Enter All the Details",Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            return true;
        }
    }


}
