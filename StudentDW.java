
public class StudentDW implements StudentInterface {
	private String name;
	private int score;

	public StudentDW(String name, String score) {
		this.name = name;
		this.score = Integer.parseInt(score);
	}

	@Override
	public int compareTo(StudentInterface o) {
		if (this.score > o.getScore())
			return 1;
		else if (this.score < o.getScore())
			return -1;
		else
			return 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getScore() {
		return score;
	}

	public String toString() {
		return "Name: " + name + " || Score: " + score;
	}

}

