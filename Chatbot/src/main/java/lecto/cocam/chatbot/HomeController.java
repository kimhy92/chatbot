package lecto.cocam.chatbot;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lecto.cocam.chatbot.dao.TestDAO;

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
}
