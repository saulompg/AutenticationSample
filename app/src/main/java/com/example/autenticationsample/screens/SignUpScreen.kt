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
import com.example.autenticationsample.app.components.*


@Composable
fun SignUpScreen() {
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
            NormalTextComponent(value = stringResource(id = com.example.autenticationsample.R.string.hello))
            HeadingTextComponent(value = stringResource(id = com.example.autenticationsample.R.string.create_account))
            // adiciona um espaçador
            Spacer(modifier = Modifier.height(20.dp))
            // insere os campos de texto, passando o ícone e a string como parâmetro
            MyTextFieldComponent(
                stringResource(id = com.example.autenticationsample.R.string.first_name),
                painterResource(id = com.example.autenticationsample.R.drawable.profile)
            )
            MyTextFieldComponent(
                stringResource(id = com.example.autenticationsample.R.string.last_name),
                painterResource(id = com.example.autenticationsample.R.drawable.profile)
            )
            EmailTextFieldComponent(
                stringResource(id = com.example.autenticationsample.R.string.email),
                painterResource(id = com.example.autenticationsample.R.drawable.message)
            )
            PasswordTextFieldComponent(
                stringResource(id = com.example.autenticationsample.R.string.password),
                painterResource(id = com.example.autenticationsample.R.drawable.lock)
            )
            // insere o checkbox
            CheckBoxComponent(stringResource(id = com.example.autenticationsample.R.string.terms_and_conditions))
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}