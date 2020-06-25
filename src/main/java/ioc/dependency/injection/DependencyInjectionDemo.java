package ioc.dependency.injection;

import ioc.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DependencyInjectionDemo {

    public static void main(String[] args) {

        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义 Bean
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

        // 依赖来源二：依赖注入（內建依赖）
        System.out.println(userRepository.getBeanFactory());

        // ApplicationContext 就是 BeanFactory 但是这个表达式为什么不能成立
        // 因为 ApplicationContext 和 BeanFactory 既是继承关系，内部也存在组合，ApplicationContext 是 代理了 BeanFactory
        // 所以需要 ApplicationContext#getBeanFactory 获取到的 BeanFactory 表达式才成立
        // false
        System.out.println(userRepository.getBeanFactory() == applicationContext);
        // true
        System.out.println(userRepository.getBeanFactory() == ((ClassPathXmlApplicationContext) applicationContext).getBeanFactory());

        ((ClassPathXmlApplicationContext) applicationContext).getBeanFactory();

        // 依赖来源三：容器內建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean：" + environment);
    }
}
