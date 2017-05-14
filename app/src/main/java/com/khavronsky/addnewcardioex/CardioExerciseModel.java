package com.khavronsky.addnewcardioex;


class CardioExerciseModel {
    private String exerciseTitle;
    private int sets;
    private int repeats;
    private int weight;

    public String getExerciseTitle() {
        return exerciseTitle;
    }

    public CardioExerciseModel setExerciseTitle(final String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
        return this;
    }

    public int getSets() {
        return sets;
    }

    public CardioExerciseModel setSets(final int sets) {
        this.sets = sets;
        return this;
    }

    public int getRepeats() {
        return repeats;
    }

    public CardioExerciseModel setRepeats(final int repeats) {
        this.repeats = repeats;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public CardioExerciseModel setWeight(final int weight) {
        this.weight = weight;
        return this;
    }
}
