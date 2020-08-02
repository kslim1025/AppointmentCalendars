package com.kslim1025.daily10minute.datas

import org.json.JSONObject

class Reply {

    var id = 0
    var content = ""
    lateinit var writer : User
    var likeCount = 0
    var myLike = false

    companion object {

        fun getReplyFromJson(json : JSONObject) : Reply {
            val r = Reply()

            r.id = json.getInt("id")
            r.content = json.getString("content")
            r.likeCount = json.getInt("like_count")
            r.myLike = json.getBoolean("my_like")

//            user JSONObject 추출 => User 클래스로 변환 => r.writer에 대입
            r.writer = User.getUserFromJson(json.getJSONObject("user"))

            return r
        }

    }
}