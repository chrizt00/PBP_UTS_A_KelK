package com.hendrianto.uts_petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hendrianto.uts_petshop.api.APIUser;
import com.hendrianto.uts_petshop.api.UserResponse;
import com.hendrianto.uts_petshop.api.UsersApiClient;
import com.hendrianto.uts_petshop.api.UsersApiInterface;
import com.hendrianto.uts_petshop.entity.Users;
import com.hendrianto.uts_petshop.mail.JavaMailAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.volley.Request.Method.POST;

public class RegisterActivity extends AppCompatActivity {
    private Button btnCancel, btnRegister;
    private EditText nama,email,telp,password;
    private ProgressDialog progressDialog;
    private List<Users> users;
    int checkMail=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(this);
        btnCancel = findViewById(R.id.btnBack);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        telp = findViewById(R.id.telp);
        password = findViewById(R.id.password);
        loadUser();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nama.getText().toString().isEmpty()){
                    nama.setError("Isikan Dengan Benar");
                    nama.requestFocus();
                }
                else if(telp.getText().toString().isEmpty()){
                    telp.setError("Isikan Dengan Benar");
                    telp.requestFocus();
                }
                else if(email.getText().toString().isEmpty() || isValidEmail(email.getText().toString())==false){
                    email.setError("Isikan Dengan Benar");
                    email.requestFocus();
                }
                else if(password.getText().toString().isEmpty() || password.getText().toString().length()<6){
                    password.setError("Password minimal 6 karakter");
                    password.requestFocus();
                }else{
                    for(int i =0;i<users.size();i++){
                        if(users.get(i).getEmail().equals(email.getText().toString())){
                            checkMail = 1;
                        }
                    }
                        if(checkMail==1){
                            email.setError("Email Sudah Tersedia");
                            email.requestFocus();
                        }else {
                            progressDialog.show();
                            String encryptedPass = encrypt(password.getText().toString());
                            String randomString = randomString();
                            tambahData(email.getText().toString(),
                            encryptedPass,
                            nama.getText().toString(),
                            telp.getText().toString(),
                            randomString,0);
                            sendMail(email.getText().toString(),randomString);
//                            saveUser();
                        }
                }
            }
        });
    }

    public void sendMail(String email, String randomString){
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,email
                ,"Email Verification","Please Verif your account here " +
                "https://pbp.inipunyaku.my.id/verify.php?vkey="+randomString);
//        Toast.makeText(this,email,Toast.LENGTH_SHORT).show();
        javaMailAPI.execute();
    }

    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    public void loadUser(){
        UsersApiInterface apiService = UsersApiClient.getClient().create(UsersApiInterface.class);
        Call<UserResponse> call = apiService.getAllUser("data");

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                users = response.body().getUsers();
//                Toast.makeText(RegisterActivity.this, users.size(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Kesalahan Jaringan",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tambahData(String email, String password, String nama, String telp, String verifCode, int isVerified){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        progressDialog.setMessage("loading...");
        progressDialog.setTitle("Mengecek Registrasi");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(POST, APIUser.URL_ADD, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(RegisterActivity.this, "Register Success, Verified Your Mail", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                progressDialog.dismiss();
//                Toast.makeText(RegisterActivity.this, error.getMessage()+email+password+nama+telp+verifCode+isVerified, Toast.LENGTH_SHORT).show();
                Toast.makeText(RegisterActivity.this,"Jaringan Error",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                params.put("nama", nama);
                params.put("telp", telp);
                params.put("verifCode", verifCode);
                params.put("isVerified", String.valueOf(isVerified));

                return params;
            }
        };

        //Disini proses penambahan request yang sudah kita buat ke reuest queue yang sudah dideklarasi
        queue.add(stringRequest);
    }

    private String randomString(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    private void saveUser() {
            String verifCode = randomString();
            UsersApiInterface apiService = UsersApiClient.getClient().create(UsersApiInterface.class);
            String encryptedPass = encrypt(password.getText().toString());
            System.out.println("Encrypted pass = "+encryptedPass);
            Call<UserResponse> add = apiService.
                    createUser(email.getText().toString(),
                            "reg"+encryptedPass,nama.getText().toString(),telp.getText().toString(),
                            verifCode,0);

            progressDialog.setMessage("loading...");
            progressDialog.setTitle("Mengecek Registrasi");
            add.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this,
                            "Silahkan Verifikasi Email",
                            Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
    }

    public String encrypt(String data){
        String Newstr = "";
        for (int i=0;i<data.length();i++)
        {
            char ch=Character.toLowerCase(data.charAt(i));
            switch (ch)
            {
                case 'a':
                    Newstr=Newstr+"{";
                    break;
                case 'b':
                    Newstr=Newstr+"}";
                    break;
                case 'c':
                    Newstr=Newstr+"#";
                    break;
                case 'd':
                    Newstr=Newstr+"~";
                    break;
                case 'e':
                    Newstr=Newstr+"+";
                    break;
                case 'f':
                    Newstr=Newstr+"-";
                    break;
                case 'g':
                    Newstr=Newstr+"*";
                    break;
                case 'h':
                    Newstr=Newstr+"@";
                    break;
                case 'i':
                    Newstr=Newstr+"/";
                    break;
                case 'j':
                    Newstr=Newstr+"\\";
                    break;
                case 'k':
                    Newstr=Newstr+"?";
                    break;
                case 'l':
                    Newstr=Newstr+"$";
                    break;
                case 'm':
                    Newstr=Newstr+"!";
                    break;
                case 'n':
                    Newstr=Newstr+"^";
                    break;
                case 'o':
                    Newstr=Newstr+"(";
                    break;
                case 'p':
                    Newstr=Newstr+")";
                    break;
                case 'q':
                    Newstr=Newstr+"<";
                    break;
                case 'r':
                    Newstr=Newstr+">";
                    break;
                case 's' :
                    Newstr=Newstr+"=";
                    break;
                case 't':
                    Newstr=Newstr+";";
                    break;
                case 'u':
                    Newstr=Newstr+",";
                    break;
                case 'v' :
                    Newstr=Newstr+"_";
                    break;
                case 'w':
                    Newstr=Newstr+"[";
                    break;
                case 'x' :
                    Newstr=Newstr+"]";
                    break;
                case 'y':
                    Newstr=Newstr+":";
                    break;
                case 'z' :
                    Newstr=Newstr+"a";
                    break;
                case ' ' :
                    Newstr=Newstr+" ";
                    break;
                case '.':
                    Newstr=Newstr+'3';
                    break;
                case ',':
                    Newstr=Newstr+"1";
                    break;
                case '(':
                    Newstr=Newstr+'4';
                    break;
                case '\"':
                    Newstr=Newstr+'5';
                    break;
                case ')' :
                    Newstr=Newstr+"7";
                    break;
                case '?' :
                    Newstr= Newstr+"2";
                    break;
                case '!':
                    Newstr= Newstr+"8";
                    break;
                case '-' :
                    Newstr= Newstr+"6";
                    break;
                case '%' :
                    Newstr = Newstr+"9";
                    break;
                case '1':
                    Newstr=Newstr+"r";
                    break;
                case '2':
                    Newstr=Newstr+"k";
                    break;
                case '3':
                    Newstr=Newstr+"b";
                    break;
                case '4':
                    Newstr = Newstr+"e";
                    break;
                case '5':
                    Newstr = Newstr+"q";
                    break;
                case '6':
                    Newstr = Newstr+"h";
                    break;
                case '7':
                    Newstr = Newstr+"u";
                    break;
                case '8' :
                    Newstr= Newstr+"y";
                    break;
                case '9':
                    Newstr = Newstr+"w";
                    break;
                case '0':
                    Newstr = Newstr+"z";
                    break;
                default:
                    Newstr=Newstr+"0";
                    break;
            }
        }
        return Newstr;
    }
}