import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;

public class LoginInfo {

	/*
	 * {
	 * "url": "https://gateway.watsonplatform.net/natural-language-understanding/api",
	 * "username": "b098dd60-f2eb-4153-920a-cafa6c70ce7e",
	 * "password": "Q8MjnrzQdviF"
	 * }
	 * 
	 */
	
	
	public static void main(String[] args) {
		NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
				  NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
				  "b098dd60-f2eb-4153-920a-cafa6c70ce7e",
				  "Q8MjnrzQdviF"
				);

				String text = "IBM is an American multinational technology " +
				  "company headquartered in Armonk, New York, " +
				  "United States, with operations in over 170 countries.";

				EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
				  .emotion(true)
				  .sentiment(true)
				  .limit(2)
				  .build();

				KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
				  .emotion(true)
				  .sentiment(true)
				  .limit(2)
				  .build();

				Features features = new Features.Builder()
				  .entities(entitiesOptions)
				  .keywords(keywordsOptions)
				  .build();

				AnalyzeOptions parameters = new AnalyzeOptions.Builder()
				  .text(text)
				  .features(features)
				  .build();

				AnalysisResults response = service
				  .analyze(parameters)
				  .execute();
				System.out.println(response);
	}
	
}
