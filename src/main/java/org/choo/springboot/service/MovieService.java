package org.choo.springboot.service;

import org.choo.springboot.dto.MovieDTO;
import org.choo.springboot.dto.MovieImageDTO;
import org.choo.springboot.entity.Movie;
import org.choo.springboot.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {
    Long register(MovieDTO movieDTO);

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) {
        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder().mno(movieDTO.getMno()).title(movieDTO.getTitle()).build();
        entityMap.put("movie", movie);

        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();
        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<MovieImage> movieImageList = imageDTOList.stream().map(dto -> {
                MovieImage movieImage = MovieImage.builder().path(dto.getPath()).imgName(dto.getImgName()).uuid(dto.getUuid()).movie(movie).build();
                return movieImage;
            }).collect(Collectors.toList());
            entityMap.put("imgList", movieImageList);
        }
        return entityMap;
    }
}
