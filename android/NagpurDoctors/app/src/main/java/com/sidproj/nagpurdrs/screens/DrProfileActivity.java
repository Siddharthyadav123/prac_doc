package com.sidproj.nagpurdrs.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.application.MyApplication;
import com.sidproj.nagpurdrs.entities.AppointmentDo;
import com.sidproj.nagpurdrs.entities.DrProfileDo;

import java.util.ArrayList;

public class DrProfileActivity extends BaseActivity {
    private ImageView drImageView;
    private TextView DrNameTextView;
    private TextView DrDegreeTextView;
    private TextView DrSpecializationTextView;

    //rating stars
    private ImageView star1;
    private ImageView star2;
    private ImageView star3;
    private ImageView star4;
    private ImageView star5;

    private TextView addressTextView;
    private Button locateBtn;
    private TextView descriptionTextView;
    private LinearLayout servicesLinerLayout;

    private TextView callTextView;
    private TextView bookTextView;

    //parent layout
    private LinearLayout containerLinerLayout;


    private TextView feeTextView;
    private TextView verfiedViaTextView;
    private TextView drExperienceTextView;

    //dr profile object
    private DrProfileDo drProfileDo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drProfileDo = getIntent().getParcelableExtra("key_dr_profile");
        setContentView(R.layout.activity_dr_profile);
        setupActionBar(true, "Doctor Profile");

        initViews();
        registerEvents();
        setDrInfoInUI();
        MyApplication.getInstance().enableGPS(this);
    }

    @Override
    protected void onNewNotificationArrived(ArrayList<AppointmentDo> newAppointements) {

    }

    private void initViews() {
        containerLinerLayout = (LinearLayout) findViewById(R.id.containerLinerLayout);
        drImageView = (ImageView) findViewById(R.id.drImageView);
        DrNameTextView = (TextView) findViewById(R.id.DrNameTextView);
        DrDegreeTextView = (TextView) findViewById(R.id.DrDegreeTextView);
        DrSpecializationTextView = (TextView) findViewById(R.id.DrSpecializationTextView);

        star1 = (ImageView) findViewById(R.id.star1);
        star2 = (ImageView) findViewById(R.id.star2);
        star3 = (ImageView) findViewById(R.id.star3);
        star4 = (ImageView) findViewById(R.id.star4);
        star5 = (ImageView) findViewById(R.id.star5);

        addressTextView = (TextView) findViewById(R.id.addressTextView);
        locateBtn = (Button) findViewById(R.id.locateBtn);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        servicesLinerLayout = (LinearLayout) findViewById(R.id.servicesLinerLayout);

        feeTextView = (TextView) findViewById(R.id.feeTextView);
        verfiedViaTextView = (TextView) findViewById(R.id.verfiedViaTextView);
        drExperienceTextView = (TextView) findViewById(R.id.drExperienceTextView);

        callTextView = (TextView) findViewById(R.id.callTextView);
        bookTextView = (TextView) findViewById(R.id.bookTextView);
    }

    private void registerEvents() {
        locateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drProfileDo.getDr_clinic_lat() != null && drProfileDo.getDr_clinic_long() != null) {
                    float myLat = MyApplication.getInstance().locationModel.getLatitude();
                    float myLong = MyApplication.getInstance().locationModel.getLongitude();

                    if (myLat == 0.0) {
                        float drLat = Float.parseFloat(drProfileDo.getDr_clinic_lat());
                        float drLong = Float.parseFloat(drProfileDo.getDr_clinic_long());

                        String mapUrl = "http://maps.google.com/maps?saddr=" + myLat + "," + myLong + "&daddr=" + drLat + "," + drLong;
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(mapUrl));
                        startActivity(intent);
                    } else {
                        Toast.makeText(DrProfileActivity.this, "Unable to trace your locatin please turn on your location from settings.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DrProfileActivity.this, "Doctor location not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        callTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + drProfileDo.getDrContactNum()));
                startActivity(intent);
            }
        });

        bookTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrProfileActivity.this, TimeSlotActivity.class);
                intent.putExtra("key_dr_profile", drProfileDo);
                startActivity(intent);
            }
        });
    }


    private void setDrInfoInUI() {
        DrNameTextView.setText(drProfileDo.getDrFullName());
        DrDegreeTextView.setText(drProfileDo.getDrQualification());
        DrSpecializationTextView.setText(drProfileDo.getSpecializationText());
        setRatingStarts();
        addressTextView.setText(drProfileDo.getDrClinicName() + "\n" + drProfileDo.getDrClinicAddress());
//        descriptionTextView.setText(drProfileDo.getDr());
        feedServices();
        feeTextView.setText(drProfileDo.getDrConsulationFee() + " /-");
        verfiedViaTextView.setText(drProfileDo.getDrVerifiedVia());
        drExperienceTextView.setText(drProfileDo.getDrExperience() + " yrs exp");
    }

    private void feedServices() {
        servicesLinerLayout.removeAllViews();
        if (drProfileDo.getDrServices() != null && drProfileDo.getDrServices().contains(",")) {
            String[] sevicesSplit = drProfileDo.getDrServices().split(",");
            //adding services
            for (int i = 0; i < sevicesSplit.length; i++) {
                TextView serviceTextView = new TextView(this);
                serviceTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                serviceTextView.setText("# " + sevicesSplit[i].trim());
                servicesLinerLayout.addView(serviceTextView);
            }
        } else {
            servicesLinerLayout.setVisibility(View.GONE);
        }

    }

    private void setRatingStarts() {
        float rating = drProfileDo.getDrClinicRating();
        if (rating == 1.0f) {
            star1.setImageResource(R.drawable.ic_star_filled_yellow);
        } else if (rating == 1.5f) {
            star1.setImageResource(R.drawable.ic_star_filled_yellow);
            star2.setImageResource(R.drawable.ic_star_half_yellow);
        } else if (rating == 2.0f) {
            star1.setImageResource(R.drawable.ic_star_filled_yellow);
            star2.setImageResource(R.drawable.ic_star_filled_yellow);
        } else if (rating == 2.5f) {
            star1.setImageResource(R.drawable.ic_star_filled_yellow);
            star2.setImageResource(R.drawable.ic_star_filled_yellow);
            star3.setImageResource(R.drawable.ic_star_half_yellow);
        } else if (rating == 3.0f) {
            star1.setImageResource(R.drawable.ic_star_filled_yellow);
            star2.setImageResource(R.drawable.ic_star_filled_yellow);
            star3.setImageResource(R.drawable.ic_star_filled_yellow);
        } else if (rating == 3.5f) {
            star1.setImageResource(R.drawable.ic_star_filled_yellow);
            star2.setImageResource(R.drawable.ic_star_filled_yellow);
            star3.setImageResource(R.drawable.ic_star_filled_yellow);
            star4.setImageResource(R.drawable.ic_star_half_yellow);
        } else if (rating == 4.0f) {
            star1.setImageResource(R.drawable.ic_star_filled_yellow);
            star2.setImageResource(R.drawable.ic_star_filled_yellow);
            star3.setImageResource(R.drawable.ic_star_filled_yellow);
            star4.setImageResource(R.drawable.ic_star_filled_yellow);
        } else if (rating == 4.5f) {
            star1.setImageResource(R.drawable.ic_star_filled_yellow);
            star2.setImageResource(R.drawable.ic_star_filled_yellow);
            star3.setImageResource(R.drawable.ic_star_filled_yellow);
            star4.setImageResource(R.drawable.ic_star_filled_yellow);
            star5.setImageResource(R.drawable.ic_star_half_yellow);
        } else if (rating == 5.0f) {
            star1.setImageResource(R.drawable.ic_star_filled_yellow);
            star2.setImageResource(R.drawable.ic_star_filled_yellow);
            star3.setImageResource(R.drawable.ic_star_filled_yellow);
            star4.setImageResource(R.drawable.ic_star_filled_yellow);
            star5.setImageResource(R.drawable.ic_star_filled_yellow);
        }
    }

    @Override
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString) {

    }
}
