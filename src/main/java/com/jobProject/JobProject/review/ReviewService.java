package com.jobProject.JobProject.review;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReview(Long companyId);
    boolean addReview(Long companyId , Review review);
    Review getReview(Long companyId , Long reviewId);
    boolean updateReview(Long companyId , Long reviewId , Review review);

    boolean deleteReview(Long companyId, Long reviewId);
}
