package com.example.baitapvn8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.baitapvn8.databinding.ActivityMainBinding;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    final Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[@#*$%^&+=])(?=\\S+$).{6,}$");
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = binding.etUser.getText().toString().trim();
                String pass = binding.etPass.getText().toString().trim();

                if(user.isEmpty() || pass.isEmpty()){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Thông Báo")
                            .setMessage("Tài khoản hoặc Mật khẩu không được để trống!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }
               else if(!p.matcher(pass).find()){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Cảnh Báo")
                            .setMessage("Mật khẩu phải có 6 ký tự trở lên \n" +
                                    "Chữ hoa và Chũ thường\n" +
                                    "Gồm ký tự đặc biệt\n" +
                                    "Gồm ít nhất 1 chữ số\n"
                                    )
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .create();
                    alertDialog.show();
                }
                else if (user.equalsIgnoreCase("admin") && pass.equals("Admin123*")){
                    //Toast.makeText(getBaseContext(),"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                    Intent  intent = new Intent(getBaseContext(),NewNoteActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}