package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hendrianto.uts_petshop.api.UserResponse;
import com.hendrianto.uts_petshop.api.UsersApiClient;
import com.hendrianto.uts_petshop.api.UsersApiInterface;
import com.hendrianto.uts_petshop.entity.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    private EditText email,password;
    private ProgressDialog progressDialog;
    private List<Users> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnToRegister);
        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        loadUser();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cekValidateLogin = 0;
                if(email.getText().toString().equalsIgnoreCase("admin") && password.getText().toString().equalsIgnoreCase("admin")) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else{
                    for(int i =0;i<users.size();i++){
                        if(email.getText().toString().equalsIgnoreCase(users.get(i).getEmail())){
                            String newPass = decrypt(users.get(i).getPassword());
                            if(password.getText().toString().equalsIgnoreCase(newPass)){
                                cekValidateLogin = 1;
                            }else{
                                cekValidateLogin = 2;
                            }
                        }
                    }
                    if(cekValidateLogin==0){
                        Toast.makeText(LoginActivity.this, "Username Tidak ada", Toast.LENGTH_SHORT).show();
                    }else if(cekValidateLogin==2){
                        Toast.makeText(LoginActivity.this,"Password Salah",Toast.LENGTH_SHORT).show();
                    }else if(cekValidateLogin==1){
                        Toast.makeText(LoginActivity.this,"Login Berhasil",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void loadUser(){
        UsersApiInterface apiService = UsersApiClient.getClient().create(UsersApiInterface.class);
        Call<UserResponse> call = apiService.getAllUser("data");

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                users = response.body().getUsers();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Kesalahan Jaringan",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public String decrypt(String data){
        String Newstr = "";
        for (int i=0;i<data.length();i++)
        {
            char ch=Character.toLowerCase(data.charAt(i));
            switch (ch)
            {
                case '{':
                    Newstr=Newstr+"A";
                    break;
                case '}':
                    Newstr=Newstr+"B";
                    break;
                case '#':
                    Newstr=Newstr+"C";
                    break;
                case '~':
                    Newstr=Newstr+"D";
                    break;
                case '+':
                    Newstr=Newstr+"E";
                    break;
                case '-':
                    Newstr=Newstr+"F";
                    break;
                case '*':
                    Newstr=Newstr+"G";
                    break;
                case '@':
                    Newstr=Newstr+"H";
                    break;
                case '/':
                    Newstr=Newstr+"I";
                    break;
                case '\\':
                    Newstr=Newstr+"J";
                    break;
                case '?':
                    Newstr=Newstr+"K";
                    break;
                case '$':
                    Newstr=Newstr+"L";
                    break;
                case '!':
                    Newstr=Newstr+"M";
                    break;
                case '^':
                    Newstr=Newstr+"N";
                    break;
                case '(':
                    Newstr=Newstr+"O";
                    break;
                case ')':
                    Newstr=Newstr+"P";
                    break;
                case '<':
                    Newstr=Newstr+"Q";
                    break;
                case '>':
                    Newstr=Newstr+"R";
                    break;
                case '=' :
                    Newstr=Newstr+"S";
                    break;
                case ';':
                    Newstr=Newstr+"T";
                    break;
                case ',':
                    Newstr=Newstr+"U";
                    break;
                case '_' :
                    Newstr=Newstr+"V";
                    break;
                case '[':
                    Newstr=Newstr+"W";
                    break;
                case ']' :
                    Newstr=Newstr+"X";
                    break;
                case ':':
                    Newstr=Newstr+"Y";
                    break;
                case 'a' :
                    Newstr=Newstr+"Z";
                    break;
                case 'r':
                    Newstr=Newstr+"1";
                    break;
                case 'k':
                    Newstr=Newstr+"2";
                    break;
                case 'b':
                    Newstr=Newstr+"3";
                    break;
                case 'e':
                    Newstr = Newstr+"4";
                    break;
                case 'q':
                    Newstr = Newstr+"5";
                    break;
                case 'h':
                    Newstr = Newstr+"6";
                    break;
                case 'u':
                    Newstr = Newstr+"7";
                    break;
                case 'y' :
                    Newstr= Newstr+"8";
                    break;
                case 'w':
                    Newstr = Newstr+"9";
                    break;
                case 'z':
                    Newstr = Newstr+"0";
                    break;
                default:
                    Newstr=Newstr+"0";
                    break;
            }
        }
        return Newstr;
    }
}