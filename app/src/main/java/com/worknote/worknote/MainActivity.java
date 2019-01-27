package com.worknote.worknote;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import static com.worknote.worknote.WeekActivity.EXTRA_ID;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    private NoteViewModel noteViewModel;
    private FloatingActionButton fabOpen;
    private BottomAppBar mBar;
    //Red swipe delete background
    private ColorDrawable swipeColor = new ColorDrawable(Color.parseColor("#ffff4444"));
    //White swipe delete background right excess
    private ColorDrawable excessColor = new ColorDrawable(Color.parseColor("#EAF0FF"));
    private Drawable iconDelete;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBar = findViewById(R.id.mainBar);
        setSupportActionBar(mBar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);
            }
        });

        iconDelete = ContextCompat.getDrawable(this, R.drawable.ic_delete);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast toast = Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_SHORT);
                View view = toast.getView();
                view.setBackgroundResource(R.drawable.toast_background);
                toast.show();
            }

            @Override
            public void onChildDraw(@NonNull Canvas c,
                                    @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder,
                                    float dX,
                                    float dY,
                                    int actionState,
                                    boolean isCurrentlyActive) {

                View itemView = viewHolder.itemView;

                int iconMargin = (itemView.getHeight() - iconDelete.getIntrinsicHeight()) / 2;

                if (dX < 1) {
                    swipeColor.setBounds(
                            itemView.getRight() + Math.round(dX) + 2,
                            itemView.getTop(),
                            itemView.getRight() - 8,
                            itemView.getBottom());
                    excessColor.setBounds(
                            itemView.getRight() - Math.round(dX) + 5,
                            itemView.getTop(),
                            itemView.getRight() - 10,
                            itemView.getBottom());
                    iconDelete.setBounds(
                            itemView.getRight() - iconMargin - iconDelete.getIntrinsicWidth(),
                            itemView.getTop() + iconMargin,
                            itemView.getRight() - iconMargin,
                            itemView.getBottom() - iconMargin);
                }
                swipeColor.draw(c);
                excessColor.draw(c);
                iconDelete.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(final Note note) {
                fabOpen = findViewById(R.id.fabOpen);
                fabOpen.animate().scaleX(0).scaleY(0).setDuration(200)
                        .setInterpolator(new LinearInterpolator()).rotation(90.0f).start();

                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_down);
                mBar.setAnimation(animation);
                mBar.setVisibility(View.INVISIBLE);

                //OnItemClick animation
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        fabOpen.animate()
                                .scaleX(0).scaleY(0).start();
                        Animation recyclerAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
                        recyclerView.setAnimation(recyclerAnimation);
                        recyclerView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(MainActivity.this, WeekActivity.class);
                        intent.putExtra(EXTRA_ID, note.getId());
                        intent.putExtra(WeekActivity.EXTRA_WEEK_DATE, note.getWeekDate());
                        intent.putExtra(WeekActivity.EXTRA_MON_TIME, note.getMon());
                        intent.putExtra(WeekActivity.EXTRA_TUE_TIME, note.getTue());
                        intent.putExtra(WeekActivity.EXTRA_WED_TIME, note.getWed());
                        intent.putExtra(WeekActivity.EXTRA_THU_TIME, note.getThu());
                        intent.putExtra(WeekActivity.EXTRA_FRI_TIME, note.getFri());
                        intent.putExtra(WeekActivity.EXTRA_SAT_TIME, note.getSat());
                        intent.putExtra(WeekActivity.EXTRA_SUN_TIME, note.getSun());
                        startActivityForResult(intent, EDIT_NOTE_REQUEST);
                        overridePendingTransition(
                                //first view
                                R.anim.no_animation,
                                //second view
                                R.anim.no_animation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        onActivityOpenAnimation();
        getData(adapter);
    }

    public void getData(final NoteAdapter adapter) {
        Bundle data = getIntent().getExtras();
        if (data != null && !data.containsKey(EXTRA_ID)) {
            String weekDate = data.getString(WeekActivity.EXTRA_WEEK_DATE);
            String lastUpdated = data.getString(WeekActivity.EXTRA_LAST_UPDATE);
            String monTime = data.getString(WeekActivity.EXTRA_MON_TIME);
            String tueTime = data.getString(WeekActivity.EXTRA_TUE_TIME);
            String wedTime = data.getString(WeekActivity.EXTRA_WED_TIME);
            String thuTime = data.getString(WeekActivity.EXTRA_THU_TIME);
            String friTime = data.getString(WeekActivity.EXTRA_FRI_TIME);
            String satTime = data.getString(WeekActivity.EXTRA_SAT_TIME);
            String sunTime = data.getString(WeekActivity.EXTRA_SUN_TIME);
            Boolean m = data.getBoolean("m");
            Boolean tu = data.getBoolean("tu");
            Boolean w = data.getBoolean("w");
            Boolean th = data.getBoolean("th");
            Boolean f = data.getBoolean("f");
            Boolean st = data.getBoolean("st");
            Boolean su = data.getBoolean("su");

            Note note = new Note(weekDate, lastUpdated, monTime, tueTime, wedTime,
                    thuTime, friTime, satTime, sunTime,
                    m, tu, w, th, f, st, su);
            noteViewModel.insert(note);

            Toast toast = Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT);
            View view = toast.getView();
            view.setBackgroundResource(R.drawable.toast_background);
            toast.show();

        } else if (data != null && data.containsKey(EXTRA_ID)) {
            int id = getIntent().getIntExtra(EXTRA_ID, -1);

            String weekDate = data.getString(WeekActivity.EXTRA_WEEK_DATE);
            String lastUpdated = data.getString(WeekActivity.EXTRA_LAST_UPDATE);
            String monTime = data.getString(WeekActivity.EXTRA_MON_TIME);
            String tueTime = data.getString(WeekActivity.EXTRA_TUE_TIME);
            String wedTime = data.getString(WeekActivity.EXTRA_WED_TIME);
            String thuTime = data.getString(WeekActivity.EXTRA_THU_TIME);
            String friTime = data.getString(WeekActivity.EXTRA_FRI_TIME);
            String satTime = data.getString(WeekActivity.EXTRA_SAT_TIME);
            String sunTime = data.getString(WeekActivity.EXTRA_SUN_TIME);
            Boolean m = data.getBoolean("m");
            Boolean tu = data.getBoolean("tu");
            Boolean w = data.getBoolean("w");
            Boolean th = data.getBoolean("th");
            Boolean f = data.getBoolean("f");
            Boolean st = data.getBoolean("st");
            Boolean su = data.getBoolean("su");

            Note note = new Note(weekDate, lastUpdated, monTime, tueTime, wedTime,
                    thuTime, friTime, satTime, sunTime,
                    m, tu, w, th, f, st, su);
            note.setId(id);
            noteViewModel.update(note);

            Toast toast = Toast.makeText(getBaseContext(), "Updated", Toast.LENGTH_SHORT);
            View view = toast.getView();
            view.setBackgroundResource(R.drawable.toast_background);
            toast.show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            }, 500);
        }
    }

    //When fabOpen is pressed
    private void onActivityOpenAnimation() {
        fabOpen = findViewById(R.id.fabOpen);
        fabOpen.setScaleX(0);
        fabOpen.setScaleY(0);
        fabOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabOpen.setEnabled(false);
                fabOpen.animate().setDuration(200)
                        .setInterpolator(new LinearInterpolator()).rotation(90.0f).start();

                Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_down);
                mBar.setAnimation(animation);
                mBar.setVisibility(View.INVISIBLE);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        fabOpen.animate()
                                .scaleX(0).scaleY(0).start();
                        Animation recyclerAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_in);
                        recyclerView.setAnimation(recyclerAnimation);
                        recyclerView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(MainActivity.this, DateActivity.class);
                        startActivityForResult(intent, ADD_NOTE_REQUEST);
                        overridePendingTransition(
                                //second view
                                R.anim.no_animation,
                                //first view
                                R.anim.no_animation);
                        fabOpen.setEnabled(true);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    //When you come back to this activity
    @Override
    protected void onResume() {
        super.onResume();
        Animation recyclerAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.fade_out);
        recyclerView.setAnimation(recyclerAnimation);
        recyclerView.setVisibility(View.VISIBLE);

        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_up);
        mBar.setAnimation(animation);
        mBar.setVisibility(View.VISIBLE);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                fabOpen.animate().setDuration(200)
                        .setInterpolator(new LinearInterpolator()).rotation(0).start();

                fabOpen.setScaleX(0);
                fabOpen.setScaleY(0);
                fabOpen.animate()
                        .setDuration(100)
                        .setStartDelay(200)
                        .scaleX(1).scaleY(1).start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
