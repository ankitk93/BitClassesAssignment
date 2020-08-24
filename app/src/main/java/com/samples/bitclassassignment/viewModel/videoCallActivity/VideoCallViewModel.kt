package com.samples.bitclassassignment.viewModel.videoCallActivity

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samples.bitclassassignment.R
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoEncoderConfiguration

/**
 * Created by ak93.droid@gmail.com on 24,August,2020
 */

class VideoCallViewModel(val application: Application) : ViewModel() {

    val remoteUserUid = MutableLiveData<Int>()

    val mRtcEngine =  MutableLiveData<RtcEngine>()

    val shouldCloseLiveData = MutableLiveData<Void>()

    val mRemoteUserMutedVideo = MutableLiveData<Boolean>()

    val mute = MutableLiveData<Boolean>(false)

    val muteLocalVideo = MutableLiveData<Boolean>(false)

    val muteLocalAudio = MutableLiveData<Boolean>(false)

    private val mRtcEventHandler = object : IRtcEngineEventHandler() {

        override fun onFirstRemoteVideoDecoded(uid: Int, width: Int, height: Int, elapsed: Int) {
            remoteUserUid.postValue(uid)
        }

        override fun onUserOffline(uid: Int, reason: Int) {
        }

        override fun onUserMuteVideo(uid: Int, muted: Boolean) {
            mRemoteUserMutedVideo.postValue(muted)
        }
    }

    init {
        try {
            mRtcEngine.value = RtcEngine.create(
                application,
                application.getString(R.string.agora_app_id),
                mRtcEventHandler
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        setupVideoProfile()
        joinChannel()
    }

    fun setupVideoProfile() {
        mRtcEngine.value?.apply {
            enableVideo()
            setVideoEncoderConfiguration(
                VideoEncoderConfiguration(
                    VideoEncoderConfiguration.VD_640x360,
                    VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_15,
                    VideoEncoderConfiguration.STANDARD_BITRATE,
                    VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
                )
            )
        }
    }

    /**
     * join a specific channel
     */
    fun joinChannel(){
        var token: String? = application.getString(R.string.agora_access_token)
        if (token!!.isEmpty()) {
            token = null
        }
        mRtcEngine.value?.apply {
            joinChannel(token, "demoChannel", "Extra Optional Data", 0)
        }
    }

    /**
     * end call
     * clear rtc engine
     */
    fun leaveChannel() {
        mRtcEngine.value?.apply {
            leaveChannel()
            shouldCloseLiveData.postValue(null)
        }
    }


    /**
     * switch camera
     */
    fun onSwitchCameraClicked(){
        mRtcEngine.value?.apply {
            switchCamera()
        }
    }

    /**
     * remote video mute
     */
    fun onRemoteVideoMuteClicked(){
        mute.postValue(mute.value?.not())
    }

    /**
     * local audio mute
     */
    fun onLocalVideoMuteClicked() {
        muteLocalVideo.postValue(muteLocalVideo.value?.not())
    }

    /**
     * local video mute
     */
    fun onLocalAudioMuteClicked() {
        muteLocalAudio.postValue(muteLocalAudio.value?.not())
    }

    override fun onCleared() {
        RtcEngine.destroy()
    }
}