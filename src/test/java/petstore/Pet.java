// 1 - Pacote
package petstore;
// 2 - Biblioteca

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

// 3 - Classes
public class Pet {
    //3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    //3.2 - Métodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson))); // ler o arquivo json e devolve o conteúdo
    }

    // Incluir - Create - Post
    @Test //Identifica o método ou função como um teste  para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        //Sintaxe Gherkin
        //Dado - Quando - Então
        //Given - When - then

        given() //Dado
                .contentType("application/json") //comum em API REST - antigas era "text/xml"
                .log().all() // pedir pra logar
                .body(jsonBody) //colocar a informação que será transmitida
        .when()
                .post(uri)
        .then()
                .log().all() //refere-se a volta
                .statusCode(200) //verificar se foi e voltou
                .body("name", is("hector"))
                .body("status", is("available") )
        ;


    }
}
