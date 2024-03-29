package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {
    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);

        // JDK 동적 프록시를 구현 클래스로 캐스팅 시도 시 실패
        Assertions.assertThrows(ClassCastException.class, () -> {
            MemberService memberServiceProxy = (MemberServiceImpl) proxyFactory.getProxy();
        });
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);

        // JDK 동적 프록시를 구현 클래스로 캐스팅 시도 시 성공
        MemberService memberServiceProxy = (MemberServiceImpl) proxyFactory.getProxy();
    }
}
