package api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BaseTest {

    static {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.filters(new RequestLoggingFilter(System.out),new ResponseLoggingFilter(System.out));
        RestAssured.baseURI="http://9b142cdd34e.vps.myjino.ru:49268";
    }

}
