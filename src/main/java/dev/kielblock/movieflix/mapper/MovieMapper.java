package dev.kielblock.movieflix.mapper;

import dev.kielblock.movieflix.controller.request.MovieRequest;
import dev.kielblock.movieflix.controller.response.CategoryResponse;
import dev.kielblock.movieflix.controller.response.MovieResponse;
import dev.kielblock.movieflix.controller.response.StreamingResponse;
import dev.kielblock.movieflix.entity.Category;
import dev.kielblock.movieflix.entity.Movie;
import dev.kielblock.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {
    //Request -> Entity -> Response

    public static Movie toMovie(MovieRequest request) {
        List<Category> categories = request.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = request.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie
                .builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse (Movie movie) {
        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return MovieResponse
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
