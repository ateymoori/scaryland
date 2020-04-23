package com.pixabay.utils.rxbus

import com.pixabay.utils.models.AudioModel
import com.pixabay.utils.models.AudioStatus

class RxEvent {
    data class AudioPlay(val audio: AudioModel)
    data class AudioControl(val audioStatus: AudioStatus)
}