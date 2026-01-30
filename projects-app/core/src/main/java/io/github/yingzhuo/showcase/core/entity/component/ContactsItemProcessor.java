/*
 * Copyright 2022-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.yingzhuo.showcase.core.entity.component;

import io.github.yingzhuo.showcase.core.entity.Contacts;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.ItemProcessor;

@Slf4j
public class ContactsItemProcessor implements ItemProcessor<Contacts, String> {

	private JobParameters jobParameters;

	@BeforeStep
	public void beforeStep(final StepExecution stepExecution) {
		// 在Step开始前，保存JobParameters到成员变量
		this.jobParameters = stepExecution.getJobParameters();
	}

	@Override
	@Nullable
	public String process(Contacts item) {
		item.setId(String.valueOf(jobParameters.getLong("time")));
		log.info("process contacts: {}, {}", item, jobParameters.getLong("time"));
		return item.toString();
	}

}
