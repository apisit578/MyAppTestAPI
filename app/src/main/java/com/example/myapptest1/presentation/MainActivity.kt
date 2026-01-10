package com.example.myapptest1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapptest1.app.AppContainer
import com.example.myapptest1.presentation.stateUI.CatUiState
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
            val uiCatState by viewModel.uiCatState.collectAsState()

//            val uiState by viewModel.uiState.observeAsState(
//                DogFactUiState.Loading
//            )
            MyAppTest1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when (val state = uiCatState) {
                            is CatUiState.Success -> {
                                CatImage(
                                    url = state.cat.url,
                                    modifier = Modifier
                                        .size(220.dp)
                                        .padding(top = 24.dp)
                                )
                            }

                            is CatUiState.Error -> {
                                Text("Load cat image failed")
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        when (val state = uiState) {
                            is DogFactUiState.Success -> {
                                DogFact(
                                    id = state.fact.id,
                                    fact = state.fact.fact
                                )
                            }

                            is DogFactUiState.Error -> {
                                DogFact(
                                    id = "don't know",
                                    fact = state.message
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun DogFact(id: String, fact: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "ID: $id",
            modifier = modifier
        )
        Text(
            text = "Detail: $fact",
            modifier = modifier
        )
    }

}

@Composable
fun CatImage(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAppTest1Theme {
        DogFact(id = "Android", fact = "testfac")
    }
}