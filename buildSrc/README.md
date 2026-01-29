## 构建逻辑

### 内容提要

* [buildlogic.root-project-conventions](src/main/groovy/buildlogic.root-project-conventions.gradle) - 根项目专属构建逻辑
* [buildlogic.java-conventions](src/main/groovy/buildlogic.java-conventions.gradle)- Java语言程序构建规约
* [buildlogic.spring-boot-conventions](src/main/groovy/buildlogic.spring-boot-conventions.gradle) - spring-boot应用程序构建逻辑
* [buildlogic.docker-conventions](src/main/groovy/buildlogic.docker-conventions.gradle) - docker相关构建逻辑
* [buildlogic.distribution-conventions](src/main/groovy/buildlogic.distribution-conventions.gradle) - 发布打包相关构建逻辑
* [buildlogic.check-conventions](src/main/groovy/buildlogic.check-conventions.gradle) - 代码风格检查相关构建逻辑
* [buildlogic.license-conventions](src/main/groovy/buildlogic.license-conventions.gradle) - 许可证相关构建逻辑
* [buildlogic.ssh-conventions](src/main/groovy/buildlogic.ssh-conventions.gradle) - SSH远程部署相关构建逻辑

### Gradle第三方插件文档

| 插件                                  | 文档                                                                                  |
|-------------------------------------|-------------------------------------------------------------------------------------|
| org.springframework.boot            | [spring.io](https://docs.spring.io/spring-boot/gradle-plugin/index.html)            |
| io.spring.dependency-management     | [github.com](https://github.com/spring-gradle-plugins/dependency-management-plugin) |
| com.gorylenko.gradle-git-properties | [github.com](https://github.com/n0mer/gradle-git-properties)                        |
| com.github.hierynomus.license       | [github.com](https://github.com/hierynomus/license-gradle-plugin)                   |
| org.hidetake.ssh                    | [github.com](https://github.com/int128/gradle-ssh-plugin)                           |
