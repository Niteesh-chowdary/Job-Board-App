package com.Niteesh.JobBoard.controller;

import com.Niteesh.JobBoard.model.Company;
import com.Niteesh.JobBoard.model.Review;
import com.Niteesh.JobBoard.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;
    private CompanyController companyController;

    public ReviewController(ReviewService reviewService, CompanyController companyController){
        this.reviewService =reviewService;
        this.companyController = companyController;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if(reviews == null || reviews.isEmpty()){
            return new ResponseEntity<>(reviews,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        ResponseEntity<Company> companyResponse = companyController.findCompanyById(companyId);
        if(companyResponse.getStatusCode().equals(HttpStatus.OK)){
            review.setCompany(companyResponse.getBody());
            reviewService.addReview(review);
            return new ResponseEntity<>("Review Created",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Review Creation Failed",HttpStatus.FAILED_DEPENDENCY);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReviewById(reviewId);
        if(review != null){
            return new ResponseEntity<>(review,HttpStatus.OK);
        }
        else return new ResponseEntity<>(review,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId,
                                                   @PathVariable Long reviewId,
                                                   @RequestBody Review review){
        ResponseEntity<Company> companyResponseEntity = companyController.findCompanyById(companyId);
        if(companyResponseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            return new ResponseEntity<>("Company Not Found",HttpStatus.FAILED_DEPENDENCY);
        }
        review.setCompany(companyResponseEntity.getBody());
        String reviewResponse = reviewService.upateReview(reviewId,review);
        if(reviewResponse.equals("NO ITEM")){
            return new ResponseEntity<>(reviewResponse,HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(reviewResponse,HttpStatus.OK);
    }

    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId,@PathVariable Long reviewId){
        String response = reviewService.deleteReviewById(reviewId);
        if(response.equals("REVIEW DELETED SUCCESSFULLY")){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
