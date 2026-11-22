package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.PostMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = {
	"spring.datasource.url=jdbc:h2:mem:bulletin;MODE=MySQL;DB_CLOSE_DELAY=-1",
	"spring.datasource.username=sa",
	"spring.datasource.password="
})
@AutoConfigureMockMvc
@Transactional
class BulletinApplicationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	PostMapper posts;

	@Test
	void createsReadsUpdatesAndDeletesPost() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk());
		mvc.perform(get("/api/posts"))
			.andExpect(status().isOk())
			.andExpect(content().json("[]"));
		mvc.perform(post("/api/posts").contentType(MediaType.APPLICATION_JSON)
			.content("""
				{"title":"First post","kategorie":"General","content":"Hello"}
				"""))
			.andExpect(status().isOk());

		int id = posts.selectAllPosts().get(0).getId();
		mvc.perform(get("/api/posts/{id}", id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("First post"))
			.andExpect(jsonPath("$.createdAt").exists());
		mvc.perform(put("/api/posts/{id}", id).contentType(MediaType.APPLICATION_JSON)
			.content("""
				{"title":"Updated post","kategorie":"General","content":"Hello again"}
				"""))
			.andExpect(status().isOk());
		mvc.perform(get("/api/posts"))
			.andExpect(jsonPath("$[0].title").value("Updated post"));
		mvc.perform(delete("/api/posts/{id}", id)).andExpect(status().isOk());
		mvc.perform(get("/api/posts/{id}", id)).andExpect(status().isNotFound());
	}

}
