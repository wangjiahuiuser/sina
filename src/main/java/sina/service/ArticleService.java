package sina.service;

import sina.domain.Article;
import sina.domain.PageBean;

import java.util.List;

public interface ArticleService {

    PageBean<Article> findPage(int currentPage, int cid, String aword,int uid);


    Article findOneArticle(int aid,int uid,int currentPart);

    Article addArticle(Article article);

    List<Article> findOwnArticle(int uid, int currentNub);

    List<Article> findHotArticle();
}
