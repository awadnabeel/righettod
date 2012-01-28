package be.hikage.aop;

import be.hikage.exeption.DirectException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Aspect;

import javax.faces.application.NavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @Autor : Hikage
 */
@Aspect
public class ExceptionHandler {
    private String errorOutcome = "exceptionPage";
    public void setErrorOutcome(String errorOutcome) {
        this.errorOutcome = errorOutcome;
    }

    @Pointcut("execution(* be.hikage.managed..* (..))")
    public void publicMethodsInManagedBean() {
    }

    @Around("be.hikage.aop.ExceptionHandler.publicMethodsInManagedBean() ")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {

        try {
            Object retVal = pjp.proceed();
            return retVal;
        } catch (Throwable throwable) {
            if (throwable instanceof DirectException) {
                throw throwable;
            } else {
                // Ajout de l'exception dans les attributs de la requête
                FacesContext context = FacesContext.getCurrentInstance();
                NavigationHandler navigation = context.getApplication().getNavigationHandler();
                context.getExternalContext().getRequestMap().put("exception", throwable);
                // Redirection vers la page des erreurs
                navigation.handleNavigation(context, "", errorOutcome);


            }
        }

        return null;
    }
}
