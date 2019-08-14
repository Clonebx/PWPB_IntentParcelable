package com.example.pwpb_intentparcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public Button btnMoveActivity;
    public Button btnMoveWithDataActivity;
    public Button btnMoveWithObject;
    Button btnDialNumber;
    Button btnMoveResult;
    TextView tvResult;
    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        eventComponent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_move_activity:
                btnMoveEvent();
                break;
            case R.id.btn_move_with_data_activity:
                btnMoveWithData("Syarif Hidayatulloh", 17);
                break;
            case R.id.btn_move_activity_object:
                btnMoveWithObject("Syarif Hidayatulloh", 17, "Syarif040802@gmail.com", "Bandung");
                break;
            case R.id.btn_dial_number:
                btnDialNumber("0895346423981");
                break;
            case R.id.btn_move_for_result:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE) {
                int selectedValue =
                        data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
                tvResult.setText(String.format("Hasil : %s", selectedValue));
            }
        }
    }

    private void initComponent() {
        btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveWithDataActivity = findViewById(R.id.btn_move_with_data_activity);
        btnMoveWithObject = findViewById(R.id.btn_move_activity_object);
        btnDialNumber = findViewById(R.id.btn_dial_number);
        btnMoveResult = findViewById(R.id.btn_move_for_result);
        tvResult = findViewById(R.id.tv_result);
    }

    private void eventComponent() {
        btnMoveActivity.setOnClickListener(this);
        btnMoveWithDataActivity.setOnClickListener(this);
        btnMoveWithObject.setOnClickListener(this);
        btnDialNumber.setOnClickListener(this);
        btnMoveResult.setOnClickListener(this);
    }

    private void btnMoveEvent() {
        Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
        startActivity(moveIntent);
    }

    private void btnMoveWithData(String name, int age) {
        Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
        moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, name);
        moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, age);
        startActivity(moveWithDataIntent);
    }

    private void btnMoveWithObject(String name, int age, String email, String city) {
        Person mPerson = new Person();

        mPerson.setName(name);
        mPerson.setAge(age);
        mPerson.setEmail(email);
        mPerson.setCity(city);

        Intent moveWithObjectIntent = new Intent(MainActivity.this, MoveWithObjectActivity.class);
        moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, mPerson);
        startActivity(moveWithObjectIntent);
    }

    private void btnDialNumber(String numberTelp) {
        String phoneNumber = numberTelp;
        Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(dialPhoneIntent);
    }

    private void btnMoveForResult() {
        Intent moveForResultIntent = new Intent(MainActivity.this, MoveForResultActivity.class);
        startActivityForResult(moveForResultIntent, REQUEST_CODE);
    }
}