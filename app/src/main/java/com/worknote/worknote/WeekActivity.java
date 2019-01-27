package com.worknote.worknote;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import static com.worknote.worknote.DateActivity.EXTRA_DATE;

public class WeekActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,
        View.OnClickListener {
    public static final String EXTRA_ID =
            "com.worknote.worknote.EXTRA_WEEK_ID";
    public static final String EXTRA_WEEK_DATE =
            "com.worknote.worknote.EXTRA_WEEK_DATE";
    public static final String EXTRA_LAST_UPDATE =
            "com.worknote.worknote.EXTRA_LAST_UPDATE";
    public static final String EXTRA_MON_TIME =
            "com.worknote.worknote.EXTRA_MON_TIME";
    public static final String EXTRA_TUE_TIME =
            "com.worknote.worknote.EXTRA_TUE_TIME";
    public static final String EXTRA_WED_TIME =
            "com.worknote.worknote.EXTRA_WED_TIME";
    public static final String EXTRA_THU_TIME =
            "com.worknote.worknote.EXTRA_THU_TIME";
    public static final String EXTRA_FRI_TIME =
            "com.worknote.worknote.EXTRA_FRI_TIME";
    public static final String EXTRA_SAT_TIME =
            "com.worknote.worknote.EXTRA_SAT_TIME";
    public static final String EXTRA_SUN_TIME =
            "com.worknote.worknote.EXTRA_SUN_TIME";

    private String date;
    private TextView dateTVWeek;
    private TextView monTV, tueTV, wedTV, thuTV, friTV, satTV, sunTV;
    private TextView setMonTime, setTueTime, setWedTime, setThuTime,
            setFriTime, setSatTime, setSunTime;
    private TextView textViewMon, textViewTue, textViewWed,
            textViewThu, textViewFri, textViewSat, textViewSun;
    private FloatingActionButton saveBtn, backBtn;
    private View orangeView, darkView;
    private ConstraintLayout mainLayout;
    private String checker = "";
    private Boolean monBool, tueBool, wedBool,
            thuBool, friBool, satBool, sunBool = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        mainLayout = findViewById(R.id.mainLayout);
        orangeView = findViewById(R.id.orangeViewWeek);
        darkView = findViewById(R.id.darkViewWeek);
        dateTVWeek = findViewById(R.id.dateTVWeek);
        //textView left of time
        textViewMon = findViewById(R.id.textViewMon);
        textViewTue = findViewById(R.id.textViewTue);
        textViewWed = findViewById(R.id.textViewWed);
        textViewThu = findViewById(R.id.textViewThu);
        textViewFri = findViewById(R.id.textViewFri);
        textViewSat = findViewById(R.id.textViewSat);
        textViewSun = findViewById(R.id.textViewSun);
        //Where time is shown
        monTV = findViewById(R.id.monTV);
        tueTV = findViewById(R.id.tueTV);
        wedTV = findViewById(R.id.wedTV);
        thuTV = findViewById(R.id.thuTV);
        friTV = findViewById(R.id.friTV);
        satTV = findViewById(R.id.satTV);
        sunTV = findViewById(R.id.sunTV);
        //example text: Set Monday Time
        setMonTime = findViewById(R.id.setMonTimeTV);
        setTueTime = findViewById(R.id.setTueTimeTV);
        setWedTime = findViewById(R.id.setWedTimeTV);
        setThuTime = findViewById(R.id.setThuTimeTV);
        setFriTime = findViewById(R.id.setFriTimeTV);
        setSatTime = findViewById(R.id.setSatTimeTV);
        setSunTime = findViewById(R.id.setSunTimeTV);
        saveBtn = findViewById(R.id.nextBtnWeek);
        backBtn = findViewById(R.id.closeBtnWeek);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                date = extras.getString(EXTRA_DATE);
                dateTVWeek.setText(date);
            }
        }
        saveBtn.setEnabled(false);

        getData();
        onActivityOpenAnimation();

        saveBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        monTV.setOnClickListener(this);
        tueTV.setOnClickListener(this);
        wedTV.setOnClickListener(this);
        thuTV.setOnClickListener(this);
        friTV.setOnClickListener(this);
        satTV.setOnClickListener(this);
        sunTV.setOnClickListener(this);
        setMonTime.setOnClickListener(this);
        setTueTime.setOnClickListener(this);
        setWedTime.setOnClickListener(this);
        setThuTime.setOnClickListener(this);
        setFriTime.setOnClickListener(this);
        setSatTime.setOnClickListener(this);
        setSunTime.setOnClickListener(this);
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            dateTVWeek.setText(intent.getStringExtra(EXTRA_WEEK_DATE));
            monTV.setText(intent.getStringExtra(EXTRA_MON_TIME));
            tueTV.setText(intent.getStringExtra(EXTRA_TUE_TIME));
            wedTV.setText(intent.getStringExtra(EXTRA_WED_TIME));
            thuTV.setText(intent.getStringExtra(EXTRA_THU_TIME));
            friTV.setText(intent.getStringExtra(EXTRA_FRI_TIME));
            satTV.setText(intent.getStringExtra(EXTRA_SAT_TIME));
            sunTV.setText(intent.getStringExtra(EXTRA_SUN_TIME));

            if (!monTV.getText().toString().isEmpty()) {
                setMonTime.setVisibility(View.INVISIBLE);
                textViewMon.setVisibility(View.VISIBLE);
                monTV.setVisibility(View.VISIBLE);
            }
            if (!tueTV.getText().toString().isEmpty()) {
                setTueTime.setVisibility(View.INVISIBLE);
                textViewTue.setVisibility(View.VISIBLE);
                tueTV.setVisibility(View.VISIBLE);
            }
            if (!wedTV.getText().toString().isEmpty()) {
                setWedTime.setVisibility(View.INVISIBLE);
                textViewWed.setVisibility(View.VISIBLE);
                wedTV.setVisibility(View.VISIBLE);
            }
            if (!thuTV.getText().toString().isEmpty()) {
                setThuTime.setVisibility(View.INVISIBLE);
                textViewThu.setVisibility(View.VISIBLE);
                thuTV.setVisibility(View.VISIBLE);
            }
            if (!friTV.getText().toString().isEmpty()) {
                setFriTime.setVisibility(View.INVISIBLE);
                textViewFri.setVisibility(View.VISIBLE);
                friTV.setVisibility(View.VISIBLE);
            }
            if (!satTV.getText().toString().isEmpty()) {
                setSatTime.setVisibility(View.INVISIBLE);
                textViewSat.setVisibility(View.VISIBLE);
                satTV.setVisibility(View.VISIBLE);
            }
            if (!sunTV.getText().toString().isEmpty()) {
                setSunTime.setVisibility(View.INVISIBLE);
                textViewSun.setVisibility(View.VISIBLE);
                sunTV.setVisibility(View.VISIBLE);
            }
        }
    }

    private void dayOfWeekChecker(Intent data,
                                  String monTime,
                                  String tueTime,
                                  String wedTime,
                                  String thuTime,
                                  String friTime,
                                  String satTime,
                                  String sunTime) {
        if (!monTime.isEmpty()) {
            monBool = true;
            data.putExtra("m", monBool);
        } else {
            monBool = false;
            data.putExtra("m", monBool);
        }

        if (!tueTime.isEmpty()) {
            tueBool = true;
            data.putExtra("tu", tueBool);
        } else {
            tueBool = false;
            data.putExtra("tu", tueBool);
        }

        if (!wedTime.isEmpty()) {
            wedBool = true;
            data.putExtra("w", wedBool);
        } else {
            wedBool = false;
            data.putExtra("w", wedBool);
        }

        if (!thuTime.isEmpty()) {
            thuBool = true;
            data.putExtra("th", thuBool);
        } else {
            thuBool = false;
            data.putExtra("th", thuBool);
        }

        if (!friTime.isEmpty()) {
            friBool = true;
            data.putExtra("f", friBool);
        } else {
            friBool = false;
            data.putExtra("f", friBool);
        }

        if (!satTime.isEmpty()) {
            satBool = true;
            data.putExtra("st", satBool);
        } else {
            satBool = false;
            data.putExtra("st", satBool);
        }

        if (!sunTime.isEmpty()) {
            sunBool = true;
            data.putExtra("su", sunBool);
        } else {
            sunBool = false;
            data.putExtra("su", sunBool);
        }
    }

    private void save() {
        String date = dateTVWeek.getText().toString();
        String monTime = monTV.getText().toString();
        String tueTime = tueTV.getText().toString();
        String wedTime = wedTV.getText().toString();
        String thuTime = thuTV.getText().toString();
        String friTime = friTV.getText().toString();
        String satTime = satTV.getText().toString();
        String sunTime = sunTV.getText().toString();

        java.text.DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        String currentDate = dateFormat.format(calendar.getTime());

        final Intent data = new Intent(WeekActivity.this, MainActivity.class);
        data.putExtra(EXTRA_WEEK_DATE, date);
        data.putExtra(EXTRA_LAST_UPDATE, currentDate);
        data.putExtra(EXTRA_MON_TIME, monTime);
        data.putExtra(EXTRA_TUE_TIME, tueTime);
        data.putExtra(EXTRA_WED_TIME, wedTime);
        data.putExtra(EXTRA_THU_TIME, thuTime);
        data.putExtra(EXTRA_FRI_TIME, friTime);
        data.putExtra(EXTRA_SAT_TIME, satTime);
        data.putExtra(EXTRA_SUN_TIME, sunTime);
        data.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        dayOfWeekChecker(data, monTime, tueTime, wedTime, thuTime, friTime, satTime, sunTime);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
            dayOfWeekChecker(data, monTime, tueTime, wedTime, thuTime, friTime, satTime, sunTime);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(data);
                overridePendingTransition(
                        //second view
                        R.anim.no_animation,
                        //first view
                        R.anim.no_animation);
                saveBtn.setEnabled(true);
            }
        }, 225);
    }

    @Override
    public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
        final TimePickerFragment timePicker = new TimePickerFragment();

        if (checker.equals("monTVPressed")) {
            monTV.setText(String.format(Locale.ENGLISH, "%02d:%02d", hourOfDay, minute));
            timePicker.show(getSupportFragmentManager(), "timePicker");
            checker = "monTVResume";
        } else if (checker.equals("monTVResume")) {
            timePicker.show(getSupportFragmentManager(), "timePicker");
            String extra = monTV.getText().toString() + " - ";
            monTV.setText(String.format(extra + "%02d:%02d", hourOfDay, minute));
            timePicker.dismiss();
            checker = "monTVPressed";
            //Animation for views
            dayTimeAnimation("monAnimation");
        }

        if (checker.equals("tueTVPressed")) {
            tueTV.setText(String.format(Locale.ENGLISH, "%02d:%02d", hourOfDay, minute));
            timePicker.show(getSupportFragmentManager(), "timePicker");
            checker = "tueTVResume";
        } else if (checker.equals("tueTVResume")) {
            timePicker.show(getSupportFragmentManager(), "timePicker");
            String extra = tueTV.getText().toString() + " - ";
            tueTV.setText(String.format(extra + "%02d:%02d", hourOfDay, minute));
            timePicker.dismiss();
            checker = "tueTVPressed";
            //Animation for views
            dayTimeAnimation("tueAnimation");
        }

        if (checker.equals("wedTVPressed")) {
            wedTV.setText(String.format(Locale.ENGLISH, "%02d:%02d", hourOfDay, minute));
            timePicker.show(getSupportFragmentManager(), "timePicker");
            checker = "wedTVResume";
        } else if (checker.equals("wedTVResume")) {
            timePicker.show(getSupportFragmentManager(), "timePicker");
            String extra = wedTV.getText().toString() + " - ";
            wedTV.setText(String.format(extra + "%02d:%02d", hourOfDay, minute));
            timePicker.dismiss();
            checker = "wedTVPressed";
            //Animation for views
            dayTimeAnimation("wedAnimation");
        }

        if (checker.equals("thuTVPressed")) {
            thuTV.setText(String.format(Locale.ENGLISH, "%02d:%02d", hourOfDay, minute));
            timePicker.show(getSupportFragmentManager(), "timePicker");
            checker = "thuTVResume";
        } else if (checker.equals("thuTVResume")) {
            timePicker.show(getSupportFragmentManager(), "timePicker");
            String extra = thuTV.getText().toString() + " - ";
            thuTV.setText(String.format(extra + "%02d:%02d", hourOfDay, minute));
            timePicker.dismiss();
            checker = "thuTVPressed";
            //Animation for views
            dayTimeAnimation("thuAnimation");
        }

        if (checker.equals("friTVPressed")) {
            friTV.setText(String.format(Locale.ENGLISH, "%02d:%02d", hourOfDay, minute));
            timePicker.show(getSupportFragmentManager(), "timePicker");
            checker = "friTVResume";
        } else if (checker.equals("friTVResume")) {
            timePicker.show(getSupportFragmentManager(), "timePicker");
            String extra = friTV.getText().toString() + " - ";
            friTV.setText(String.format(extra + "%02d:%02d", hourOfDay, minute));
            timePicker.dismiss();
            checker = "friTVPressed";
            //Animation for views
            dayTimeAnimation("friAnimation");
        }

        if (checker.equals("satTVPressed")) {
            satTV.setText(String.format(Locale.ENGLISH, "%02d:%02d", hourOfDay, minute));
            timePicker.show(getSupportFragmentManager(), "timePicker");
            checker = "satTVResume";
        } else if (checker.equals("satTVResume")) {
            timePicker.show(getSupportFragmentManager(), "timePicker");
            String extra = satTV.getText().toString() + " - ";
            satTV.setText(String.format(extra + "%02d:%02d", hourOfDay, minute));
            timePicker.dismiss();
            checker = "satTVPressed";
            //Animation for views
            dayTimeAnimation("satAnimation");
        }

        if (checker.equals("sunTVPressed")) {
            sunTV.setText(String.format(Locale.ENGLISH, "%02d:%02d", hourOfDay, minute));
            timePicker.show(getSupportFragmentManager(), "timePicker");
            checker = "sunTVResume";
        } else if (checker.equals("sunTVResume")) {
            timePicker.show(getSupportFragmentManager(), "timePicker");
            String extra = sunTV.getText().toString() + " - ";
            sunTV.setText(String.format(extra + "%02d:%02d", hourOfDay, minute));
            timePicker.dismiss();
            checker = "sunTVPressed";
            //Animation for views
            dayTimeAnimation("sunAnimation");
        }

    }

    private void dayTimeAnimation(String checker) {
        switch (checker) {
            case "monAnimation":
                if (setMonTime.getVisibility() == View.VISIBLE) {
                    Animation setMondayAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.fade_in);
                    setMonTime.setAnimation(setMondayAnimation);
                    setMonTime.setVisibility(View.GONE);
                    setMondayAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation textViewMonAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            textViewMon.setAnimation(textViewMonAnimation);
                            textViewMon.setVisibility(View.VISIBLE);

                            monTV.setAnimation(textViewMonAnimation);
                            monTV.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } else if (setMonTime.getVisibility() != View.VISIBLE) {
                    Animation setAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.no_animation);
                    textViewMon.setAnimation(setAnimation);
                    monTV.setAnimation(setAnimation);
                    setMonTime.setVisibility(View.GONE);
                }
                break;
            case "tueAnimation":
                if (setTueTime.getVisibility() == View.VISIBLE) {
                    Animation setMondayAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.fade_in);
                    setTueTime.setAnimation(setMondayAnimation);
                    setTueTime.setVisibility(View.GONE);
                    setMondayAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation textViewMonAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            textViewTue.setAnimation(textViewMonAnimation);
                            textViewTue.setVisibility(View.VISIBLE);

                            Animation monTVAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            tueTV.setAnimation(monTVAnimation);
                            tueTV.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } else if (setTueTime.getVisibility() != View.VISIBLE) {
                    Animation setAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.no_animation);
                    textViewTue.setAnimation(setAnimation);
                    tueTV.setAnimation(setAnimation);
                    setTueTime.setVisibility(View.GONE);
                }
                break;
            case "wedAnimation":
                if (setWedTime.getVisibility() == View.VISIBLE) {
                    Animation setMondayAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.fade_in);
                    setWedTime.setAnimation(setMondayAnimation);
                    setWedTime.setVisibility(View.GONE);
                    setMondayAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation textViewMonAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            textViewWed.setAnimation(textViewMonAnimation);
                            textViewWed.setVisibility(View.VISIBLE);

                            Animation monTVAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            wedTV.setAnimation(monTVAnimation);
                            wedTV.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } else if (setWedTime.getVisibility() != View.VISIBLE) {
                    Animation setAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.no_animation);
                    textViewWed.setAnimation(setAnimation);
                    wedTV.setAnimation(setAnimation);
                    setWedTime.setVisibility(View.GONE);
                }
                break;
            case "thuAnimation":
                if (setThuTime.getVisibility() == View.VISIBLE) {
                    Animation setMondayAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.fade_in);
                    setThuTime.setAnimation(setMondayAnimation);
                    setThuTime.setVisibility(View.GONE);
                    setMondayAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation textViewMonAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            textViewThu.setAnimation(textViewMonAnimation);
                            textViewThu.setVisibility(View.VISIBLE);

                            Animation monTVAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            thuTV.setAnimation(monTVAnimation);
                            thuTV.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } else if (setThuTime.getVisibility() != View.VISIBLE) {
                    Animation setAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.no_animation);
                    textViewThu.setAnimation(setAnimation);
                    thuTV.setAnimation(setAnimation);
                    setThuTime.setVisibility(View.GONE);
                }
                break;
            case "friAnimation":
                if (setFriTime.getVisibility() == View.VISIBLE) {
                    Animation setMondayAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.fade_in);
                    setFriTime.setAnimation(setMondayAnimation);
                    setFriTime.setVisibility(View.GONE);
                    setMondayAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation textViewMonAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            textViewFri.setAnimation(textViewMonAnimation);
                            textViewFri.setVisibility(View.VISIBLE);

                            Animation monTVAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            friTV.setAnimation(monTVAnimation);
                            friTV.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } else if (setFriTime.getVisibility() != View.VISIBLE) {
                    Animation setAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.no_animation);
                    textViewFri.setAnimation(setAnimation);
                    friTV.setAnimation(setAnimation);
                    setFriTime.setVisibility(View.GONE);
                }
                break;
            case "satAnimation":
                if (setSatTime.getVisibility() == View.VISIBLE) {
                    Animation setMondayAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.fade_in);
                    setSatTime.setAnimation(setMondayAnimation);
                    setSatTime.setVisibility(View.GONE);
                    setMondayAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation textViewMonAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            textViewSat.setAnimation(textViewMonAnimation);
                            textViewSat.setVisibility(View.VISIBLE);

                            Animation monTVAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            satTV.setAnimation(monTVAnimation);
                            satTV.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } else if (setSatTime.getVisibility() != View.VISIBLE) {
                    Animation setAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.no_animation);
                    textViewSat.setAnimation(setAnimation);
                    satTV.setAnimation(setAnimation);
                    setSatTime.setVisibility(View.GONE);
                }
                break;
            case "sunAnimation":
                if (setSunTime.getVisibility() == View.VISIBLE) {
                    Animation setMondayAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.fade_in);
                    setSunTime.setAnimation(setMondayAnimation);
                    setSunTime.setVisibility(View.GONE);
                    setMondayAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Animation textViewMonAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            textViewSun.setAnimation(textViewMonAnimation);
                            textViewSun.setVisibility(View.VISIBLE);

                            Animation monTVAnimation = AnimationUtils
                                    .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
                            sunTV.setAnimation(monTVAnimation);
                            sunTV.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } else if (setSunTime.getVisibility() != View.VISIBLE) {
                    Animation setAnimation = AnimationUtils
                            .loadAnimation(this, R.anim.no_animation);
                    textViewSun.setAnimation(setAnimation);
                    sunTV.setAnimation(setAnimation);
                    setSunTime.setVisibility(View.GONE);
                }
                break;
        }
    }

    //When activity opens animation
    private void onActivityOpenAnimation() {
        backBtn.setScaleX(0);
        backBtn.setScaleY(0);
        saveBtn.setScaleX(0);
        saveBtn.setScaleY(0);

        Animation layoutAnimation = AnimationUtils
                .loadAnimation(getBaseContext(), R.anim.fade_out_small_scale);
        mainLayout.setAnimation(layoutAnimation);

        Animation titleAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down_fade);
        dateTVWeek.setAnimation(titleAnimation);
        dateTVWeek.setVisibility(View.VISIBLE);

        Animation orangeViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down_orange_view);
        orangeView.setAnimation(orangeViewAnimation);
        orangeView.setVisibility(View.VISIBLE);

        Animation darkViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        darkView.setAnimation(darkViewAnimation);
        darkView.setVisibility(View.VISIBLE);
        darkViewAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                backBtn.animate()
                        .scaleX(1).scaleY(1).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).start();
                saveBtn.animate()
                        .scaleX(1).scaleY(1).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).start();
                saveBtn.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //When backBtn is pressed animation
    private void closeActivityAnimation() {
        Animation titleAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade);
        dateTVWeek.setAnimation(titleAnimation);
        dateTVWeek.setVisibility(View.INVISIBLE);

        Animation orangeViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_orange_view);
        orangeView.setAnimation(orangeViewAnimation);
        orangeView.setVisibility(View.INVISIBLE);

        Animation darkViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        darkView.setAnimation(darkViewAnimation);
        darkView.setVisibility(View.INVISIBLE);
        darkViewAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                backBtn.animate()
                        .scaleX(0).scaleY(0).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).rotation(0.0f).start();
                saveBtn.animate()
                        .scaleX(0).scaleY(0).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation layoutAnimation = AnimationUtils
                        .loadAnimation(getBaseContext(), R.anim.fade_in);
                mainLayout.setAnimation(layoutAnimation);
                mainLayout.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }
                }, 200);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //When saveBtn is pressed
    private void nextActivityAnimation() {
        Animation titleAnimation = AnimationUtils
                .loadAnimation(this, R.anim.slide_up_fade);
        dateTVWeek.setAnimation(titleAnimation);
        dateTVWeek.setVisibility(View.INVISIBLE);

        Animation orangeViewAnimation = AnimationUtils
                .loadAnimation(this, R.anim.slide_up_orange_view);
        orangeView.setAnimation(orangeViewAnimation);
        orangeView.setVisibility(View.INVISIBLE);

        Animation darkViewAnimation = AnimationUtils
                .loadAnimation(this, R.anim.slide_down);
        darkView.setAnimation(darkViewAnimation);
        darkView.setVisibility(View.INVISIBLE);
        darkViewAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                backBtn.animate()
                        .scaleX(0).scaleY(0).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).rotation(0.0f).start();
                saveBtn.animate()
                        .scaleX(0).scaleY(0).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation layoutAnimation = AnimationUtils
                        .loadAnimation(getBaseContext(), R.anim.fade_in_small_scale);
                mainLayout.setAnimation(layoutAnimation);
                mainLayout.setVisibility(View.GONE);
                save();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        TimePickerFragment timePicker = new TimePickerFragment();
        switch (v.getId()) {
            case R.id.nextBtnWeek:
                saveBtn.setEnabled(false);
                nextActivityAnimation();
                break;
            case R.id.closeBtnWeek:
                backBtn.setEnabled(false);
                closeActivityAnimation();
                break;
            case R.id.monTV:
                checker = "monTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.tueTV:
                checker = "tueTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.wedTV:
                checker = "wedTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.thuTV:
                checker = "thuTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.friTV:
                checker = "friTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.satTV:
                checker = "satTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.sunTV:
                checker = "sunTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.setMonTimeTV:
                checker = "monTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.setTueTimeTV:
                checker = "tueTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.setWedTimeTV:
                checker = "wedTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.setThuTimeTV:
                checker = "thuTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.setFriTimeTV:
                checker = "friTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.setSatTimeTV:
                checker = "satTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
            case R.id.setSunTimeTV:
                checker = "sunTVPressed";
                timePicker.show(getSupportFragmentManager(), "timePicker");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(
                //second view
                R.anim.no_animation,
                //first view
                R.anim.no_animation);
        backBtn.setEnabled(true);
    }
}
