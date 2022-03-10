package zhou.web;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import zhou.web.mapper.UserMapper;
import zhou.web.pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    //接收用户的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //封装用户对象
        User user= new User();
        user.setUsername(username);
        user.setPassword(password);
        //调用mapper，根据用户名查询用户对象
        //获取sqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //调用方法
        User u = userMapper.selectByUsername(username);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        //判断对象是否存在 是否为null
        if (u==null) {
            //用户不存在，可以添加用户
            userMapper.add(user);
            //提交事务
            sqlSession.commit();
            writer.write("注册成功");
            //释放资源
            sqlSession.close();
        }else {
            //用户已经存在，无法注册
           writer.write("用户名已存在");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
