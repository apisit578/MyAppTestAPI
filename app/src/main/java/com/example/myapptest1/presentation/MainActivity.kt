package com.example.myapptest1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapptest1.app.AppContainer
import com.example.myapptest1.presentation.stateUI.DogFactUiState
import com.example.myapptest1.presentation.viewmodel.DogFactViewModel
import com.example.myapptest1.ui.theme.MyAppTest1Theme


class MainActivity : ComponentActivity() {

    private val viewModel: DogFactViewModel by viewModels {
        AppContainer.provideDogFactViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val viewModel = hiltViewModel<DogFactViewModel>()
            val uiState by viewModel.uiState.collectAsState()
//            val uiState by viewModel.uiState.observeAsState(
//                DogFactUiState.Loading
//            )

            when (val state = uiState) {
                is DogFactUiState.Success -> {
                    MyAppTest1Theme {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Greeting(
                                id = state.fact.id,
                                fact = state.fact.fact,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }

                is DogFactUiState.Error -> {
                    MyAppTest1Theme {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Greeting(
                                id = "don't know",
                                fact = state.message,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun Greeting(id: String, fact: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Fact ID: $id",
            modifier = modifier
        )
        Text(
            text = "Fact random about dog is: $fact",
            modifier = modifier
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAppTest1Theme {
        Greeting(id = "Android", fact = "testfac")
    }
}