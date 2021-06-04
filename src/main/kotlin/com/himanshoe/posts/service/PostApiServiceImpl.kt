package com.himanshoe.posts.service

import com.himanshoe.posts.Post
import com.mongodb.client.model.UnwindOptions
import org.bson.conversions.Bson
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.aggregate
import java.util.*
import kotlin.reflect.full.memberProperties

class PostApiServiceImpl(
    private val postCollection: CoroutineCollection<Post>
) : PostApiService {

    companion object {
        private const val ZERO = 0
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
            Post::createdByUser from "${DOLLAR}${CREATED_BY_USER}",
            *Post::class.memberProperties
                .filter { it != Post::createdByUser }
                .map { it from it }.toTypedArray(),
        )
    }

    override suspend fun fetchPosts(page: Int, count: Int): Pair<List<Post>, Int> {
        val skips = page.minus(ONE) * count

        val fields: Bson = fields(exclude(Post::isDeleted))

        return Pair(
            postCollection.aggregate<Post>(
                skip(skips),
                limit(count),
                project(fields),
                sort(ascending(Post::createdAt)),
                lookUpBson,
                unwind("$DOLLAR$CREATED_BY_USER", UnwindOptions().preserveNullAndEmptyArrays(true)),
                projectBson,
            ).toList(), postCollection.estimatedDocumentCount().toInt()
        )
    }

    override suspend fun createPost(userId: String?, post: Post): Boolean {
        val postToBeCreated = Post(title = post.title, post = post.post).copy(
            createdBy = userId,
            createdAt = Date().toInstant().toString(),
            updatedAt = Date().toInstant().toString()
        )
        return postCollection.insertOne(postToBeCreated).wasAcknowledged()
    }

    override suspend fun findPostById(postId: String?): Post? {
        return postId?.let { id -> postCollection.findOneById(id) }
    }

    override suspend fun likePost(postId: String?, likes: List<String>): Boolean? {
        val post = findPostById(postId)
        val postToBeUpdated = post?.copy(
            likes = likes
        )
        return postToBeUpdated?.let { postId?.let { id -> postCollection.updateOneById(id, it).wasAcknowledged() } }
    }

    override suspend fun dislikePost(postId: String?, likes: List<String>): Boolean? {
        val post = findPostById(postId)
        val postToBeUpdated = post?.copy(
            likes = likes
        )
        return postToBeUpdated?.let { postId?.let { id -> postCollection.updateOneById(id, it).wasAcknowledged() } }

    }

    override suspend fun deletePost(postId: String?): Boolean? {
        return if (postId != null) {
            postCollection.deleteOneById(postId).wasAcknowledged()
        } else {
            false
        }
    }

}