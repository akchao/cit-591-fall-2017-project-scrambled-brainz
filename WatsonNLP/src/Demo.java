import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;

public class Demo {
	
	private static AnalysisResults response;

	public Demo() {
		
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
				  NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
				  Secret.username, Secret.password);

		
		String text = "I think I am in love!";
		
//		String url = "http://www.gutenberg.org/files/45609/45609-h/45609-h.htm";

				EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
				  .emotion(true)
				  .sentiment(true)
				  .limit(2)
				  .build();
				
				ConceptsOptions concepts = new ConceptsOptions.Builder()
				  .limit(2)						
				  .build();

				KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
				  .emotion(true)
				  .sentiment(true)
				  .limit(2)
				  .build();

				Features features = new Features.Builder()
				  .entities(entitiesOptions)
				  .concepts(concepts)
				  .keywords(keywordsOptions)
				  .build();

				AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				  .text(text)
				  .features(features)
				  .build();

				response = service
				  .analyze(parameters)
				  .execute();
//				System.out.println(response);
				
	
//		String language = response.getLanguage();
//		System.out.println(language);
//				
	}
	
	public String getLanguage() {
		return response.getLanguage();
	}
	
	
}
