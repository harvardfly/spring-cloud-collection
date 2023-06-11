# 基于Spring boot + Spring Cloud + Mybatis + mybatis generator的项目

## 技术栈介绍
1. 目录划分：  
    domain - 放实体类（数据库映射字段）和dto（DTO代表服务层（service）需要接收的数据和返回的数据，而VO代表展示层(controller)需要显示的数据）  
    dao - 放dao方法的interface  
    service - 放service层逻辑  
    controller  
2. nacos作为服务发现注册和配置中心组件，安装并启动nacos服务后，访问localhost:8848能看到管理界面：  
    服务注册发现：pom配置nacos-discovery，application.yaml指定其server-addr，即可注册服务  
    配置中心：  
      a. pom配置nacos-config，bootstrap.yml 指定其server-addr，即可使用配置中心  
      b. 通过@Value("${}") 可以获取本地配置文件和配置中心的属性值  
      c. bootstrap配置文件是比application配置文件优先加载的，因为bootstrap是由spring父上下文加载，而application是由子上下文加载  
      d. 给类加上 @RefreshScope注解 表示能够自动更新加载配置中心配置

3. 负载均衡-Ribbon（nacos服务发现后根据服务名找到具体哪个实例需负载均衡器Ribbon，spring-boot3.0以上已经去除了Ribbon）；  
    a. 通过远程服务调用时Ribbon负载均衡请求到某个实例，这里可以结合 restTemplate和feign远程调用；  
    b. 在RestTemplate对象初始化方法上加@Bean和@LoadBalanced注解，restTemplate通过服务名即可调用其它服务；  
    c. 通过代码或者配置文件可指定负载均衡策略，如：轮询、随机、最小并发请求等：  
        代码：自定义 RibbonConfiguration类（实现繁琐，不推荐）
        配置文件：  
    ```yaml
    ribbon:
        NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
    ```

4. 远程调用HTTP客户端-Feign；
   a. feign其实是Ribbon和RestTemplate的进一步封装，通过定义FeignClient interface指定哪些接口远程调用；  
   b. 请求接口地址路由、方法等在FeignClient interface实现即可，上层service和controller只需调用FeignClient interface方法即可；

5. 服务容错-Sentinel（限流、降级、熔断等）：
    a. 通过 sentinel-dashboard 可以配置sentinel的限流规则；  
    b. RestTemplate、Feign都可以整合Sentinel，通过在@FeignClient注解指定fallbackFactory，当远程调用触发限流时可执行指定的fallbackFactory

6. API网关-SpringCloudGateway（gateway一般要单独起一个服务）:  
   a. SpringCloudGateway可以通过nacos服务注册和发现；  
   b. 通过在gateway服务配置 uri 可指定哪些服务可通过网关访问，如：  
    ```yaml
    routes:
        - id: header_route
          uri: lb://content-center
    ```
   c. gateway可配置鉴权，如JWT等
