package com.james.point.enums;

public enum Score {
    CONTENT(1,1), PHOTO(1,1), FIRST_PLACE(1,1);

    private final int min;
    private final int score;

    Score(int min, int score) {
        this.min = min;
        this.score = score;
    }

    public int getMin() {
        return this.min;
    }

    public int getScore() {
        return this.score;
    }
}
