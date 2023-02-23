package com.nhnacademy.bookpub.bookpubfront.handler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.bookpub.bookpubfront.exception.NotFoundException;
import com.nhnacademy.bookpub.bookpubfront.exception.NotLoginException;
import com.nhnacademy.bookpub.bookpubfront.exception.ServerErrorException;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.*;
import com.nhnacademy.bookpub.bookpubfront.handler.exception.gotoexception.*;
import com.nhnacademy.bookpub.bookpubfront.handler.response.ErrorResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResourceAccessException;

/**
 * 백 서버로 보낸 요청의 응답 코드에 따라 예외를 발생시킵니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
@Component
@Slf4j
public class CustomResponseErrorHandler extends DefaultResponseErrorHandler {
    private final ObjectMapper mapper;

    public CustomResponseErrorHandler() {
        mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.registerModule(new JavaTimeModule());
    }


    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus status = response.getStatusCode();

        if (status.equals(HttpStatus.BAD_REQUEST)) {
            try {
                InputStream body = response.getBody();
                String jsonBody = StreamUtils.copyToString(body, StandardCharsets.UTF_8);
                ErrorResponse error = mapper.readValue(jsonBody, ErrorResponse.class);

                goToPage(error.getCode());
            } catch (ResourceAccessException e) {
                log.warn(e.getMessage());
                throw new BadRequestException();
            }

            throw new BadRequestException();
        } else if (status.equals(HttpStatus.UNAUTHORIZED)) {
            throw new UnAuthorizedException();
        } else if (status.equals(HttpStatus.NOT_FOUND)) {
            throw new NotFoundException();
        } else if (status.equals(HttpStatus.FORBIDDEN)) {
            throw new NotLoginException();
        } else if (status.is4xxClientError()) {
            throw new NotFoundException();
        } else if (status.equals(HttpStatus.BAD_GATEWAY)) {
            throw new BadGatewayException();
        } else if (status.is5xxServerError()) {
            throw new ServerErrorException();
        }
    }

    private void goToPage(String code) {
        if (code.equals("MAIN")) {
            throw new GoToMainException();
        } else if (code.equals("CATEGORY")) {
            throw new GoToAdminCategoryException();
        } else if (code.equals("AUTHORITY")) {
            throw new GoToMainException();
        } else if (code.equals("ADMIN")) {
            throw new GoToAdminException();
        } else if (code.equals("TAG")) {
            throw new GoToAdminTagException();
        } else if (code.equals("TIER")) {
            throw new GoToAdminTierException();
        } else if (code.equals("ORDER")) {
            throw new GoToAdminOrderException();
        }
    }
}
