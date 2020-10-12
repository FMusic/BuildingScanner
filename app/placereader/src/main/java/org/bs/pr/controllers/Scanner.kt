package org.bs.pr.controllers

import org.bs.pr.model.Room

interface Scanner {
    fun changeRoom(newRoom: Room)
    fun scan()
    fun stopScan()
}