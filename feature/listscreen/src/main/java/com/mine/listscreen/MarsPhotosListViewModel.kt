package com.mine.listscreen

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarsPhotosListViewModel @Inject constructor(private val marsPhotosUseCase: MarsPhotosUseCase) {

}
