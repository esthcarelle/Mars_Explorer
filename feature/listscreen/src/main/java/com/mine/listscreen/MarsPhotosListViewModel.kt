package com.mine.listscreen

import androidx.lifecycle.ViewModel
import com.mine.common.base.BaseViewModel
import com.mine.domain.MarsPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarsPhotosListViewModel @Inject constructor(private val marsPhotosUseCase: MarsPhotosUseCase) :
    BaseViewModel() {

}
