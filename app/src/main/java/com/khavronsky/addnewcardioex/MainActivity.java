package com.khavronsky.addnewcardioex;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar mToolbar;

    EditText exTitle;

    EditText exSets;

    EditText exRepeats;

    EditText exWeight;

    TextWatcher mTextWatcher;

    private CardioExerciseModel mCardioExerciseModel;

    public MainActivity setCardioExerciseModel(final CardioExerciseModel cardioExerciseModel) {
        mCardioExerciseModel = cardioExerciseModel;
        return this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setToolbar();
        setEditText(mCardioExerciseModel);
        setCardioExerciseModel(new CardioExerciseModel());

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

// Настраиваем адаптер
        ArrayAdapter<?> adapter = ArrayAdapter
                .createFromResource(this, R.array.calc_calories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        ArrayAdapter<?> adapter2 = ArrayAdapter
//                .createFromResource(this, R.array.type_of_intensity, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                mCardioExerciseModel.getIntensityType());

// Вызываем адаптер
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
    }

    private void setEditText(CardioExerciseModel cardioExerciseModel) {
        exTitle = (EditText) findViewById(R.id.ex_title);
        exSets = (EditText) findViewById(R.id.ex_sets);
        exRepeats = (EditText) findViewById(R.id.ex_repeats);
        exWeight = (EditText) findViewById(R.id.ex_weight);
        if (cardioExerciseModel != null) {

            exTitle.setText(cardioExerciseModel.getExerciseTitle());
            exSets.setText(cardioExerciseModel.getLowIntensity());
            exRepeats.setText(cardioExerciseModel.getMiddleIntensity());
            exWeight.setText(cardioExerciseModel.getHighIntensity());
        }
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
        exTitle.addTextChangedListener(mTextWatcher);
        exSets.addTextChangedListener(mTextWatcher);
        exRepeats.addTextChangedListener(mTextWatcher);
        exWeight.addTextChangedListener(mTextWatcher);
//        setListener(exSets);
//        setListener(exTitle);
//        setListener(exRepeats);
//        setListener(exWeight);

    }

    void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Новое упражнение");
        setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(R.menu.menu);
        mToolbar.setNavigationOnClickListener(this);
        mToolbar.setNavigationIcon(R.drawable.arrow_back);
    }

    private boolean saveExercise() {
        if (exTitle.getText().length() == 0) {
            Toast.makeText(this, "Ойойой1", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (exSets.getText().length() == 0) {
            Toast.makeText(this, "Ойойой2", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (exRepeats.getText().length() == 0) {
            Toast.makeText(this, "Ойойой3", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (exWeight.getText().length() == 0) {
            Toast.makeText(this, "Ойойой4", Toast.LENGTH_SHORT).show();
            return false;
        }
        mCardioExerciseModel.setExerciseTitle(String.valueOf(exTitle.getText()))
                .setLowIntensity(Integer.parseInt(String.valueOf(exSets.getText())))
                .setMiddleIntensity(Integer.parseInt(String.valueOf(exRepeats.getText())))
                .setHighIntensity(Integer.parseInt(String.valueOf(exWeight.getText())));
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
            boolean qwe = saveExercise();
            if (qwe) {
                Toast.makeText(this, "SAVE", Toast.LENGTH_SHORT).show();
                finish();
            }
            return qwe;
        }
        return super.onOptionsItemSelected(item);
    }
}
