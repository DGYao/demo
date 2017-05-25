package com.springboot;

import com.springboot.web.DemoApplication;
import com.springboot.web.mapper.CityMapper;
import com.springboot.web.model.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@SpringBootTest(classes = DemoApplication.class)
public class RedisCacheTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    CityMapper cityMapper;

    @Test
    public void redisTest() throws Exception {
        //保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        //读取字符串
        String aaa = stringRedisTemplate.opsForValue().get("aaa");
        System.out.println(aaa);
    }

    @Test
    public void test1() {
        try {
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
            //计数前打印
            System.out.println(opsForValue.get("test1"));
            for (int i = 0; i < 100; i++) {
                //计数，第一个参数为key值，第二个参数为每次增加计数的单位
                opsForValue.increment("test1", 1);
            }
            //计数后打印
            System.out.println(opsForValue.get("test1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 按时间计数
    @Test
    public void test2() {
        //将日期添加到key值中
        String key = "test2_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try {
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
            System.out.println(opsForValue.get(key));
            for (int i = 0; i < 100; i++) {
                opsForValue.increment(key, 1);
            }
            System.out.println(opsForValue.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 模糊K值查询
    @Test
    public void test3() {
        try {
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
            //先获取前缀为test的Key值列表。
            Set<String> keys = stringRedisTemplate.keys("test*");
            //遍历满足条件的Key值获取对应的value值
            for (String a : keys) {
                System.out.println(a + ":" + opsForValue.get(a));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 设置key值的有效时间
    @Test
    public void test4() {
        try {
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
            opsForValue.set("test4", "test4");
            System.out.println(opsForValue.get("test4"));
            // TimeUnit.SECONDS:解释定时参数的单位
            // MICROSECONDS 微秒 一百万分之一秒（就是毫秒/1000）
            // MILLISECONDS 毫秒 千分之一秒
            // NANOSECONDS 毫微秒 十亿分之一秒（就是微秒/1000）
            // SECONDS 秒
            // MINUTES 分钟
            // HOURS 小时
            // DAYS 天
            if(stringRedisTemplate.expire("test4", 5, TimeUnit.SECONDS)){
                System.out.println("设置过期时间成功,等待。。。。");
                Thread.sleep(5001);
            }
            System.out.println(opsForValue.get("test4"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 使用hashs存储获取对象
    @Test
    public void test5(){
        Set<String> keys = stringRedisTemplate.keys("test*");
        stringRedisTemplate.delete(keys);
        City city1 = cityMapper.selectByPrimaryKey(1);
        HashOperations<String, Object, City> hash = stringRedisTemplate.opsForHash();
        hash.put("test5",city1.getId(),city1);
        System.out.println(hash.get("test5", city1.getId()));
    }

    //使用lists存储读取 有序
    @Test
    public void test6(){
        ListOperations<String, String> list = stringRedisTemplate.opsForList();
        list.leftPush("test6", "1");
        list.leftPush("test6", "2");
        list.leftPush("test6", "3");
        list.leftPush("test6", "4");
        list.leftPush("test6", "5");
        list.leftPush("test6", "6");
        list.leftPush("test6", "7");
        //保持链表只有3位
        list.trim("test6", 0, 2);
        System.out.println(list.range("test6", 0, list.size("test6")-1));
    }

    //使用set存储读取  无序 去重  求差集，交集，并集
    @Test
    public void test7(){
        SetOperations<String, String> set = stringRedisTemplate.opsForSet();
        set.add("test7_1", "2", "1","2","3","4","4","3");
        set.add("test7_2", "2", "6","2","3","7","6","5");
        System.out.println("全部成员"+set.members("test7_1"));
        System.out.println("差集"+set.difference("test7_1", "test7_2"));
        System.out.println("交集"+set.intersect("test7_1", "test7_2"));
        System.out.println("并集"+set.union("test7_1", "test7_2"));
    }
}
;