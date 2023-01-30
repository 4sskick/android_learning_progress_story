package id.niteroomcreation.learningprogressstory.data.repository

import id.niteroomcreation.learningprogressstory.data.datasource.StoriesDataSource
import id.niteroomcreation.learningprogressstory.domain.model.Resource
import id.niteroomcreation.learningprogressstory.domain.model.stories.StoriesResponse

/**
 * Created by Septian Adi Wijaya on 29/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoriesRepository(private val dataSource: StoriesDataSource) : StoriesRepositoryImpl {

    override suspend fun getAll(): Resource<StoriesResponse> {
        return dataSource.getAll()
    }


}