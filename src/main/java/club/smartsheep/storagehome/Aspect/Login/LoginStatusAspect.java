package club.smartsheep.storagehome.Aspect.Login;

import club.smartsheep.storagehome.DAO.Entity.ConfigEntity;
import club.smartsheep.storagehome.DAO.Mappers.ConfigMapper;
import club.smartsheep.storagehome.Services.InitDatabaseService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoginStatusAspect {

    @Autowired
    ConfigMapper configMapper;

    @Autowired
    InitDatabaseService initDatabaseService;

    @Around("@annotation(club.smartsheep.storagehome.Aspect.Login.LoginRequire))")
    public Object CheckStatus(ProceedingJoinPoint point) throws Throwable {
        ConfigEntity setupConfig;
        try {
            setupConfig = configMapper.selectByName("setup.done");
        } catch (DataAccessException e) {
            return "redirect:/error/database";
        }

        if (((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("loginToken") == null) {
            if(setupConfig != null || setupConfig.getValue().equalsIgnoreCase("TRUE")) {
                return "redirect:/account/login?message=Please%20login%20first";

            }
        }

        return point.proceed();
    }
}
