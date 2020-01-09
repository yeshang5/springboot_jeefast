package com.bhcloud.jeefast;

import com.bhcloud.jeefast.common.utils.CacheUtils;
import com.bhcloud.jeefast.core.cache.CacheRegion;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan(basePackages = "com.bhcloud.jeefast")
@SpringBootTest
@EnableAutoConfiguration
@Slf4j
class SpringbootJeefastApplicationTests {

    @Test
    void contextLoads() {
        CacheUtils.save(CacheRegion.SYS_CACHE.value(),"test","123456");
        log.info("写入缓存成功");
    }

}
