package com.example.zolo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.zolo.data.Datasource
import com.example.zolo.model.Book
import com.example.zolo.ui.theme.ZoloTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZoloTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage()
                }
            }
        }
    }
}



@Composable
fun HomePage(modifier: Modifier = Modifier) {
    var searchInput by remember { mutableStateOf("") }
    var selectedBook by remember { mutableStateOf<Book?>(null) }
    var isBorrowDialogVisible by remember { mutableStateOf(false) }
    var selectedBookForDetails by remember { mutableStateOf<Book?>(null) }

    Box {

        BackHandler {
            // Reset selectedBook and selectedBookForDetails to null when the back button is pressed
            selectedBook = null
            selectedBookForDetails = null
        }
        Box {
            if (selectedBook != null) {
                BookDetails(book = selectedBook!!, onBorrowClick = { isBorrowDialogVisible = true })
            }
            else{
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Read",
                            modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.notifications_fill0_wght400_grad0_opsz24),
                                contentDescription = "notification",
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                    Divider()
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SearchBar(value = searchInput, onValueChange = { searchInput = it }, modifier = Modifier)
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Text(text = "Filter")
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    ScrollableBookList(Datasource().loadBooks()) {book -> selectedBook = book}
                }


                BottomAppBar()
            }
            // Show BorrowDialog if isBorrowDialogVisible is true
            if (isBorrowDialogVisible) {
                BorrowDialog(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .zIndex(1f),
                    onDoneClick = { ->
                        isBorrowDialogVisible = false
                        selectedBookForDetails = selectedBook
                    }
                )
            }
        }

    }

}

@Composable
fun ScrollableBookList(booklist: List<Book>, modifier: Modifier = Modifier, onBorrowClick: (Book) -> Unit) {
    LazyColumn{
        items(booklist) {
            book -> CreateABookCard(book = book, onBorrowClick = onBorrowClick)
        }
    }
}

@Composable
fun CreateABookCard(modifier: Modifier = Modifier, book: Book, onBorrowClick: (Book) -> Unit) {
        Row() {
            Column() {
                Text(text = book.name, modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "by ${book.author}", modifier = Modifier.padding(4.dp), fontSize = 10.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { onBorrowClick(book) }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Blue)) {
                Text(text = "Borrow")
            }
        }
}

// This function generates the bottom app bar
@Composable
fun BottomAppBar(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
        Row(modifier = modifier
            .fillMaxWidth()
            .background(Color.White), horizontalArrangement = Arrangement.SpaceAround) {
            BottomAppBarItem(image = R.drawable.home_fill0_wght400_grad0_opsz24, text = "Home")
            BottomAppBarItem(image = R.drawable.post_add_fill0_wght400_grad0_opsz24, text = "Post")
            BottomAppBarItem(image = R.drawable.account_circle_fill0_wght400_grad0_opsz24, text = "Profile")
        }
    }
}

// This function generates the bottom app bar's items
@Composable
fun BottomAppBarItem(@DrawableRes image: Int, text: String, modifier: Modifier = Modifier) {
    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black)) {
        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(image),
                contentDescription = text,
                modifier = Modifier.padding(4.dp)
            )
            Text(text = text, modifier = Modifier.padding(4.dp))
        }
    }
}

// This function generates the search bar (only the text field)
@Composable
fun SearchBar(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    TextField(value = value, onValueChange = onValueChange, modifier = Modifier
        .background(Color.Transparent)
        .padding(start = 10.dp), shape = RoundedCornerShape(32.dp), colors = TextFieldDefaults.colors(focusedIndicatorColor =  Color.Transparent, unfocusedIndicatorColor = Color.Transparent, disabledIndicatorColor = Color.Transparent))
}

// The function is supposed to direct to a new page with the book details
@Composable
fun BookDetails(modifier: Modifier = Modifier, book: Book, onBorrowClick: () -> Unit) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Column {
            Text(text = book.name, modifier = Modifier.padding(4.dp), fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Text(text = "by ${book.author}", modifier = Modifier.padding(4.dp), fontSize = 20.sp)
        }
        Divider()
        Text(text = book.bookDescription, modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        Divider()
        Text(text = "Genres: ${book.genre}", modifier = Modifier.padding(4.dp), fontSize = 15.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onBorrowClick , modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()) {
            Text(text = "Borrow this book")
        }
    }
}


@Composable
fun BorrowDialog(
    modifier: Modifier = Modifier,
    onDoneClick: () -> Unit
) {
    var selectedDays by remember { mutableStateOf(7) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Rounded corner rectangle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "How many days do you need the book for?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Circular options
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CircularOption(value = 6, isSelected = selectedDays == 6) {
                        selectedDays = 6
                    }
                    CircularOption(value = 7, isSelected = selectedDays == 7) {
                        selectedDays = 7
                    }
                    CircularOption(value = 8, isSelected = selectedDays == 8) {
                        selectedDays = 8
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Done button
                Button(
                    onClick = {
                        onDoneClick()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Done")
                }
            }
        }
    }
}

@Composable
fun CircularOption(value: Int, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(
                if (isSelected) Color.Blue else Color.Gray,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value.toString(),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ZoloTheme {
        HomePage()
    }
}


/*
    Helpful Resource: https://developer.android.com/codelabs/basic-android-compose-training-add-scrollable-list#0
*/