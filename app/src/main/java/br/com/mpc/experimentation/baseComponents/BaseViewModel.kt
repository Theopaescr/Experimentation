package br.com.mpc.experimentation.baseComponents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

abstract class BaseViewModel<T>(dispatcher: BaseDispatcher<T>): ViewModel()