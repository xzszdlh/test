package zhou.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import zhou.mapper.BrandMapper;
import zhou.pojo.Brand;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    @Test
    public void testselectAll() throws IOException {
        //获取sqlsessionfactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
            //执行方法
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);
        //释放
        sqlSession.close();

    }

    @Test
    public void testselecById() throws IOException {

        int id=1;
        //获取sqlsessionfactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        Brand brand = brandMapper.selectById(id);
        System.out.println(brand);
        //释放
        sqlSession.close();

    }

    @Test
    public void testselecByCondition() throws IOException {

        int status =1;
        String companyName="华为";
        String brandName ="华为";

        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        Map map=new HashMap();
        map.put("status",status);
        map.put("companyName",companyName);
        map.put("brandName",brandName);

        //获取sqlsessionfactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        //List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        List<Brand> brands =brandMapper.selectByCondition(map);
        System.out.println(brands);
        //释放
        sqlSession.close();

    }



    @Test
    public void testselecByConditionSinple() throws IOException {

        int status =1;
        String companyName="华为";
        String brandName ="华为";

        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

            Brand brand=new Brand();
            brand.setStatus(status);
            brand.setCompanyName(companyName);
            brand.setBrandName(brandName);
        //获取sqlsessionfactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        //List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        //List<Brand> brands =brandMapper.selectByCondition(map);
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        System.out.println(brands);
        //释放
        sqlSession.close();

    }

    @Test
    public void testAdd() throws IOException {

         int status =1;
        String companyName="波导手机";
        String brandName ="波导";
        String description="波导手机，手机中的战斗机";
        int ordered =100;




        Brand brand=new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);


        //获取sqlsessionfactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        //List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        //List<Brand> brands =brandMapper.selectByCondition(map);
        //List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        brandMapper.Add(brand);
        int id= brand.getId();
        System.out.println(id);
        //提交事务
        sqlSession.commit();
        //释放
        sqlSession.close();

    }

    @Test
    public void testupdate() throws IOException {

        int status =1;
        String companyName="波导手机";
        String brandName ="波导";
        String description="手机中的战斗机";
        int ordered =200;
        int id= 7;

        Brand brand=new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);
        brand.setId(id);



        //获取sqlsessionfactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        //List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        //List<Brand> brands =brandMapper.selectByCondition(map);
        //List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        //brandMapper.Add(brand);
        int count = brandMapper.update(brand);
        System.out.println(count);
        //提交事务
        sqlSession.commit();
        //释放
        sqlSession.close();

    }

    @Test
    public void testdeleteById() throws IOException {

        int id= 10;
        //获取sqlsessionfactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        //List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        //List<Brand> brands =brandMapper.selectByCondition(map);
        //List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        //brandMapper.Add(brand);
        //int count = brandMapper.update(brand);
        brandMapper.deleteById(id);
        //提交事务
        sqlSession.commit();
        //释放
        sqlSession.close();

    }

    @Test
    public void testdeleteByIds() throws IOException {

        int[] ids = {6,8};
        //获取sqlsessionfactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //获取mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        //List<Brand> brands = brandMapper.selectByCondition(status, companyName, brandName);
        //List<Brand> brands =brandMapper.selectByCondition(map);
        //List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        //brandMapper.Add(brand);
        //int count = brandMapper.update(brand);
        brandMapper.deleteByIds(ids);
        //提交事务
        sqlSession.commit();
        //释放
        sqlSession.close();

    }



}
