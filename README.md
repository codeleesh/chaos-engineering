# Chaos Engineering
실 서비스에 장애를 주입(Failure Injection)하여, 출시 전 테스트에서 드러나지 않은 아키텍처상의 문제를 직접 드러내는 것

## Chaos Engineering 원칙

- 정상 상태 행동에 관한 가설 구축

- 현실 문제 파악 시도하기

- 실제 프로덕션 환경에서 실험하기

- 자동화를 통한 지속적 실험

- 폭발 반경 최소화


## 장애 주입(Failure Injection)

포인트는 작게 시작해서 점진적 신뢰성 구축

- 애플리케이션 부하 테스트

- 호스트 서버 이슈 발생

- 데이터베이스 서버 셧다운

- 자원 공격(CPU, memory, ...)

- 네트워크 공격(dependencies, latency, ...)

- 데이터 센터 공격


## Chaos 오픈소스 라이브러리

### Chaos Monkey For Spring Boot (CM4SB)

- Netflix에서 만든 카오스 엔지니어링 라이브러리

- Spring Boot에서 쉽게 사용할 수 있도록 만든 라이브러리


### CM4SB 동작 방식

> 참고
>
> [Chaos Monkey for Spring Boot Reference Guide](https://codecentric.github.io/chaos-monkey-spring-boot/latest/)

![sbchaosmonkeyarchitecturepng](file://\\fileserver.score\share\조직-데이터혁신플랫폼그룹\개인폴더\이상호\99_기록\2022.02\2022.02.08_카오스엔지니어링\images\sb-chaos-monkey-architecture.png)

- 공격 대상 : @Controller, @Repository, @Service, @RestController, @Component

- Watcher : 감시자 (공격 대상 별로 Watcher가 존재)

- Assault : 공격 종류


### Properties

> 참고
>
> [Chaos Monkey properties](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#_properties)

| Property | Description | Values | Default |
| --- | --- | --- | --- |
| chaos.monkey.enabled | chaos.monkey 활성화 유무 | TRUE or FALSE | FALSE |
| chaos.monkey.assaults.level |     | 1-10000 | 1   |
| chaos.monkey.assaults.deterministic | 요청마다 공격(x 요청마다 공격) 또는 평균적으로 공격(x 요청 중 1개 공격) | TRUE or FALSE | FALSE |
| chaos.monkey.assaults.latencyRangeStart | 최소 지연 시간 | Integer.MIN_VALUE, Integer.MAX_VALUE | 1000 |
| chaos.monkey.assaults.latencyRangeEnd | 최대 지연 시간 | Integer.MIN_VALUE, Integer.MAX_VALUE | 3000 |
| chaos.monkey.assaults.latencyActive | latency 공격 활성 | TRUE or FALSE | FALSE |
| chaos.monkey.assaults.exceptionsActive | exceptions 공격 활성 | TRUE or FALSE | FALSE |
| chaos.monkey.assaults.exception | 사용자 정의 RuntimeException 또는 기본 RuntimeException | de.codecentric.spring.boot.chaos. monkey.configuration.AssaultException | java.lang.RuntimeException("Chaos Monkey - RuntimeException"") |
| chaos.monkey.assaults.killApplicationActive | AppKiller 공격 활성 | TRUE or FALSE | FALSE |
| chaos.monkey.assaults.killApplication.cron.expression | Cron 표현식은 일정에 따라 AppKiller assaults 활성화 설정 ex) `*/1 * * * * ?` | Any valid cron expression (or OFF) | OFF |
| chaos.monkey.watcher.controller | Controller watcher 활성화 유무 | TRUE or FALSE | FALSE |
| chaos.monkey.watcher.restController | RestController watcher 활성화 유무 | TRUE or FALSE | FALSE |
| chaos.monkey.watcher.service | Service watcher 활성화 유무 | TRUE or FALSE | FALSE |
| chaos.monkey.watcher.repository | Repository watcher 활성화 유무 | TRUE or FALSE | FALSE |
| chaos.monkey.watcher.component | Component watcher 활성화 유무 | TRUE or FALSE | FALSE |
| chaos.monkey.watcher.beans | 감시할 대상자 빈 | List of bean names | Empty list |
| chaos.monkey.assaults.memoryActive | Memory assault 활성화 유무 | TRUE or FALSE | FALSE |
| chaos.monkey.assaults.memoryMillisecondsHoldFilledMemory | 설정된 메모리값에 도달하였을때 공격 시간 | min=1500, max=Integer.MAX_VALUE | 90000 |
| chaos.monkey.assaults.memoryMillisecondsWaitNextIncrease | 메모리 사용량 증가되는 시간 | min=100, max=30000 | 1000 |
| chaos.monkey.assaults.memoryFillIncrementFraction | Fraction of one individual memory increase iteration. `1.0` equals 100 %. | min=0.01, max=1.0 | 0.15 |
| chaos.monkey.assaults.memoryFillTargetFraction | Final fraction of used memory by assault. `0.95` equals 95 %. | min=0.01, max=0.95 | 0.25 |
| chaos.monkey.assaults.memory.cron.expression | Cron 표현식은 일정에 따라 memory assaults 활성화 설정 ex) `*/1 * * * * ?` | Any valid cron expression (or OFF) | OFF |
| chaos.monkey.assaults.cpuActive | CPU assault 활성화 유무 | TRUE or FALSE | FALSE |
| chaos.monkey.assaults.cpuMillisecondsHoldLoad | Duration to assault cpu when requested load is reached in ms. | min=1500, max=Integer.MAX_VALUE | 90000 |
| chaos.monkey.assaults.cpuLoadTargetFraction | Final fraction of used cpu by assault. `0.95` equals 95 %. | min=0.1, max=1.0 | 0.9 |
| chaos.monkey.assaults.cpu.cron.expression | Cron 표현식은 일정에 따라 cpu assaults 활성화 설정 ex) `*/1 * * * * ?` | Any valid cron expression (or OFF) | OFF |
| chaos.monkey.assaults.runtime.scope.assault.cron.expression | Cron 표현식은 일정에 따라 cpu runtime assaults 활성화 설정 ex) `*/1 * * * * ?` | Any valid cron expression (or OFF) | OFF |
| chaos.monkey.assaults.watchedCustomServices | 감시 대상자 직접 지정(패키지/클래스/메소드) | List of fully qualified packages, class and/or method names | Empty list |

### Watcher 종류

감시자는 다음을 제공합니다.

- Annotation Watchers

- Actuator Watchers

- Outgoing Request Watchers

- Alternative Bean Watcher


#### Annotation Watchers

애플리케이션의 해당 빈을 찾는 관찰자 역할

- @Controller

- @RestController

- @Service

- @Repository

- @Component


#### Actuator Watchers

Chaos Monkey도 Spring Boot의 auto-configures인 `HealthIndicators`를 볼 수 있습니다.

> 참고
>
> [Production-ready Features (spring.io)](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.health.auto-configured-health-indicators)

예외 및 대기 시간으로 상태 확인을 공격합니다. 예외는 HealthIndicators가 DOWN 상태로 들어가고 지연 공격이 응답을 연기하도록 합니다.

#### Outgoing Request Watchers

감시자는 주어진 공격 구성을 기반으로 Spring 컨텍스트의 모든 RestTemplate 및 WebClient 빈에 대한 공격을 주입합니다.

다음의 감시자 대상을 제공합니다.

- RestTemplate

- WebClient


> 참고
>
> new RestTemplate() 및 WebClient.create()를 통해 빈으로 생성되지 않은 RestTemplate 및 WebClient는 대상에서 제외됩니다.
>
> 이 감시자는 AOP를 사용하지 않습니다. 대신 각각의 커스터마이저를 주입합니다.

#### Alternative Bean Watcher

Chaos Monkey는 이름으로 애플리케이션의 모든 Bean을 볼 수 있습니다.

> 참고
>
> chaos monkey 프로필이 활성화되어 있어야 하며 빈이 생성될 때 빈 이름을 구성해야 합니다. Bean이 생성된 후 구성에 Bean 이름을 추가하면 이를 감시할 수 없습니다.

### Customize Watcher

watchCustomServices 속성을 사용하여 모든 감시자의 동작을 사용자 정의하고 공격해야 하는 클래스와 공개 메서드를 결정할 수 있습니다.

공개 메서드, 클래스 또는 패키지를 참조하도록 속성을 설정할 수 있습니다.

WatchedCustomServices가 설정되어 있지 않으면 활성화된 감시자가 인식하는 모든 클래스와 공용 메서드가 공격을 받습니다.

애플리케이션 속성에서 목록을 유지하거나 Spring Boot Actuator Endpoint를 사용하여 런타임에 조정할 수 있습니다.

`Chaos Monkey Spring Boot Actuator Endpoint (/actuator/chaosmonkey/assaults)`

```json
{
  "level": 3,
  "latencyActive": true,
  "latencyRangeStart": 1000,
  "latencyRangeEnd": 3000,
  "watchedCustomServices": [
    "com.example.chaos.monkey.chaosdemo.controller.HelloController.sayHello",
    "com.example.chaos.monkey.chaosdemo.service.HelloService"
  ]
}
```

`application.yml`

```yml
chaos:
  monkey:
    enabled: true
    watcher:
      controller: true
    assaults:
      level: 1
      latency-active: true
      watched-custom-services:
        - com.example.chaos.monkey.chaosdemo.controller.HelloController.sayHello
        - com.example.chaos.monkey.chaosdemo.service.HelloService
```

> 참고
>
> 이 목록은 감시자가 발견한 공격 클래스만 제한합니다. 이 목록이 설정되지 않았을 때 클래스나 메소드가 공격받지 않았다면 목록에 추가되어도 공격을 받지 않습니다.

### Assaults 종류

사용자의 구성에 따라 공격을 사용하며 다음 항목을 제공합니다.

- Request Assaults

- Runtime Assaults

- Chaos Monkey Assault Scheduler


#### Request Assaults

다음의 path를 통해서 공격 대상 여부를 확인할 수 있습니다.

`/chaosmonkey/watchers - Response 200 OK`

```json
{
  "controller": false,
  "restController": true,
  "service": false,
  "repository": false,
  "component": false,
  "restTemplate": false,
  "webClient": false,
  "actuatorHealth": false
}
```

다음 요청 공격의 항목은 다음과 같습니다.

- Latency Assaults

- Exception Assaults


##### Latency Assaults

활성화된 경우 요청에 지연 시간이 추가됩니다.

지연 시간에 대한 발생 확률도 제어 가능합니다.

##### Exception Assaults

예외 공격, 메소드를 실행할 때 예외 발생 여부를 런타임 시점에 결정할 수 있습니다.

거의 모든 종류의 RuntimeException을 던질 수 있습니다.

Actuator Endpoint를 통해 런타임 시 필요한 예외를 구성할 수 있습니다.

#### Runtime Assaults

전체 애플리케이션의 대한 공격 가능합니다.

공격의 대한 트리거를 사용하기 위해서는 Chaos Monkey Assault Scheduler 또는 Chaos Monkey의 엔드포인트를 활용해야 합니다.

> 참고
>
> [Assault Scheduling](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#_actuator_watchers)
>
> [Chaos Monkey Endpoint](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#assaultsattack)

다음 항목을 제공합니다.

- Appkiller Assaults

- Memory Assaults

- Cpu Assaults


##### Appkiller Assaults

특정 메소드 실행시 프로그램 종료 공격을 합니다.

##### Memory Assaults

메모리 공격은 Java 가상 머신의 메모리를 공격합니다.

- 메모리 공격은 사용 중인 Java 버전에 따라 크게 달라집니다. 우리는 각 자바 버전의 기본 가비지 수집기로 테스트 중입니다. Java 8에서 채우기 속도는 슬라이스당 256MB로 제한됩니다

> 슬라이스?

##### Cpu Assaults

CPU 공격은 Java 가상 머신의 CPU를 공격합니다.

#### Chaos Monkey Assault Scheduler

예약 공격을 통하여 Runtime Assaults(Memory, CPU, AppKiller) 가능합니다.

> 참고
>
> [Configuration](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#chaos_monkey_assault_scheduler)

## 설정

### 개발 환경

- IDE : Intelij 2021.2.2

- JDK : 1.8

- Spring Boot : 2.5.8


### Gradle

```
dependencies {
    ...
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
    implementation 'de.codecentric:chaos-monkey-spring-boot:2.5.4'
    ...
}
```

### application.yml

```
management:
  endpoint:
    chaosmonkey:
      enabled: true

  endpoints:
    web:
      exposure:
        # include specific endpoints
        include:
          - health
          - info
          - chaosmonkey
```

### application 실행

```
     _____ _                       __  __             _
    / ____| |                     |  \/  |           | |
   | |    | |__   __ _  ___  ___  | \  / | ___  _ __ | | _____ _   _
   | |    | '_ \ / _` |/ _ \/ __| | |\/| |/ _ \| '_ \| |/ / _ | | | |
   | |____| | | | (_| | (_) \__ \ | |  | | (_) | | | |   |  __| |_| |
    \_____|_| |_|\__,_|\___/|___/ |_|  |_|\___/|_| |_|_|\_\___|\__, |
                                                                __/ |
    _ready to do evil!                                         |___/

:: Chaos Monkey for Spring Boot                                    ::
```

### HTTP Endpoint

로컬호스트 기준

- http://127.0.0.1:8080/actuator


| ID  | Description | Methods |
| --- | --- | --- |
| [/chaosmonkey](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#chaosmonkey) | Running Chaos Monkey configuration | GET |
| [/chaosmonkey/status](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#chaosmonkeystatus) | Is Chaos Monkey enabled or disabled? | GET |
| [/chaosmonkey/enable](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#chaosmonkeyenable) | Enable Chaos Monkey | POST |
| [/chaosmonkey/disable](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#chaosmonkeydisable) | Disable Chaos Monkey | POST |
| [/chaosmonkey/watchers](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#watchers) | Running Watchers configuration. | GET |
| [/chaosmonkey/watchers](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#watcherspost) | Change Watchers Configuration | POST |
| [/chaosmonkey/assaults](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#assaultsget) | Running Assaults configuration | GET |
| [/chaosmonkey/assaults](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#assaultspost) | Change Assaults configuration | POST |
| [/chaosmonkey/assaults/runtime/attack](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#assaultspost) | Execute configured runtime Assault | POST |

참고

[Chaos Monkey HTTP_Endpoint](https://codecentric.github.io/chaos-monkey-spring-boot/latest/#_http_endpoint)

### 실습

#### Latency Assaults

##### 설정을 위한 호출

- chaos-monkey 활성화

- watchers 활성화

- assaults 활성화 및 설정값


##### 소스

`@RestController` 와 `@Service`를 간단하게 생성

`HelloController`

```java
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService service;

    @GetMapping("index")
    public String hello() {
        return service.hello();
    }
}
```

`HelloService`

```java
@Service
public class HelloService {
    public String hello() {
        return "Hello";
    }
}
```

#### Exception Assaults

#### Memory Assaults