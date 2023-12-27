package com.demo.rps

import Gestures.SCISSORS

class Game:
  private def selectPlay: Option[Gestures] = {
    val random: Int = scala.util.Random.between(1, 4)

    random match
      case 1 => Some(Gestures.SCISSORS)
      case 2 => Some(Gestures.ROCK)
      case 3 => Some(Gestures.PAPER)
  }

  def giveResult(userPlay: Int): Option[Boolean] = {
    val pcPlay: Gestures = selectPlay.get

    val result: String = pcPlay match {
      case Gestures.SCISSORS => resultScissors(userPlay)
      case Gestures.ROCK => resultRock(userPlay)
      case Gestures.PAPER => resultPaper(userPlay)
    }

    result match {
      case s if s.eq("win") => Some(true)
      case s if s.eq("loose") => Some(false)
      case s if s.eq("draw") => None
    }
  }

  private def resultScissors(userPlay: Int): String = {
    userPlay match {
      case 1 => "win"
      case 2 => "loose"
      case 3 => "draw"
    }
  }

  private def resultRock(userPlay: Int): String = {
    userPlay match {
      case 1 => "draw"
      case 2 => "win"
      case 3 => "loose"
    }
  }

  private def resultPaper(userPlay: Int): String = {
    userPlay match {
      case 1 => "loos"
      case 2 => "draw"
      case 3 => "win"
    }
  }
end Game
