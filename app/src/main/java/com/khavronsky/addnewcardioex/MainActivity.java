package com.khavronsky.addnewcardioex;

import com.khavronsky.addnewcardioex.CardioExerciseModel.IntensityType;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.ex_cardio_title)
    EditText mTitle;

    @BindView(R.id.ex_cardio_burned_per_hour)
    EditText mBurnedPerHour;

    @BindView(R.id.ex_cardio_low_intensity)
    EditText mLowIntensity;

    @BindView(R.id.ex_cardio_middle_intensity)
    EditText mMiddleIntensity;

    @BindView(R.id.ex_cardio_high_intensity)
    EditText mHighIntensity;

    @BindView(R.id.ex_cardio_count_method)
    Spinner mCountCalMethod;

    @BindView(R.id.ex_cardio_Intensity_type)
    Spinner mIntensityType;

    private TextWatcher mTextWatcher;

    private CardioExerciseModel mCardioExerciseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        if (getIntent().getExtras() != null) {
            mCardioExerciseModel = getIntent().getExtras().getParcelable("model");
            setEditText(mCardioExerciseModel);
        }

        setSpinners();
        createTextWatcher();
        setTextWatcher();

        setViews();
        //заглушка
//        createModel();

    }

    private void setViews() {




    }


    private void setTextWatcher() {
        mTitle.addTextChangedListener(mTextWatcher);
        mLowIntensity.addTextChangedListener(mTextWatcher);
        mMiddleIntensity.addTextChangedListener(mTextWatcher);
        mHighIntensity.addTextChangedListener(mTextWatcher);
    }

    private void setSpinners() {
        ArrayAdapter<?> adapter = ArrayAdapter
                .createFromResource(this, R.array.calc_calories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountCalMethod.setAdapter(adapter);
        mCountCalMethod.setOnItemClickListener((parent, view, position, id) -> {

        });

        ArrayAdapter<?> adapter2 = ArrayAdapter
                .createFromResource(this, R.array.type_of_intensity, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIntensityType.setAdapter(adapter2);
    }

    private void setEditText(CardioExerciseModel cardioExerciseModel) {
        if (cardioExerciseModel != null) {
            mTitle.setText(cardioExerciseModel.getExerciseTitle());
            mLowIntensity.setText(String.valueOf(cardioExerciseModel.getLowIntensity()));
            mMiddleIntensity.setText(String.valueOf(cardioExerciseModel.getMiddleIntensity()));
            mHighIntensity.setText(String.valueOf(cardioExerciseModel.getHighIntensity()));
        }
    }

    private void createTextWatcher() {
        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count,
                    final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                try {
                    if (Integer.parseInt(String.valueOf(s)) == 0) {
                        s.clear();
                    }
                } catch (NumberFormatException e) {
                }
            }
        };
    }

    public void setCardioExerciseModel(final CardioExerciseModel cardioExerciseModel) {
        mCardioExerciseModel = cardioExerciseModel;
    }

    void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle("Новое упражнение");
        mToolbar.inflateMenu(R.menu.menu);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Новое упражнение");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(this);
    }

    private boolean saveExercise(@IntensityType int intensityType) {

        if (mTitle.getText().length() == 0) {
            Toast.makeText(this, "Ойойой1", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(intensityType == CardioExerciseModel.TYPE_SPECIFY){

            if (mLowIntensity.getText().length() == 0) {
                Toast.makeText(this, "Ойойой2", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (mMiddleIntensity.getText().length() == 0) {
                Toast.makeText(this, "Ойойой3", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (mHighIntensity.getText().length() == 0) {
                Toast.makeText(this, "Ойойой4", Toast.LENGTH_SHORT).show();
                return false;
            }
            mCardioExerciseModel.setExerciseTitle(String.valueOf(mTitle.getText()))
                    .setLowIntensity(Integer.parseInt(String.valueOf(mLowIntensity.getText())))
                    .setMiddleIntensity(Integer.parseInt(String.valueOf(mMiddleIntensity.getText())))
                    .setHighIntensity(Integer.parseInt(String.valueOf(mHighIntensity.getText())));
        } else {
            if (mBurnedPerHour.getText().length() == 0){
                Toast.makeText(this, "Ойойой5", Toast.LENGTH_SHORT).show();
                return false;
            }
            mCardioExerciseModel.setExerciseTitle(String.valueOf(mTitle.getText()))
                    .setBurnedPerHour(Integer.parseInt(String.valueOf(mBurnedPerHour.getText())));
        }
        return true;
    }

    @Override
    public void onClick(final View v) {
        onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save) {
            boolean qwe = saveExercise(mCardioExerciseModel.getIntensityType());
            if (qwe) {
                Toast.makeText(this, "SAVE", Toast.LENGTH_SHORT).show();
                finish();
            }
            return qwe;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createModel() {
        mCardioExerciseModel = new CardioExerciseModel();
        mCardioExerciseModel.setExerciseTitle("Жим органики челюстями")
                .setCountCalMethod(CardioExerciseModel.METHOD_MET_VALUES)
                .setIntensityType(CardioExerciseModel.TYPE_SPECIFY)
                ;
    }
}
