package com.gzt;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class BigEventApplicationTest
{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void testRedis(){
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("mykey","value1");
        String mykey = stringStringValueOperations.get("mykey");
        System.out.println(mykey);
    }


}
