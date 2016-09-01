package telegrambot;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public interface Examinator {

    boolean answerIsCorrect(int answer);
    int skipCurrent();

    Exercise getCurrentExercise();
    boolean hasCurrentExercise();
    Exercise generateNextExercise();

    boolean hasPupilName();
    String getPupilName();
    void setPupilName(String name);

    String getImage();
}
