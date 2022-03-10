package zhou.mapper;


import org.apache.ibatis.annotations.Param;
import zhou.pojo.Brand;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    /*
    查询所有
    * */
    public List<Brand> selectAll();

    //查询详情，根据id查询
    Brand selectById(int id);

    //条件查询
   // List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

    //List<Brand> selectByCondition(Brand brand);

    List<Brand> selectByCondition(Map map);
//单条件
    List<Brand> selectByConditionSingle(Brand brand);

    /*
    * 添加
    * */
    void Add(Brand brand);

    /*
    * 修改
    * */
            int update(Brand brand);
            //单个删除
            void deleteById(int id);

            //批量删除
    void  deleteByIds( @Param("ids") int[] ids);
}