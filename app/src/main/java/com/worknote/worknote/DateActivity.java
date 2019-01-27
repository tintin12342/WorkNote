package com.worknote.worknote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DateActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_DATE =
            "com.worknote.worknote.EXTRA_DATE";

    private TextView title;
    private TextView weekDateTV;
    private TextView textInputError;
    private FloatingActionButton closeBtn;
    private FloatingActionButton nextBtn;
    private CalendarView calendarView;
    private View darkView, orangeView;
    private LinearLayout calendarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        calendarLayout = findViewById(R.id.calendarLayout);
        title = findViewById(R.id.dateTVWeek);
        textInputError = findViewById(R.id.textinputError);
        weekDateTV = findViewById(R.id.weekDateTV);
        closeBtn = findViewById(R.id.closeBtnDate);
        nextBtn = findViewById(R.id.nextBtnDate);
        calendarView = findViewById(R.id.calendarView);
        darkView = findViewById(R.id.darkViewDate);
        orangeView = findViewById(R.id.orangeViewDate);

        calendarViewListener();
        onActivityOpenAnimation();

        weekDateTV.setOnClickListener(this);
        closeBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }

    private void calendarViewListener() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                weekDateTV.setText(date);
                textInputError.setVisibility(View.GONE);
            }
        });
    }
    //When activity opens
    private void onActivityOpenAnimation() {
        Animation titleAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down_fade);
        title.setAnimation(titleAnimation);
        title.setVisibility(View.VISIBLE);

        Animation orangeViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down_orange_view);
        orangeView.setAnimation(orangeViewAnimation);
        orangeView.setVisibility(View.VISIBLE);

        Animation weekDateTVAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_small_scale);
        weekDateTV.setAnimation(weekDateTVAnimation);
        weekDateTV.setVisibility(View.VISIBLE);

        Animation calendarAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_small_scale);
        calendarLayout.setAnimation(calendarAnimation);
        calendarLayout.setVisibility(View.VISIBLE);

        Animation darkViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        darkView.setAnimation(darkViewAnimation);
        darkView.setVisibility(View.VISIBLE);
        darkViewAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                closeBtn.animate()
                        .scaleX(1).scaleY(1).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).rotation(180.0f).start();
                nextBtn.animate()
                        .scaleX(1).scaleY(1).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    //When closeBtn is pressed
    private void closeActivityAnimation() {
        Animation titleAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade);
        title.setAnimation(titleAnimation);
        title.setVisibility(View.INVISIBLE);

        Animation orangeViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_orange_view);
        orangeView.setAnimation(orangeViewAnimation);
        orangeView.setVisibility(View.INVISIBLE);

        if (textInputError.getVisibility() == View.VISIBLE) {
            Animation errorMsgAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_small_scale);
            textInputError.setAnimation(errorMsgAnimation);
            textInputError.setVisibility(View.GONE);
        }

        Animation weekDateTVAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_small_scale);
        weekDateTV.setAnimation(weekDateTVAnimation);


        Animation calendarAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_small_scale);
        calendarLayout.setAnimation(calendarAnimation);

        Animation darkViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down_dark_view);
        darkView.setAnimation(darkViewAnimation);
        darkView.setVisibility(View.INVISIBLE);
        darkViewAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                closeBtn.animate()
                        .scaleX(0).scaleY(0).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).rotation(90.0f).start();
                nextBtn.animate()
                        .scaleX(0).scaleY(0).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onBackPressed();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //When nextBtn is pressed
    private void nextActivityAnimation() {
        Animation titleAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade);
        title.setAnimation(titleAnimation);
        title.setVisibility(View.INVISIBLE);

        Animation orangeViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_orange_view);
        orangeView.setAnimation(orangeViewAnimation);
        orangeView.setVisibility(View.INVISIBLE);

        if (textInputError.getVisibility() == View.VISIBLE) {
            Animation errorMsgAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_small_scale);
            textInputError.setAnimation(errorMsgAnimation);
            textInputError.setVisibility(View.GONE);
        }

        Animation weekDateTVAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_small_scale);
        weekDateTV.setAnimation(weekDateTVAnimation);
        weekDateTV.setVisibility(View.GONE);

        Animation calendarAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_small_scale);
        calendarLayout.setAnimation(calendarAnimation);
        calendarLayout.setVisibility(View.GONE);

        Animation darkViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down_dark_view);
        darkView.setAnimation(darkViewAnimation);
        darkView.setVisibility(View.INVISIBLE);
        darkViewAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                closeBtn.animate()
                        .scaleX(0).scaleY(0).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).rotation(90.0f).start();
                nextBtn.animate()
                        .scaleX(0).scaleY(0).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                String date = weekDateTV.getText().toString();

                final Intent intent = new Intent(DateActivity.this, WeekActivity.class);
                intent.putExtra(EXTRA_DATE ,date);
                startActivity(intent);
                overridePendingTransition(
                        //second view
                        R.anim.no_animation,
                        //first view
                        R.anim.no_animation);
                nextBtn.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //When you come back to this activity
    @Override
    protected void onResume() {
        super.onResume();
        closeBtn.setScaleX(0);
        closeBtn.setScaleY(0);
        nextBtn.setScaleX(0);
        nextBtn.setScaleY(0);

        Animation titleAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down_fade);
        title.setAnimation(titleAnimation);
        title.setVisibility(View.VISIBLE);

        Animation orangeViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down_orange_view);
        orangeView.setAnimation(orangeViewAnimation);
        orangeView.setVisibility(View.VISIBLE);

        Animation weekDateTVAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_small_scale);
        weekDateTV.setAnimation(weekDateTVAnimation);
        weekDateTV.setVisibility(View.VISIBLE);

        Animation calendarAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_small_scale);
        calendarLayout.setAnimation(calendarAnimation);
        calendarLayout.setVisibility(View.VISIBLE);

        Animation darkViewAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        darkView.setAnimation(darkViewAnimation);
        darkView.setVisibility(View.VISIBLE);
        darkViewAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                closeBtn.animate()
                        .scaleX(1).scaleY(1).setDuration(200)
                        .setInterpolator(new DecelerateInterpolator()).rotation(180.0f).start();
                nextBtn.animate()
                        .scaleX(1).scaleY(1).setDuration(200)
                        .setInterpolator(new DecelerateInterpolator()).start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextBtnDate:
                if (weekDateTV.getText().toString().isEmpty()) {
                    textInputError.setVisibility(View.VISIBLE);
                    nextBtn.setEnabled(true);
                } else {
                    nextBtn.setEnabled(false);
                    nextActivityAnimation();
                }
                break;
            case R.id.closeBtnDate:
                closeBtn.setEnabled(false);
                closeActivityAnimation();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(
                //first view
                R.anim.no_animation,
                //second view
                R.anim.no_animation);
        closeBtn.setEnabled(true);
    }
}
