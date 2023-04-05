package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@CrossOrigin("*")
@Controller
@RequestMapping(path = "/api")
public class MovieController {
	// TODO: Task 3, Task 4, Task 8
	@Autowired
	MovieService movieService;

	@ResponseBody
	@GetMapping(path = "/search")
	public ResponseEntity<String> getReviews(@RequestParam("query") String movieName) {
		List<Review> result = movieService.searchReviews(movieName);
		JsonArrayBuilder reviewsJson = Json.createArrayBuilder();
		if (result.size() > 0) {
			for (Review review : result) {
				Integer commentCount = movieService.searchReview(review);
				review.setCommentCount(commentCount);
				System.out.println(review.toString() + ">>>>added to jsonarray");
				reviewsJson.add(review.toJsonBuilder());
			}

		}

		return ResponseEntity.ok().body(reviewsJson.build().toString());
	}

	@ResponseBody
	@PostMapping(path = "/comment")
	public ResponseEntity<String> postComment(@RequestBody String payload){
		Document result = movieService.insertComment(payload);
		
		return ResponseEntity.ok().body(result.toJson());
	}
}
