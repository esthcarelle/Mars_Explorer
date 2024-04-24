package com.mine.listscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mine.design.components.Title
import com.mine.design.components.TopBar
import com.mine.model.Photo
import com.mine.utils.Network.Utils.isConnected
import java.net.URLEncoder.encode
import java.nio.charset.StandardCharsets

@Composable
fun MarsPhotosList(
    navController: NavHostController,
    marsPhotosListViewModel: MarsPhotosListViewModel = hiltViewModel(),
    toggleTheme: () -> Unit
) {
    LazyColumn {
        item {
            TopBar(
                onToggle = {
                    toggleTheme()
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            if (marsPhotosListViewModel.uiState.isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillParentMaxSize()
                        .background(Color.White)
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        item {
            NoInternetConnectionView(marsPhotosListViewModel = marsPhotosListViewModel)
        }

        marsPhotosListViewModel.uiState.marsPhotos?.let {
                photos: List<Photo> ->
            items(photos) {
                val encodedUrl = encode(it.img_src, StandardCharsets.UTF_8.toString())
                ItemMarsPhotoCard(
                    it,
                    onItemClicked = { photo ->
                        navController.navigate("details/${encodedUrl}/${photo.rover.name}/${photo.rover.launch_date}/${photo.rover.landing_date}")
                    }
                )
            }
        }
    }
}


@Composable
fun NoInternetConnectionView(marsPhotosListViewModel: MarsPhotosListViewModel) {
    val context = LocalContext.current
    if (!isConnected(context) && marsPhotosListViewModel.uiState.marsPhotos?.isEmpty() == true) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Title(title = stringResource(id = R.string.text_no_internet_connection))


            Button(
                onClick = { marsPhotosListViewModel.getMarsPhotosList() },
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                shape = RectangleShape,
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                )
            ) {
                Text(
                    text = stringResource(R.string.text_retry),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.surface
                )
            }
        }
    }
}