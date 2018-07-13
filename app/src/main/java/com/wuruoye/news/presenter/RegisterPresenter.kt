package com.wuruoye.news.presenter

import com.wuruoye.library.model.Listener
import com.wuruoye.library.util.net.WNet
import com.wuruoye.news.contract.LoginRegisterContract
import com.wuruoye.news.model.API
import com.wuruoye.news.model.util.SecretUtil
import java.net.URLEncoder

/**
 * @Created : wuruoye
 * @Date : 2018/7/12 21:03.
 * @Description :
 */
class RegisterPresenter : LoginRegisterContract.Presenter() {
    override fun requestRegister(id: String, nickname: String, pwd: String, email: String) {
        val values = mapOf(Pair("id", id), Pair("name", nickname),
                Pair("password", URLEncoder.encode(SecretUtil.getPublicSecret(pwd), "utf8")),
                Pair("email", URLEncoder.encode(SecretUtil.getPublicSecret(email), "utf8")))
        WNet.postInBackground(API.USER, values, object : Listener<String> {
            override fun onFail(p0: String?) {
                view.onResultRegister(false, p0!!)
            }

            override fun onSuccessful(p0: String?) {
                view?.onResultRegister(true, p0!!)
            }

        })
    }
}