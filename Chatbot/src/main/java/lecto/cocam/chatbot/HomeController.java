package lecto.cocam.chatbot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.compute.ComputeScopes;
import com.google.cloud.language.v1.Token;

import lecto.cocam.chatbot.dao.QuestDAO;
import lecto.cocam.chatbot.util.AnalyzeQuestion;
import vo.Quest;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private QuestDAO questDao;

	public void setQuestDao(QuestDAO questDao) {
		this.questDao = questDao;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//List<Quest> names = questDao.read();
		//model.addAttribute("names", names);
		questDao.deleteQuset(new Quest("sdgsdg","",Timestamp.valueOf("1111-11-11 11:11:11"),Timestamp.valueOf("1111-11-11 11:11:11"),"",""));
		//questDao.insert(new Quest("sdgsdg","SDg",Timestamp.valueOf("2015-4-30 10:34:24"),Timestamp.valueOf("2015-4-30 10:34:24"),"SDg","Sdgsd"));
		return "home";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public JSONArray test(Locale locale, Model model) throws Exception {
		String token = null;
		try {
			//File file = new File("/resources/MyProject.json");
			JSONObject object=new JSONObject();
			object.put("type", "service_account");
			object.put("project_id", "chatbot-180809");
			object.put("private_key_id", "abc525433363fbb4f2223ecce7c42bc7ac34e630");
			object.put("private_key", "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxocisoq1cVdhi\nihfw1xsAy0njg0fBHzdibC0aLcNNUD8EwGSIjGpA1VkJHG1z3R2s1cf2h3fDx4Gf\nNTZBLybQE/ivjyQ4g7h+fnFIcdzdhycZGdpaqvUgQjE7W3qZ2D4YnlicXaF6z2m7\nQOoVQ5GEFrhdXaGjMiTQKAs7wffjwlGk8F3MWoZ/ZD/Wlloxo3qfnnzZceSrN+Yf\nCW0JhsF+KUmWIShO/cGA5mq7fwDPZCWupzPoH4wSFGrfbdOPjO7/+CzrL9pdRgVF\nxm2RLC9k0cQRvfxwqdKg47DZhsMhgFfT9pi9DDYB0coAK9UAllB1Wd+F6lXqmNoi\nxQHm6A0RAgMBAAECggEAA5CspPUr5EVJAngYKtC9nXQddftE/3Xt4bDfOkRuXcwg\ndt4zD/MitwC0ZYSRGGj489nWxlaQNQxfrq9Kkp+mMgY6MMqlu07YSMBz0hr07msZ\nKHDc8ZwD4pA2VqUx1Moi1VHCRtS+c7PoTZmSYsRewVp6YoDvu4VU+OrA1PcO6SuN\nGg9uFkt34Uwr2+4g6pnrj3qgLfejquV6iEf4ZTwqS/+dkkwyMilp11H4VqbaCJF1\nCJIFgha8cX2FNp8PRWlCwHmkGSOeJZNes5SSqdnA64rvc8uBiycotPWUbXYhKODo\nOkZjdocWIov/6krKPhUF1b1J5c60hpJOixBxdCSMpQKBgQDsYUUBPg3xXJ8K+lao\nJZYMhg0B2yjsghDoXrRet99hJGGSIxRgwJpErl/K+8WTeBsNoLS49NEb9WjNTFrr\nKEe14pl1SWcYSLoBbxykAGurVxiNdra501NK8KVJ20/mO+LDqxapLmu/tFJFvR3T\n//z3UIvsyfB9FDIRAaF7DLMa1QKBgQDAYDR3NHWOVx/LHE1jVb13U0R+rsvoAnio\n9gTf/0bWDqcJASRognJRRLrKo9ig3TL2+3nvCFE5fD+AKJcMyNRn+7EHh4a0hCRD\nzU6HZelnC6PTMejmpI7fPyZW6LJ3EUu82/ctJJ/YulNfk6NfMlAggWmGMVMV+8Xd\n6Us1EtyPTQKBgQCQlw82q1asBRPkCy1mvVcDYhwivoRR512gRg3ewmqJaU+lrH+S\njz3ONTuzVj6M7jLeKydg25/tkjgBHGug3IrA31MvQy8k05ezuNu0Fe3GaQhDwBIV\nqQaSGJGCk/xTwN39AnJiGlsecJbCweO1AikivspYlU3dtLTLYkv8kNAd4QKBgCTP\nQ7q7cvL1gMPgen5TXZmDeUu0LpgKQQQzbuNdeTRkQVnbuhe1PiKGwCceCsldCe9w\nVviB4ujY+V2QZravhtUOt+T07bWEw1ul4SzPN0XcWFCE8EeRnTTiZHektuFJkfBl\nNnqQ03Uo0eMr4MrKWcKegI6IyZkfP9EiXp2dKk7RAoGAPmGiBoiKnb+qNXztAGQr\neuWZFHaorx+by3IOM2XFKKZEE5GCQzpE7q7DETE1el9bXNwJIJGuPHlZuthztCKK\nVWQ9X+mywRqYpbN9HcXZux8ieEhB2YRD5if3E/OfDC5c7v6NCqhD3L77JzCjBuTe\nGLLJUj9dZDLE9zmYHEhKnvk=\n-----END PRIVATE KEY-----\n");
			object.put("client_email", "chatbot@chatbot-180809.iam.gserviceaccount.com");
			object.put("client_id", "107993109382179915496");
			object.put("auth_uri", "https://accounts.google.com/o/oauth2/auth");
			object.put("token_uri", "https://accounts.google.com/o/oauth2/token");
			object.put("auth_provider_x509_cert_url", "https://www.googleapis.com/oauth2/v1/certs");
			object.put("client_x509_cert_url", "https://www.googleapis.com/robot/v1/metadata/x509/chatbot%40chatbot-180809.iam.gserviceaccount.com");
			
			InputStream inputStream=new ByteArrayInputStream(object.toString().getBytes());
			//InputStream inputStream = new BufferedInputStream(new FileInputStream(object.toString()));
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
		
		List<Token> res=AnalyzeQuestion.analyzeSyntaxText("전문가 과정의 다음 기수 일정은 어떻게 되나요?");
		JSONArray ary=new JSONArray();
		for(Token tmp:res){
			JSONObject obj=new JSONObject();
			obj.put("Text", tmp.getText().getContent());
			obj.put("BeginOffset", tmp.getText().getBeginOffset());
			obj.put("Lemma", tmp.getLemma());
			obj.put("PartOfSpeeachTag", tmp.getPartOfSpeech().getTag());
			obj.put("Aspect",tmp.getPartOfSpeech().getAspect());
			obj.put("Case",tmp.getPartOfSpeech().getCase());
			obj.put("Form",tmp.getPartOfSpeech().getForm());
			obj.put("Gender",tmp.getPartOfSpeech().getGender());
			obj.put("Mood",tmp.getPartOfSpeech().getMood());
			obj.put("Number",tmp.getPartOfSpeech().getNumber());
			obj.put("Person",tmp.getPartOfSpeech().getPerson());
			obj.put("Proper",tmp.getPartOfSpeech().getProper());
			obj.put("Reciprocity",tmp.getPartOfSpeech().getReciprocity());
			obj.put("Tense",tmp.getPartOfSpeech().getTense());
			obj.put("Voice",tmp.getPartOfSpeech().getVoice());
			obj.put("HeadTokenIndex",tmp.getDependencyEdge().getHeadTokenIndex());
			obj.put("Label",tmp.getDependencyEdge().getLabel());
			ary.put(obj);
		}
		return ary;
	}
	
	@RequestMapping(value = "/getMessage", method = RequestMethod.GET)
	public void getMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg=request.getParameter("userMessage");
		
		JSONObject obj=new JSONObject();
		obj.put("msg", msg);
		//여기서 응답메시지 만들어서 obj에 넣으면 됨
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			
			out.write(obj.toString());
			// System.out.println("[" +  obj.toString() + "]");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
