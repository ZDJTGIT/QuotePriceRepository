package com.zhongda.quote.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *<p>Mybatis操作数据库的工具类</p>
 * @author zmdeng
 * @date 2017年8月9日
 */
public class MyBatisUtil {

	private static ThreadLocal<SqlSession> threadLcoal = new ThreadLocal<SqlSession>();
	private static SqlSessionFactory factory;

	static{
		 String resource = "mybatis-config.xml";
	     InputStream is = MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
	     factory = new SqlSessionFactoryBuilder().build(is);
	}

    /**
      * 获取SqlSession
      * @return SqlSession
      */
    public static SqlSession getSqlSession() {
    	//从当前线程获取
        SqlSession sqlSession = threadLcoal.get();
        if(sqlSession == null){
            sqlSession = factory.openSession();
            //将sqlSession与当前线程绑定
            threadLcoal.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭Session
     */
    public static void closeSqlSession(){
        //从当前线程获取
        SqlSession sqlSession = threadLcoal.get();
        if(sqlSession != null){
            sqlSession.close();
            threadLcoal.remove();
        }
    }

    /**
      * 获取SqlSession
      * @param isAutoCommit
      *        true 表示创建的SqlSession对象在执行完SQL之后会自动提交事务
      *        false 表示创建的SqlSession对象在执行完SQL之后不会自动提交事务，这时就需要我们手动调用sqlSession.commit()提交事务
      * @return SqlSession
      */
    public static SqlSession getSqlSession(boolean isAutoCommit) {
    	//从当前线程获取
        SqlSession sqlSession = threadLcoal.get();
        if(sqlSession == null){
            sqlSession = factory.openSession(isAutoCommit);
            //将sqlSession与当前线程绑定
            threadLcoal.set(sqlSession);
        }
        return sqlSession;
    }
}