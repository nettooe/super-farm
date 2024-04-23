package io.superfarm;

public class Player {

    private final String name;
    private int points;

    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public void gainPoint() {
        points++;
    }

    public boolean hasScoreBiggerThan(Score score) {
        return this.points > score.points();
    }

    public int pointsDifference(Player other) {
        return points - other.points;
    }

    public String name() {
        return name;
    }


    public String score() {
        return Score.from(points).label();
    }

    public enum Score {
        LOVE(0, "Love"),
        FIFTEEN(1, "Fifteen"),
        THIRTY(2, "Thirty"),
        FORTY(3, "Forty");

        private final int points;
        private final String label;

        Score(int points, String label) {
            this.points = points;
            this.label = label;
        }

        public String label() {
            return label;
        }

        public int points() {
            return points;
        }

        public static Score from(int points) {
            return switch (points) {
                case 0 -> Score.LOVE;
                case 1 -> Score.FIFTEEN;
                case 2 -> Score.THIRTY;
                case 3 -> Score.FORTY;
                default -> null;
            };
        }
    }
}
