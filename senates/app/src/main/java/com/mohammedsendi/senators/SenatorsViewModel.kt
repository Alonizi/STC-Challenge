package com.mohammedsendi.senators

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mohammedsendi.senators.model.Senator

class SenatorsViewModel : ViewModel() {

    private val repo = SenatorsRepository.get()

    fun getSenates () : List<Senator> {

        return repo.getSenates()

    }

    fun getSenatesByBioId(bioId : String) : Senator? {

        return repo.getSenatorByBioId(bioId)
    }

}