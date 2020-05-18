package com.modestack.userregistration.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.modestack.userregistration.entity.Article;
import com.modestack.userregistration.entity.User;
import com.modestack.userregistration.model.ArticleDTO;
import com.modestack.userregistration.model.Response;
import com.modestack.userregistration.repository.ArticleRepository;
import com.modestack.userregistration.repository.UserRepository;

@RestController
public class ArticleRestController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ArticleRepository articleRepository;

	@RequestMapping(value = "/createArtilce", method = RequestMethod.POST)
	public Response createUser(@RequestBody ArticleDTO articleDTO) {
		Article article = new Article();
		Response response = new Response();
		User user = null;
		try {
			user = userRepository.findByName(articleDTO.getAuthor());
			if (user == null) {
				response.setErrorCode("01");
				response.setErrorMesage("Invalid Author/Register Author To Create Article");
			} else if (!user.getAccessToken().equals(articleDTO.getAccess_Token())) {
				response.setErrorCode("01");
				response.setErrorMesage("Invaid Access Token");
			} else {

			}
			article.setAuthor(articleDTO.getAuthor());
			article.setTitle(articleDTO.getTitle());
			article.setBody(articleDTO.getBody());
			article.setCreatedBy(user.getUserId().toString());
			articleRepository.save(article);
		} catch (Exception e) {
			System.out.println("Exception occurred:" + e);
		}

		response.setErrorCode("00");
		response.setErrorMesage("SUCCESS");
		return response;

	}

	@RequestMapping(value = "/fetchArticles", method = RequestMethod.GET)
	public Response fetchArticles() {
		Response response = new Response();
		List<ArticleDTO> article = new ArrayList<ArticleDTO>();

		try {
			List<Article> articleList = (List<Article>) articleRepository.findAll();

			for (Article articleEntity : articleList) {
				ArticleDTO articleDTO = new ArticleDTO();
				articleDTO.setAuthor(articleEntity.getAuthor());
				articleDTO.setTitle(articleEntity.getTitle());
				articleDTO.setBody(articleEntity.getBody());
				article.add(articleDTO);
			}

		} catch (Exception e) {
			System.out.println("Exception occurred:" + e);

		}

		response.setErrorCode("00");
		response.setErrorMesage("SUCCESS");
		response.setArticle(article);
		return response;

	}

	@RequestMapping(value = "/fetchArticlesByPage", method = RequestMethod.GET)
	public List<Article> fetchArticlesByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
		Response response = new Response();
		List<ArticleDTO> article = new ArrayList<ArticleDTO>();

		Pageable paging = PageRequest.of(page, size);
		try {
			Page<Article> articleList = articleRepository.findAll(paging);

			if (articleList.hasContent()) {
				return articleList.getContent();
			} else {
				return new ArrayList<Article>();
			}

		} catch (Exception e) {
			System.out.println("Exception occurred:" + e);
		}
		return new ArrayList<Article>();

	}

}
