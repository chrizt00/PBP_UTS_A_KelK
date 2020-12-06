package com.hendrianto.uts_petshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hendrianto.uts_petshop.api.APIUser;
import com.hendrianto.uts_petshop.api.UserResponse;
import com.hendrianto.uts_petshop.api.UsersApiClient;
import com.hendrianto.uts_petshop.api.UsersApiInterface;
import com.hendrianto.uts_petshop.databinding.FragmentMeBinding;
import com.hendrianto.uts_petshop.entity.User;
import com.hendrianto.uts_petshop.entity.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

import static android.app.Activity.RESULT_OK;
import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.PUT;

public class MeFragment extends Fragment {
    private ImageView profileImage;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    TextInputLayout nameLayout,emailLayout,telpLayout;
    TextInputEditText Iname,Iemail,Itelp;
    Button Logout,Edit;
    public static String KEY_ACTIVITY = "msg_activity";
    private int isVerified = 1;
    TextView nameHeader;
    private String nama,telp,email,pass;
    private View v;

    public MeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_me, container, false);
        Button takepicture = (Button)v.findViewById(R.id.takePicture);
        profileImage = v.findViewById(R.id.profileImage);
        Iname = v.findViewById(R.id.nameInput);
        Iemail = v.findViewById(R.id.emailInput);
        Itelp = v.findViewById(R.id.telpInput);
        Edit = v.findViewById(R.id.update);
        Logout = v.findViewById(R.id.logout);
        nameLayout = v.findViewById(R.id.nameLayout);
        emailLayout = v.findViewById(R.id.emailLayout);
        telpLayout = v.findViewById(R.id.telpLayout);
        nameHeader = v.findViewById(R.id.namaHeader);
        setEnabled(false);
//        int getId = 0;
        String getNama = null,getTelp=null,getEmail=null;

        int getId = getArguments().getInt("id");

//           getNama = getArguments().getString("nama");
//           getTelp = getArguments().getString("telp");
//           getEmail = getArguments().getString("email");

//        Iname.setText(getNama);
//        nameHeader.setText(getNama);
//        Iemail.setText(getTelp);
//        Itelp.setText(getEmail);
//        loadUserById(getId);
        getUserById(getId);
        takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(imageTakeIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(imageTakeIntent,REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnabled(true);
                if(Edit.getText().equals("Simpan Perubahan")){
//                    System.out.println("test : "+getId);
                    editData(getId, Iname.getText().toString(), Itelp.getText().toString(),Iemail.getText().toString(),pass,isVerified);
//                    loadFragment(new MeFragment());
                    Edit.setText("Edit");
                    setEnabled(false);
                }else{
                    Edit.setText("Simpan Perubahan");
                    emailLayout.setEnabled(false);
                }
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profileImage.setImageBitmap(imageBitmap);
        }
    }

    public void setEnabled(boolean set){
        nameLayout.setEnabled(set);
        emailLayout.setEnabled(set);
        telpLayout.setEnabled(set);
    }

//    public void loadUser(){
//        UsersApiInterface apiService = UsersApiClient.getClient().create(UsersApiInterface.class);
//        Call<UserResponse> call = apiService.getAllUser("data");
//
//        call.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                user = response.body().getUsers();
////                Toast.makeText(getContext(), user.size(),Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//            }
//        });
//    }
    public void editData(final int id,final String nama, final String telp, final String email, final String pass, final int isVerif) {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(PUT, APIUser.URL_UPDATE+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
//                    Toast.makeText(getContext(),"catch", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                System.out.println(APIUser.URL_UPDATE+id);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                    /*
                        Disini adalah proses memasukan/mengirimkan parameter key dengan data value,
                        dan nama key nya harus sesuai dengan parameter key yang diminta oleh jaringan
                        API.
                    */
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email",email);
                params.put("nama", nama);
                params.put("telp", telp);
                params.put("password", pass);
                params.put("isVerified", String.valueOf(isVerif));

                return params;
            }
        };

        //Disini proses penambahan request yang sudah kita buat ke reuest queue yang sudah dideklarasi
        queue.add(stringRequest);
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            fragmentTransaction.setReorderingAllowed(false);
        }
        fragmentTransaction.replace(R.id.fragment, fragment)
                .detach(this)
                .attach(this)
                .commit();
    }

    public void getUserById(int id) {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        final JsonObjectRequest stringRequest = new JsonObjectRequest(GET, APIUser.URL_SELECTBYID+id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject newObj = response.getJSONObject("data");
                    String nama = newObj.optString("nama");
                    String email = newObj.optString("email");
                    String telp = newObj.optString("telp");
                    String password = newObj.optString("password");
                    int isVerifieds = newObj.optInt("isVerified");

                    Iname.setText(nama);
                    nameHeader.setText(nama);
                    Iemail.setText(email);
                    Itelp.setText(telp);
                    pass = password;
                    isVerified = isVerifieds;
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Toast.makeText(v.getContext(), response.optString("message"), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(v.getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}