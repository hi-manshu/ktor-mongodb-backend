package com.himanshoe.feature.comment.request

import com.himanshoe.feature.comment.Comment

data class AddCommentRequest(val userId: String?, val postId: String, val comment: Comment)
