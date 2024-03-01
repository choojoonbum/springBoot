package org.choo.springboot.repository;

import lombok.extern.log4j.Log4j2;
import org.choo.springboot.entity.Movie;
import org.choo.springboot.entity.MovieImage;
import org.choo.springboot.entity.Poster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MovieRepositoryTests {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieImageRepository imageRepository;

    @Commit
    @Transactional
    @Test
    public void insertMovies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder().title("Movie..." + i).build();
            System.out.println("----------");
            movieRepository.save(movie);
            int count = (int) (Math.random() * 5) + 1;
            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder().uuid(UUID.randomUUID().toString()).movie(movie).imgName("test" + j + ".jpg").build();
                imageRepository.save(movieImage);
                System.out.println("==========================");
            }
        });
    }

    @Test
    public void testListPage() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
        Page<Object[]> result = movieRepository.getListPage(pageRequest);
        for (Object[] objects :  result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void testGetMovieWithAll() {
        List<Object[]> result = movieRepository.getMovieWithAll(94L);
        System.out.println(result);
        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testInsert() {
        log.info("testInsert..........");

        Movie movie = Movie.builder().title("극한직업2").build();
        movie.addPoster(Poster.builder().fname("극한직업2포스터1.jpg").build());
        movie.addPoster(Poster.builder().fname("극한직업2포스터2.jpg").build());
        movie.addPoster(Poster.builder().fname("극한직업2포스터3.jpg").build());

        movieRepository.save(movie);
        log.info(movie.getMno());
    }

    @Test
    @Transactional
    @Commit
    public void testRemovePoster() {
        Movie movie = movieRepository.getOne(105L);
        movie.removePoster(4L);
        movieRepository.save(movie);
    }

    @Test
    public void insertMoviesAndPosters() {
        IntStream.rangeClosed(10, 100).forEach(i -> {
            Movie movie = Movie.builder().title("세계명작" + i).build();
            movie.addPoster(Poster.builder().fname("세계명작" + i + "포스터1.jpg").build());
            movie.addPoster(Poster.builder().fname("세계명작" + i + "포스터2.jpg").build());
            movieRepository.save(movie);
        });
    }

    @Test
    public void testPaging1() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());
        Page<Movie> result = movieRepository.findAll(pageable);
        result.getContent().forEach(m -> {
            log.info(m.getMno());
            log.info(m.getTitle());
            log.info(m.getPosterList().size());
            log.info("-------------------");
        });
    }

    @Test
    public void testPaging2() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());
        Page<Movie> result = movieRepository.findAll2(pageable);
        result.getContent().forEach(m -> {
            log.info(m.getMno());
            log.info(m.getTitle());
            log.info(m.getPosterList());
            log.info("-------------------");
        });
    }

    @Test
    public void testPaging3() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("mno").descending());
        Page<Object[]> result = movieRepository.findAll3(pageable);
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
            log.info("-------------------");
        });
    }

}
