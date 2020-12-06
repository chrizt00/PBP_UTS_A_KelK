package com.hendrianto.uts_petshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.hendrianto.uts_petshop.api.UserResponse;
import com.hendrianto.uts_petshop.api.UsersApiClient;
import com.hendrianto.uts_petshop.api.UsersApiInterface;
import com.hendrianto.uts_petshop.entity.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView nav;
    MeFragment meFragment = new MeFragment();
    NewsFragment newsFragment = new NewsFragment();
    HomeFragment homeFragment = new HomeFragment();
    MenuItem potrait,landscape;

    public int getId;
    public String getNama, getTelp, getEmail;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu,menu);
        potrait = menu.findItem(R.id.potrait);
        landscape = menu.findItem(R.id.landscape);
        int orientation = this.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            potrait.setVisible(false);
            landscape.setVisible(true);
        }else{
            landscape.setVisible(false);
            potrait.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.landscape :
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                return true;
            case R.id.potrait :
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return true;
            case R.id.about :
                Intent i = new Intent(this,AboutActivity.class);
                startActivity(i);
            case R.id.cart :
                Intent in = new Intent(this,CartActivity.class);
                in.putExtra("key",getEmail);
                startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(R.id.homemenu);
        Bundle extras = getIntent().getExtras();
        getId = extras.getInt("id");
        getNama = extras.getString("nama");
        getTelp = extras.getString("telp");
        getEmail = extras.getString("email");
        Bundle data2 = new Bundle();
        data2.putString("email",getEmail);
        homeFragment.setArguments(data2);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment,homeFragment).commit();
        nav.setOnNavigationItemSelectedListener(this);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            String CHANNEL_ID = "Channel 1";
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String mag = "Successful";
                        if(!task.isSuccessful()){
                            mag="Failed";
                        }
//                        Toast.makeText(HomeActivity.this,mag, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch ((item.getItemId())){
            case R.id.homemenu :
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment,homeFragment).commit();
                return true;
            case R.id.memenu :
                Bundle data = new Bundle();
                data.putInt("id",getId);
                data.putString("nama",getNama);
                data.putString("telp",getTelp);
                data.putString("email",getEmail);
                meFragment.setArguments(data);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment,meFragment).commit();
                return true;
            case R.id.newspapermenu :
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.fragment,newsFragment).commit();
                return true;
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}