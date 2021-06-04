package com.himanshoe.feature.comment.service

import com.himanshoe.feature.comment.Comment

interface CommentApiService {

    suspend fun fetchComments(page: Int, count: Int): Pair<List<Comment>, Int>

    suspend fun createComment(userId: String?, postId: String, comment: Comment): Boolean

    suspend fun findPostById(commentId: String?): Comment?
}
