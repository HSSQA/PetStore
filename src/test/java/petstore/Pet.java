// 1 - Pacote
package petstore;

// 2 - Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

// 3 - Classe
public class Pet {
    // 3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    // 3.2 - Métodos e Funções
     public String lerJson(String caminhoJson) throws IOException {
         return new String(Files.readAllBytes(Paths.get(caminhoJson)));
     }

     // Incluir - Creaye - Post
    @Test // Identifica o método ou função como m teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given() //Dado
                .contentType("application/json") // comum em API REST - antigas era "text/xml"
                .log().all()
                .body(jsonBody)
        .when() // Quando
                .post(uri)
        .then() //Então
                .log().all()
                .statusCode(200)
                .body("name", is("Shailon"))
                .body("status", is("available"))
                .body("category.name", is("Dog"))
                .body("tags.name", contains("sta"))
        ;
    }

    @Test
    public void consultarPet(){
        String petID = "19723541404";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + petID)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Shailon"))
                .body("category.name", is("Dog"))
                .body("status", is("available"))


        ;

    }
}