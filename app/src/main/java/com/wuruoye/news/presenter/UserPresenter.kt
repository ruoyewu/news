package com.wuruoye.news.presenter

import com.wuruoye.library.model.Listener
import com.wuruoye.library.util.net.WNet
import com.wuruoye.news.contract.UserContract
import com.wuruoye.news.model.API
import com.wuruoye.news.model.UserCache
import com.wuruoye.news.model.util.DataUtil

/**
 * @Created : wuruoye
 * @Date : 2018/7/13 13:40.
 * @Description :
 */
class UserPresenter : UserContract.Presenter() {
    private val mUserCache = UserCache.getInstance()

    override fun isLogin(): Boolean {
        return mUserCache.isLogin
    }

    override fun requestUserInfo() {
        val values = mapOf(Pair("id", mUserCache.userId))
        WNet.getInBackground(API.USER, values, object : Listener<String> {
            override fun onFail(p0: String?) {

            }

            override fun onSuccessful(p0: String?) {
                val loginUser = DataUtil.parseLoginUser(p0!!)
                view?.onResultUserInfo(loginUser)
            }
        })
    }
}