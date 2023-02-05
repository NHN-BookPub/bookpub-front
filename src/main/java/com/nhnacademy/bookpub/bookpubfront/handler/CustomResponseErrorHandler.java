package com.nhnacademy.bookpub.bookpubfront.handler;

import com.nhnacademy.bookpub.bookpubfront.exception.*;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

/**
 * 백 서버로 보낸 요청의 응답 코드에 따라 예외를 발생시킵니다.
 *
 * @author : 여운석
 * @since : 1.0
 **/
public class CustomResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus status = response.getStatusCode();

        if (status.equals(HttpStatus.NOT_FOUND)) {
            throw new NotFoundException();
        } else if (response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
            throw new UnAuthorizedException();
        } else if (response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
            throw new BadRequestException();
        } else if (response.getStatusCode().is4xxClientError()) {
            throw new NotFoundException();
        } else if (response.getStatusCode().equals(HttpStatus.BAD_GATEWAY)) {
            throw new BadGatewayException();
        } else if (response.getStatusCode().is5xxServerError()) {
            throw new ServerErrorException();
        }
    }
}
