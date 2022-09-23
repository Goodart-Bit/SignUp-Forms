package com.example.signupforms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpFormActivity extends AppCompatActivity {

    private SignUpDataBaseHelper appDb;
    private EditText editFullName, editUserName, editEmail, editPassword;
    private RadioGroup rGroupGenders;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        appDb = new SignUpDataBaseHelper(this);
        appDb.initDb();
        editFullName= (EditText) findViewById(R.id.fullname);
        editUserName= (EditText) findViewById(R.id.username);
        editEmail= (EditText) findViewById(R.id.email);
        editPassword= (EditText) findViewById(R.id.password);
        rGroupGenders= (RadioGroup) findViewById(R.id.genders);
        registerBtn= (Button) findViewById(R.id.register_btn);

        listenForNewUser();
    }

    private void listenForNewUser(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText[] registrationEditTexts = new EditText[]{editFullName, editUserName, editEmail, editPassword};
                ArrayList<String> registrationData = getDataStrings(registrationEditTexts);
                int selectedGenderId = rGroupGenders.getCheckedRadioButtonId();
                if(selectedGenderId == -1 || !validateFields(registrationData)){
                    Toast.makeText(SignUpFormActivity.this,"Invalid data fields",Toast.LENGTH_SHORT).show();
                    return;
                }
                String gender = ((RadioButton)findViewById(selectedGenderId)).getText().toString();
                User new_user = new User(editFullName.getText().toString(), editUserName.getText().toString(),
                        editEmail.getText().toString(), editPassword.getText().toString(), gender);
                appDb.insertUser(new_user);
                Toast.makeText(SignUpFormActivity.this,"User created successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String> getDataStrings(EditText[] dataFields){
        ArrayList<String> dataStrings = new ArrayList<String>();
        for (EditText field:dataFields) {
            dataStrings.add(field.getText().toString());
        }
        return dataStrings;
    }

    private boolean validateFields(ArrayList<String> dataStrings){
        for (String data:dataStrings) {
            if(data.equals("")) {  //non-filled field
                return false;
            }
        }
        return true;
    }
}