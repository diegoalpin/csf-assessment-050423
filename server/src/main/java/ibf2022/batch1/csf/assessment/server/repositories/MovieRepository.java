package ibf2022.batch1.csf.assessment.server.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {
	@Autowired
	MongoTemplate mongoTemplate;
	private final String COLLECTION_COMMENTS = "comments";
	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	//

	// NATIVE MONGO QUERY
	/*
	 * db.getCollection("comments")
	 * .find({"moviename":<movie_name>})
	 */
	public int countComments(/* Object param, */String movieName) {
		Criteria criteria = Criteria.where("moviename").is(movieName);
		Query query = Query.query(criteria);
		List<Document> result = mongoTemplate.find(query, Document.class, COLLECTION_COMMENTS);

		return result.size();
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below

	//// NATIVE MONGO QUERY
	/*
	 * db.comments.insert({
	 * name:'Diego',
	 * rating:4,
	 * comment:'this is very good'})
	 */
	public Document saveComment(String jsonString) {
		Document doc = Document.parse(jsonString);
		mongoTemplate.insert(doc, COLLECTION_COMMENTS);
		return doc;
	}
}
