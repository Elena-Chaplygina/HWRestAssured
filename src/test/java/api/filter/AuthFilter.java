package api.filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AuthFilter implements Filter {

    private String accessToken;

    public AuthFilter(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response filter(FilterableRequestSpecification filterableRequestSpecification, FilterableResponseSpecification filterableResponseSpecification, FilterContext filterContext) {
        filterableRequestSpecification.header(new Header("Authorization", "Bearer " + accessToken));
        return filterContext.next(filterableRequestSpecification, filterableResponseSpecification);
    }
}
