package kr.khy.theve.blog;

import com.google.cloud.language.v1.AnalyzeEntitiesRequest;
import com.google.cloud.language.v1.AnalyzeEntitiesResponse;
import com.google.cloud.language.v1.AnalyzeSentimentResponse;
import com.google.cloud.language.v1.AnalyzeSyntaxRequest;
import com.google.cloud.language.v1.AnalyzeSyntaxResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;

import junit.framework.Assert;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.google.cloud.language.v1.Token;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.compute.ComputeScopes;

public class Test {
	public static void main(String... args) throws Exception {
		String token = null;
		try {
			File file = new File("MyProject.json");

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			GoogleCredential credential = GoogleCredential.fromStream(inputStream);

			List<String> COMPUTE_SCOPES = Collections.singletonList(ComputeScopes.COMPUTE);
			if (credential.createScopedRequired()) {
				credential = credential.createScoped(COMPUTE_SCOPES);
			}

			credential.refreshToken();
			token = credential.getAccessToken();

			System.out.println(token);
		} catch (IOException ie) {
			Assert.assertNotNull(token);
		}

		// Instantiates a client
		try (LanguageServiceClient language = LanguageServiceClient.create()) {

			// The text to analyze
			String text = "전문가 과정 다음 기수 일정은 어떻게 되나요?";
			Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();

			// Detects the sentiment of the text
			// Sentiment sentiment =
			// language.analyzeSentiment(doc).getDocumentSentiment();
			//
			//
			// System.out.printf("Text: %s%n", text);
			// System.out.println(sentiment);
			//// System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(),
			// sentiment.getMagnitude());

			AnalyzeEntitiesRequest request = AnalyzeEntitiesRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();

			AnalyzeEntitiesResponse response = language.analyzeEntities(request);

			System.out.println("==================================================");
			System.out.println("Entities");
			// 단어 해석 (Analyze Entity)
			for (Entity entity : response.getEntitiesList()) {
				System.out.printf("Entity: %s%n", entity.getName());
				System.out.printf("Salience: %.3f%n", entity.getSalience());
				System.out.println("Metadata : ");
				for (Map.Entry<String, String> entry : entity.getMetadataMap().entrySet()) {
					System.out.printf("%s : %s", entry.getKey(), entry.getValue());
				}
				for (EntityMention mention : entity.getMentionsList()) {
					System.out.printf("Begin offset: %d\n", mention.getText().getBeginOffset());
					System.out.printf("Content: %s\n", mention.getText().getContent());
					System.out.printf("Type: %s\n\n", mention.getType());
				}
			}
			System.out.println("==================================================");
			System.out.println("Sentiment");
			// Sentiment(감정) 분석
			AnalyzeSentimentResponse response2 = language.analyzeSentiment(doc);
			Sentiment sentiment = response2.getDocumentSentiment();
			if (sentiment == null) {
				System.out.println("No sentiment found");
			} else {
				System.out.printf("Sentiment magnitude : %.3f%n", sentiment.getMagnitude());
				System.out.printf("Sentiment score : %.3f%n", sentiment.getScore());
			}

			System.out.println("==================================================");
			System.out.println("Syntax");
			// Syntax 분석
			AnalyzeSyntaxRequest request2 = AnalyzeSyntaxRequest.newBuilder().setDocument(doc)
					.setEncodingType(EncodingType.UTF16).build();
			AnalyzeSyntaxResponse response3 = language.analyzeSyntax(request2);
			
			
			
			
			
			for (Token token2 : response3.getTokensList()) {
				System.out.printf("\tText: %s\n", token2.getText().getContent());
				System.out.printf("\tBeginOffset: %d\n", token2.getText().getBeginOffset());
				System.out.printf("Lemma: %s\n", token2.getLemma());
				System.out.printf("PartOfSpeechTag: %s\n", token2.getPartOfSpeech().getTag());
				System.out.printf("\tAspect: %s\n", token2.getPartOfSpeech().getAspect());
				System.out.printf("\tCase: %s\n", token2.getPartOfSpeech().getCase());
				System.out.printf("\tForm: %s\n", token2.getPartOfSpeech().getForm());
				System.out.printf("\tGender: %s\n", token2.getPartOfSpeech().getGender());
				System.out.printf("\tMood: %s\n", token2.getPartOfSpeech().getMood());
				System.out.printf("\tNumber: %s\n", token2.getPartOfSpeech().getNumber());
				System.out.printf("\tPerson: %s\n", token2.getPartOfSpeech().getPerson());
				System.out.printf("\tProper: %s\n", token2.getPartOfSpeech().getProper());
				System.out.printf("\tReciprocity: %s\n", token2.getPartOfSpeech().getReciprocity());
				System.out.printf("\tTense: %s\n", token2.getPartOfSpeech().getTense());
				System.out.printf("\tVoice: %s\n", token2.getPartOfSpeech().getVoice());
				System.out.println("DependencyEdge");
				System.out.printf("\tHeadTokenIndex: %d\n", token2.getDependencyEdge().getHeadTokenIndex());
				System.out.printf("\tLabel: %s\n\n", token2.getDependencyEdge().getLabel());
			}
		}
	}
}
