package com.actuator.indicator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;


@Component
@Slf4j
public class CustomHealthIndicator extends AbstractHealthIndicator {
    /**
     * @return void
     * site: https://www.ant-loiter.com
     * @Author chenhj(brenda)
     * @Description //TODO 自定义项目健康测试结果显示内容；
     * @Date 11:06 2020/5/19
     * @Param [builder]
     **/
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        ///使用builder来创建健康状态信息
        // 如果你throw了一个exception 那么status 就会被置为DOWN， 异常信息会被记录下来
        log.info(">>>> , {}", this.getClass().getName());
        builder.up()
                .withDetail("app", "项目很健康")
                .withDetail("error", "Nothing, I am very good");
    }
}
