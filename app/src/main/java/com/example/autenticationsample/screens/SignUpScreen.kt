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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.autenticationsample.R
import com.example.autenticationsample.components.ButtonComponent
import com.example.autenticationsample.components.CheckBoxComponent
import com.example.autenticationsample.components.ClickableLoginTextComponent
import com.example.autenticationsample.components.DividerTextComponent
import com.example.autenticationsample.components.EmailTextFieldComponent
import com.example.autenticationsample.components.HeadingTextComponent
import com.example.autenticationsample.components.MyTextFieldComponent
import com.example.autenticationsample.components.NormalTextComponent
import com.example.autenticationsample.components.PasswordTextFieldComponent
import com.example.autenticationsample.data.SignUpViewModel
import com.example.autenticationsample.data.SignUpUIEvent
import com.example.autenticationsample.navigation.Screen

@Composable
fun SignUpScreen(navController: NavController, signUpViewModel: SignUpViewModel = viewModel()) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            // define que a disposição dos elementos será em coluna
            Column(modifier = Modifier.fillMaxSize()) {
                // chama o método que exibe o texto em tela, passando a string a ser exibida como parâmetro
                NormalTextComponent(value = stringResource(id = R.string.title_hello))
                HeadingTextComponent(value = stringResource(id = R.string.title_create_account))
                // adiciona um espaçador
                Spacer(modifier = Modifier.height(20.dp))
                // insere os campos de texto, passando o ícone e a string como parâmetro
                MyTextFieldComponent(
                    labelText =  stringResource(id = R.string.first_name),
                    painterResource = painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signUpViewModel.signUpUIState.value.firstNameError
                )
                MyTextFieldComponent(
                    labelText = stringResource(id = R.string.last_name),
                    painterResource = painterResource(id = R.drawable.profile),
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signUpViewModel.signUpUIState.value.lastNameError
                )
                EmailTextFieldComponent(
                    labelText = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.message),
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it))
                    },
                    errorStatus = signUpViewModel.signUpUIState.value.emailError
                )
                PasswordTextFieldComponent(
                    labelText = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signUpViewModel.signUpUIState.value.passwordError
                )
                // insere o checkbox
                CheckBoxComponent(
                    onTextSelected = {
                        navController.navigate(it)
                    },
                    onCheckedChange = {
                        signUpViewModel.onEvent(SignUpUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )
                // adiciona um espaçador
                Spacer(modifier = Modifier.height(40.dp))
                // insere o botão de cadastro
                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        signUpViewModel.onEvent(SignUpUIEvent.SignUpButtonClicked)
                    }
                )
                // adiciona um espaçador
                Spacer(modifier = Modifier.height(20.dp))
                // adiciona separador
                DividerTextComponent()
                // adiciona opção para fazer login
                ClickableLoginTextComponent(
                    onTextSelected =  {
                        navController.navigate(route = Screen.LoginScreen.route) {
                            popUpTo(Screen.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        if(signUpViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }

    }

}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(
        navController = rememberNavController(),
        signUpViewModel = SignUpViewModel(navController = rememberNavController())
    )
}