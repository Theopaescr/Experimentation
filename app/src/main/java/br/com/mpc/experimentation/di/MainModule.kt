package br.com.mpc.experimentation.di

import androidx.lifecycle.SavedStateHandle
import br.com.mpc.experimentation.ui.dashboard.DashboardViewModel
import br.com.mpc.experimentation.ui.home.HomeViewModel
import br.com.mpc.experimentation.ui.notifications.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module(override = true) {
    viewModel<HomeViewModel> { (handle: SavedStateHandle) -> HomeViewModel(handle) }
    viewModel<NotificationsViewModel> { (handle: SavedStateHandle) -> NotificationsViewModel(handle) }
    viewModel<DashboardViewModel> { (handle: SavedStateHandle) -> DashboardViewModel(handle) }
}