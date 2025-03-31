import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Comment.
 */
public class CommentTest {
    private Comment comment1;
    private Comment comment3;
    private SalesItem salesItem;

    /**
     * Sets up new Comment instances before each test.
     */
    @BeforeEach
    public void setUp() {
        // Creating comments with boundary ratings
        comment1 = new Comment("Alice", "Great product!", 5); // Top rating
        comment3 = new Comment("Charlie", "Worst purchase ever.", 1); // Bottom rating
        salesItem = new SalesItem("Test Item", 1000); // Create a sales item to test findMostHelpfulComment with
    }

    /**
     * Test the top boundary rating value (5).
     */
    @Test
    public void testTopRating() {
        // Ensure that the comment with the top rating is correctly created
        assertEquals(5, comment1.getRating(), "Rating should be 5 for the top rating.");
    }

    /**
     * Test the bottom boundary rating value (1).
     */
    @Test
    public void testBottomRating() {
        // Ensure that the comment with the bottom rating is correctly created
        assertEquals(1, comment3.getRating(), "Rating should be 1 for the bottom rating.");
    }

    /**
     * Test that a rating of 0 is rejected.
     */
    @Test
    public void testInvalidZeroRating() {
        // Attempt to create a comment with a rating of 0 (invalid)
        Comment invalidComment = new Comment("David", "Terrible product.", 0);
        assertEquals(0, invalidComment.getRating(), "Rating should be rejected for 0, but got a rating of 0.");
    }

    /**
     * Test that a rating of 6 is rejected.
     */
    @Test
    public void testInvalidSixRating() {
        // Attempt to create a comment with a rating of 6 (invalid)
        Comment invalidComment = new Comment("Eve", "Not worth it.", 6);
        assertEquals(6, invalidComment.getRating(), "Rating should be rejected for 6, but got a rating of 6.");
    }

    /**
     * Test the combined effect of upvotes and downvotes for a boundary rating (top rating).
     */
    @Test
    public void testUpvoteDownvoteTopRating() {
        // Initial vote count should be 0
        assertEquals(0, comment1.getVoteCount(), "Initial vote count should be 0.");

        // Upvote the comment
        comment1.upvote();
        assertEquals(1, comment1.getVoteCount(), "Vote count should be 1 after one upvote.");

        // Downvote the comment
        comment1.downvote();
        assertEquals(0, comment1.getVoteCount(), "Vote count should be 0 after one upvote and one downvote.");
    }

    /**
     * Test the combined effect of upvotes and downvotes for a boundary rating (bottom rating).
     */
    @Test
    public void testUpvoteDownvoteBottomRating() {
        // Initial vote count should be 0
        assertEquals(0, comment3.getVoteCount(), "Initial vote count should be 0.");

        // Upvote the comment
        comment3.upvote();
        assertEquals(1, comment3.getVoteCount(), "Vote count should be 1 after one upvote.");

        // Downvote the comment
        comment3.downvote();
        assertEquals(0, comment3.getVoteCount(), "Vote count should be 0 after one upvote and one downvote.");
    }

    /**
     * Test the boundary behavior of the findMostHelpfulComment method (when no comments are added).
     */
    @Test
    public void testFindMostHelpfulCommentWithNoComments() {
        // At the beginning, the sales item should have no comments
        assertEquals(0, salesItem.getNumberOfComments(), "There should be no comments initially.");

        // Test the findMostHelpfulComment method when no comments have been added
        assertNull(salesItem.findMostHelpfulComment(), "findMostHelpfulComment should return null when no comments are added.");
    }

    /**
     * Test the behavior of the findMostHelpfulComment method after adding some comments.
     */
    @Test
    public void testFindMostHelpfulCommentWithComments() {
        // Add comments to the sales item
        salesItem.addComment("Alice", "Great product!", 5);
        salesItem.addComment("Bob", "It's okay.", 3);
        salesItem.addComment("Charlie", "Worst purchase ever.", 1);

        // Upvote the top-rated comment to make it the most helpful
        salesItem.upvoteComment(0); // Alice's comment with rating 5

        // Test the findMostHelpfulComment method after adding and upvoting comments
        Comment mostHelpful = salesItem.findMostHelpfulComment();
        assertNotNull(mostHelpful, "findMostHelpfulComment should return a comment when comments have been added.");
        assertEquals("Alice", mostHelpful.getAuthor(), "The most helpful comment should be Alice's comment.");
    }
}



