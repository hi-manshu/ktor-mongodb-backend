package com.himanshoe.util

import com.himanshoe.feature.posts.Post
import com.himanshoe.feature.posts.PostList

object TestData {

    fun dummyPost(): Post {
        return Post(
            postId = "12345",
            title = "my post title",
            post = "my post"
        )
    }

    fun falsePost(): Post {
        return Post(postId = "12345")
    }

    fun dummyPostId(): String {
        return dummyPost().postId
    }


}
