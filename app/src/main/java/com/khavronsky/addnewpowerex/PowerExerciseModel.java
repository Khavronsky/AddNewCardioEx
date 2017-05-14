package com.khavronsky.addnewpowerex;


class PowerExerciseModel {
    private String exerciseTitle;
    private int sets;
    private int repeats;
    private int weight;

    public String getExerciseTitle() {
        return exerciseTitle;
    }

    public PowerExerciseModel setExerciseTitle(final String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
        return this;
    }

    public int getSets() {
        return sets;
    }

    public PowerExerciseModel setSets(final int sets) {
        this.sets = sets;
        return this;
    }

    public int getRepeats() {
        return repeats;
    }

    public PowerExerciseModel setRepeats(final int repeats) {
        this.repeats = repeats;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public PowerExerciseModel setWeight(final int weight) {
        this.weight = weight;
        return this;
    }
}
