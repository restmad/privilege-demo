package com.example.privilegedemo.aop;

import com.example.privilegedemo.data.AuthModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Slf4j
@Component
public class PrivAOP {
    private static Map<String, List<String>> userPrivMap = new ConcurrentHashMap<>();
    private static Map<String, List<String>> userOrgIdListMap = new ConcurrentHashMap<>();

    public PrivAOP() {
    }

    @PostConstruct
    public void init() {
        userPrivMap.put("ruimaj", new ArrayList<>(Arrays.asList("a1", "a3")));
        userOrgIdListMap.put("ruimaj", new ArrayList<>(Arrays.asList("ou1", "ou2", "ou3", "ou4")));
    }

    public static List<String> getUserOUList(String userId) {
        return userOrgIdListMap.get(userId);
    }

//    @Pointcut(value = "@annotation(com.example.privilegedemo.data.AuthModel)", argNames = "proceedingJoinPoint, authModel")
//    public void pointCut(ProceedingJoinPoint proceedingJoinPoint, AuthModel authModel) {}

    @Around(value = "@annotation(authModel)", argNames = "proceedingJoinPoint, authModel")
    public Object before(ProceedingJoinPoint proceedingJoinPoint, AuthModel authModel) throws Throwable {
        log.info("before----> auth check");

        log.info("auth model: {}", authModel);

        String userId = "ruimaj";

        if (!userPrivMap.containsKey(userId)) {
            throw new RuntimeException("no priv");
        }

        List<String> resourceIdList = userPrivMap.get(userId);
        String[] codeList = authModel.codeList();
        for (String code : codeList) {
            if (resourceIdList.contains(code)) {
                log.info("proceed");
                return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            }
        }
        throw new RuntimeException("no priv");
    }

}
