package com.khavronsky.addnewcardioex;


import java.util.List;

class CardioExerciseModel {
    private String mExerciseTitle;
    private List<String> mCountingCaloriesMethod;
    private List<String> mIntensityType;
    private int mBurnedPerHour;
    private int mLowIntensity;
    private int mMiddleIntensity;
    private int mHighIntensity;

    public String getExerciseTitle() {
        return mExerciseTitle;
    }

    public CardioExerciseModel setExerciseTitle(final String exerciseTitle) {
        this.mExerciseTitle = exerciseTitle;
        return this;
    }

    public List<String> getCountingCaloriesMethod() {
        return mCountingCaloriesMethod;
    }

    public CardioExerciseModel setCountingCaloriesMethod(final List<String> countingCaloriesMethod) {
        mCountingCaloriesMethod = countingCaloriesMethod;
        return this;
    }

    public List<String> getIntensityType() {
        return mIntensityType;
    }

    public CardioExerciseModel setIntensityType(final List<String> intensityType) {
        mIntensityType = intensityType;
        return this;
    }

    public int getBurnedPerHour() {
        return mBurnedPerHour;
    }

    public CardioExerciseModel setBurnedPerHour(final int burnedPerHour) {
        mBurnedPerHour = burnedPerHour;
        return this;
    }

    public int getLowIntensity() {
        return mLowIntensity;
    }

    public CardioExerciseModel setLowIntensity(final int lowIntensity) {
        this.mLowIntensity = lowIntensity;
        return this;
    }

    public int getMiddleIntensity() {
        return mMiddleIntensity;
    }

    public CardioExerciseModel setMiddleIntensity(final int middleIntensity) {
        this.mMiddleIntensity = middleIntensity;
        return this;
    }

    public int getHighIntensity() {
        return mHighIntensity;
    }

    public CardioExerciseModel setHighIntensity(final int highIntensity) {
        this.mHighIntensity = highIntensity;
        return this;
    }
}
