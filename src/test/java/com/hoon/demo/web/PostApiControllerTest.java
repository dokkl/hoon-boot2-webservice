package com.hoon.demo.web;

import com.hoon.demo.domain.posts.Post;
import com.hoon.demo.domain.posts.PostRepository;
import com.hoon.demo.web.dto.PostSaveReqeustDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @After
    public void tearDown() throws Exception {
        //postRepository.deleteAll();
    }

    @Transactional
    @Rollback(false) //h2에서 안먹음
    @Test
    public void post_등록된다() throws Exception {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        PostSaveReqeustDto reqeustDto = PostSaveReqeustDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        HttpEntity<PostSaveReqeustDto> requestEntity = new HttpEntity<>(reqeustDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        System.out.println("id : " + responseEntity.getBody());

        List<Post> all = postRepository.findAll();
        System.out.println("list count : " + all.size());
        assertThat(all.get(0).getTitle()).isEqualTo(title);

    }
}
