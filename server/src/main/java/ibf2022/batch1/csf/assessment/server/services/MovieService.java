package ibf2022.batch1.csf.assessment.server.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.server.utils.JsonParse;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;;

@Service
public class MovieService {
	private final String nyAPIURL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";
	private final String APIKEY = "pCLL8KoSrXQRUxo8OT4GoGYt4FxDcSSI";

	@Autowired
	MovieRepository movieRepository;
	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {

		ResponseEntity<String> resp = null;
		RestTemplate template = new RestTemplate();
		List<Review> reviews = new ArrayList<>();

		String nyAPIReviewURL = UriComponentsBuilder
				.fromUriString(nyAPIURL)
				.queryParam("query", query)
				.queryParam("api-key", APIKEY)
				.toUriString();
		System.out.println(">>>>NyTimesAPI URL is" + nyAPIReviewURL);
		try {
			resp = template.getForEntity(nyAPIReviewURL, String.class);
		} catch (RestClientException e) {
			System.out.println(">>>>RestClient failed " + e.getMessage());
			return reviews;// Empty list
		}

		// System.out.println(">>>>>>resp is " + resp.getBody());
		JsonObject result = JsonParse.stringToJson(resp.getBody());
		try {
			JsonArray reviewsJson = result.getJsonArray("results");
		for (JsonValue jsonValue : reviewsJson) {
			JsonObject obj = jsonValue.asJsonObject();
			reviews.add(Review.create(obj));
		}
		} catch (Exception e) {
			System.out.println(">>>>No response from API failed " + e.getMessage());
			return reviews;
		}
		

		return reviews;
	}

	public Integer searchReview(Review review){
		String movieName = review.getTitle();
		return movieRepository.countComments(movieName);
	}

	public Document insertComment(String jsonString){
		return movieRepository.saveComment(jsonString);
	}

}
