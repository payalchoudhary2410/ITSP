package com.example.infi_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Interest_Part extends AppCompatActivity {
    public ImageButton[] igButton=new ImageButton[22];
    //public ImageButton ig3;
    public boolean[] selectigButton=new boolean[22];
    public int totalSelected;
    private Button subBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest__part);

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
                            if (selectigButton[j]==false){
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
                        Intent appMainPage_intent= new Intent(Interest_Part.this, AppMainPage.class);

                        startActivity(appMainPage_intent);
                        finish();
                        //more code to be added
                        //to update the user database
                    }
                }
        );
    }

}
