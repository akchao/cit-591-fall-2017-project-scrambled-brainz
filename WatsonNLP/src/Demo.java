import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.MetadataOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.MetadataResult;

public class Demo {
	
	private static AnalysisResults response;

	
	public static void main(String[] args) {
		
//	public Demo() {		
		
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
				  NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
				  Secret.username, Secret.password);

		
		String url = "https://www.gutenberg.org/files/56105/56105-0.txt";
		
		String text = "This longest-established ebook project"
				+ "releases books that entered the public domain,"
				+ "and can be freely used in electronic format.";
		

				MetadataOptions metadata = new MetadataOptions();
		
//				EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
//				  .emotion(true)
//				  .sentiment(true)
//				  .limit(2)
//				  .build();
//				
//				ConceptsOptions concepts = new ConceptsOptions.Builder()
//				  .limit(2)						
//				  .build();
//
//				KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
//				  .emotion(true)
//				  .sentiment(true)
//				  .limit(2)
//				  .build();

				Features features = new Features.Builder()
//				  .entities(entitiesOptions)
//				  .concepts(concepts)
//				  .keywords(keywordsOptions)
				  .metadata(metadata)
				  .build();

				AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				  .url(url)
				  .features(features)
				  .build();

				response = service
				  .analyze(parameters)
				  .execute();
				System.out.println(response);
				
	
//		String language = response.getLanguage();
//		System.out.println(language);
//				
	}
	
	public String getLanguage() {
		return response.getLanguage();
	}
	
	public MetadataResult getMetadata() {
		return response.getMetadata();
	}
	
	
}
