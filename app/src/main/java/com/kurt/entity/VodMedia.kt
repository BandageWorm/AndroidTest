package com.kurt.entity

data class VodMedia(
    val resultCode: Int,
    val total: Int,
    val searchResultList: List<SearchResult>,
    val signatureServer: String
)

data class SearchResult(
    val mediaId: Long,
    val mediaTitle: String,
    val appType: Int,
    val categoryId: Int,
    val mediaSubTitle: String,
    val rate: String,
    val doubanRate: String,
    val total: String,
    val isFee: String,
    val venderId: String,
    val timeLength: String,
    val poster: String,
    val mark: String,
    val isBuy: Int,
    val jumpParams: String,
    val current: String,
    val thirdDownloadUrl: ThirdDownloadUrl
)

data class ThirdDownloadUrl(
    val package_name: String,
    val app_name: String,
    val url: String,
    val cover_id: String
)