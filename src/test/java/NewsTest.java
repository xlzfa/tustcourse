import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tust.app.dao.NewsMapper;
import com.tust.app.domain.News;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class NewsTest {
    @Test
    public void testSelectAllNewsByPage(){
        //整合过程
        //1.通过mybatis-config.xml配置文件创建一个SqlSessionFactory对象
        try {

            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = sessionFactory.openSession();

            //2.实例化NewsMapper接口，调x用接口中的方法，实现查询数据
            NewsMapper mapper = session.getMapper(NewsMapper.class);
            //开启分页
            PageHelper.startPage(2,2);
            List<News> news = mapper.selectAllNews();
            PageInfo pageInfo = new PageInfo(news);
            System.out.println(pageInfo.getPages());

            //System.out.println(news);

            session.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSelectNewsById(){
        //整合过程
        //1.通过mybatis-config.xml配置文件创建一个SqlSessionFactory对象
        try {

            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = sessionFactory.openSession();

            //2.实例化NewsMapper接口，调用接口中的方法，实现查询数据
            NewsMapper mapper = session.getMapper(NewsMapper.class);
            News news = mapper.selectNewsById(41);
            System.out.println(news);

            session.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSelectNewsByTitle(){
        //整合过程
        //1.通过mybatis-config.xml配置文件创建一个SqlSessionFactory对象
        try {

            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = sessionFactory.openSession();

            //2.实例化NewsMapper接口，调x用接口中的方法，实现查询数据
            NewsMapper mapper = session.getMapper(NewsMapper.class);
            List<News> news = mapper.selectNewsByTitle("test");
            System.out.println(news);

            session.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testWriteNews(){
        //整合过程
        //1.通过mybatis-config.xml配置文件创建一个SqlSessionFactory对象
        try {

            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = sessionFactory.openSession();

            //2.实例化NewsMapper接口，调x用接口中的方法，实现查询数据
            NewsMapper mapper = session.getMapper(NewsMapper.class);
            News news = new News();
            news.setId(50);
            news.setTitle("test0007");
            news.setArticle("article");
            news.setTime("2024-6-30");
            mapper.writeNews(news);

            session.commit();
            session.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeleteNewsById(){
        //整合过程
        //1.通过mybatis-config.xml配置文件创建一个SqlSessionFactory对象
        try {

            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = sessionFactory.openSession();

            //2.实例化NewsMapper接口，调x用接口中的方法，实现查询数据
            NewsMapper mapper = session.getMapper(NewsMapper.class);
            boolean result = mapper.deleteNewsById(50);

            session.commit();
            System.out.println(result);
            session.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateNews(){
        //整合过程
        //1.通过mybatis-config.xml配置文件创建一个SqlSessionFactory对象
        try {

            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession session = sessionFactory.openSession();

            //2.实例化NewsMapper接口，调x用接口中的方法，实现查询数据
            NewsMapper mapper = session.getMapper(NewsMapper.class);

            News news = new News();
            news.setId(50);
            news.setTitle("test0008");
            news.setArticle("article008");
            news.setTime("2024-6-33");

            boolean result = mapper.updateNews(news);

            session.commit();
            System.out.println(result);
            session.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
