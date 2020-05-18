package com.modestack.userregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.modestack.userregistration.entity.Article;

@Repository
public interface ArticleRepository  extends PagingAndSortingRepository<Article, Long>{

}
