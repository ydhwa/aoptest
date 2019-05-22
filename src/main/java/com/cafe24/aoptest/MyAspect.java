package com.cafe24.aoptest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Before("execution(ProductVo com.cafe24.aoptest.ProductService.find(String))")
	public void beforeAdvice() {
		System.out.println("---before advice---");
	}

	// *..* -> 모든 패키지
	@After("execution(* *..*.ProductService.*(..))")
	public void afterAdvice() {
		System.out.println("---after advice---");
	}

	@AfterReturning("execution(* *..*.ProductService.*(..))")
	public void afterReturningAdvice() {
		System.out.println("---afterReturning advice---");
	}
	
	// exception 시 발생
	@AfterThrowing(value = "execution(* *..*.ProductService.*(..))", throwing="ex")
	public void afterThrowingAdvice(Throwable ex) {
		System.out.println("---afterThrowing advice---" + ex);
	}
	
	// round는 다른 걸 실행하는 코드가 있어야 한다. 
	@Around(value = "execution(* *..*.ProductService.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		/* before advice 기술 */
		System.out.println("---around(before) advice---");
		
		// PointCut 되는 메소드 호출
//		Object[] parameters = {"Camera"};
//		Object result = pjp.proceed(parameters);
		Object result = pjp.proceed();	// advice의 find를 호출함
		
		/* after advice 기술 */
		System.out.println("---around(after) advice---");
		
//		return null;
		return result;
	}
}
