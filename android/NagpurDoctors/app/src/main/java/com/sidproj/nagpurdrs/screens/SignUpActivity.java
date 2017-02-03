package com.sidproj.nagpurdrs.screens;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.constants.RequestConstant;
import com.sidproj.nagpurdrs.constants.URLConstants;
import com.sidproj.nagpurdrs.entities.AppointmentDo;
import com.sidproj.nagpurdrs.entities.UserProfileDo;
import com.sidproj.nagpurdrs.volly.APIHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class SignUpActivity extends BaseActivity {

    private EditText userNameEditText;
    private EditText pwdEditText;
    private EditText confirmPwdEditText;
    private EditText fullNameEditText;
    private EditText addressEditText;
    private EditText mobnoEditText;
    private TextView dobTextView;

    private RadioGroup radioGrp;

    private LinearLayout bottomBarLinearLayout;

    private int selectedYear = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.YEAR);
    private int selectedMonth = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.MONTH);
    private int selectedDay = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupActionBar(true, "SignUp (Patient)");
        hideNotificationBtn();
        initViews();
        registerEvents();
    }

    @Override
    protected void onNewNotificationArrived(ArrayList<AppointmentDo> newAppointements) {

    }

    private void initViews() {
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        pwdEditText = (EditText) findViewById(R.id.pwdEditText);
        confirmPwdEditText = (EditText) findViewById(R.id.confirmPwdEditText);
        fullNameEditText = (EditText) findViewById(R.id.fullNameEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        mobnoEditText = (EditText) findViewById(R.id.mobnoEditText);
        dobTextView = (TextView) findViewById(R.id.dobTextView);
        bottomBarLinearLayout = (LinearLayout) findViewById(R.id.bottomBarLinearLayout);
        radioGrp = (RadioGroup) findViewById(R.id.radioGrp);

    }

    private boolean isValid() {
        if (userNameEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter user name");
            return false;
        } else if (pwdEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter password");
            return false;
        } else if (confirmPwdEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter confirm password");
            return false;
        } else if (pwdEditText.getText().toString().trim().length() < 8 ||
                confirmPwdEditText.getText().toString().trim().length() < 8) {
            showInfoDailog(this, "Password length should be greater than or equal to 8 character");
            return false;
        } else if (!pwdEditText.getText().toString().trim().equals(confirmPwdEditText.getText().toString().trim())) {
            showInfoDailog(this, "Please confirm your password, it's incorrect");
            return false;
        } else if (fullNameEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter full name");
            return false;
        } else if (addressEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter address");
            return false;
        } else if (mobnoEditText.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter mobile number");
            return false;
        } else if (mobnoEditText.getText().toString().trim().length() != 10) {
            showInfoDailog(this, "Incorrect mobile number, check the the length");
            return false;
        } else if (dobTextView.getText().toString().trim().length() == 0) {
            showInfoDailog(this, "Please enter date of birth");
            return false;
        }
        return true;
    }

    private void registerEvents() {
        bottomBarLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    requestSignUp();
                }
            }
        });

        dobTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });
    }

    private UserProfileDo collectUserProfileInfo() {
        UserProfileDo userProfileDo = new UserProfileDo();
        userProfileDo.setUname(userNameEditText.getText().toString().trim());
        userProfileDo.setFull_name(fullNameEditText.getText().toString().trim());
        userProfileDo.setPwd(pwdEditText.getText().toString().trim());
        userProfileDo.setAddress(addressEditText.getText().toString().trim());
        userProfileDo.setMobile_no(mobnoEditText.getText().toString().trim());
        userProfileDo.setDob(dobTextView.getText().toString().trim());

        if (radioGrp.getCheckedRadioButtonId() == R.id.radioM)
            userProfileDo.setGender("M");
        else
            userProfileDo.setGender("F");

        return userProfileDo;
    }

    private void requestSignUp() {
        APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_USER_SINGUP, Request.Method.POST, URLConstants.URL_POST_USER_SIGNUP
                , true, "Please wait while registering...", toJSONString(collectUserProfileInfo()));
        apiHandler.requestAPI();
    }


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            selectedYear = arg1;
            selectedMonth = arg2 + 1;
            selectedDay = arg3;
            dobTextView.setText(selectedYear + "-" + selectedMonth + "-" + selectedDay);

        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, selectedYear, selectedMonth, selectedDay);
        }
        return null;
    }


    @Override
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString) {
        if (isSuccess) {
            finish();
            Toast.makeText(SignUpActivity.this, "Sign-Up Successfully !! Please login now.", Toast.LENGTH_SHORT).show();
        }
    }

    public String toJSONString(UserProfileDo userProfileDo) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uname", userProfileDo.getUname());
            jsonObject.put("pwd", userProfileDo.getPwd());
            jsonObject.put("full_name", userProfileDo.getFull_name());
            jsonObject.put("address", userProfileDo.getAddress());
            jsonObject.put("mobile_no", userProfileDo.getMobile_no());
            jsonObject.put("gender", userProfileDo.getGender());
            jsonObject.put("dob", userProfileDo.getDob());
            jsonObject.put("profile_pic_url", userProfileDo.getProfile_pic_url());
            return jsonObject.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
