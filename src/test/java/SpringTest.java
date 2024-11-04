import com.tust.app.dao.NewsMapper;
import com.tust.app.domain.News;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

public class SpringTest {



    @Test
    public void testSpring(){

        //创建Spring容器，也创建了@Component注解的类的对象，并把对象放入容器中
        ApplicationContext context =
                new FileSystemXmlApplicationContext("classpath:applicationContext.xml");


        //从容器中取出News对象
        //News news = (News) context.getBean("news");
//        News news = context.getBean(News.class);
//        System.out.println(news);

        NewsMapper newsMapper = context.getBean(NewsMapper.class);
        List<News> news = newsMapper.selectAllNews();
        System.out.println(news);

    }

}
