package com.khavronsky.addnewcardioex;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

class CardioExerciseModel implements Parcelable {


    @IntDef(
            flag = true,
            value = {METHOD_MET_VALUES, METHOD_CAL_PER_HOUR})
    public @interface CountingCaloriesMethod{}

    static final int METHOD_CAL_PER_HOUR = 0;
    static final int METHOD_MET_VALUES = 1;

    @IntDef(
            flag = true,
            value = {TYPE_NOT_SPECIFY, TYPE_SPECIFY})
    public @interface IntensityType{}

    static final int TYPE_NOT_SPECIFY = 0;
    static final int TYPE_SPECIFY = 1;

    private String mExerciseTitle;
    private int mCountCalMethod;
    private int mIntensityType;
    private int mBurnedPerHour;
    private int mLowIntensity;
    private int mMiddleIntensity;
    private int mHighIntensity;

    public CardioExerciseModel() {
    }

    public String getExerciseTitle() {
        return mExerciseTitle;
    }

    public CardioExerciseModel setExerciseTitle(final String exerciseTitle) {
        this.mExerciseTitle = exerciseTitle;
        return this;
    }

    @CountingCaloriesMethod
    public int getCountCalMethod() {
        return mCountCalMethod;
    }

    public CardioExerciseModel setCountCalMethod(@CountingCaloriesMethod final int countCalMethod) {
        this.mCountCalMethod = countCalMethod;
        return this;
    }

    @IntensityType
    public int getIntensityType() {
        return mIntensityType;
    }

    public CardioExerciseModel setIntensityType(@IntensityType final int intensityType) {
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

    /**
     * Parcelable implementation
     * */

    protected CardioExerciseModel(Parcel in) {
        mExerciseTitle = in.readString();
        mCountCalMethod = in.readInt();
        mIntensityType = in.readInt();
        mBurnedPerHour = in.readInt();
        mLowIntensity = in.readInt();
        mMiddleIntensity = in.readInt();
        mHighIntensity = in.readInt();
    }

    public static final Creator<CardioExerciseModel> CREATOR = new Creator<CardioExerciseModel>() {
        @Override
        public CardioExerciseModel createFromParcel(Parcel in) {
            return new CardioExerciseModel(in);
        }

        @Override
        public CardioExerciseModel[] newArray(int size) {
            return new CardioExerciseModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(mExerciseTitle);
        dest.writeInt(mCountCalMethod);
        dest.writeInt(mIntensityType);
        dest.writeInt(mBurnedPerHour);
        dest.writeInt(mLowIntensity);
        dest.writeInt(mMiddleIntensity);
        dest.writeInt(mHighIntensity);
    }
}
