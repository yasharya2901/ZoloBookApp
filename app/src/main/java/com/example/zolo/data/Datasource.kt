package com.example.zolo.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.zolo.R
import com.example.zolo.model.Book

class Datasource {
    @Composable
    fun loadBooks(): List<Book> {
        return listOf<Book>(
            Book(
                "The Alchemist",
                "Paulo Coelho",
                "Available",
                listOf("Adventure", "Fantasy", "Drama"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "The Kite Runner",
                "Khaled Hosseini",
                "Available",
                listOf("Adventure", "Drama"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "The Lord of the Rings",
                "J. R. R. Tolkien",
                "Available",
                listOf("Adventure", "Fantasy"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "The Da Vinci Code",
                "Dan Brown",
                "Available",
                listOf("Adventure", "Mystery", "Thriller"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                "Available",
                listOf("Adventure", "Drama"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "Catch-22",
                "Joseph Heller",
                "Available",
                listOf("Adventure", "Satire", "War"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "Miasto Ślepców",
                "José Saramago",
                "Available",
                listOf("Adventure", "Drama"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "Fight Club",
                "Chuck Palahniuk",
                "Available",
                listOf("Adventure", "Drama"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "Nineteen Eighty-Four",
                "George Orwell",
                "Available",
                listOf("Adventure", "Dystopian", "Science Fiction"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "Space Odyssey",
                "Arthur C. Clarke",
                "Available",
                listOf("Adventure", "Science Fiction"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "Steve Jobs",
                "Walter Isaacson",
                "Available",
                listOf("Adventure", "Biography"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "The Martian",
                "Andy Weir",
                "Available",
                listOf("Adventure", "Science Fiction"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "The Hobbit",
                "J. R. R. Tolkien",
                "Available",
                listOf("Adventure", "Fantasy"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "Jane Eyre",
                "Charlotte Brontë",
                "Available",
                listOf("Adventure", "Romance"),
                stringResource(R.string.the_alchemist_description)
            ),
            Book(
                "Three Men in a Boat",
                "Jerome K. Jerome",
                "Available",
                listOf("Adventure", "Comedy"),
                stringResource(R.string.the_alchemist_description)
            )
        )
    }
}