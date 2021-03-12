package com.amrmustafa.nytimes
import com.amrmustafa.nytimes.mostpopular.data.models.Article
import com.amrmustafa.nytimes.mostpopular.data.models.ArticlesResponse
import com.amrmustafa.nytimes.mostpopular.data.models.Media
import com.amrmustafa.nytimes.mostpopular.data.models.MediaMetaData

object TestData {
    val response = ArticlesResponse(
        "success", "test", 1,
        listOf<Article>(
            Article(
                1,
                "test nytimes",
                "test byline",
                "test abstract",
                "test url",
                "04-12-2020", listOf(
                    Media(
                        "image",
                        "imageSub",
                        "test",
                        "test copyright",
                        1,
                        listOf(MediaMetaData("https://test.com", "jpg", 200, 200))
                    )
                )
            ),
            Article(
                2,
                "test nytimes2",
                "test byline2",
                "test abstract2",
                "test url2",
                "04-12-2020",
                listOf(
                    Media(
                        "image",
                        "imageSub",
                        "test",
                        "test copyright",
                        1,
                        listOf(MediaMetaData("https://test.com", "jpg", 200, 200))
                    )
                )
            ),
            Article(
                3,
                "test nytimes3",
                "test byline3",
                "test abstract3",
                "test url3",
                "04-12-2020",
                listOf(
                    Media(
                        "image",
                        "imageSub",
                        "test",
                        "test copyright",
                        1,
                        listOf(MediaMetaData("https://test.com", "jpg", 200, 200))
                    )
                )
            )
        )
    )


}
