package preliminary_work;
import java.util.ArrayList;
import java.util.List;

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
 * Runs the Watson Natural Language Understanding service API
 * using provided text stored as a string and evaluates the whole
 * text as well as for entities/characters in it for
 * 5 emotions: joy, sadness, fear, disgust, anger
 * @author Alice
 *
 */
public class WatsonAnalyzer {
	
	private static NaturalLanguageUnderstanding service;
	private AnalysisResults response;
	private List<EntitiesResult> watsonEntities;
	private List<String> watsonEntitiesString;
	private String watsonDocEmotion;
	
	
	/**
	 * Constructor initializes Watson Natural Language Understanding API 
	 */
	public WatsonAnalyzer(String bookText) {
		
		service = new NaturalLanguageUnderstanding(
				  NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
				  Secret.username, Secret.password);
		
		runWatsonDocEmotion(bookText);		// find emotions for document/text
//		runEntitiesEmotion(bookNameOrUrl); 	// find emotions for each entity/character

	}

	
/// DOCUMENT/TEXT EMOTION  /////////////////////////////////////////////////////////
	
	/**
	 * Runs a string through customized Watson NLU API
	 * to obtain string's overall emotion 
	 * @param book
	 */
	private void runWatsonDocEmotion(String book) {
		
		// Watson only handles certain languages
		try {
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

		} catch (Exception e) {
			watsonDocEmotion = null;
			System.out.println("Document was not formatted correctly for Watson API");
		}
	}		
	
	
	/**
	 * Stores Watson document emotion json data as a string 
	 * @return the watsonDocEmotion document emotion json data as a string
	 */
	public String getWatsonDocEmotion() {
		return watsonDocEmotion;
	}

	
	
/// CHARACTER EMOTION (NOT USED IN THIS VERSION OF GUI) /////////////////////////////////
	
	/**
	 * Runs a string through customized Watson NLU API
	 * to obtain list of entities and their emotions and overall sentiment
	 * @param book the book as a string
	 */
	private void runEntitiesEmotion(String book) {
		
		// not possible to customize entity to find "Person" type
		// run max limit 250 and find "Person" type in WatsonParser
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
	
/// OTHER POTENTIAL NLU PARAMETERS (NOT USED IN THIS VERSION OF GUI) /////////////////////////////////


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
		
		// Relations goes through complete relations list to find relationships
		// within text, not between two different texts 
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
