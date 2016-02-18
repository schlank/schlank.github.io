package classcommentary.domain.commentary.util

import org.junit.Test

import static org.junit.Assert.assertEquals

class CommentaryUtilTest {

    @Test
    public void testCommentaryId_pathToDirectory() throws Exception {

        //GIVEN Valid filePath, className, and ProjectName.
        String fileName = "FileManager.java";
        String path = "/Users/ProjectName/app/src/fun";
        Integer ExpectedId = "/ProjectName/app/src/fun/FileManager.java".hashCode();

        // WHEN CommentaryUtil.commentaryId is called with the valid params.
        Integer commentaryId = CommentaryUtil.commentaryId(fileName, path, "ProjectName");

        //THEN the CommentaryModel has the expected Result
        assertEquals(commentaryId, ExpectedId);
    }

    @Test
    public void testCommentaryId_pathToFile() throws Exception {

        //GIVEN Valid filePath, className, and ProjectName.
        String fileName = "FileManager.java";
        String path = "/Users/ProjectName/app/src/fun/FileManager.java";
        Integer ExpectedId = "/ProjectName/app/src/fun/FileManager.java".hashCode();

        // WHEN CommentaryUtil.commentaryId is called with the valid params.
        Integer commentaryId = CommentaryUtil.commentaryId(fileName, path, "ProjectName");

        //THEN the CommentaryModel has the expected Result
        assertEquals(commentaryId, ExpectedId);

    }



}
