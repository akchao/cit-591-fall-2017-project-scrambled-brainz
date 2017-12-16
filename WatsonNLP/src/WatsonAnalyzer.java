import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.CategoriesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.RelationsOptions;
/**
 * Runs the Watson Natural Language Understanding service 
 * using either a URL or provided text
 * and uses it to find the mood of the characters 
 * @author Alice
 *
 */
public class WatsonAnalyzer {
	
	private static NaturalLanguageUnderstanding service;
	private String text;
	private AnalysisResults response;
	private List<EntitiesResult> watsonEntities;
	private List<String> watsonEntitiesString;
	private String watsonDocEmotion;
	
	
	/**
	 * Constructor initializes Watson Natural Language Understanding API 
	 */
	public WatsonAnalyzer(String bookNameOrUrl) {
		
		service = new NaturalLanguageUnderstanding(
				  NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
				  Secret.username, Secret.password);
		
		// THIS WILL EVENTUALLY JUST BE SIMPLIFIED TO TAKING IN BOOK AS A STRING
		// URL AND .TXT PERMITTED FOR TESTING OUT WATSON FEATURES
		
//		if (bookNameOrUrl.contains("http://")) {
//			runURLEntitiesEmotion(bookNameOrUrl);
//		} else 
			if (bookNameOrUrl.contains(".txt")) {
			text = fileReader(bookNameOrUrl);
			if (text != null) {
//			runEntitiesEmotion(text);
			runWatsonDocEmotion(text);
			}
		} else {									// pass the entire book as a string
//			runEntitiesEmotion(bookNameOrUrl);
			runWatsonDocEmotion(bookNameOrUrl);
		}

	}
	
	
	/**
	 * Temporary fileReader until this is abstracted out
	 * @return text the book stored as a string
	 */
	private String fileReader(String book) {
		File inputFile = new File(book); 
		Scanner in = null;
		try {
			in = new Scanner(inputFile, "utf-8");
			text = in.useDelimiter("\\Z").next();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return text;
	}
	
	
	/**
	 * Runs a string through customized Watson NLU API
	 * to obtain string's overall emotion 
	 * @param book
	 */
	private void runWatsonDocEmotion(String book) {

		EmotionOptions emotion = new EmotionOptions.Builder()
				.document(true)
				.build();
		
		Features features = new Features.Builder()
				.emotion(emotion)
				.build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.text(book)
				.features(features)
				.build();

		response = service
				.analyze(parameters)
				.execute();
		
		watsonDocEmotion = response.getEmotion().toString();

	}		
	
	
	
	/**
	 * Runs a string through customized Watson NLU API
	 * to obtain list of entities and their emotions and overall sentiment
	 * @param book the book as a string
	 */
	private void runEntitiesEmotion(String book) {
		
		// not possible to customize entity to find "Person" type
		// running max limit 250 and will find "Person" type in WatsonParser
		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
				.emotion(true)
				.sentiment(true)		
				.limit(250) 			// 250 max limit
				.build();

		Features features = new Features.Builder()
				.entities(entitiesOptions)
				.build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.text(book)
				.features(features)
				.build();

		response = service
				.analyze(parameters)
				.execute();
		
		watsonEntities = response.getEntities();
		
	}		

	
	/**
	 * Runs a book URL through customized Watson NLU API
	 * @param url the book's URL
	 */
	private void runURLEntitiesEmotion(String url) {

		// not possible to customize entity to find "Person" type
		// running max limit 250 and will find "Person" type in WatsonParser
		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
				.emotion(true)
				.sentiment(true)		// might not need this
				.limit(250) 			// 250 max limit
				.build();

		Features features = new Features.Builder()
				.entities(entitiesOptions)
				.build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.url(url)
				.features(features)
				.build();

		response = service
				.analyze(parameters)
				.execute();

		watsonEntities = response.getEntities();

	}	
		

	
	/**
	 * Stores WatsonEntities json data as a string into an arraylist
	 * @return watsonEntitiesString a list of Watson Entities as a string
	 */
	public List<String> getWatsonEntitiesString() {
		watsonEntitiesString = new ArrayList<>();
		
		for (int i=0; i<watsonEntities.size(); i++) {
			watsonEntitiesString.add(watsonEntities.get(i).toString());
		}
		
		return watsonEntitiesString;
	}
	

	
	/**
	 * Stores Watson document emotion json data as a string 
	 * @return the watsonDocEmotion document emotion json data as a string
	 */
	public String getWatsonDocEmotion() {
		return watsonDocEmotion;
	}


	/**
	 * This runs majority of Watson's features
	 * @param book the book as a string
	 */
	private void runAllWatsonFeatures(String book) {
		
		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
				.emotion(true)
				.sentiment(true)		
				.limit(50) 			// 250 max limit
				.build();
		
		ConceptsOptions concepts = new ConceptsOptions.Builder()
				  .limit(3)	
				  .build();
		
		CategoriesOptions categories = new CategoriesOptions();
		
		KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
				.emotion(true)
				.sentiment(true)
				.limit(50)
				.build();
		
		// create customized emotion options = this could be from user
		List<String> targets = new ArrayList<>();
		targets.add("input1");
		targets.add("input2");

		EmotionOptions emotion = new EmotionOptions.Builder()
				.targets(targets)
				.document(true)
				.build();
		
		// RELATIONS GOES THROUGH COMPLETE RELATIONS LIST TO FIND RELATIONSHIPS WITHIN TEXT
		// NOT BETWEEN TWO DIFFERENT TEXTS. CAN BE CUSTOMIZED BUT ONLY WITHIN TEXT
		RelationsOptions relations = new RelationsOptions.Builder()
				.build();

		Features features = new Features.Builder()
				.entities(entitiesOptions)
				.concepts(concepts)
				.categories(categories)
				.keywords(keywordsOptions)
				.emotion(emotion)
				.relations(relations)
				.build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.text(book)
				.features(features)
				.build();

		response = service
				.analyze(parameters)
				.execute();
		
		
	}		
	
	
}
