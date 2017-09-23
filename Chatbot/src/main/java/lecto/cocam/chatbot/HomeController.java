package lecto.cocam.chatbot;

import java.sql.Timestamp;
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

import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;

import lecto.cocam.chatbot.dao.QuestDAO;
import lecto.cocam.chatbot.dao.TestDAO;
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
}
