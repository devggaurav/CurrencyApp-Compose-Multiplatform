package presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import data.remote.api.CurrencyApiServiceImpl
import domain.model.CurrencyType
import presentation.component.CurrencyPickerDialog
import presentation.component.HomeBody
import presentation.component.HomeHeader
import ui.theme.surfaceColor

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<HomeViewModel>()
        val rateStatus by viewModel.rateStatus
        val source by viewModel.sourceCurrency
        val target by viewModel.targetCurrency
        var amount by rememberSaveable { mutableStateOf(0.0) }
        val allCurrencies = viewModel.allCurrencies


        var selectedCurrencyType: CurrencyType by remember {
            mutableStateOf(CurrencyType.None)
        }

        var dialogOpened by remember { mutableStateOf(false) }
        if (dialogOpened && selectedCurrencyType != CurrencyType.None) {
            CurrencyPickerDialog(
                currencies = allCurrencies,
                currencyType = selectedCurrencyType,
                onConfirmClick = { currencyCode ->
                    if (selectedCurrencyType is CurrencyType.Source) {
                        viewModel.sendEvent(
                            HomeUiEvent.SaveSourceCurrencyCode(
                                currencyCode = currencyCode.name
                            )
                        )
                    }else if (selectedCurrencyType is CurrencyType.Target){
                        viewModel.sendEvent(
                            HomeUiEvent.SaveTargetCurrencyCode(
                                currencyCode = currencyCode.name
                            )
                        )
                    }
                    selectedCurrencyType = CurrencyType.None
                    dialogOpened = false
                },
                onDismiss = {
                    selectedCurrencyType = CurrencyType.None
                    dialogOpened = false
                }
            )


        }


        println(rateStatus)
        Column(
            modifier = Modifier.fillMaxSize().background(surfaceColor)
        ) {

            HomeHeader(
                status = rateStatus,
                onRatesRefresh = {
                    viewModel.sendEvent(HomeUiEvent.RefreshRates)
                },
                source = source,
                target = target,
                onSwitchClick = {
                    viewModel.sendEvent(
                        HomeUiEvent.SwitchCurrencies
                    )
                },
                amount = amount,
                onAmountChange = { amount = it },
                onCurrencyTypeSelect = { currencyType ->
                    selectedCurrencyType = currencyType
                    dialogOpened = true
                }
            )
            HomeBody(
                source = source,
                target = target,
                amount = amount
            )
        }
    }
}