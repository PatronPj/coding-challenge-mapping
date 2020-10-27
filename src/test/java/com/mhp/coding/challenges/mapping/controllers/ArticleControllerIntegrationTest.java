package com.mhp.coding.challenges.mapping.controllers;

import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import io.restassured.RestAssured;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerIntegrationTest {

    @LocalServerPort
    private int port = 0;

    private static final String URI = "/article";

    @PostConstruct
    public void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    public void testGetArticleById_ValidateHttpResponseStatusCode_ShouldReturn200() {
        final Long id = 1001L;

        given()
                .when()
                .pathParam("id", id)
                .get(URI + "/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testGetArticleById_ValidateHttpResponseStatusCode_ShouldReturn404() {
        final Long id = 100001L;

        given()
                .when()
                .pathParam("id", id)
                .get(URI + "/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testGetArticleById_ValidateHttpResponseStatusCode_ShouldReturn400() {
        given()
                .when()
                .pathParam("id", 1.1)
                .get(URI + "/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testGetArticleById_ValidateResultIncludesCorrectProperties() {
        final Long id = 1001L;
        given()
                .when()
                .pathParam("id", id)
                .get(URI + "/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("id", is(1001))
                .body("description", is("Article Description 1001"))
                .body("author", is("Max Mustermann"));
    }

    @Test
    public void testGetAllArticles_ValidateResultIncludesAllCorrectIds() {
        List<ArticleDto> result =
                given()
                        .when()
                        .get(URI)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", ArticleDto.class);

        List<Long> ids = result.stream().map(ArticleDto::getId).collect(Collectors.toList());
        assertThat(ids).containsExactlyInAnyOrder(1001L, 2002L, 3003L, 4004L, 5005L);
    }

    @Test
    public void testGetArticleById_ValidateResultIsSortedAfterSortIndex() {
        final Long id = 1001L;

        given()
                .when()
                .pathParam("id", id)
                .get(URI + "/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("blocks.sortIndex[0]", is(0))
                .body("blocks.sortIndex[1]", is(1))
                .body("blocks.sortIndex[2]", is(2))
                .body("blocks.sortIndex[3]", is(3))
                .body("blocks.sortIndex[4]", is(4))
                .body("blocks.sortIndex[5]", is(5));
    }

}
