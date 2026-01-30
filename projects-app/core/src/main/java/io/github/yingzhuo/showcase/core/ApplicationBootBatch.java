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

package io.github.yingzhuo.showcase.core;

import io.github.yingzhuo.showcase.core.entity.Contacts;
import io.github.yingzhuo.showcase.core.entity.component.ContactsItemProcessor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.EnableJdbcJobRepository;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.FlatFileItemWriter;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.infrastructure.item.file.transform.PassThroughLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
@EnableJdbcJobRepository(
	dataSourceRef = "dataSource",
	transactionManagerRef = "transactionManager"
)
public class ApplicationBootBatch {

	// spring.batch.job.enabled=false  禁止job随spring自动启动
	// job 应该通过定时调度，事件，前端按钮等启动

	@Bean
	public Job job(JobRepository repository) {
		return new JobBuilder("contactsJob", repository)
			.incrementer(new RunIdIncrementer())
			.start(step(repository))
			.build();
	}

	@Bean
	public Step step(JobRepository repository) {
		return new StepBuilder("contactsStep", repository)
			.<Contacts, String>chunk(100)
			.reader(reader())
			.processor(processor())
			.writer(writer())
			.build();
	}

	@Bean
	public FlatFileItemReader<Contacts> reader() {
		return new FlatFileItemReaderBuilder<Contacts>()
			.name("contactsItemReader")
			.resource(new ClassPathResource("data/contacts.csv"))  // CSV文件路径
			.delimited()
			.names("name", "phoneNumber", "email")  // 对应CSV文件的列
			.targetType(Contacts.class)
			.build();
	}

	@Bean
	public ContactsItemProcessor processor() {
		return new ContactsItemProcessor();
	}

	@Bean
	public FlatFileItemWriter<String> writer() {
		return new FlatFileItemWriterBuilder<String>()
			.name("contactsItemWriter")
			.resource(new FileSystemResource("/tmp/contacts.txt"))
			.lineAggregator(new PassThroughLineAggregator<>())
			.build();
	}

}
