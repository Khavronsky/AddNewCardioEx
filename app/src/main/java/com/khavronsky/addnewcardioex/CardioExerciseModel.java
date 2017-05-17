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
    private float mBurnedPerHour;
    private float mLowIntensity;
    private float mMiddleIntensity;
    private float mHighIntensity;

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

    public float getBurnedPerHour() {
        return mBurnedPerHour;
    }

    public CardioExerciseModel setBurnedPerHour(final float burnedPerHour) {
        mBurnedPerHour = burnedPerHour;
        return this;
    }

    public float getLowIntensity() {
        return mLowIntensity;
    }

    public CardioExerciseModel setLowIntensity(final float lowIntensity) {
        this.mLowIntensity = lowIntensity;
        return this;
    }

    public float getMiddleIntensity() {
        return mMiddleIntensity;
    }

    public CardioExerciseModel setMiddleIntensity(final float middleIntensity) {
        this.mMiddleIntensity = middleIntensity;
        return this;
    }

    public float getHighIntensity() {
        return mHighIntensity;
    }

    public CardioExerciseModel setHighIntensity(final float highIntensity) {
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
        dest.writeFloat(mBurnedPerHour);
        dest.writeFloat(mLowIntensity);
        dest.writeFloat(mMiddleIntensity);
        dest.writeFloat(mHighIntensity);
    }
}
