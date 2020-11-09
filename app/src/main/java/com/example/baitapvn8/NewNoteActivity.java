package com.example.baitapvn8;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.baitapvn8.databinding.ActivityNewNoteBinding;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NewNoteActivity extends AppCompatActivity {

    ActivityNewNoteBinding noteBinding;
    int t1Hour,t1Minute;
    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final  int day = calendar.get(Calendar.DAY_OF_MONTH);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        noteBinding = DataBindingUtil.setContentView(this,R.layout.activity_new_note);

        noteBinding.btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(NewNoteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        t1Hour = hourOfDay;
                        t1Minute = minute;
                        Calendar calendar = Calendar.getInstance();

                        calendar.set(0, 0, 0, t1Hour, t1Minute);
                        noteBinding.btnTime.setText(DateFormat.format("hh:mm", calendar));
                    }
                }, 24, 0, false
                );

                timePickerDialog.updateTime(t1Hour,t1Minute);
                timePickerDialog.show();
            }
        });
        noteBinding.btnDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(NewNoteActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        noteBinding.btnDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        List<String> lista = new ArrayList<>();
        lista.add("Family");
        lista.add("Friend");
        lista.add("Work");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,lista);
        noteBinding.spnLuachon.setAdapter(adapter);

        noteBinding.tvMultichoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] strings ={"Android","Facebook","Instagram","Skype","GoViet"};
                boolean [] booleans = {true,false,true,false,true};

                List<String> arrList = Arrays.asList(strings);

                AlertDialog alertDialog =new AlertDialog.Builder(NewNoteActivity.this)
                        .setTitle("Choose:")
                        .setMultiChoiceItems(strings, booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                //booleans[which] =isChecked;
                                String temp = arrList.get(which);
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                noteBinding.tvMultichoice.setText("");
                                for(int i=0; i< booleans.length;i++){
                                    boolean check = booleans[i];
                                    if(check){
                                        noteBinding.tvMultichoice.setText(noteBinding.tvMultichoice.getText()+arrList.get(i)+", ");
                                    }
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(),"Hủy",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();

            }
        });


      noteBinding.tvWeek.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              String [] weeks = {"Sunday","Monday",
                      "Tuesday",
                      "Wednesday",
                      "Thursday",
                      "Friday",
                      "Saturday"};

              boolean aBoolean [] = {true,false,true,false,false,false,false};
              List<String> arrLst = Arrays.asList(weeks);

              AlertDialog alertDialog =new AlertDialog.Builder(NewNoteActivity.this)
                      .setTitle("Choose:")
                      .setMultiChoiceItems(weeks, aBoolean, new DialogInterface.OnMultiChoiceClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                              //booleans[which] =isChecked;
                             Toast.makeText(getBaseContext(),weeks[which],Toast.LENGTH_SHORT).show();
                          }
                      })
                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              noteBinding.tvWeek.setText("");
                             for (int i=0;i<aBoolean.length;i++){
                                 boolean checkd = aBoolean[i];
                                 if(checkd){
                                     noteBinding.tvWeek.setText(noteBinding.tvWeek.getText()+ arrLst.get(i) +", ");
                                 }
                             }
                          }
                      })
                      .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              Toast.makeText(getBaseContext(),"Hủy",Toast.LENGTH_SHORT).show();
                          }
                      })
                      .create();
              alertDialog.show();

          }
      });

      noteBinding.btnPopMe.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              PopupMenu popupMenu = new PopupMenu(getBaseContext(),v);
              MenuInflater menuInflater = popupMenu.getMenuInflater();
              menuInflater.inflate(R.menu.btn_menu,popupMenu.getMenu());

              popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                  @Override
                  public boolean onMenuItemClick(MenuItem item) {
                      switch (item.getItemId()){
                          case R.id.btnFFile:
                              Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                              intent.setType("*/*");
                              startActivityForResult(intent, 7);
                              break;

                          case  R.id.btnDefau:
                              String [] strings = {"Nexus Tune","Nokia Tune","Peep Tune","Window Tune","ETC"};
                              final String[] temp = new String[1];
                              AlertDialog alertDialog = new AlertDialog.Builder(NewNoteActivity.this)
                                      .setTitle("Choose Tune")
                                      .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialog, int which) {
                                              temp[0] = strings[which];
                                          }
                                      })
                                      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialog, int which) {
                                              Toast.makeText(getBaseContext(),temp[0],Toast.LENGTH_SHORT).show();
                                          }
                                      })
                                      .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialog, int which) {
                                              Toast.makeText(getBaseContext(),"Hủy",Toast.LENGTH_SHORT).show();
                                          }
                                      })
                                      .create();
                              alertDialog.show();
                              break;
                      }
                      return false;
                  }
              });
              popupMenu.show();
          }
      });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 7:

                if (resultCode == RESULT_OK) {

                    String PathHolder = data.getData().getPath();

                    Toast.makeText(NewNoteActivity.this, PathHolder, Toast.LENGTH_LONG).show();

                }
                break;

        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.item_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_Cancel:
                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}