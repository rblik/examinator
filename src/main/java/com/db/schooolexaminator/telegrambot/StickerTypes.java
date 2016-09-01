package telegrambot;

import java.util.Arrays;
import java.util.List;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public enum StickerTypes {
    FINISHED_EXAM(Arrays.asList("BQADBAAD-QMAAjJQbQAB3i-8DBnohzgC", "BQADBAADmwQAAjJQbQAB05LsVA_xvGcC", "BQADBAADiwQAAjJQbQAB7-yxquyRKnsC")),
    CORRECT_ANSWER(Arrays.asList("BQADBAAD3QMAAjJQbQAB2wOpe3KNC8MC", "BQADBAAD2wMAAjJQbQABDsPcsuZ7G6cC", "BQADBAADKQQAAjJQbQABEtPaXhUgNdsC", "BQADBAADPQQAAjJQbQABy1kq1Cnb28EC")),
    WRONG_ANSWER(Arrays.asList("BQADBAADUwQAAjJQbQABnue7cmrLL2YC")),
    SKIPPED_QUESTION(Arrays.asList("BQADBAADzAsAAjJQbQABtfMOY1xhtKcC"));

    private List<String> names;
    StickerTypes(List<String> names)
    {
        this.names = names;
    }

    @Override
    public String toString()
    {
        return  names.get((int)Math.random()%names.size());
    }

}
