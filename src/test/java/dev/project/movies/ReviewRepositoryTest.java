package dev.project.movies;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        reviewRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        reviewRepository.deleteAll();
    }

    @Test
    void testInsertAndFind() {
        Review review = new Review("Awesome movie!");
        Review savedReview = reviewRepository.save(review);

        assertNotNull(savedReview.getId());
        assertEquals(review.getBody(), savedReview.getBody());

        Review foundReview = reviewRepository.findById(savedReview.getId()).orElse(null);
        assertNotNull(foundReview);
        assertEquals(savedReview.getBody(), foundReview.getBody());
    }
}
