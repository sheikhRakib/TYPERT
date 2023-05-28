package game.util;

public enum WordSpeed {
	SLOW(100),
	NORMAL(80),
	MEDIUM(40),
	FAST(20);

    private int value;

    private WordSpeed(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
