package test;

import com.mybatistest.model.Actor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            Reader reader=Resources.getResourceAsReader("mybatis-config.xml");
            SqlSessionFactory fac=new SqlSessionFactoryBuilder().build(reader);
            SqlSession session=fac.openSession();
            List<Actor> list=session.selectList("selectAll");
            for(Actor a:list){
                System.out.println(a.getActor_id()+"\t"+a.getFirst_name()+"\t"+a.getLast_name()+"\t"+a.getTime());

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
