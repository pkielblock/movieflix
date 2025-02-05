package dev.kielblock.movieflix.service;

import dev.kielblock.movieflix.entity.Category;
import dev.kielblock.movieflix.entity.Movie;
import dev.kielblock.movieflix.entity.Streaming;
import dev.kielblock.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(long id) {
        return movieRepository.findById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> categoryService.findById(category.getId())
                .ifPresent(categoriesFound::add));

        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(category -> streamingService.findById(category.getId())
                .ifPresent(streamingsFound::add));

        return streamingsFound;
    }

    public Optional<Movie> updateById(long id, Movie updatedMovie) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isPresent()) {

            List<Category> categories = this.findCategories(updatedMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(updatedMovie.getStreamings());

            Movie movie = optionalMovie.get();
            movie.setId(id);
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setRating(updatedMovie.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);
            return Optional.of(movie);
        }
        return Optional.empty();
    }
}
