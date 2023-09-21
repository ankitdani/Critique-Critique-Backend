package dev.project.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateReviewEndpoint() {
        String reviewBody = "Amazing!";
        String imdbId = "tt123456";
        Review mockReview = new Review(reviewBody);

        when(reviewService.createReview(anyString(), anyString())).thenReturn(mockReview);

        HashMap<String, String> payload = new HashMap<>();
        payload.put("reviewBody", reviewBody);
        payload.put("imdbId", imdbId);

        ResponseEntity<Review> response = reviewController.createReview(payload);

        assertNotNull(response.getBody());
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(reviewBody, response.getBody().getBody());
    }
}
