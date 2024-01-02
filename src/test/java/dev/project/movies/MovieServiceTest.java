package dev.project.movies;

import org.bson.types.ObjectId;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAllMovies() {
        // Arrange
        List<Movie> expectedMovies = Arrays.asList(
                new Movie(new ObjectId(), "tt001", "Movie1", "2022-01-01", "2022-01-02", "poster1", Arrays.asList("Drama"), null),
                new Movie(new ObjectId(), "tt002", "Movie2", "2022-01-03", "2022-01-04", "poster2", Arrays.asList("Comedy"), null)
        );

        Mockito.when(movieRepository.findAll()).thenReturn(expectedMovies);

        // Act
        List<Movie> actualMovies = movieService.allMovies();

        // Assert
        Assert.assertEquals(actualMovies, expectedMovies);
    }

    @Test
    public void testSingleMovie() {
        // Arrange
        String imdbId = "tt001";
        Movie expectedMovie = new Movie(new ObjectId(), imdbId, "Movie1", "2022-01-01", "2022-01-02", "poster1", Arrays.asList("Drama"), null);

        Mockito.when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.of(expectedMovie));

        // Act
        Optional<Movie> actualMovie = movieService.singleMovie(imdbId);

        // Assert
        Assert.assertTrue(actualMovie.isPresent());
        Assert.assertEquals(actualMovie.get(), expectedMovie);
    }
}
