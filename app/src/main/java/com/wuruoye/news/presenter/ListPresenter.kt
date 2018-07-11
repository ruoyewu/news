package com.wuruoye.news.presenter

import com.wuruoye.library.model.Listener
import com.wuruoye.library.util.net.WNet
import com.wuruoye.news.contract.ArticleListContract
import com.wuruoye.news.model.API
import com.wuruoye.news.model.util.DataUtil

/**
 * @Created : wuruoye
 * @Date : 2018/7/10 19:35.
 * @Description :
 */
class ListPresenter : ArticleListContract.Presenter() {

    override fun requestList(category: String, url: String, page: String) {
        val url = url.replace("\${page}", page)
        WNet.getInBackground(url, mapOf(), object : Listener<String> {
            override fun onFail(p0: String?) {

            }

            override fun onSuccessful(p0: String?) {
                val values = mapOf(Pair("app", "sina"),
                        Pair("category", category), Pair("page", page), Pair("content", p0!!))
                WNet.postInBackground(API.ARTICLE_LIST, values, object : Listener<String> {
                    override fun onFail(p0: String?) {

                    }

                    override fun onSuccessful(p0: String?) {
                        val list = DataUtil.parseArticleList(p0!!)
                        view?.onResultList(list)
                    }

                })
            }

        })
    }
}