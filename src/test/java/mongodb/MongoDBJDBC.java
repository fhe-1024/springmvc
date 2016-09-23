package mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBJDBC {
	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("139.224.48.13", 27017);
		MongoDatabase mongoDatabase = mongoClient.getDatabase("fhe");
		MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		Document document = new Document().append("title", "MongoDB").append("description", "database")
				.append("likes", 100).append("by", "Fly");

		List<Document> documents = new ArrayList<Document>();
		documents.add(document);

		collection.insertMany(documents);
		System.out.println("文档插入成功");

		

		collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));  
        //检索查看结果  
        FindIterable<Document> findIterable = collection.find();  
        MongoCursor<Document> mongoCursor = findIterable.iterator();  
        while(mongoCursor.hasNext()){  
           System.out.println(mongoCursor.next());  
        }  
		
		mongoClient.close();

	}
}
