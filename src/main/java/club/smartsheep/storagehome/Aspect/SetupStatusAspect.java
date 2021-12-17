package club.smartsheep.storagehome.Aspect;

import club.smartsheep.storagehome.DAO.Entity.ConfigEntity;
import club.smartsheep.storagehome.DAO.Mappers.ConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class SetupStatusAspect {

    @Autowired
    ConfigMapper configMapper;

    @Around("execution(public * club.smartsheep.storagehome.Controller.*.*(..))")
    public Object CheckStatus(ProceedingJoinPoint point) throws Throwable {
        String uri = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();

        ConfigEntity setupConfig = configMapper.selectByName("setup.done");

        if (!uri.startsWith("/setup") && (setupConfig == null || !setupConfig.getValue().equalsIgnoreCase("TRUE"))) {
            return "redirect:/setup";
        }

        return point.proceed();
    }
}