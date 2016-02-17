package classcommentary.model;

import com.intellij.ui.JBColor;

/**
 * Created by mony on 2/15/2016.
 */
public enum ClassStatus {
    GREAT("Great", JBColor.GREEN),
    GOOD("Good", JBColor.GREEN),
    OK("OK", JBColor.LIGHT_GRAY),
    UNRATED("unrated", JBColor.LIGHT_GRAY),
    BAD("Bad", JBColor.ORANGE),
    NAZZIL("Nazzil", JBColor.RED);
    private String mLabel;
    private JBColor mColor;
    ClassStatus(String label, JBColor color) {
        mLabel = label;
        mColor = color;
    }

    public String getLabel() {
        return mLabel;
    }

    public JBColor getColor() {
        return mColor;
    }
}
