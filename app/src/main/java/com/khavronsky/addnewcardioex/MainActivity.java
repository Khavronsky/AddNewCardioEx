package com.khavronsky.addnewcardioex;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.khavronsky.addnewcardioex.CardioExerciseModel.METHOD_CAL_PER_HOUR;
import static com.khavronsky.addnewcardioex.CardioExerciseModel.METHOD_MET_VALUES;
import static com.khavronsky.addnewcardioex.CardioExerciseModel.TYPE_NOT_SPECIFY;
import static com.khavronsky.addnewcardioex.CardioExerciseModel.TYPE_SPECIFY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.ex_cardio_title)
    EditText mTitle;

    @BindView(R.id.ex_cardio_burned_per_hour)
    EditText mBurnedPerHour;

    @BindView(R.id.ex_cardio_burned_per_hour_layout)
    TextInputLayout mBurnedPerHourTextLayout;

    @BindView(R.id.ex_cardio_intensity_fields)
    View mIntensityFields;

    @BindView(R.id.ex_cardio_low_intensity)
    EditText mLowIntensity;

    @BindView(R.id.ex_cardio_low_intensity_layout)
    TextInputLayout mLowIntensityTextLayout;

    @BindView(R.id.ex_cardio_middle_intensity)
    EditText mMiddleIntensity;

    @BindView(R.id.ex_cardio_middle_intensity_layout)
    TextInputLayout mMiddleIntensityTextLayout;

    @BindView(R.id.ex_cardio_high_intensity)
    EditText mHighIntensity;

    @BindView(R.id.ex_cardio_high_intensity_layout)
    TextInputLayout mHighIntensityTextLayout;

    @BindView(R.id.ex_cardio_count_method)
    Spinner mCountCalMethod;

    @BindView(R.id.ex_cardio_Intensity_type)
    Spinner mIntensityType;

    private TextWatcher mTextWatcher;

    private CardioExerciseModel mCardioExerciseModel = new CardioExerciseModel();

    AlertDialog.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        if (getIntent().getExtras() != null) {
            mCardioExerciseModel = getIntent().getExtras().getParcelable("model");
            editExercise(mCardioExerciseModel);
        }

        setSpinners();
        editTextVisibility(mIntensityType.getSelectedItemPosition());
        setHintFromSelectedCountingMethod(mCountCalMethod.getSelectedItemPosition());
        createTextWatcher();
        setTextWatcher();

        createAlertDialogWithPicker();
    }

    private int createAlertDialogWithPicker() {
        int result = -1;
        String okBtn = "OK";
        String cancelBtn = "CANCEL";
        NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        mBuilder = new AlertDialog.Builder(this);
        mBuilder.setView(numberPicker);
        mBuilder.setPositiveButton(okBtn, new OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                numberPicker.getValue();
            }
        });
        mBuilder.setNegativeButton(cancelBtn, new OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
            }
        });
        mBuilder.setCancelable(true);
        mBuilder.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {

            }
        });
        return result;
    }


    private void setTextWatcher() {
        mBurnedPerHour.addTextChangedListener(mTextWatcher);
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
        mCountCalMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position,
                    final long id) {
                setHintFromSelectedCountingMethod(position);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
            }
        });

        ArrayAdapter<?> adapter2 = ArrayAdapter
                .createFromResource(this, R.array.type_of_intensity, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIntensityType.setAdapter(adapter2);
        mIntensityType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position,
                    final long id) {
                editTextVisibility(position);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
            }
        });
    }

    private void editExercise(CardioExerciseModel cardioExerciseModel) {
        if (cardioExerciseModel != null) {
            mTitle.setText(cardioExerciseModel.getExerciseTitle());
            mBurnedPerHour.setText(String.valueOf(cardioExerciseModel.getBurnedPerHour()));
            mLowIntensity.setText(String.valueOf(cardioExerciseModel.getLowIntensity()));
            mMiddleIntensity.setText(String.valueOf(cardioExerciseModel.getMiddleIntensity()));
            mHighIntensity.setText(String.valueOf(cardioExerciseModel.getHighIntensity()));
        }
    }

    private void editTextVisibility(int specify) {
        int specVisibility;
        int notSpecVisibility;
        if (specify == TYPE_SPECIFY) {
            specVisibility = VISIBLE;
            notSpecVisibility = GONE;
        } else {
            specVisibility = GONE;
            notSpecVisibility = VISIBLE;
        }
        mBurnedPerHourTextLayout.setVisibility(notSpecVisibility);
        mIntensityFields.setVisibility(specVisibility);
    }

    private void setHintFromSelectedCountingMethod(int selectedCountingMethod) {
        String methodPrefix = "(значение МЕТ)";
        String hintBurnedCalWithoutSpec = "Значение МЕТ";
        if (selectedCountingMethod == METHOD_CAL_PER_HOUR) {
            methodPrefix = "(ккал/час)";
            hintBurnedCalWithoutSpec = "Сожжено калорий в час";
        }
        mBurnedPerHourTextLayout.setHint(hintBurnedCalWithoutSpec);
        mLowIntensityTextLayout.setHint("Низкая нагрузка " + methodPrefix);
        mMiddleIntensityTextLayout.setHint("Средняя нагрузка " + methodPrefix);
        mHighIntensityTextLayout.setHint("Высокая нагрузка " + methodPrefix);
    }

    private void createTextWatcher() {
        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count,
                    final int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, final int start, final int before, final int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                try {
                    if (Integer.parseInt(String.valueOf(s)) == 0) {
                        s.clear();
                    }
                } catch (NumberFormatException e) {
                }
                if(s.length() > 6){
                    Toast.makeText(MainActivity.this, "Ой ой ой! \nОсеня мунога букавка твоя писать",
                            Toast.LENGTH_SHORT).show();
                    s.delete(0, 1);
                }
            }
        };
    }

    public void setCardioExerciseModel(final CardioExerciseModel cardioExerciseModel) {
        mCardioExerciseModel = cardioExerciseModel;
    }

    private void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.menu);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Новое упражнение");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(this);
    }

    private boolean saveExercise(int intensityType) {

        if (mTitle.getText().length() == 0) {
            Toast.makeText(this, "Ойойой1", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (intensityType == TYPE_SPECIFY) {

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
                    .setIntensityType(TYPE_SPECIFY)
                    .setCountCalMethod(mCountCalMethod.getSelectedItemPosition() == 0 ? METHOD_CAL_PER_HOUR
                            : METHOD_MET_VALUES)
                    .setLowIntensity(Integer.parseInt(String.valueOf(mLowIntensity.getText())))
                    .setMiddleIntensity(Integer.parseInt(String.valueOf(mMiddleIntensity.getText())))
                    .setHighIntensity(Integer.parseInt(String.valueOf(mHighIntensity.getText())));
        } else {
            if (mBurnedPerHour.getText().length() == 0) {
                Toast.makeText(this, "Ойойой5", Toast.LENGTH_SHORT).show();
                return false;
            }
            mCardioExerciseModel.setExerciseTitle(String.valueOf(mTitle.getText()))
                    .setIntensityType(TYPE_NOT_SPECIFY)
                    .setBurnedPerHour(Integer.parseInt(String.valueOf(mBurnedPerHour.getText())));
        }
        showSavedToast();

        return true;
    }

    @Override
    public void onClick(final View v) {

        mBuilder.show();

//        onBackPressed();
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
            saveExercise(mIntensityType.getSelectedItemPosition());
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSavedToast() {
        Toast.makeText(this, "MODEL SAVED \n"
                        + "Title: " + mCardioExerciseModel.getExerciseTitle() + "\n"
                ,
                Toast.LENGTH_SHORT)
                .show();
        Toast.makeText(this, "Calculating method: " + (mCardioExerciseModel.getCountCalMethod() ==
                        METHOD_CAL_PER_HOUR ?
                        "Calories per hour" : "MET values") + "\n"
                        + "Intensity type: " + (mCardioExerciseModel.getIntensityType() == TYPE_SPECIFY ? "Specify" :
                        "Not specify") + "\n",
                Toast.LENGTH_SHORT)
                .show();
        Toast.makeText(this, "Burned calories: " + (mCardioExerciseModel.getIntensityType() == TYPE_SPECIFY ?
                        (mCardioExerciseModel.getLowIntensity() + "/" + mCardioExerciseModel.getMiddleIntensity
                                () + "/" + mCardioExerciseModel.getHighIntensity()) : mCardioExerciseModel
                        .getBurnedPerHour()),
                Toast.LENGTH_SHORT)
                .show();
    }

    private void createModel() {
        mCardioExerciseModel = new CardioExerciseModel();
        mCardioExerciseModel.setExerciseTitle("Жим органики челюстями")
                .setCountCalMethod(METHOD_MET_VALUES)
                .setIntensityType(TYPE_SPECIFY)
        ;
    }
}
