package lecto.cocam.chatbot.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {
	private SqlSession sqlSession;

	public TestDAO() {
		super();
	}

	public TestDAO(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<String> read() {	
		return sqlSession.selectList("readAll");
	}

}
