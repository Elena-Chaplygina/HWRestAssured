package api.service;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import api.schema.Schema;

public class Service {
    public static RequestSpecification prepareGet(){
        return RestAssured.given().accept(ContentType.JSON);
    }

    public static RequestSpecification preparePost(Schema body){
        return RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body);
    }

    public static ResponseSpecification responseOK200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }


    public static ResponseSpecification responseCreated201(){
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }

    public static ResponseSpecification responseUnauthorized401(){
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .build();
    }

    public static ResponseSpecification responseNotFound404(){
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification=request;
        RestAssured.responseSpecification=response;
    }

    public static void installSpecification(RequestSpecification requestSpec){
        RestAssured.requestSpecification =requestSpec;
    }
    public static void installSpecification(ResponseSpecification responseSpec){
        RestAssured.responseSpecification =responseSpec;
    }
}
