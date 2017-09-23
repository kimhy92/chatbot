package lecto.cocam.chatbot;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.compute.ComputeScopes;

import junit.framework.Assert;
import lecto.cocam.chatbot.dao.TestDAO;
import lecto.cocam.chatbot.util.AnalyzeQuestion;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private TestDAO testDao;

	public void setTestDao(TestDAO testDao) {
		this.testDao = testDao;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		List<String> names = testDao.read();
		model.addAttribute("names", names);

		return "home";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) throws Exception {
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
			ie.printStackTrace();
//			Assert.assertNotNull(token);
		}
		
		AnalyzeQuestion.analyzeEntitiesText("전문가 과정의 다음 기수 일정은 어떻게 되나요?");
		
		return "test";
	}
}
