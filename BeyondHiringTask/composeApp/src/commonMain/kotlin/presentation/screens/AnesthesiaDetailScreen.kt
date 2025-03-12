package presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import beyondhiringtask.composeapp.generated.resources.Res
import beyondhiringtask.composeapp.generated.resources.compose_multiplatform
import beyondhiringtask.composeapp.generated.resources.home_location_down_arrow_icon
import beyondhiringtask.composeapp.generated.resources.profile_favourites_icon
import beyondhiringtask.composeapp.generated.resources.share_icon
import beyondhiringtask.composeapp.generated.resources.view_blog_left_image_arrow
import beyondhiringtask.composeapp.generated.resources.view_cart_icon
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import navigation.cook.AnesthesiaComponent
import org.example.beyondhiringtask.data.model.response.ApiResponse
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import presentation.viewModel.AnesthesiaDetailViewModel

@Composable
fun AnesthesiaDetailScreen(
    component: AnesthesiaComponent
) {
    val coroutineScope = rememberCoroutineScope()

    val projectImagePagerState = rememberPagerState(pageCount = { 8 })
    var isTextVisible by remember { mutableStateOf(true) }
    var count by remember { mutableStateOf(0) }
    var response: Any? = null

    var apiResponse: ApiResponse? = null

    val cookViewModel: AnesthesiaDetailViewModel = koinInject()
    val cookScreenState by cookViewModel.anesthesiaDetailViewState.collectAsState()

    LaunchedEffect(Unit) {
        cookViewModel.getData()
    }

    when (cookScreenState) {
        is AnesthesiaDetailViewModel.AnesthesiaDetailScreenState.Loading -> {
        }

        is AnesthesiaDetailViewModel.AnesthesiaDetailScreenState.Success -> {
            response =
                (cookScreenState as AnesthesiaDetailViewModel.AnesthesiaDetailScreenState.Success).responseData
            //itemList = response
            //val jsonString = response
            apiResponse = Json { ignoreUnknownKeys = true }.decodeFromString(response.toString())

            //  println("cheking" + apiResponse?.data?.webUrl.toString() )
        }

        is AnesthesiaDetailViewModel.AnesthesiaDetailScreenState.Error -> {
            val error =
                (cookScreenState as AnesthesiaDetailViewModel.AnesthesiaDetailScreenState.Error).errorMessage
            println("responseError${error}")
        }

        else -> {}
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier,
                backgroundColor = Color.White,
                navigationIcon = {

                    //back arrow
                    Image(
                        painter = painterResource(Res.drawable.view_blog_left_image_arrow),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(5.dp)
                            //   .clip(CircleShape)
                            .size(40.dp),
                        //   .background(Color.DarkGray),
                        contentScale = ContentScale.Inside
                    )

                },
                title = {
                    Text(
                        modifier = Modifier,
                        //.padding(start = 8.dp),
                        text = "Once Collection Weekly",
                        color = Color.Black,
                        fontSize = 16.sp
                    )

                },
                actions = {
                    //fav
                    Image(
                        painter = painterResource(Res.drawable.profile_favourites_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(3.dp)
                            .clip(CircleShape)
                            .size(30.dp)
                            .background(Color.LightGray),
                        contentScale = ContentScale.Inside
                    )
                    //share
                    Image(
                        painter = painterResource(Res.drawable.share_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(3.dp)
                            .clip(CircleShape)
                            .size(30.dp)
                            .background(Color.LightGray),
                        contentScale = ContentScale.Inside
                    )
                    //cart bag
                    Image(
                        painter = painterResource(Res.drawable.view_cart_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(3.dp)
                            .clip(CircleShape)
                            .size(30.dp)
                            .background(Color.LightGray),
                        contentScale = ContentScale.Inside
                    )
                }
            )
        },
        bottomBar = {

            Card(
                modifier = Modifier,
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                    // .padding(15.dp)
                ) {
                    //add to bag button
                    Button(
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                            .fillMaxWidth()
                            .heightIn(45.dp),
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Black
                        ),
                        onClick = {
                            coroutineScope.launch {
                                cookViewModel.getData()
                            }

                        }
                    ) {

                        Text(
                            modifier = Modifier,
                            text = "Add to bag",
                            color = Color.White
                        )
                    }
                    //share button
                    Button(
                        modifier = Modifier
                            .padding(start = 8.dp, top = 3.dp, end = 8.dp, bottom = 4.dp)
                            .fillMaxWidth()
                            .heightIn(45.dp),
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White
                        ),
                        onClick = {
                            coroutineScope.launch {
                                cookViewModel.getData()
                            }
                        },
                        border = BorderStroke(width = 1.dp, Color.Black)
                    ) {

                        Text(
                            modifier = Modifier,
                            text = "Share",
                            color = Color.Black
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HorizontalPager(state = projectImagePagerState) { page ->
                    Box {
                        AsyncImageComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp)
                                .align(Alignment.CenterStart)
                                .zIndex(1f).clickable {
                                    coroutineScope.launch {
                                        if (projectImagePagerState.currentPage > 0) {
                                            projectImagePagerState.animateScrollToPage(
                                                projectImagePagerState.currentPage - 1
                                            )
                                        }
                                    }
                                },
                            imageUrl = apiResponse?.data?.imageUrl.toString()
                        )

                        AsyncImageComponent(
                            modifier = Modifier,
                            imageUrl = "",
                            placeholder = Res.drawable.view_cart_icon,
                            error = Res.drawable.view_cart_icon,
                            contentScale = ContentScale.None
                        )

                        ImageComponent(
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .align(Alignment.CenterEnd)
                                .zIndex(1f)
                                .clickable {
                                    coroutineScope.launch {
                                        if (projectImagePagerState.currentPage < projectImagePagerState.pageCount - 1) {
                                            projectImagePagerState.animateScrollToPage(
                                                projectImagePagerState.currentPage + 1
                                            )
                                        }
                                    }
                                },
                            image = Res.drawable.view_cart_icon
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(8) {
                        val backgroundColor = if (projectImagePagerState.currentPage == it)
                            Color.Black
                        else
                            Color.LightGray

                        Box(
                            modifier = Modifier
                                .padding(end = 4.dp)
                                //.align(Alignment.CenterHorizontally)
                                .background(
                                    color = backgroundColor,
                                    shape = CircleShape
                                )
                                .padding(6.dp)
                                .clip(shape = CircleShape)
                        )
                    }
                }
                //anesthesia row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        //.padding(start = 8.dp),
                        text = apiResponse?.data?.brandName ?: "",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                    Text(
                        modifier = Modifier
                            .padding(end = 8.dp),
                        text = apiResponse?.data?.price + " KWD",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
                //
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp),
                    text = apiResponse?.data?.name ?: "",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp),
                    text = "SKU " + apiResponse?.data?.sku,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                //color
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 6.dp),
                    text = "Color:",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                //lazy row
                LazyRow(
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        //  items = apiResponse?.data?.configurableOption.attributes,
                        count = 10
                    ) {

                        //card
                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(1.dp, Color.Black, shape = CircleShape),
                            backgroundColor = Color.White,
                        ) {
                            AsyncImage(
                                model = apiResponse?.data?.imageUrl.toString(),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(50.dp)
                                    .padding(2.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Inside,
                                error = painterResource(Res.drawable.view_cart_icon),
                                placeholder = painterResource(Res.drawable.view_cart_icon),
                                contentDescription = null,
                            )
                        }
                    }
                }
                //card
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        //.clip(shape = RoundedCornerShape(20.dp))
                        .clickable {}
                        .border(0.4.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color.White,//Color(0xF8855C),
                    elevation = 4.dp,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 6.dp),
                        text = "or 4 interest free payments \n 0.88 KWD ",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
                //quantity
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp, start = 6.dp),
                    text = "Quantity",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Row(modifier = Modifier.padding(start = 6.dp)) {
                    //minus button
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                if (count > 0) count--
                            },
                        backgroundColor = Color.LightGray,//Color(0xF8855C),

                    ) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = "-",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }

                    // count
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {}
                            .border(0.4.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = Color.White,
                        elevation = 4.dp,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                            text = count.toString(),
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                    //plus button
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                count++
                            },
                        backgroundColor = Color.Black,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = "+",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                }
                //product info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(start = 6.dp, top = 16.dp),
                        //.padding(start = 8.dp),
                        text = "PRODUCT INFORMATION",
                        color = Color.Black,
                        fontSize = 16.sp
                    )

                    Image(
                        painter = painterResource(Res.drawable.home_location_down_arrow_icon),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(5.dp)
                            .size(30.dp)
                            .clickable {
                                isTextVisible = !isTextVisible
                            },
                        contentScale = ContentScale.FillBounds
                    )
                }
                if (isTextVisible) {
                    Text(
                        modifier = Modifier
                            .padding(start = 6.dp, top = 12.dp),
                        text = apiResponse?.data?.description
                            ?: "",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }

        }

    }
}

@Composable
fun ImageComponent(
    modifier: Modifier,
    image: DrawableResource
) {
    Image(
        painter = painterResource(image),
        modifier = modifier,
        contentDescription = null
    )
}

@Composable
fun AsyncImageComponent(
    modifier: Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Fit,
    placeholder: DrawableResource = Res.drawable.view_cart_icon,
    error: DrawableResource = Res.drawable.view_cart_icon,
) {
    AsyncImage(
        model = imageUrl,
        modifier = modifier,
        contentDescription = null,
        contentScale = contentScale,
        placeholder = painterResource(placeholder),
        error = painterResource(error),
    )
}

