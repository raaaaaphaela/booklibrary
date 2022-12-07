package de.neuefische.booklibrary.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class LibraryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllBooksFromAPI() throws Exception {
        // given
        String expectedJSON = """
                [
                    {
                        "title": "Java ist auch eine Insel",
                        "author": "Christian Ullenboom",
                        "cover": "Hard-Cover",
                        "isbn": "978-3-8362-8745-6"
                    },
                    {
                        "title": "Lebensweisheiten",
                        "author": "Miss Unbekannt",
                        "cover": "E-Book",
                        "isbn": "278-4-8362-8345-7"
                    }
                ]
                """;

        // when & then
        mvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON, true));

    }

    @Test
    void getBookForCertainISNB() throws Exception {
        // given
        String expectedJSON = """
                    {
                        "title": "Java ist auch eine Insel",
                        "author": "Christian Ullenboom",
                        "cover": "Hard-Cover",
                        "isbn": "978-3-8362-8745-6"
                    }
                """;

        // when & then
        mvc.perform(MockMvcRequestBuilders.get("/api/books/978-3-8362-8745-6"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON, true));
    }

    @Test
    void saveNewBook_WithISBN() throws Exception {
        // given
        String JSON = """
                    {
                        "title": "Bali ist auch ganz schön",
                        "author": "Unbekannter Balinese",
                        "cover": "Hard-Cover",
                        "isbn": "178-3-8362-8745-6"
                    }
                """;

        // when & then
        mvc.perform(MockMvcRequestBuilders.put("/api/books/178-3-8362-8745-6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON, true));
    }

    @Test
    void saveNewBook_WithUnmatchingISBN() throws Exception {
        // given
        String body = """
                    {
                        "title": "Bali ist auch ganz schön",
                        "author": "Unbekannter Balinese",
                        "cover": "Hard-Cover",
                        "isbn": "278-3-8362-8745-6"
                    }
                """;
        String response = """
                {
                    "title": null,
                    "author": null,
                    "cover": null,
                    "isbn": null
                }
                """;

        // when & then
        mvc.perform(MockMvcRequestBuilders.put("/api/books/178-3-8362-8745-6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(content().json(response, true));
    }

    @Test
    void deleteBookByISBN() throws Exception {

        // when & then
        mvc.perform(MockMvcRequestBuilders.delete("/api/books/978-3-8362-8745-6"))
                .andExpect(status().isOk());
    }
}