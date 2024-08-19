package com.example.animationwithjetpackcompose

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animationwithjetpackcompose.ui.theme.AnimationWithJetpackComposeTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun AnimationMain() {
    Column(modifier = Modifier) {
        //Header
        AppHeader()

        AppBody(modifier = Modifier)

    }

}

@Composable
fun AppBody(modifier: Modifier) {

    var scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    var rotate = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    var animateScaleAgain by remember {
        mutableStateOf(false)
    }

    var animateRotateAgain by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = animateScaleAgain) {
        scale.animateTo(targetValue = 1f, animationSpec = tween(
            durationMillis = 2000,
            easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            }
        ))
    }

    LaunchedEffect(key1 = animateRotateAgain) {
        rotate.animateTo(targetValue = 360f, animationSpec = tween(
            durationMillis = 2000,
            easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            }
        ))
    }

    Column(
        modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .weight(1f)
                .rotate(rotate.value)
                .scale(scale.value),
            painter = painterResource(id = R.drawable.log_co),
            contentDescription = "logo"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(modifier = modifier.clip(RectangleShape), onClick = {
                GlobalScope.launch {
                    scale.snapTo(0f)

                }
                animateScaleAgain = !animateScaleAgain
            }) {
                Text(text = "Scale")
            }

            Button(modifier = modifier.clip(RectangleShape), onClick = {
                GlobalScope.launch {

                    rotate.snapTo(0f)
                }

                animateRotateAgain = !animateRotateAgain
            }) {
                Text(text = "Rotate")
            }
        }

    }

}

@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            text = "Animations In Jetpack Compose",
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun previewAnimation() {
    AnimationWithJetpackComposeTheme {
        AnimationMain()
    }
}