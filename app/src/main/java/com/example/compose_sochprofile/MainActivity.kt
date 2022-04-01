package com.example.compose_sochprofile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.compose_sochprofile.ui.theme.Compose_SochProfileTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Compose_SochProfileTheme {
                navController= rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(this,navController)
                }
            }
        }
    }

    class onback(enable:Boolean):OnBackPressedCallback(enable){
        lateinit var onBackPressed: () -> Unit
        override fun handleOnBackPressed() {
            onBackPressed()
        }

    }
}

@SuppressLint("RememberReturnType")
@Composable
fun Greeting(context: Context,nav:NavHostController) {
    val activity = (LocalContext.current as? Activity)

    Column(Modifier.fillMaxWidth()) {
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.fillMaxWidth()) {
                ConstraintLayout(
                    Modifier
                        .fillMaxWidth()

                ) {
                    var (save, cancel, image, tabs) = createRefs()

                    Button(
                        onClick = {
                            activity?.onBackPressed()
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .constrainAs(cancel) {
                                top.linkTo(parent.top, 10.dp)
                                start.linkTo(parent.start, 10.dp)
                            },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Back")
                    }

                    Button(
                        onClick = {
                            Toast.makeText(activity, "you did it buddy", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .constrainAs(save) {
                                top.linkTo(parent.top, 10.dp)
                                end.linkTo(parent.end, 10.dp)
                            },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Green,
                            contentColor = Color.Blue
                        )
                    ) {
                        Text("save")
                    }

                    var img = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRt01EgmJsQWqveOszhWMoxwAyyJ5RJfjbdrQ&usqp=CAU")
                            .error(R.drawable.ic_launcher_background)
                            .placeholder(R.drawable.ic_launcher_background)
                            .transformations(
                                CircleCropTransformation()
                            )
                            .build()
                    )

                    Image(painter = img, contentDescription = "img",
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .constrainAs(image) {
                                top.linkTo(cancel.bottom, 15.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom, 15.dp)
                            })
                }
            }
        }


//    BackHandler {
//        Toast.makeText(activity, "done", Toast.LENGTH_SHORT).show()
//    }
//
//    @RequiresApi(Build.VERSION_CODES.M)
//    fun isNetworkAvailable(context: Context):Boolean{
//        var connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        var capabilities=
//            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//
//
//        if(capabilities!=null){
//            when{
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                return true
//                }
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->{
//                    return true
//                }
//
//                }
//        }
//
//        return false
//    }
        var nav1 = rememberNavController()

        Scaffold(
            topBar = {
                TopBar(nav1)
            }
        ) {
            navGraph1(navController = nav1)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}