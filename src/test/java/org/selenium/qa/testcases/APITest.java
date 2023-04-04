package org.selenium.qa.testcases;

import org.selenium.qa.baseclass.BaseClass;
import org.selenium.qa.pages.POJOAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APITest extends BaseClass {

	@Test
	public void CreateUsers() {
		POJOAPI user = new POJOAPI();
		user.setName(prop.getProperty("APIName"));
		user.setJob(prop.getProperty("APIJob"));

		Response response = RestAssured.given().baseUri(prop.getProperty("posturl")).body(user)
				.contentType(ContentType.JSON).post();
		Assert.assertEquals(response.statusCode(), 201);
		response.getBody().prettyPrint();
	}
}
