package com.hyewon.springplayground2.xss.controller;

import com.hyewon.springplayground2.xss.dto.XssDummyDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xss")
public class XssDummyController {
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public XssDummyDto xssTest1(XssDummyDto xssDummyDto) {
        System.out.println("xssDummyDto = " + xssDummyDto.getData());
        return xssDummyDto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public XssDummyDto xssTest2(@RequestBody XssDummyDto xssDummyDto) {
        System.out.println("xssDummyDto JSON = " + xssDummyDto.getData());
        return xssDummyDto;
    }

}
