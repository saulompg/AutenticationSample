package com.example.autenticationsample.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.autenticationsample.navigation.Screen

@Composable
fun SignUpScreen(navController: NavController) {
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
                stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.profile)
            )
            MyTextFieldComponent(
                stringResource(id = R.string.last_name),
                painterResource(id = R.drawable.profile)
            )
            EmailTextFieldComponent(
                stringResource(id = R.string.email),
                painterResource(id = R.drawable.message)
            )
            PasswordTextFieldComponent(
                stringResource(id = R.string.password),
                painterResource(id = R.drawable.lock)
            )
            // insere o checkbox
            CheckBoxComponent(
                value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
                    navController.navigate(Screen.TermsAndConditionsScreen.route)
                }
            )
            // adiciona um espaçador
            Spacer(modifier = Modifier.height(40.dp))
            // insere o botão de cadastro
            ButtonComponent(
                value = stringResource(id = R.string.register)
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
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = rememberNavController())
}