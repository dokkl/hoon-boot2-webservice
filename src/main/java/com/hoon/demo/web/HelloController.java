package com.hoon.demo.web;

import com.hoon.demo.web.dto.HelloResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class HelloController {
    private static final long ONE_DAY = 24L*60L*60L*1000L;

    @GetMapping("/hello")
    public String hello() throws Exception {
        String date_s = " 2019-12-03 18:10:00.0";
        SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
        Date date = dt.parse(date_s);

        long value = getElapsedDaysArrival(date);
        log.info("value : {}", value);
        return "hello";
    }

    private long getElapsedDaysArrival(Date arrivedAt) {
        long calDate = System.currentTimeMillis() - arrivedAt.getTime();
        long calDateDays = calDate / ONE_DAY;
        log.info("calDateDays : {}", calDateDays);
        return Math.abs(calDateDays);
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
