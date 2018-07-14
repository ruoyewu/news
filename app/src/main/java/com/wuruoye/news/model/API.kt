package com.wuruoye.news.model

/**
 * @Created : wuruoye
 * @Date : 2018/7/10 10:24.
 * @Description :
 */
object API {
    const val REMOTE_HOST = "https://all.wuruoye.com"

    const val API = "$REMOTE_HOST/article/api"
    const val ARTICLE_LIST_ = "$REMOTE_HOST/article/list_"
    const val ARTICLE_LIST = "$REMOTE_HOST/article/list"
    const val ARTICLE_DETAIL = "$REMOTE_HOST/article/detail"
    const val USER_LOGIN = "$REMOTE_HOST/user/login"
    const val USER = "$REMOTE_HOST/user/user"
    const val ARTICLE_COMMENT = "$REMOTE_HOST/user/article_comment"
    const val COMMENT_PRAISE = "$REMOTE_HOST/user/comment_praise"
}