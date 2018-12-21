package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestComponent
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [SpringBootJunit5IntegrationTest::class])
@ComponentScan
class SpringBootJunit5IntegrationTest {

    @Autowired lateinit var foo: Foo

    @Test fun `Can Autowire components with JUnit Jupiter`() {
        assertThat(foo).isNotNull
    }
}


@TestComponent
class Foo
