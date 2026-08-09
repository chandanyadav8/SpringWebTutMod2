package com.example.SpringWebTutMod2.advices;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // Get the generic type from ResponseEntity
        Class<?> parameterType = returnType.getParameterType();
        
        // Exclude String types from being wrapped
        if (parameterType == String.class) {
            return false;
        }
        
        // Check if it's ResponseEntity<String>
        String genericType = returnType.getGenericParameterType().toString();
        if (genericType.contains("String")) {
            return false;
        }
        
        return true;
    }

    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ApiResponses<?>)
        {
            return body;
        }
        return new ApiResponses<>(body);
    }
}
