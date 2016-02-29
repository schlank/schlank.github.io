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

    public void testGetTodoCount_FiveTodos() throws Exception {

        // GIVEN a test java class with expectedTodoCount todos
        int expectedTodoCount = 5;
        myFixture.configureByFiles("FiveMatches.java");
        PsiFile psiTestFile = myFixture.getFile();

        // WHEN getTodoCount is calculated.
        int todoCount = PainPointPresentationFactory.getTodoCount(psiTestFile);

        // Then the result should be expectedTodoCount
        assertEquals(expectedTodoCount, todoCount);
    }

    //TODO make test pass
    public void testGetTodoCount_TodoInMethodName() throws Exception {

        // GIVEN a test java class with expectedTodoCount todos
        int expectedTodoCount = 0;
        myFixture.configureByFiles("TodoInMethodName.java");
        PsiFile psiTestFile = myFixture.getFile();

        // WHEN getTodoCount is calculated.
        int todoCount = PainPointPresentationFactory.getTodoCount(psiTestFile);

        // Then the result should be expectedTodoCount
        assertEquals(expectedTodoCount, todoCount);
    }

    //TODO make test pass
    public void testGetTodoCount_TodoInStringLiteral() throws Exception {

        // GIVEN a test java class with expectedTodoCount todos
        int expectedTodoCount = 0;
        myFixture.configureByFiles("TodoInStringLiteral.java");
        PsiFile psiTestFile = myFixture.getFile();

        // WHEN getTodoCount is calculated.
        int todoCount = PainPointPresentationFactory.getTodoCount(psiTestFile);

        // Then the result should be expectedTodoCount
        assertEquals(expectedTodoCount, todoCount);
    }

}