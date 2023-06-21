package com.example.autenticationsample.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.autenticationsample.R
import com.example.autenticationsample.components.ButtonComponent
import com.example.autenticationsample.components.ClickableLoginTextComponent
import com.example.autenticationsample.components.DividerTextComponent
import com.example.autenticationsample.components.EmailTextFieldComponent
import com.example.autenticationsample.components.HeadingTextComponent
import com.example.autenticationsample.components.NormalTextComponent
import com.example.autenticationsample.components.PasswordTextFieldComponent
import com.example.autenticationsample.components.UnderLineTextComponent
import com.example.autenticationsample.data.LoginUIEvent
import com.example.autenticationsample.data.LoginViewModel
import com.example.autenticationsample.navigation.Screen

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // cabeçalho
                NormalTextComponent(value = stringResource(id = R.string.title_hello))
                HeadingTextComponent(value = stringResource(id = R.string.title_welcome))
                // adiciona um espaçador
                Spacer(modifier = Modifier.height(20.dp))
                // adiciona o campo de email
                EmailTextFieldComponent(
                    labelText = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.message),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )
                // adiciona o campo de senha
                PasswordTextFieldComponent(
                    labelText = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )
                // adiciona um espaçador
                Spacer(modifier = Modifier.height(20.dp))
                // adiciona a opção para recuperar a senha
                UnderLineTextComponent(value = stringResource(id = R.string.forgot_password))
                // adiciona um espaçador
                Spacer(modifier = Modifier.height(20.dp))
                // adiciona o botão de login
                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    }
                )
                // adiciona um espaçador
                Spacer(modifier = Modifier.height(20.dp))
                // adiciona o divisor
                DividerTextComponent()
                // adiciona opção para cadastrar novo usuario
                ClickableLoginTextComponent(
                    tryingToLog = false,
                    onTextSelected =  {
                        navController.navigate(route = Screen.SignUpScreen.route)
                    }
                )
            }
        }

        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }

    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}