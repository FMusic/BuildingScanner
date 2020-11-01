package org.fcs.ac

import org.bs.pr.model.Building


class ApiCommunicator{
    companion object{
        val success = "Save Successfull"
        val failed = "Save Failed"
    }
    fun SaveBuilding(building: Building): Boolean{
        TODO("Serialize to JSON and send to api")
    }

    fun LoadBuilding(id: Int): Building {
        TODO("Load JSON from Server and deserialize to Building")
    }
}