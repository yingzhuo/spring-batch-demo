ifeq ($(OS), Windows_NT)
    MAKEFILE_PATH := $(dir $(abspath $(lastword $(MAKEFILE_LIST))))
    GRADLEW := $(MAKEFILE_PATH)/gradlew.bat
else
    MAKEFILE_PATH := $(shell dirname $(realpath $(firstword $(MAKEFILE_LIST))))
		GRADLEW := $(MAKEFILE_PATH)/gradlew
endif

.DEFAULT_GOAL=usage

.PHONY: usage \
clean purge rebuild-build-logic \
update-dependencies compile build rebuild check test \
build-docker-image remove-docker-image \
update-gradle-wrapper \
update-license-header \
push-to-vcs reset-and-push-to-vcs \
ls-projects ls-tasks \
deploy

usage:
	@echo '=============================================================================================================='
	@echo 'usage (default)                : 显示本菜单'
	@echo 'clean                          : 清理本项目构建产物'
	@echo 'purge                          : 清理本项目构建产物以及构建逻辑'
	@echo 'rebuild-build-logic            : 构建构建逻辑'
	@echo 'update-dependencies            : 更新依赖'
	@echo 'compile                        : 编译项目'
	@echo 'build                          : 构建项目'
	@echo 'rebuild                        : 重新构建项目'
	@echo 'build-docker-image             : 构建docker镜像'
	@echo 'remove-docker-image            : 删除docker镜像'
	@echo 'test                           : 执行单元测试'
	@echo 'check                          : 检查代码风格'
	@echo 'update-gradle-wrapper          : 设置gradle-wrapper'
	@echo 'stop-gradle-daemon             : 停止gradle-daemon'
	@echo 'update-license-header          : 添加代码的许可证头'
	@echo 'push-to-vcs                    : 提交文件'
	@echo 'ls-projects                    : 展示项目信息'
	@echo 'ls-tasks                       : 展示任务信息'
	@echo '=============================================================================================================='

clean:
	@$(GRADLEW) 'clean' -q

purge:
	@$(GRADLEW) 'clean' ':buildSrc:clean' -q

rebuild-build-logic:
	@$(GRADLEW) ':buildSrc:clean' -q
	@$(GRADLEW) ':buildSrc:jar' -q

update-dependencies:
	@$(GRADLEW) -U

compile:
	@$(GRADLEW) 'classes'

build:
	@$(GRADLEW) 'build' -x 'test' -x 'check'

rebuild: clean build

build-docker-image:
	@$(GRADLEW) 'buildDockerImage' -x 'test' -x 'check'

remove-docker-image:
	@$(GRADLEW) 'removeDockerImage'

check:
	@$(GRADLEW) 'check'

test:
	@$(GRADLEW) 'test'

update-gradle-wrapper:
	@$(GRADLEW) ':wrapper' -q

stop-gradle-daemon:
	@$(GRADLEW) --stop -q > /dev/null

update-license-header:
	@$(GRADLEW) "applyLicenses" -q

push-to-vcs: update-license-header
	@$(GRADLEW) 'pushToVcs' -q

ls-projects:
	@$(GRADLEW) ':projects' -q

ls-tasks:
	@$(GRADLEW) ':tasks' -q

deploy:
	@$(GRADLEW) ':projects-app:core:deploy' -x 'test' -x 'check' -q
