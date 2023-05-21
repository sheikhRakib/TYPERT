package game.util;

public enum WordSpeed {
	SLOW(880),
	NORMAL(40),
	MEDIUM(20),
	FAST(10);

    private int value;

    private WordSpeed(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
