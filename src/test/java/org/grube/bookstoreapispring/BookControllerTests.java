package org.grube.bookstoreapispring;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /books (valid) should return 200 and request body with newly created book. ")
    void postBook() throws Exception {

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "test_title");
        requestBody.put("subtitle", "test_subtitle");
        requestBody.put("isbn13", "1234567890128");
        requestBody.put("price", "42.42");
        requestBody.put("image", "https://picsum.photos/200/300/?blur");
        requestBody.put("url", "http://www.someUrl.org");
        MvcResult mvcResult = mockMvc.perform(post("/books")
                .content(requestBody.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("test_title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.subtitle").value("test_subtitle"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn13").value("1234567890128"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("42.42"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value("https://picsum.photos/200/300/?blur"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.url").value("http://www.someUrl.org"))
                .andReturn();
        JSONObject response = new JSONObject(mvcResult.getResponse().getContentAsString());
        long id = response.getLong("id");
        System.out.println("id=" + id);
    }

    @Test
    @DisplayName("GET /books/{id} for unknown id should return 404")
    void getBookByUnknownId_test() throws Exception {
        mockMvc.perform(get("/books/0"))
                .andExpect(status().isNotFound());
    }
}