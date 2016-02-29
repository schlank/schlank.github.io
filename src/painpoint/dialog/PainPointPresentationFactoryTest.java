package painpoint.dialog;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PainPointPresentationFactoryTest extends LightCodeInsightFixtureTestCase {

    @Override
    protected String getTestDataPath() {
        return "testData";
    }


    //TODO Make this test pass by parsing through PSIElements
    // Right now we just do a simple string match count.
    // It returns extra counts when the Todos are in a...
    // 1. Function name
    // 2. String literal
    // The test is good.  Fix the code in PainPointPresentationFactory.getTodoCount(PsiFile psiFile);

    public void testGetTodoCount() throws Exception {

        // GIVEN a test java class with expectedTodoCount todos
        int expectedTodoCount = 5;
        myFixture.configureByFiles("GrammarError.java");
        PsiFile psiTestFile = myFixture.getFile();

        // WHEN getTodoCount is calculated.
        int todoCount = PainPointPresentationFactory.getTodoCount(psiTestFile);

        // Then the result should be expectedTodoCount
        assertEquals(expectedTodoCount, todoCount);
    }

}