package lecto.cocam.chatbot.util;

import com.google.cloud.language.v1.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1.AnalyzeEntitySentimentRequest;
import com.google.cloud.language.v1.AnalyzeEntitySentimentResponse;
import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.language.v1.Token;

import java.util.List;
import java.util.Map;


public class AnalyzeQuestion {
	public static void analyzeEntitiesText(String text) throws Exception {
		// [START analyze_entities_text]
		// Instantiate the Language client
		// com.google.cloud.language.v1.LanguageServiceClient
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
			AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();

			AnalyzeEntitiesResponse response = language.analyzeEntities(request);

			// Print the response
			for (Entity entity : response.getEntitiesList()) {
				System.out.printf("Entity: %s", entity.getName());
				System.out.printf("Salience: %.3f\n", entity.getSalience());
				System.out.println("Metadata: ");
				for (Map.Entry<String, String> entry : entity.getMetadataMap().entrySet()) {
					System.out.printf("%s : %s", entry.getKey(), entry.getValue());
				}
				for (EntityMention mention : entity.getMentionsList()) {
					System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
					System.out.printf("Content: %s\n", mention.getText().getContent());
					System.out.printf("Type: %s\n\n", mention.getType());
				}
			}
		}
		// [END analyze_entities_text]
	}

	/**
	 * Identifies entities in the contents of the object at the given GCS
	 * {@code path}.
	 */
	public static void analyzeEntitiesFile(String gcsUri) throws Exception {
		// [START analyze_entities_gcs]
		// Instantiate the Language client
		// com.google.cloud.language.v1.LanguageServiceClient
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			// set the GCS Content URI path to the file to be analyzed
			Document doc = Document.newBuilder().setGcsContentUri(gcsUri).setType(Type.PLAIN_TEXT).build();
			AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();

			AnalyzeEntitiesResponse response = language.analyzeEntities(request);

			// Print the response
			for (Entity entity : response.getEntitiesList()) {
				System.out.printf("Entity: %s", entity.getName());
				System.out.printf("Salience: %.3f\n", entity.getSalience());
				System.out.println("Metadata: ");
				for (Map.Entry<String, String> entry : entity.getMetadataMap().entrySet()) {
					System.out.printf("%s : %s", entry.getKey(), entry.getValue());
				}
				for (EntityMention mention : entity.getMentionsList()) {
					System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
					System.out.printf("Content: %s\n", mention.getText().getContent());
					System.out.printf("Type: %s\n\n", mention.getType());
				}
			}
		}
		// [END analyze_entities_gcs]
	}

	/**
	 * Identifies the sentiment in the string {@code text}.
	 */
	public static Sentiment analyzeSentimentText(String text) throws Exception {
		// [START analyze_sentiment_text]
		// Instantiate the Language client
		// com.google.cloud.language.v1.LanguageServiceClient
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
			AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
			Sentiment sentiment = response.getDocumentSentiment();
			if (sentiment == null) {
				System.out.println("No sentiment found");
			} else {
				System.out.printf("Sentiment magnitude: %.3f\n", sentiment.getMagnitude());
				System.out.printf("Sentiment score: %.3f\n", sentiment.getScore());
			}
			return sentiment;
		}
		// [END analyze_sentiment_text]
	}

	/**
	 * Gets {@link Sentiment} from the contents of the GCS hosted file.
	 */
	public static Sentiment analyzeSentimentFile(String gcsUri) throws Exception {
		// [START analyze_sentiment_file]
		// Instantiate the Language client
		// com.google.cloud.language.v1.LanguageServiceClient
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			Document doc = Document.newBuilder().setGcsContentUri(gcsUri).setType(Type.PLAIN_TEXT).build();
			AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
			Sentiment sentiment = response.getDocumentSentiment();
			if (sentiment == null) {
				System.out.println("No sentiment found");
			} else {
				System.out.printf("Sentiment magnitude : %.3f\n", sentiment.getMagnitude());
				System.out.printf("Sentiment score : %.3f\n", sentiment.getScore());
			}
			return sentiment;
		}
		// [END analyze_sentiment_file]
	}

	/**
	 * from the string {@code text}.
	 */
	public static List<Token> analyzeSyntaxText(String text) throws Exception {
		// [START analyze_syntax_text]
		// Instantiate the Language client
		// com.google.cloud.language.v1.LanguageServiceClient
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
			AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();
			// analyze the syntax in the given text
			AnalyzeSyntaxResponse response = language.analyzeSyntax(request);
			// print the response
			for (Token token : response.getTokensList()) {
				System.out.printf("\tText: %s\n", token.getText().getContent());
				System.out.printf("\tBeginOffset: %d\n", token.getText().getBeginOffset());
				System.out.printf("Lemma: %s\n", token.getLemma());
				System.out.printf("PartOfSpeechTag: %s\n", token.getPartOfSpeech().getTag());
				System.out.printf("\tAspect: %s\n", token.getPartOfSpeech().getAspect());
				System.out.printf("\tCase: %s\n", token.getPartOfSpeech().getCase());
				System.out.printf("\tForm: %s\n", token.getPartOfSpeech().getForm());
				System.out.printf("\tGender: %s\n", token.getPartOfSpeech().getGender());
				System.out.printf("\tMood: %s\n", token.getPartOfSpeech().getMood());
				System.out.printf("\tNumber: %s\n", token.getPartOfSpeech().getNumber());
				System.out.printf("\tPerson: %s\n", token.getPartOfSpeech().getPerson());
				System.out.printf("\tProper: %s\n", token.getPartOfSpeech().getProper());
				System.out.printf("\tReciprocity: %s\n", token.getPartOfSpeech().getReciprocity());
				System.out.printf("\tTense: %s\n", token.getPartOfSpeech().getTense());
				System.out.printf("\tVoice: %s\n", token.getPartOfSpeech().getVoice());
				System.out.println("DependencyEdge");
				System.out.printf("\tHeadTokenIndex: %d\n", token.getDependencyEdge().getHeadTokenIndex());
				System.out.printf("\tLabel: %s\n\n", token.getDependencyEdge().getLabel());
			}
			return response.getTokensList();
		}
		// [END analyze_syntax_text]
	}

	/**
	 * Get the syntax of the GCS hosted file.
	 */
	public static List<Token> analyzeSyntaxFile(String gcsUri) throws Exception {
		// [START analyze_syntax_file]
		// Instantiate the Language client
		// com.google.cloud.language.v1.LanguageServiceClient
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			Document doc = Document.newBuilder().setGcsContentUri(gcsUri).setType(Type.PLAIN_TEXT).build();
			AnalyzeSyntaxRequest request = AnalyzeSyntaxRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();
			// analyze the syntax in the given text
			AnalyzeSyntaxResponse response = language.analyzeSyntax(request);
			// print the response
			for (Token token : response.getTokensList()) {
				System.out.printf("\tText: %s\n", token.getText().getContent());
				System.out.printf("\tBeginOffset: %d\n", token.getText().getBeginOffset());
				System.out.printf("Lemma: %s\n", token.getLemma());
				System.out.printf("PartOfSpeechTag: %s\n", token.getPartOfSpeech().getTag());
				System.out.printf("\tAspect: %s\n", token.getPartOfSpeech().getAspect());
				System.out.printf("\tCase: %s\n", token.getPartOfSpeech().getCase());
				System.out.printf("\tForm: %s\n", token.getPartOfSpeech().getForm());
				System.out.printf("\tGender: %s\n", token.getPartOfSpeech().getGender());
				System.out.printf("\tMood: %s\n", token.getPartOfSpeech().getMood());
				System.out.printf("\tNumber: %s\n", token.getPartOfSpeech().getNumber());
				System.out.printf("\tPerson: %s\n", token.getPartOfSpeech().getPerson());
				System.out.printf("\tProper: %s\n", token.getPartOfSpeech().getProper());
				System.out.printf("\tReciprocity: %s\n", token.getPartOfSpeech().getReciprocity());
				System.out.printf("\tTense: %s\n", token.getPartOfSpeech().getTense());
				System.out.printf("\tVoice: %s\n", token.getPartOfSpeech().getVoice());
				System.out.println("DependencyEdge");
				System.out.printf("\tHeadTokenIndex: %d\n", token.getDependencyEdge().getHeadTokenIndex());
				System.out.printf("\tLabel: %s\n\n", token.getDependencyEdge().getLabel());
			}

			return response.getTokensList();
		}
		// [END analyze_syntax_file]
	}

	/**
	 * Detects the entity sentiments in the string {@code text} using the
	 * Language Beta API.
	 */
	public static void entitySentimentText(String text) throws Exception {
		// [START entity_sentiment_text]
		// Instantiate a beta client :
		// com.google.cloud.language.v1beta2.LanguageServiceClient
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
			AnalyzeEntitySentimentRequest request = AnalyzeEntitySentimentRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();
			// detect entity sentiments in the given string
			AnalyzeEntitySentimentResponse response = language.analyzeEntitySentiment(request);
			// Print the response
			for (Entity entity : response.getEntitiesList()) {
				System.out.printf("Entity: %s\n", entity.getName());
				System.out.printf("Salience: %.3f\n", entity.getSalience());
				System.out.printf("Sentiment : %s\n", entity.getSentiment());
				for (EntityMention mention : entity.getMentionsList()) {
					System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
					System.out.printf("Content: %s\n", mention.getText().getContent());
					System.out.printf("Magnitude: %.3f\n", mention.getSentiment().getMagnitude());
					System.out.printf("Sentiment score : %.3f\n", mention.getSentiment().getScore());
					System.out.printf("Type: %s\n\n", mention.getType());
				}
			}
		}
		// [END entity_sentiment_text]
	}

	/**
	 * Identifies the entity sentiments in the the GCS hosted file using the
	 * Language Beta API.
	 */
	public static void entitySentimentFile(String gcsUri) throws Exception {
		// [START entity_sentiment_file]
		// Instantiate a beta client :
		// com.google.cloud.language.v1beta2.LanguageServiceClient
		try (LanguageServiceClient language = LanguageServiceClient.create()) {
			Document doc = Document.newBuilder().setGcsContentUri(gcsUri).setType(Type.PLAIN_TEXT).build();
			AnalyzeEntitySentimentRequest request = AnalyzeEntitySentimentRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();
			// Detect entity sentiments in the given file
			AnalyzeEntitySentimentResponse response = language.analyzeEntitySentiment(request);
			// Print the response
			for (Entity entity : response.getEntitiesList()) {
				System.out.printf("Entity: %s\n", entity.getName());
				System.out.printf("Salience: %.3f\n", entity.getSalience());
				System.out.printf("Sentiment : %s\n", entity.getSentiment());
				for (EntityMention mention : entity.getMentionsList()) {
					System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
					System.out.printf("Content: %s\n", mention.getText().getContent());
					System.out.printf("Magnitude: %.3f\n", mention.getSentiment().getMagnitude());
					System.out.printf("Sentiment score : %.3f\n", mention.getSentiment().getScore());
					System.out.printf("Type: %s\n\n", mention.getType());
				}
			}
		}
		// [END entity_sentiment_file]
	}
}
