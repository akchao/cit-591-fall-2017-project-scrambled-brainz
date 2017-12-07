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

public class WatsonAnalyzer {
	
	private static NaturalLanguageUnderstanding service;
	private static String text;
	private static AnalysisResults response;
	private static List<ConceptsResult> watsonConcepts;
	private static List<EntitiesResult> watsonEntities;
	
	
	/**
	 * Constructor initializes Watson Natural Language Understanding API 
	 */
	public WatsonAnalyzer(String bookName) {
		fileReader(bookName);
//		fileReader("pride-prejudice.txt");	// instead pass in book to constructor
		service = new NaturalLanguageUnderstanding(
				  NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
				  Secret.username, Secret.password);
		runWatson(text);
	}
	
	
	/**
	 * Temporary fileReader until this is abstracted out
	 * @return
	 */
	public String fileReader(String book) {
		File inputFile = new File(book); 	
		try {
			Scanner in = new Scanner(inputFile, "utf-8");
			text = in.useDelimiter("\\Z").next();

			in.close(); 	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return text;
	}
	
	
	/**
	 * Runs customized Watson NLU API
	 * @param book the book as a string
	 */
	public void runWatson(String book) {
		
		// TODO: CUSTOMIZE ENTITY FOR JUST "PERSON" TYPE, OTHERWISE FILTER OUT
		EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
				.emotion(true)
				.sentiment(true)
				.limit(3) 	// increase the limit to max to find all characters in a book
				.build();
		
		ConceptsOptions concepts = new ConceptsOptions.Builder()
				  .limit(3)	// set limit?
				  .build();
		
		CategoriesOptions categories = new CategoriesOptions();
		
		// TODO: CUSTOMIZE OR FILTER OUT KEYWORDS BASED ON METRIC
		KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
				.emotion(true)
				.sentiment(true)
				.limit(3)
				.build();
		
//		// create customized emotion options = need to be from user
//		List<String> targets = new ArrayList<>();
//		targets.add("apples");
//		targets.add("oranges");
//
//		EmotionOptions emotion = new EmotionOptions.Builder()
//				.targets(targets)
//				.build();
//		
// RELATIONS GOES THROUGH COMPLETE RELATIONS LIST TO FIND RELATIONSHIPS WITHIN TEXT
// NOT BETWEEN TWO DIFFERENT TEXTS. CAN BE CUSTOMIZED BUT ONLY WITHIN TEXT
//		RelationsOptions relations = new RelationsOptions.Builder()
//				.build();

		Features features = new Features.Builder()
				.entities(entitiesOptions)
				.concepts(concepts)
				.categories(categories)
				.keywords(keywordsOptions)
//				.emotion(emotion)
//				.relations(relations)
				.build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				.text(text)
				.features(features)
				.build();

		response = service
				.analyze(parameters)
				.execute();
		
		watsonConcepts = response.getConcepts();
		watsonEntities = response.getEntities();
		
	}		

		
	/**
	 * @return the watsonResponse
	 */
	public AnalysisResults getWatsonResponse() {
		return response;
	}
	
	
	/**
	 * @return the watsonEntities
	 */
	public List<EntitiesResult> getWatsonEntities() {
		return watsonEntities;
	}
	
}
