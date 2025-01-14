package com.hyewon.springplayground2.xss.controller;

import com.hyewon.springplayground2.xss.dto.XssDummyDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/xss")
public class XssDummyController {
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String xssTest1(@RequestParam("param") String param) {
        String filteredParam = HtmlUtils.htmlEscape(param);
        System.out.println("param = " + param);
        return filteredParam;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public XssDummyDto xssTest2(@RequestBody XssDummyDto xssDummyDto) {
        System.out.println("xssDummyDto JSON = " + xssDummyDto.getData());
        return xssDummyDto;
    }

}
