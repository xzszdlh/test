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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //接收并获取用户名与密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //使用mybatis完成查询操作
        //获取sqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = userMapper.select(username, password);
        //释放资源
        sqlSession.close();

        //获取字符输出流，并设置type
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        //判断user是否存在 是否为null
        if (user!=null) {
            //登录成功
            writer.write("登录成功");
        }else {
            //登录失败
            writer.write("登录失败");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
