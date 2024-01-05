package dev.project.movies;

import org.bson.types.ObjectId;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @Test
    public void testGetAllMovies() {
        // Arrange
        List<Movie> expectedMovies = Arrays.asList(
                new Movie(new ObjectId(), "tt001", "Movie1", "2022-01-01", "2022-01-02", "poster1", Arrays.asList("Drama"), null),
                new Movie(new ObjectId(), "tt002", "Movie2", "2022-01-03", "2022-01-04", "poster2", Arrays.asList("Comedy"), null)
        );

        Mockito.when(movieService.allMovies()).thenReturn(expectedMovies);

        // Act
        ResponseEntity<List<Movie>> responseEntity = movieController.getAllMovies();

        // Assert
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(responseEntity.getBody(), expectedMovies);
    }

    @Test
    public void testGetSingleMovie() {
        // Arrange
        String imdbId = "tt001";
        Movie expectedMovie = new Movie(new ObjectId(), imdbId, "Movie1", "2022-01-01", "2022-01-02", "poster1", Arrays.asList("Drama"), null);

        Mockito.when(movieService.singleMovie(imdbId)).thenReturn(Optional.of(expectedMovie));

        // Act
        ResponseEntity<Optional<Movie>> responseEntity = movieController.getSingleMovie(imdbId);

        // Assert
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(responseEntity.getBody(), Optional.of(expectedMovie));
    }
}

