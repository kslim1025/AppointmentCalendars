package com.kslim1025.daily10minute

import android.os.Bundle
import com.kslim1025.daily10minute.adapters.NotificationAdapter
import com.kslim1025.daily10minute.datas.Notification
import com.kslim1025.daily10minute.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_notification_list.*
import org.json.JSONObject

class NotificationListActivity : BaseActivity() {

    val mNotiList = ArrayList<Notification>()
    lateinit var mNotiAdapter : NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        getNotificationListFromServer()

        mNotiAdapter = NotificationAdapter(mContext, R.layout.notification_list_item, mNotiList)
        notificationListView.adapter = mNotiAdapter

    }

    fun getNotificationListFromServer() {
        ServerUtil.getRequestNotificationList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val notifications = data.getJSONArray("notifications")

                for (i in 0 until notifications.length()) {

//                    배열 내부의 JSONArrary 추출 => Notification 형태로 변환 => 알림목록에 추가
                    mNotiList.add(Notification.getNotificationFromJson(notifications.getJSONObject(i)))

                }

//                받아 온 모든 알림중 최신 알림의 id값을 서버에 전달 => 여기까지 읽음 처리
                ServerUtil.postRequestNotification(mContext, mNotiList[0].id, null)


                runOnUiThread {

//                배열에 목록이 모두 추가되면 => 새로고침
                    mNotiAdapter.notifyDataSetChanged()
                }

            }

        })
    }

}