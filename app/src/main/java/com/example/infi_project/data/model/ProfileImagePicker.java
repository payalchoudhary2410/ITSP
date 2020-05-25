package com.example.infi_project.data.model;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.infi_project.AppMainPage;
import com.example.infi_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import static android.app.Activity.RESULT_OK;


public class ProfileImagePicker extends Fragment {

    private Button Edit;
    private Button Remove;
    private static final int galleryPick=1;
    private ImageView profile;
    private DatabaseReference RootRef;
    private StorageReference  userProfileImagesReference;
    public String mobileText;




    public ProfileImagePicker() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "Meow", Toast.LENGTH_SHORT).show();

        AppMainPage activity= (AppMainPage) getActivity();
        mobileText=activity.sendData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_image_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Edit=getView().findViewById(R.id.edit);
        Remove=getView().findViewById(R.id.remove);
        profile=getView().findViewById(R.id.ImageProfile);

        RootRef= FirebaseDatabase.getInstance().getReference();
        userProfileImagesReference= FirebaseStorage.getInstance().getReference().child("Profile Images");



        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,galleryPick);


            }
        });

        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable mDrawable=getResources().getDrawable(R.drawable.profile_image);
                profile.setImageDrawable(mDrawable);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==galleryPick &&  resultCode==RESULT_OK && data!=null)
        {
            Uri ImageUri = data.getData();

            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(getContext(),this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if(resultCode==RESULT_OK)
            {

                Uri resultUri = result.getUri();


                StorageReference filePath = userProfileImagesReference.child(mobileText + ".jpg");

                filePath.putFile(resultUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                final Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl();
                                firebaseUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        final String downloadUrl = uri.toString();

                                        RootRef.child("Users").child(mobileText).child("image")
                                                .setValue(downloadUrl)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Toast.makeText(getContext(), "Image saved in database successfuly", Toast.LENGTH_SHORT).show();

                                                        }
                                                        else{
                                                            String message = task.getException().toString();
                                                            Toast.makeText(getContext(), "Error: " + message,Toast.LENGTH_SHORT).show();


                                                        }

                                                    }
                                                });

                                    }
                                });

                            }
                        });

            }
        }

    }
}
