package org.choo.springboot.service;

import org.choo.springboot.dto.ReviewDTO;
import org.choo.springboot.entity.Member2;
import org.choo.springboot.entity.Movie;
import org.choo.springboot.entity.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getListOfMovie(Long mno);
    Long register(ReviewDTO movieReviewDTO);
    void modify(ReviewDTO movieReviewDTO);
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO movieReviewDTO) {
        Review movieReview = Review.builder()
                .reviewNum(movieReviewDTO.getReviewnum())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .member(Member2.builder().mid(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText()).build();
        return movieReview;
    }

    default ReviewDTO entityToDTO(Review movieReview) {
        ReviewDTO movieReviewDTO = ReviewDTO.builder()
                .reviewnum(movieReview.getReviewNum())
                .mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMember().getMid())
                .nickname(movieReview.getMember().getNickname())
                .email(movieReview.getMember().getEmail())
                .grade(movieReview.getGrade())
                .text(movieReview.getText())
                .regDate(movieReview.getRegDate())
                .modDate(movieReview.getModDate()).build();
        return movieReviewDTO;
    }
}
