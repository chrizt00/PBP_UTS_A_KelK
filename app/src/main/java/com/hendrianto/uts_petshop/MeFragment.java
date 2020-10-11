package com.hendrianto.uts_petshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hendrianto.uts_petshop.databinding.FragmentMeBinding;
import com.hendrianto.uts_petshop.entity.User;

import static android.app.Activity.RESULT_OK;

public class MeFragment extends Fragment {
    private ImageView profileImage;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private int stat=0; //Status of Button Update clicked
    TextInputLayout username,email,telp,address;
    TextInputLayout usernameH,emailH,telpH,addressH; //Hidden layout
    TextInputEditText usernameI,emailI,telpI,addressI; //Hidden input
    TextInputEditText Iusername,Iemail,Itelp,Iaddress;
    User usr;
    private FragmentMeBinding binding;
    private SharedPreferences preferences;
//    private static final int mode = this.getActivity().MODE_PRIVATE;

    //For Load Preferences
    private String sUsername = "";
    private String sEmail = "";
    private String sTelp = "";
    private String sAddress = "";


    public MeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = binding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Button takepicture = (Button)v.findViewById(R.id.takePicture);
        profileImage = v.findViewById(R.id.profileImage);
        final Button update = (Button)v.findViewById(R.id.update);
        usernameH = v.findViewById(R.id.userLayoutHide);
        emailH = v.findViewById(R.id.emailLayoutHide);
        telpH = v.findViewById(R.id.telpLayoutHide);
        addressH = v.findViewById(R.id.addressLayoutHide);
        username = v.findViewById(R.id.userLayout);
        email = v.findViewById(R.id.emailLayout);
        telp = v.findViewById(R.id.telpLayout);
        address = v.findViewById(R.id.addressLayout);

        setVisibleH(View.GONE);
        setEnabled(false);

        loadPreferences();
        usr = new User(sUsername,sEmail,sTelp,sAddress);
        binding.setUsr(usr);
//        Iusername = v.findViewById(R.id.userInput);
//        Iemail = v.findViewById(R.id.emailInput);
//        Itelp = v.findViewById(R.id.telpInput);
//        Iaddress = v.findViewById(R.id.addressInput);
//        Toast.makeText(this.getContext(),Iusername.getText().toString(), Toast.LENGTH_SHORT).show();
//        Iusername.setText(sUsername);
//        Iemail.setText(sEmail);
//        Itelp.setText(sTelp);
//        Iaddress.setText(sAddress);

        if(sUsername != ""||sEmail!=""||sTelp!=""||sAddress!=""){
            update.setText("EDIT");
        }else{
            update.setText("INPUT");
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                savePreferences();
                if(stat==0){
                    setVisible(View.GONE);
                    setVisibleH(View.VISIBLE);
                    update.setText("SIMPAN");
                    stat=1;
                }else{
                    stat=0;
                    setVisibleH(View.GONE);
                    setVisible(View.VISIBLE);
                    update.setText("EDIT");
                }
            }
        });

        takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(imageTakeIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(imageTakeIntent,REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        return v;
    }



    public void setVisible(int visibility){
        username.setVisibility(visibility);
        email.setVisibility(visibility);
        telp.setVisibility(visibility);
        address.setVisibility(visibility);
    }
    public void setVisibleH(int visibility){
        usernameH.setVisibility(visibility);
        emailH.setVisibility(visibility);
        telpH.setVisibility(visibility);
        addressH.setVisibility(visibility);
    }
    public void setEnabled(boolean enabled){
        username.setEnabled(enabled);
        email.setEnabled(enabled);
        telp.setEnabled(enabled);
        address.setEnabled(enabled);
    }
    private void loadPreferences(){
        String name = "profile";
        preferences = this.getActivity().getSharedPreferences(name, Context.MODE_PRIVATE);
        if(preferences!=null){
            sUsername = preferences.getString("username","");
            sEmail = preferences.getString("email","");
            sTelp = preferences.getString("telp","");
            sAddress = preferences.getString("address","");
        }
    }
    private void savePreferences(){
        usernameI = getView().findViewById(R.id.userInputHide);
        emailI = getView().findViewById(R.id.emailInputHide);
        telpI = getView().findViewById(R.id.telpInputHide);
        addressI = getView().findViewById(R.id.addressInputHide);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",usernameI.getText().toString());
        editor.putString("email",emailI.getText().toString());
        editor.putString("telp",telpI.getText().toString());
        editor.putString("address",addressI.getText().toString());
        editor.apply();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profileImage.setImageBitmap(imageBitmap);
        }
    }

}