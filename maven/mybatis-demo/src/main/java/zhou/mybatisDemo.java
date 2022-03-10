package zhou;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import zhou.mapper.UserMapper;
import zhou.pojo.user;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class mybatisDemo {
    public static void main(String[] args) throws IOException {
        //加载mybatis的核心配置文件，获取SqlSessionFactory\
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //获取sqlsession对象，用他执行对象
        SqlSession sqlSession= sqlSessionFactory.openSession();
        //执行
        //List<user> users = sqlSession.selectList("test.selectAll");
        //获取usermapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<user> users = userMapper.selectAll();

        System.out.println(users);

        sqlSession.close();
    }
}