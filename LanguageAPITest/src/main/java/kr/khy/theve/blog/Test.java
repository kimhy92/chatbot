package kr.khy.theve.blog;

// Imports the Google Cloud client library
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;

import junit.framework.Assert;

import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.compute.ComputeScopes;

public class Test {
	public static void main(String... args) throws Exception {
		 String token=null;
		 try {
		 File file=new File("MyProject.json");
		
		 InputStream inputStream = new BufferedInputStream(new
		 FileInputStream(file));
		 GoogleCredential credential =
		 GoogleCredential.fromStream(inputStream);
		
		 List<String> COMPUTE_SCOPES =
		 Collections.singletonList(ComputeScopes.COMPUTE);
		 if(credential.createScopedRequired()) {
		 credential = credential.createScoped(COMPUTE_SCOPES);
		 }
		
		 credential.refreshToken();
		 token = credential.getAccessToken();
		
		 System.out.println(token);
		 } catch(IOException ie) {
		 Assert.assertNotNull(token);
		 }

		// Instantiates a client
		try (LanguageServiceClient language = LanguageServiceClient.create()) {

			// The text to analyze
			String text = "안녕하세요? 1)서울대 추계강의를 듣고 싶은데, 일정이 불확실해서 겨울방학 또는 내년 춘계 일정이 있는지 궁금합니다."
					+ "2)추계강의도 온라인강의를 개설하실 계획이 있으신지도 궁금합니다. 감사합니다.  ";
			Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();

			// Detects the sentiment of the text
			Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();
			System.out.printf("Text: %s%n", text);
			System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());
//			System.out.printf("Mood : %s, %s%n", )
		}
	}
}