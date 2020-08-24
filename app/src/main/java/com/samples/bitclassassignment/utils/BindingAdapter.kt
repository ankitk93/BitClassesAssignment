package com.samples.bitclassassignment.utils

import android.graphics.PorterDuff
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.samples.bitclassassignment.R
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas

/**
 * Created by ak93.droid@gmail.com on 24,August,2020
 */

@BindingAdapter("rtc_engine_remote", "set_uid","remote_user_muted_video")
fun setupRemoteVideo(container: FrameLayout, rtcEngine: RtcEngine, uid: Int, muted: Boolean) {
    if(uid != 0) {
        val surfaceView = RtcEngine.CreateRendererView(container.context)
        surfaceView.visibility = if (muted) View.GONE else View.VISIBLE
        container.addView(surfaceView)
        Log.e("userUid", uid.toString())
        rtcEngine.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, uid))
    }
}

@BindingAdapter("rtc_engine_local")
fun setUpLocalVideo(container: FrameLayout, rtcEngine: RtcEngine) {
    val surfaceView = RtcEngine.CreateRendererView(container.context)
    surfaceView.setZOrderMediaOverlay(true)
    container.addView(surfaceView)
    rtcEngine.setupLocalVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, 0))
}

@BindingAdapter("rtc_engine_remote_video", "set_uid", "handle_remote_video_mute")
fun onRemoteVideoMuteClicked(iv: ImageView, rtcEngine: RtcEngine, remoteUserUid:Int, mute: Boolean){
    if (remoteUserUid != 0) {
        if (!mute) {
            iv.clearColorFilter()
        } else {
            iv.setColorFilter(iv.context.getColor(R.color.button_color), PorterDuff.Mode.MULTIPLY)
        }
        rtcEngine.muteRemoteVideoStream(remoteUserUid, mute)
    }
}

@BindingAdapter("rtc_engine_audio", "handle_local_audio_mute")
fun onLocalAudioMute(iv: ImageView, rtcEngine: RtcEngine, mute: Boolean){
    if (!mute) {
        iv.clearColorFilter()
    } else {
        iv.setColorFilter(iv.context.getColor(R.color.button_color), PorterDuff.Mode.MULTIPLY)
    }

    rtcEngine.muteLocalAudioStream(mute)
}

@BindingAdapter("rtc_engine_video", "handle_local_video_mute")
fun onLocalVideoMute(iv: ImageView, rtcEngine: RtcEngine, mute: Boolean){
    if (!mute) {
        iv.clearColorFilter()
    } else {
        iv.setColorFilter(iv.context.getColor(R.color.button_color), PorterDuff.Mode.MULTIPLY)
    }
    rtcEngine.muteLocalVideoStream(mute)
}
