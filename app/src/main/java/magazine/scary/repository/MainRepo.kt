package com.pixabay.repo.repo

import com.pixabay.utils.tools.log
import magazine.scary.domain.entities.ImageModel
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject

class MainRepo @Inject
constructor(private val remoteRepo: RemoteRepo ) {

    //if remote repo get error in rest connection, then Database will check to return cached data
    suspend fun search(word: String): List<ImageModel> {
        return remoteRepo.searchImages(word).hits
//        return try {
//            val remoteResults = remoteRepo.searchImages(word).hits
//            cacheRemoteResults(word, remoteResults)
//            remoteResults
//        } catch (e: UnknownHostException) {
//            dbRepo.search(word)
//        } catch (e: Exception) {
//            listOf()
//        }
    }

    //Save API data to DB, Send results to View Layer
//    private fun cacheRemoteResults(word: String, images: List<ImageModel>) {
//        dbRepo.insertAll(images.onEach { it.searchWord = word })
//    }

}