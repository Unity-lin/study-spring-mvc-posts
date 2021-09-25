package com.example;

import com.example.dto.PostsSaveRequestDto;
import com.example.model.PostsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MvcStudyApplicationTests {

	@LocalServerPort
	private int port;

	@Test
	void contextLoads() {
	}

	@Autowired
	DataSource dataSource;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PostsRepository postsRepository;

	@Test
	public void connectionTest() throws Exception{
		Connection connection = dataSource.getConnection();
		assertNotNull(connection);
	}
	@Test
	public void testMainPageLoading_ok(){
		String body = restTemplate.getForObject("/", String.class);
		assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
	}
	@Test
	public void testPostSave_ok(){

		String title = "title";
		String content = "content";
		PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
				.title(title)
				.content(content)
				.author("jwon")
				.build();

		String url = "http://localhost:" + port + "/api/v1/posts";

		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);

		postsRepository.findById(responseEntity.getBody().longValue()).map(posts -> {
			assertThat(posts.getTitle()).isEqualTo(title);
			assertThat(posts.getContent()).isEqualTo(content);
			return null;
		});
	}

}
