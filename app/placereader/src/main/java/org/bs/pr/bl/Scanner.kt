package org.bs.pr.bl

import org.bs.pr.model.Room
import org.bs.pr.model.Space

interface Scanner {
    fun changeRoom(newRoom: Space)
    fun scan()
    fun stopScan()
}