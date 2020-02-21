package com.pixabay.repo.repo

import magazine.scary.repository.rest.RestService
import magazine.scary.tools.utils.Cons
import javax.inject.Inject


class RemoteRepo @Inject
constructor(private val repoService: RestService) {

    suspend fun searchImages(word: String) =
        repoService.getImages(
            word
        )
}