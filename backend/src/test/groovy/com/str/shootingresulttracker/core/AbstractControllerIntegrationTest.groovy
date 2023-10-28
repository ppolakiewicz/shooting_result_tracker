package com.str.shootingresulttracker.core

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
abstract class AbstractControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected MockMvc mockMvc

    @Autowired
    protected ObjectMapper objectMapper
}
