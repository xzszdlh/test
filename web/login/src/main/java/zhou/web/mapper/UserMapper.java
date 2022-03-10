package zhou.web.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import zhou.web.pojo.User;

public interface UserMapper {

    //根据用户名和密码查询用户对象
    @Select("select * from user where username=#{username} and password=#{password}")
    User select(@Param("username") String username, @Param("password") String password);

    //根据用户名查询用户对象
    @Select("select * from user where username=#{username}")
    User selectByUsername(String name);

    //注册 添加用户

    @Select("insert into user values (null,#{username},#{password})")
    void add(User user);

}
