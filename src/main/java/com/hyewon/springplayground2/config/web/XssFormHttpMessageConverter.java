package com.hyewon.springplayground2.config.web;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.HtmlUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XssFormHttpMessageConverter extends FormHttpMessageConverter {
    @Override
    public MultiValueMap<String, String> read(Class<? extends MultiValueMap<String, ?>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        MultiValueMap<String, String> result = super.read(clazz, inputMessage);
        LinkedMultiValueMap<String, String> decodedMap = new LinkedMultiValueMap<>();
        for (Map.Entry<String, List<String>> entry : result.entrySet()) {
            List<String> decodedValues = new ArrayList<>();
            for (String value : entry.getValue()) {
                decodedValues.add(HtmlUtils.htmlUnescape(value));
            }
            decodedMap.put(entry.getKey(), decodedValues);
        }
        return decodedMap;
    }

}