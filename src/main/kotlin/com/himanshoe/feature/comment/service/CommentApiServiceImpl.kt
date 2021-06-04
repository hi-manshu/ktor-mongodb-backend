package com.himanshoe.feature.comment.service

import com.himanshoe.feature.comment.Comment
import org.litote.kmongo.coroutine.CoroutineCollection

class CommentApiServiceImpl(commentCollection: CoroutineCollection<Comment>) : CommentApiService {

    override suspend fun fetchComments(page: Int, count: Int): Pair<List<Comment>, Int> {
    }

    override suspend fun createComment(userId: String?, postId: String, comment: Comment): Boolean {
    }

    override suspend fun findPostById(commentId: String?): Comment? {
    }
}
