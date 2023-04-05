package ibf2022.batch1.csf.assessment.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

// DO NOT MODIFY THIS CLASS
public class Review {
	// display_title
	private String title;
	// mpaa_rating
	private String rating;
	// byline
	private String byline;
	// headline
	private String headline;
	// summary_short
	private String summary;
	// link.url
	private String reviewURL;
	// multimedia.src
	private String image = null;

	private int commentCount = 0;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRating() {
		return this.rating;
	}

	public void setByline(String byline) {
		this.byline = byline;
	}

	public String getByline() {
		return this.byline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setReviewURL(String reviewURL) {
		this.reviewURL = reviewURL;
	}

	public String getReviewURL() {
		return this.reviewURL;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}

	public boolean hasImage() {
		return null != this.image;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	};

	public int getCommentCount() {
		return this.commentCount;
	}

	public static Review create(JsonObject obj) {
		Review review = new Review();
		review.setTitle(obj.getString("display_title", "default"));
		review.setRating(obj.getString("mpaa_rating", "default"));
		review.setByline(obj.getString("byline", "default"));
		review.setHeadline(obj.getString("headline", "default"));
		review.setSummary(obj.getString("summary_short", "default"));
		System.out.println(">>>processing for " + obj.getString("display_title"));

		try {
			JsonObject link = obj.getJsonObject("link");
			review.setReviewURL(link.getString("url", "default"));

		} catch (Exception e) {
			review.setReviewURL("default");
			System.out.println(e.getMessage());
		}

		try {
			JsonObject multimedia = obj.getJsonObject("multimedia");
			review.setImage(multimedia.getString("src", "default"));

		} catch (Exception e) {
			review.setImage("default");
			System.out.println(e.getMessage());
		}

		return review;
	}

	public JsonObjectBuilder toJsonBuilder() {
		return Json.createObjectBuilder()
				.add("title", getTitle())
				.add("rating", getRating())
				.add("byline", getByline())
				.add("headline", getHeadline())
				.add("summary", getSummary())
				.add("reviewURL", getReviewURL())
				.add("image", hasImage() ? getImage() : null)
				.add("commentCount", getCommentCount());
	}

	@Override
	public String toString() {
		return "Review{title:%s, rating:%s}".formatted(title, rating);
	}
}
