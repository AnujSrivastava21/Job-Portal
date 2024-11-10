package com.jobProject.JobProject.review.impl;


import com.jobProject.JobProject.companies.Company;
import com.jobProject.JobProject.companies.CompanyService;
import com.jobProject.JobProject.review.Review;
import com.jobProject.JobProject.review.ReviewRepository;
import com.jobProject.JobProject.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {


    private CompanyService companyService;

    // Change this in ReviewServiceImplementation
    private ReviewRepository reviewRepository;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }


    @Override
    public List<Review> getAllReview(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return  reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company!=null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return  false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId()
                .equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            Review existingReview = reviewRepository.findByCompanyIdAndId(companyId, reviewId).orElse(null);
            if (existingReview != null) {
                existingReview.setTitle(updatedReview.getTitle());
                existingReview.setDescription(updatedReview.getDescription());
                existingReview.setRating(updatedReview.getRating());
                reviewRepository.save(existingReview);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
       if (companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId)){
           Review review = reviewRepository.findById(reviewId).orElse(null);
           Company company = review.getCompany();
           company.getReviews().remove(review);
           review.setCompany(null);
           companyService.updateCompany(company , companyId);
           reviewRepository.deleteById(reviewId);
           return true;
       }
       return  false;

    }

}
