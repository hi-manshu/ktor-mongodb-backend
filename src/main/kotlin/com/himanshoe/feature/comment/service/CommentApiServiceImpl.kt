package com.himanshoe.feature.comment.service

import com.himanshoe.feature.comment.Comment
import com.mongodb.client.model.UnwindOptions
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.aggregate
import java.util.*
import kotlin.reflect.full.memberProperties

class CommentApiServiceImpl(private val commentCollection: CoroutineCollection<Comment>) : CommentApiService {
    companion object {
        private const val ONE = 1
        private const val USER = "user"
        private const val CREATED_BY = "createdBy"
        private const val CREATED_BY_USER = "createdByUser"
        private const val ID = "_id"
        private const val DOLLAR = "$"
        private val lookUpBson = lookup(
            from = USER,
            localField = CREATED_BY,
            foreignField = ID,
            newAs = CREATED_BY_USER
        )

        private val projectBson = project(
            Comment::createdByUser from "${DOLLAR}$CREATED_BY_USER",
            *Comment::class.memberProperties
                .filter { it != Comment::createdByUser }
                .map { it from it }.toTypedArray(),
        )
    }

    override suspend fun fetchComments(page: Int, count: Int): Pair<List<Comment>, Int> {
        val skips = page.minus(ONE) * count
        val result = commentCollection.aggregate<Comment>(
            skip(skips),
            limit(count),
            sort(ascending(Comment::createdAt)),
            lookUpBson,
            unwind("${DOLLAR}$CREATED_BY_USER", UnwindOptions().preserveNullAndEmptyArrays(true)),
            projectBson
        ).toList()
        return Pair(result, commentCollection.estimatedDocumentCount().toInt())
    }

    override suspend fun addComment(userId: String, postId: String, comment: Comment): Boolean {
        val commentToBeCreated = Comment(comment = comment.comment).copy(
            createdBy = userId,
            createdAt = Date().toInstant().toString(),
        )
        return commentCollection.insertOne(commentToBeCreated).wasAcknowledged()
    }

    override suspend fun findCommentById(commentId: String?): Comment? {
        return commentId?.let { commentCollection.findOneById(it) }
    }
}
