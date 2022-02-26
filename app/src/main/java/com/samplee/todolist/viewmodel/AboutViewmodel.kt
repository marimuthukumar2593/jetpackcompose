package com.samplee.todolist.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samplee.todolist.model.AboutData
import com.samplee.todolist.repository.AboutRepository
import kotlinx.coroutines.launch



class AboutViewmodel : ViewModel() {

    var repository: AboutRepository ?=null

    private var _about: LiveData<AboutData> = MutableLiveData()
    val about: LiveData<AboutData> get() = _about

    private var _abouts: LiveData<List<AboutData>> = MutableLiveData()
    val abouts: LiveData<List<AboutData>> get() = _abouts



    /****************************************************************
     * initRepository() - Initialize the epository and room db
     ****************************************************************/

    fun initRepository(con:Context){

        repository = AboutRepository(con)
        repository?.initializeAboutDB(con)

    }
    /****************************************************************
     * fetchAll() - fetch hte all abouts list
     ****************************************************************/

    fun fetchAll() {
        _abouts = repository?.fetchAll()!!
    }
    /****************************************************************
     * fetchById() - fetch about by id
     ****************************************************************/

    fun fetchById(id: Int) {
        _about = repository?.fetchById(id)!!
    }

    /****************************************************************
     * insert() - insert the about message in db
     ****************************************************************/

    fun insert(about: AboutData) {
        viewModelScope.launch {
            repository?.insert(about)
        }

    }

    /****************************************************************
     * update() - update the about message in db
     ****************************************************************/

    fun update(about: AboutData) {
        viewModelScope.launch {
            repository?.update(about)
        }

    }


}