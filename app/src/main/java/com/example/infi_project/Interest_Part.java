package com.example.infi_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.infi_project.data.Interest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Vector;

public class Interest_Part extends AppCompatActivity {
    public ImageButton[] igButton=new ImageButton[22];
    //public ImageButton ig3;
    public boolean[] selectigButton=new boolean[22];
    public int totalSelected;
    private Button subBtn;
    String mobileText;
    public Vector userInterest=new Vector();

    public Toolbar toolbar;






    DatabaseReference user,interests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest__part);

        toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Infi");



        //ig3= (ImageButton)findViewById(R.id.ig3);
        igButton[0]=null;
        selectigButton[0]=false;
        totalSelected=0;
        for (int i=1; i<22; i++){
            String b="ig"+i;
            int imageId=getResources().getIdentifier(b,"id",getPackageName());
            igButton[i]=findViewById(imageId);
            selectigButton[i]=false;
        }
        subBtn=(Button)findViewById(R.id.button_submitInterest);
        subBtn.setEnabled(false);

        Intent regIntent=getIntent();
        mobileText=regIntent.getStringExtra("mobileText");

        user= FirebaseDatabase.getInstance().getReference("userDetails");
        interests= FirebaseDatabase.getInstance().getReference("interests");

        igOnclick();
        subBtnOnclickListener();


    }

    public void igOnclick(){
        for (int k=1; k<22; k++){
            final int j=k;
            igButton[j].setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!selectigButton[j]){
                                selectigButton[j]=true;
                                igButton[j].setImageAlpha(64);
                                igButton[j].setAlpha(0.25f);
                                totalSelected++;
                                if (totalSelected>=3){
                                    subBtn.setEnabled(true);
                                }
                            }

                            else {
                                selectigButton[j]=false;
                                igButton[j].setImageAlpha(255);
                                igButton[j].setAlpha(1.0f);
                                totalSelected--;
                                if (totalSelected<3){
                                    subBtn.setEnabled(false);
                                }
                            }
                        }
                    }
            );
        }

    }

    public void subBtnOnclickListener(){
        subBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int[] interestUploaded = {0};

                        for (int i=1; i<22; i++){
                            final String b= "ig"+i;
                            Interest interestDetails= new Interest("qw",mobileText);
                            if (selectigButton[i]){
                                interests.child(b).child(mobileText).setValue(interestDetails);
                                userInterest.add(b);
                            }
                        }

                        user.child(mobileText).child("choiceSelected").setValue(true);
                        user.child(mobileText).child("userInterest").setValue(userInterest);

                        Intent appMainPage_intent = new Intent(Interest_Part.this, AppMainPage.class);
                        appMainPage_intent.putExtra("mobileText", mobileText);
                        Toast.makeText(Interest_Part.this, "Interest added successfully", Toast.LENGTH_SHORT).show();
                        startActivity(appMainPage_intent);
                        finish();

//                        else{
//                            Toast.makeText(Interest_Part.this, interestUploaded[0] +"Connectivity Problem", Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(Interest_Part.this, Interest_Part.class));
//
//                        }

                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuforinterest) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menuforinterest, menuforinterest);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.logoutMenu: {
                Logout();
                break;
            }
            case R.id.ContactUs: {
                startActivity(new Intent(Interest_Part.this, Contact_Us.class));
                break;

            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void Logout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Interest_Part.this, MainActivity.class));
        finishAndRemoveTask();
    }

}
