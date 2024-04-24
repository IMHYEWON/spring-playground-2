package com.hyewon.springplayground2.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class XssDummyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("XSS 필터링 되기 전 테스트")
    @Disabled
    class XssNotFiltered {
        @Test
        @DisplayName("[성공] 필터링이 되지 않고 요청값 그대로 리턴되어야 한다.")
        void xssTest1() throws Exception {
            String data = "<script>alert('XSS');</script>";
            mockMvc.perform(MockMvcRequestBuilders.post("/xss")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("data", data))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{\"data\":\"<script>alert('XSS');</script>\"}"));
        }

        @Test
        @DisplayName("[실패] 필터링이 되면 안됨.")
        void xssTest2() throws Exception {
            String data = "<script>alert('XSS');</script>";
            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/xss")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("data", data))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();

            String content = response.getResponse().getContentAsString();
            assert !content.contains("&lt;script&gt;alert('XSS');&lt;/script&gt;");
        }
    }


    @Nested
    @DisplayName("XSS 필터링 적용 후 테스트")
    class XssFiltered {
        @Test
        @DisplayName("[성공] 변환된 값으로 리턴.")
        void xssTest1() throws Exception {
            String data = "<script>alert('XSS');</script>";
            mockMvc.perform(MockMvcRequestBuilders.post("/xss")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("data", data))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("{\"data\":\"&lt;script&gt;alert(&#39;XSS&#39;);&lt;/script&gt;\"}"));
        }

        @Test
        @DisplayName("[실패] 값이 그대로 리턴되면 안됨.")
        void xssTest2() throws Exception {
            String data = "<script>alert('XSS');</script>";
            MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/xss")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("data", data))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();

            String content = response.getResponse().getContentAsString();
            assert !content.contains("<script>");
        }
    }
}